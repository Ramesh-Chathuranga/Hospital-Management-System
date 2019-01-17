package lk.pos.hospital.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.pos.hospital.business.BOFactory;
import lk.pos.hospital.business.custom.ClinicBO;
import lk.pos.hospital.business.custom.DoctorRegBO;
import lk.pos.hospital.business.custom.ScheduleBO;
import lk.pos.hospital.dto.DoctorDTO;
import lk.pos.hospital.dto.DoctorTimeTableDTO;
import lk.pos.hospital.model.TBDoctorSchedule;

import java.net.URL;
import java.util.ResourceBundle;

import static lk.pos.hospital.controller.util.AnimationClass.*;

public class AddScheduleTODOCTORFormController implements Initializable {


    @FXML
    private JFXComboBox<String> comboDoctor;

    @FXML
    private JFXComboBox<String> comboSchedule;

    @FXML
    private Label labTitle;

    @FXML
    private Label labTitle1;

    @FXML
    private JFXTextField txtNic;

    @FXML
    private JFXTextField txtName;

    @FXML
    private Button butSave;

    @FXML
    private AnchorPane butBack;

    @FXML
    private Label labBack;

    @FXML
    private Button butRemove;

    @FXML
    private TableView<TBDoctorSchedule> tabDoctorSchrdule;

    private ScheduleBO scheduleBO;

    private DoctorRegBO regBO;

    private ClinicBO clinicBO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scheduleBO= BOFactory.getInstance().getBo(BOFactory.BOType.SCHEDULE);
        regBO= BOFactory.getInstance().getBo(BOFactory.BOType.DOCTOR);
        clinicBO= BOFactory.getInstance().getBo(BOFactory.BOType.CLINIC);
        butSave.setOpacity(0.4);
        butRemove.setOpacity(0.4);
        labBack.setVisible(false);
        txtName.setEditable(false);
        txtNic.setEditable(false);
        butRemove.setDisable(false);
        tabDoctorSchrdule.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("scheduleid"));
        tabDoctorSchrdule.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("regid"));
        tabDoctorSchrdule.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tabDoctorSchrdule.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("mid"));
        tabDoctorSchrdule.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("name"));
        tabDoctorSchrdule.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("tele"));
        txtNic.setEditable(false);
        txtName.setEditable(false);
        try {
            loadcomboBox();
            loadTable();
        } catch (Exception e) {
            e.printStackTrace();
        }

        comboDoctor.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue==null){
                return;
            }
            try {
                DoctorDTO doctorDTO = regBO.searchDoctor(newValue);
                txtName.setText(doctorDTO.getFullName());
                txtNic.setText(doctorDTO.getNic());
                String sfield=doctorDTO.getFieldId();
                comboSchedule.getItems().removeAll(comboSchedule.getItems());
                clinicBO.getALLForTable().stream().filter(customDTO -> customDTO.getFieldId().equalsIgnoreCase(sfield)).forEach(customDTO -> {
                    comboSchedule.getItems().add(customDTO.getSceduleid());
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        });


        tabDoctorSchrdule.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue==null){
                return;
            }
            txtNic.setText(newValue.getNic());
            txtName.setText(newValue.getName());
            comboSchedule.getSelectionModel().select(newValue.getScheduleid());
            comboDoctor.getSelectionModel().select(newValue.getRegid());
        });
    }

    private void loadcomboBox()throws  Exception{
      comboDoctor.getItems().removeAll(comboDoctor.getItems());
      comboSchedule.getItems().removeAll(comboSchedule.getItems());
      regBO.getAllDoctor().stream().forEach(dto -> {
          comboDoctor.getItems().add(dto.getDrId());
      });
    }


    @FXML
    void backTODoctorRegForm(MouseEvent event) {
     try{
         Parent  parent= FXMLLoader.load(this.getClass().getResource("/lk/pos/hospital/view/DoctorRegistryForm.fxml"));
         navigateAll(parent,(Stage)this.labBack.getScene().getWindow()," MEDIC HOSPITAL \t\t\t DOCTOR REGISTRY",1);
     }catch (Exception e){

     }
    }


    private  void  loadTable()throws  Exception{
        ObservableList<TBDoctorSchedule> list = FXCollections.observableArrayList();
        regBO.getoctorScheduleDetails().stream().forEach(dto -> {
            String did=dto.getDoctorId();
            String scheduleId=dto.getScheduleId();
            try {
                regBO.getAllDoctor().stream().forEach(dto1 -> {
                    if(dto1.getDrId().equalsIgnoreCase(did)){
                        list.add(new TBDoctorSchedule(scheduleId,did,dto1.getNic(),dto1.getMedId(),dto1.getFullName(),dto1.getTelephone()));
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        tabDoctorSchrdule.setItems(list);
    }


    @FXML
    void onActionRemove(ActionEvent event) {
      String did=comboDoctor.getValue();
      String sid=comboSchedule.getValue();
      if(did.trim().isEmpty()){
          comboDoctor.requestFocus();
          return;
      }else  if(sid.trim().isEmpty()){
          comboSchedule.requestFocus();
          return;
      }
      if(tabDoctorSchrdule.getItems().stream().anyMatch(tbDoctorSc ->tbDoctorSc.getScheduleid().equalsIgnoreCase(sid) && tbDoctorSc.getRegid().equalsIgnoreCase(did) )){
          try {
              regBO.deleteoctorSchedule(new DoctorTimeTableDTO(did,sid));
              clearField();
              loadTable();
              return;
          } catch (Exception e) {
              e.printStackTrace();
          }
      }

    }

    void  clearField(){
        tabDoctorSchrdule.getSelectionModel().clearSelection();
        comboSchedule.getSelectionModel().clearSelection();
        comboDoctor.getSelectionModel().clearSelection();
        txtName.clear();
        txtNic.clear();


    }

    @FXML
    void onActionSave(ActionEvent event) {
        String did=comboDoctor.getValue();
        String sid=comboSchedule.getValue();
        validate(sid,sid);
        if(!tabDoctorSchrdule.getItems().stream().anyMatch(tbDoctorSc ->tbDoctorSc.getScheduleid().equalsIgnoreCase(sid) && tbDoctorSc.getRegid().equalsIgnoreCase(did) )){
            try {
                regBO.saveDoctorSchedule(new DoctorTimeTableDTO(did,sid));
                clearField();
               loadTable();
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private  void validate(String did,String sid){
        if(did.trim().isEmpty()){
            comboDoctor.requestFocus();
            return;
        }else  if(sid.trim().isEmpty()){
            comboSchedule.requestFocus();
            return;
        }
    }

    @FXML
    void onMouseAnimationEntered(MouseEvent event) {
        Node node = (Node) event.getSource();
        switch (node.getId()){
            case "butSave":
                butSave.setOpacity(1);
                butSave.setStyle("-fx-background-color: green;-fx-text-fill: white;-fx-border-color: white;-fx-border-width: 0px 0px 3px 0px;");
                break;
            case "butRemove":
                butRemove.setOpacity(1);
                butRemove.setStyle("-fx-background-color: darkorange;-fx-text-fill: white;-fx-border-color: white;-fx-border-width: 0px 0px 3px 0px;");
                break;
            case "butBack":
                labBack.setVisible(true);
                scaleTransition(node,1.11);
                dropShadow(node);
                break;
            default:break;
        }
    }

    @FXML
    void onMouseAnimationExited(MouseEvent event) {
        Node node = (Node) event.getSource();
        switch (node.getId()){
            case "butSave":
                butSave.setOpacity(0.4);
                butSave.setStyle("-fx-background-color:brown;-fx-text-fill: black;-fx-border-color: transparent;-fx-border-width: 0px 0px 0px 0px;");
                break;
            case "butRemove":
                butRemove.setOpacity(0.4);
                butRemove.setStyle("-fx-background-color:brown;-fx-text-fill: black;-fx-border-color:transparent;-fx-border-width: 0px 0px 0px 0px;");
                break;
            case "butBack":
                labBack.setVisible(false);
                scaleTransition(node,1.0);
                dropShadow(node);
                break;
            default:break;
        }
    }

}
