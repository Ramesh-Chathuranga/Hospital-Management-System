package lk.pos.hospital.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.pos.hospital.business.BOFactory;
import lk.pos.hospital.business.custom.ScheduleBO;
import lk.pos.hospital.controller.util.AnimationClass;
import lk.pos.hospital.dto.ScheduleDTO;
import lk.pos.hospital.model.TBSchedule;


import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

import static lk.pos.hospital.controller.util.AnimationClass.dropShadow;
import static lk.pos.hospital.controller.util.AnimationClass.scaleTransition;

public class ScheduleFormController implements Initializable {


    @FXML
    private Label labTitle;

    @FXML
    private Label labSchedule;

    @FXML
    private JFXButton butADD;

    @FXML
    private JFXButton butRemove;

    @FXML
    private AnchorPane butGO;

    @FXML
    private Label labGo;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtDay;

    @FXML
    private JFXTimePicker timeEnd;

    @FXML
    private JFXTimePicker timeStart;

    @FXML
    private JFXDatePicker txtDate;

    private ScheduleBO scheduleBO;

    @FXML
    private JFXButton butClearField;


    @FXML
    private TableView<TBSchedule> tabSchedule;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       labGo.setVisible(false);
       butADD.setOpacity(0.3);
       butRemove.setOpacity(0.3);
       scheduleBO= BOFactory.getInstance().getBo(BOFactory.BOType.SCHEDULE);
        try {
            labSchedule.setText(scheduleBO.getRegisterID());
        } catch (Exception e) {
            e.printStackTrace();
        }
        tabSchedule.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("scheduleid"));
        tabSchedule.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("schedulename"));
        tabSchedule.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("day"));
        tabSchedule.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("date"));
        tabSchedule.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("starttime"));
        tabSchedule.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("endtime"));
        loadTable();


        tabSchedule.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue==null){
                return;
            }
            txtName.setText(newValue.getSchedulename());
            labSchedule.setText(newValue.getScheduleid());
            txtDay.setText(newValue.getDay());
            txtDate.setValue(newValue.getDate());
            timeStart.setValue(newValue.getStarttime());
            timeEnd.setValue(newValue.getEndtime());

        });
    }

    private void loadTable(){
        try {
            List<ScheduleDTO> allSchedule = scheduleBO.getAllSchedule();
            ObservableList<TBSchedule> list = FXCollections.observableArrayList();
            FXCollections.observableArrayList(allSchedule).stream().forEach(dto -> {
                list.add(new TBSchedule(dto.getsId(),dto.getScheduleName(),dto.getDay(),dto.getDate(),dto.getStartTime(),dto.getEndTime()));
            });
            tabSchedule.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void navigateTOAdminForm(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/pos/hospital/view/AdminForm.fxml"));
            Stage window = (Stage) this.butGO.getScene().getWindow();
            AnimationClass.navigateAll(parent,window," MEDIC HOSPITAL",1);
        }catch (Exception e){

        }
    }

    @FXML
    void onActionDelete(ActionEvent event) {
        String id = labSchedule.getText();
        if (tabSchedule.getItems().stream().anyMatch(tbSchedule -> {
            if (tbSchedule.getScheduleid().equalsIgnoreCase(id)) {
                return true;
            }
            return false;
        })) {
            try {
                boolean isDelete = scheduleBO.deleteSchedule(id);
                if(isDelete){
                    System.out.println("delete");
                }else {
                    System.out.println("note Delete");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    clearForm();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @FXML
    void onActionSave(ActionEvent event) {
        try {
                String scheduleId=labSchedule.getText();
                String scheduleName=txtName.getText();
                String day=txtDay.getText();
                LocalDate date=txtDate.getValue();
                LocalTime startTime=timeStart.getValue();
                LocalTime endTime=timeEnd.getValue();
                System.out.println(endTime);
                if(scheduleId.isEmpty()){
                    return;
                }else if (day.isEmpty()){
                    txtDay.requestFocus();
                    return;
                }else if(date.toString().isEmpty()){
                    txtDate.requestFocus();
                    return;
                }else if(startTime.toString().isEmpty()){
                    //message
                    timeStart.requestFocus();
                    System.out.println("set Time");
                    return;
                }else if(endTime.toString().isEmpty()){
                    timeEnd.requestFocus();
                    System.out.println("set end time");
                    return;
                }else  if(date.compareTo(LocalDate.now())<0){
                    txtDate.setValue(null);
                    txtDate.requestFocus();
                    return;
                }else  if(date.compareTo(LocalDate.now())==0){
                    if(startTime.compareTo(LocalTime.now())<=0) {
                        timeStart.setValue(null);
                        timeStart.requestFocus();
                        return;
                    }
                }else  if(startTime.compareTo(endTime)<=0){
                    timeEnd.setValue(null);
                    timeEnd.requestFocus();
                    return;
                }


                 if(tabSchedule.getItems().isEmpty()){
                     boolean isSave = scheduleBO.saveSchedule(new ScheduleDTO(scheduleId, scheduleName, day, date, startTime, endTime));
                     if(isSave){
                         System.out.println("save ok");

                     }
                 }

                 if(scheduleBO.searchSchedule(scheduleId)==null){
                     boolean isSave = scheduleBO.saveSchedule(new ScheduleDTO(scheduleId, scheduleName, day, date, startTime, endTime));
                     if(isSave){
                         System.out.println("save ok");

                     }
                 }else {
                      scheduleBO.updateSchedule(new ScheduleDTO(scheduleId, scheduleName, day, date, startTime, endTime));
                 }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

           try{
               clearForm();
           }catch (Exception e){
               e.printStackTrace();
           }
        }
    }

    private void clearForm() throws Exception {
        txtDay.clear();
        txtName.clear();
        txtDate.setValue(null);
        timeStart.setValue(null);
        timeEnd.setValue(null);
        tabSchedule.getSelectionModel().clearSelection();
        labSchedule.setText(scheduleBO.getRegisterID());
        loadTable();
    }



    @FXML
    void onAnimationMouseEntered(MouseEvent event) {
        switch (((JFXButton) event.getSource()).getId()){
            case "butADD":
                butADD.setOpacity(1);
                butADD.setStyle("-fx-background-color: orange;-fx-text-fill: white;-fx-border-color: black;-fx-border-width: 0px 0px 2px 0px;");
                break;
            case "butRemove":
                butRemove.setOpacity(1);
                butRemove.setStyle("-fx-background-color: orange;-fx-text-fill: white;-fx-border-color: black;-fx-border-width: 0px 0px 2px 0px;");
                break;
            case "butClearField":
                butClearField.setStyle("-fx-text-fill:#227f6f; -fx-border-color: #227f6f;-fx-border-width: 0px 0px 2px 0px ");
                break;
        }
    }

    @FXML
    void onActionClearController(ActionEvent event) {
        try {
            clearForm();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onAnimationMouseExited(MouseEvent event) {
        switch (((JFXButton) event.getSource()).getId()){
            case "butADD":
                butADD.setOpacity(0.3);
                butADD.setStyle("-fx-background-color: brown;-fx-text-fill: black;-fx-border-color: transparent;-fx-border-width: 0px 0px 0px 0px;");
                break;
            case "butRemove":
                butRemove.setOpacity(0.3);
                butRemove.setStyle("-fx-background-color: brown;-fx-text-fill: black;-fx-border-color: transparent;-fx-border-width: 0px 0px 0px 0px;");
                break;
            case "butClearField":
                butClearField.setStyle("-fx-text-fill:White; -fx-border-color: transparent;-fx-border-width: 0px 0px 0px 0px ");
                break;
        }
    }

    @FXML
    void onMouseEnteredGOButtonAnim(MouseEvent event) {
        AnchorPane pane = (AnchorPane) event.getSource();
        if(pane.getId().equals("butGO")){
            labGo.setVisible(true);
            scaleTransition(pane,1.2);
            dropShadow(pane);
        }
    }

    @FXML
    void onMouseExitedGOButtonAnim(MouseEvent event) {
        AnchorPane pane = (AnchorPane) event.getSource();
        if(pane.getId().equals("butGO")){
            labGo.setVisible(false);
            scaleTransition((AnchorPane) event.getSource(),1);
            pane.setEffect(null);
        }
    }


}

