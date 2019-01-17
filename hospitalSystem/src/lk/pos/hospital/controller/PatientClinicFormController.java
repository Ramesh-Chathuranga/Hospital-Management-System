package lk.pos.hospital.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.pos.hospital.business.BOFactory;
import lk.pos.hospital.business.custom.*;
import lk.pos.hospital.dto.*;
import lk.pos.hospital.model.TBPatientClinic;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static lk.pos.hospital.controller.util.AnimationClass.*;

public class PatientClinicFormController implements Initializable {

    @FXML
    private Label labTitle;

    @FXML
    private JFXButton butADD;

    @FXML
    private JFXButton butRemove;

    @FXML
    private AnchorPane butGO;

    @FXML
    private Label labGo;

    @FXML
    private JFXComboBox<String> txtpID;

    @FXML
    private JFXComboBox<String> comboSFID;

    @FXML
    private JFXComboBox<String> comboDID;

    @FXML
    private JFXTextField txtPNIC;

    @FXML
    private JFXTextField txtboDNIC;

    @FXML
    private JFXTextField txtSF;

    @FXML
    private JFXTextField txtPName;

    @FXML
    private JFXTextField txtboDNAME;

    @FXML
    private JFXTextField txtClinicDate;

    @FXML
    private TableView<TBPatientClinic> tabPatientClinic;

    @FXML
    private JFXComboBox<String> comboSID;

    @FXML
    private JFXTextField txtTime;

    private PatientBO patientBO;

    private DoctorRegBO regBO;

    private ScheduleBO scheduleBO;

    private SpecialFieldBO fieldBO;

    private ClinicBO clinicBO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        patientBO= BOFactory.getInstance().getBo(BOFactory.BOType.PATIENT);
        regBO= BOFactory.getInstance().getBo(BOFactory.BOType.DOCTOR);
        scheduleBO= BOFactory.getInstance().getBo(BOFactory.BOType.SCHEDULE);
        fieldBO= BOFactory.getInstance().getBo(BOFactory.BOType.SPECIAL_FIELD);
        clinicBO=BOFactory.getInstance().getBo(BOFactory.BOType.CLINIC);
        labGo.setVisible(false);

        butADD.setOpacity(0.6);
        butRemove.setOpacity(0.6);
        butGO.setOpacity(0.6);
        txtSF.setEditable(false);
        txtPName.setEditable(false);
        txtPNIC.setEditable(false);
        txtTime.setEditable(false);
        txtClinicDate.setEditable(false);
        txtboDNIC.setEditable(false);
        txtboDNAME.setEditable(false);
        tabPatientClinic.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("pid"));
        tabPatientClinic.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("pname"));
        tabPatientClinic.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("pnic"));
        tabPatientClinic.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("sfieldid"));
        tabPatientClinic.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("sfname"));
        tabPatientClinic.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("did"));
        tabPatientClinic.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("dname"));
        tabPatientClinic.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("dnic"));
        tabPatientClinic.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("date"));
        tabPatientClinic.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("scid"));

        try {
            loadcomboBox();
            loadTable();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        txtpID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue==null){return;}
            try {
                PatientDTO patient = patientBO.searchPatient(newValue);
                txtPNIC.setText(patient.getNic());
                txtPName.setText(patient.getFullName());
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }
        });


        comboDID.getItems().removeAll(comboDID.getItems());

        comboSFID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue==null){return;}
            try {
                SpecialFieldDTO dto = fieldBO.searchSField(newValue);
                txtSF.setText(dto.getFieldName());
                comboDID.getItems().removeAll(comboDID.getItems());
                regBO.getAllDoctor().stream().filter(dto1 -> dto1.getFieldId().equalsIgnoreCase(newValue)).forEach(dto1 -> {
                    comboDID.getItems().add(dto1.getDrId());
                });

            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }

        });


        comboDID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue==null){return;}
            try {
                DoctorDTO dto = regBO.searchDoctor(newValue);
                txtboDNIC.setText(dto.getNic());
                txtboDNAME.setText(dto.getFullName());
                comboSID.getItems().removeAll(comboSID.getItems());
                regBO.getoctorScheduleDetails().stream().filter(dto1 -> dto1.getDoctorId().equalsIgnoreCase(newValue)).forEach(dto1 -> {
                    try {
                        scheduleBO.getAllSchedule().stream().filter(dto2 -> dto2.getsId().equalsIgnoreCase(dto1.getScheduleId())).forEach(dto2 -> {
                            comboSID.getItems().add(dto2.getsId());
                        });
                    } catch (Exception e) {
                        Logger.getLogger("").log(Level.SEVERE,null,e);
                    }
                });

            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }
        });

        comboSID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue==null){return;}
            try {
                ScheduleDTO scheduleDTO = scheduleBO.searchSchedule(newValue);
                txtClinicDate.setText(scheduleDTO.getDate().toString());
                txtTime.setText("FROM "+scheduleDTO.getStartTime().toString()+"   TO  "+scheduleDTO.getEndTime());
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }
        });
    }

    @FXML
    void navigateTOpatientREG(MouseEvent event) {
        try {
            navigateAll((Parent) FXMLLoader.load(this.getClass().getResource("/lk/pos/hospital/view/PatientRegistryForm.fxml")),
                    (Stage)this.butADD.getScene().getWindow(),"MEDICA HOSPITAL",1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadcomboBox()throws Exception{
        txtpID.getItems().removeAll(txtpID.getItems());
        comboSFID.getItems().removeAll(comboSFID.getItems());
        comboSID.getItems().removeAll(comboSID.getItems());
        patientBO.getAllPatient().stream().forEach(dto -> {
            txtpID.getItems().add(dto.getPid());
        });

        fieldBO.getAllSField().stream().forEach(dto -> {
            comboSFID.getItems().add(dto.getfId());
        });
    }

    @FXML
    void onActionDelete(ActionEvent event) {
      validate();
        try {
            clinicBO.getALLForTable().stream().filter(cDTO ->cDTO.getSceduleid().equalsIgnoreCase(comboSID.getValue()) ).forEach(customDTO -> {

                try {
                    ClinicDTO clinic = clinicBO.searchClinic(customDTO.getCid());
                    if(clinic.getFieldID().equalsIgnoreCase(comboSFID.getValue())){
                        if(patientBO.searchPatientDetail(new PatientDetailDTO(txtpID.getValue(),clinic.getcId(),null))!=null){
                            patientBO.deletePatientDetail(new PatientDetailDTO(txtpID.getValue(),clinic.getcId(),null));
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionSave(ActionEvent event) {
      validate();
      String sf=comboSFID.getValue();
      String sch=comboSID.getValue();
        try {
            clinicBO.getAllClinic().stream().filter(clinicDTO -> clinicDTO.getFieldID().equalsIgnoreCase(sf)).forEach(cDTO -> {
                System.out.println(cDTO);
                try {
                    PatientDetailDTO detail = patientBO.searchPatientDetail(new PatientDetailDTO(cDTO.getcId(), sch, null));
                     if(detail!=null){
                         return;
                     }
                     patientBO.savePatientDetail(new PatientDetailDTO(txtpID.getValue(),cDTO.getcId(), null),new AppointmentDTO(0,comboDID.getValue(),txtpID.getValue(),comboSID.getValue()));
                     loadTable();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void loadTable(){
        ObservableList<TBPatientClinic> list = FXCollections.observableArrayList();
        try {
            patientBO.getForPatienDetailTable().stream().filter(customdto ->!customdto.getSid().isEmpty() && !customdto.getpId().isEmpty() ).forEach(customdto -> {
                try {
                    patientBO.getAllApointment().stream().forEach(dto -> {
                        if(customdto.getpId().equalsIgnoreCase(dto.getPatientId())&&customdto.getDrID().equalsIgnoreCase(dto.getDoctorId())&&customdto.getSid().equalsIgnoreCase(dto.getSceduleId())){
                            System.out.println(customdto.getSid()+""+customdto.getDate());
                            list.add(new TBPatientClinic(customdto.getpId(),customdto.getPname(),customdto.getPnic(),customdto.getSfID(),customdto.getSf(),customdto.getDrID(),customdto.getdName(),customdto.getdNic(),customdto.getDate(),customdto.getSid()));
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        tabPatientClinic.setItems(list);
    }

    private void validate(){
        if(txtpID.getValue().toString().trim().isEmpty()) {
            txtpID.requestFocus();
            return;
        }else  if(comboSID.getValue().toString().trim().isEmpty()){
            comboSID.requestFocus();
            return;
        }else  if(comboSFID.getValue().toString().trim().isEmpty()){
            comboSFID.requestFocus();
            return;
        }
    }

    @FXML
    void onAnimationMouseEntered(MouseEvent event) {
        Node node = (Node) event.getSource();
        switch (node.getId()){
            case"butRemove":
                butRemove.setOpacity(1);
                butRemove.setStyle("-fx-border-color: White;-fx-border-width: 0px 0px 2px 0px; -fx-text-fill: white;-fx-background-color: DarkOrange");
                break;
            case "butADD":
                butADD.setOpacity(1);
                butADD.setStyle("-fx-border-color: White;-fx-border-width: 0px 0px 2px 0px; -fx-text-fill: white;-fx-background-color: green");
                break;

            case "butGO":
                butGO.setOpacity(1);
                labGo.setVisible(true);
                scaleTransition(node,1.11);
                dropShadow(node);
                break;

        }
    }

    @FXML
    void onAnimationMouseExited(MouseEvent event) {
        Node node = (Node) event.getSource();
        switch (node.getId()){
            case"butRemove":
                butRemove.setOpacity(0.6);
                butRemove.setStyle("-fx-border-color: transparent;-fx-border-width: 0px 0px 0px 0px; -fx-text-fill: black;-fx-background-color: brown");
                break;
            case "butADD":
                butADD.setOpacity(0.6);
                butADD.setStyle("-fx-border-color:transparent;-fx-border-width: 0px 0px 0px 0px; -fx-text-fill: black;-fx-background-color:brown");
                break;

            case "butGO":
                butGO.setOpacity(0.6);
                labGo.setVisible(false);
                scaleTransition(node,1);
                dropShadow(node);
                break;

        }
    }

}
