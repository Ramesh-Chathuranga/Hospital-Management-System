package lk.pos.hospital.controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.pos.hospital.business.BOFactory;
import lk.pos.hospital.business.custom.ClinicBO;
import lk.pos.hospital.business.custom.ScheduleBO;
import lk.pos.hospital.business.custom.SpecialFieldBO;
import lk.pos.hospital.controller.util.AnimationClass;
import lk.pos.hospital.dto.ClinicDTO;
import lk.pos.hospital.dto.ClinicTimeTableDTO;
import lk.pos.hospital.dto.ScheduleDTO;
import lk.pos.hospital.dto.SpecialFieldDTO;
import lk.pos.hospital.model.TBClinicTimeTable;

import java.net.URL;
import java.util.ResourceBundle;

import static lk.pos.hospital.controller.util.AnimationClass.dropShadow;
import static lk.pos.hospital.controller.util.AnimationClass.scaleTransition;

public class ClinicTimeTableFormController implements Initializable {


    @FXML
    private AnchorPane clinicSheduleAnchorPane;

    @FXML
    private Label labTitle;

    @FXML
    private Label labTitle1;

    @FXML
    private JFXButton butSave;

    @FXML
    private JFXButton butDelete;

    @FXML
    private AnchorPane butGO;

    @FXML
    private Label labGo;

    @FXML
    private JFXComboBox<String> comboScheduleId;

    @FXML
    private JFXComboBox<String> comboClinicID;

    @FXML
    private JFXDatePicker date;



    @FXML
    private Label labTitle11;


    @FXML
    private Label labTitle111;

    @FXML
    private JFXTextField txtSpecialField;

    @FXML
    private TableView<TBClinicTimeTable> tabClinicShedule;

    @FXML
    private JFXTextField txtDay;

    @FXML
    private JFXTimePicker timeStart;

    @FXML
    private JFXTimePicker timeEnd;

    private ScheduleBO scheduleBO;

    private ClinicBO clinicBO;

    private SpecialFieldBO fieldBO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
              scheduleBO=BOFactory.getInstance().getBo(BOFactory.BOType.SCHEDULE);
              clinicBO= BOFactory.getInstance().getBo(BOFactory.BOType.CLINIC);
              fieldBO=BOFactory.getInstance().getBo(BOFactory.BOType.SPECIAL_FIELD);
              labGo.setVisible(false);
              txtSpecialField.setEditable(false);
              butDelete.setOpacity(0.6);
              butSave.setOpacity(0.6);
              txtDay.setStyle("-fx-text-fill: white;-fx-font-size: 13PX;-fx-font-family: 'Bell MT'");
              txtSpecialField.setStyle("-fx-text-fill: white;-fx-font-size: 13PX;-fx-font-family: 'Bell MT'");



            tabClinicShedule.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("scheduleid"));
            tabClinicShedule.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("day"));
            tabClinicShedule.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("date"));
            tabClinicShedule.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("starttime"));
            tabClinicShedule.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("endtime"));
            tabClinicShedule.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("clinicid"));
            tabClinicShedule.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("clinicname"));
            tabClinicShedule.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("clinicspecialfield"));

            loadTable();

            loadComboBox();
        } catch (Exception e) {
            e.printStackTrace();
        }

        comboScheduleId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue==null||newValue.isEmpty()){
                return;
            }
            String scheduleId=newValue;
            try {

                ScheduleDTO scheduleDTO = scheduleBO.searchSchedule(newValue);
                System.out.println(newValue+"  "+scheduleDTO.getDate().toString());
                txtDay.setText(scheduleDTO.getDay());
                date.setValue(scheduleDTO.getDate());
                timeStart.setValue(scheduleDTO.getStartTime());
                timeEnd.setValue(scheduleDTO.getEndTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        comboClinicID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue==null){
                return;
            }
            try {
                ClinicDTO clinicDTO = clinicBO.searchClinic(newValue);
                txtSpecialField.setText(fieldBO.searchSField(clinicDTO.getFieldID()).getFieldName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

         tabClinicShedule.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
             if(newValue==null){return;}
             comboClinicID.setValue(newValue.getClinicid());
             comboScheduleId.setValue(newValue.getScheduleid());
         });


    }

    private  void loadComboBox() throws Exception {
        comboClinicID.getItems().removeAll(comboClinicID.getItems());
        comboScheduleId.getItems().removeAll(comboScheduleId.getItems());
        clinicBO.getAllClinic().stream().forEach(clinicDTO -> {
            comboClinicID.getItems().add(clinicDTO.getcId());
        });
        scheduleBO.getAllSchedule().stream().forEach(dto -> {
            comboScheduleId.getItems().add(dto.getsId());
        });
    }

    private void loadTable() throws Exception {
        ObservableList<TBClinicTimeTable> list = FXCollections.observableArrayList();
        clinicBO.getAllClinicSchedule().stream().forEach(dto -> {
         String clid=dto.getClinicId();
         String schid=dto.getSheduleId();
            try {
                ClinicDTO clinicDTO = clinicBO.searchClinic(clid);
                ScheduleDTO scheduleDTO = scheduleBO.searchSchedule(schid);
                SpecialFieldDTO fieldDTO = fieldBO.searchSField(clinicDTO.getFieldID());
                list.add(new TBClinicTimeTable(scheduleDTO.getsId(),scheduleDTO.getDay(),scheduleDTO.getDate(),scheduleDTO.getStartTime(),scheduleDTO.getEndTime(),clid,clinicDTO.getClinicName(),fieldDTO.getFieldName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        tabClinicShedule.setItems(list);
        System.out.println("hi");
    }

    @FXML
    void navigateTOClinic(MouseEvent event) {
       try {
           Parent parent =FXMLLoader.load(this.getClass().getResource("/lk/pos/hospital/view/ClinicForm.fxml"));
           Stage window = (Stage) clinicSheduleAnchorPane.getScene().getWindow();
           AnimationClass.navigateAll(parent,window," MEDIC HOSPITAL",1);
       }catch (Exception e){

       }
    }

    @FXML
    void onActionDelete(ActionEvent event) {
        String cid=comboClinicID.getValue();
        String schid=comboScheduleId.getValue();
        try {
            if(clinicBO.getClincSchedule(new ClinicTimeTableDTO(cid,schid))==null){
               return;
            }
            clinicBO.deleteClinicSchedule(new ClinicTimeTableDTO(cid,schid));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            cleanField();
        }
    }

    @FXML
    void onActionSave(ActionEvent event) {
       String cid=comboClinicID.getValue();
       String schid=comboScheduleId.getValue();
        try {
            if(clinicBO.getClincSchedule(new ClinicTimeTableDTO(cid,schid))==null){
                clinicBO.saveClincToSCXhedule(new ClinicTimeTableDTO(cid,schid));
            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            cleanField();
        }
    }

    private void cleanField(){

        try {
            loadTable();
            txtDay.clear();
            txtSpecialField.clear();
            timeEnd.setValue(null);
            timeStart.setValue(null);
            date.setValue(null);
            comboClinicID.getSelectionModel().clearSelection();
            comboScheduleId.getSelectionModel().clearSelection();
            tabClinicShedule.getSelectionModel().clearSelection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onAnimationMouseEntered(MouseEvent event) {

        switch (((JFXButton) event.getSource()).getId()){
            case "butSave":
                butSave.setOpacity(1);
                butSave.setStyle("-fx-background-color: orange;-fx-text-fill: white;-fx-border-color: black;-fx-border-width: 0px 0px 2px 0px;");
                break;
            case "butDelete":
                butDelete.setOpacity(1);
                butDelete.setStyle("-fx-background-color: orange;-fx-text-fill: white;-fx-border-color: black;-fx-border-width: 0px 0px 2px 0px;");
                break;
        }
    }

    @FXML
    void onAnimationMouseExited(MouseEvent event) {
        switch (((JFXButton) event.getSource()).getId()){
            case "butSave":
                butSave.setOpacity(0.6);
                butSave.setStyle("-fx-background-color: brown;-fx-text-fill: black;-fx-border-color: transparent;-fx-border-width: 0px 0px 0px 0px;");
                break;
            case "butDelete":
                butDelete.setOpacity(0.6);
                butDelete.setStyle("-fx-background-color: brown;-fx-text-fill: black;-fx-border-color: transparent;-fx-border-width: 0px 0px 0px 0px;");
                break;
        }
    }

    @FXML
    void onMouseEnteredGOButtonAnimationClck(MouseEvent event) {
        AnchorPane pane = (AnchorPane) event.getSource();
        if(pane.getId().equalsIgnoreCase("butGO")){
            labGo.setVisible(true);
            scaleTransition(pane,1.2);
            dropShadow(pane);
        }
    }

    @FXML
    void onMouseExitedGOButtonAnimationClick(MouseEvent event) {
        AnchorPane pane = (AnchorPane) event.getSource();
        if(pane.getId().equalsIgnoreCase("butGO")){
            labGo.setVisible(false);
            scaleTransition((AnchorPane) event.getSource(),1);
            pane.setEffect(null);
        }
    }


}
