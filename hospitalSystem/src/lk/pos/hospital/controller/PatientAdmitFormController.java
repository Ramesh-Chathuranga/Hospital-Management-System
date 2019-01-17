package lk.pos.hospital.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.pos.hospital.business.BOFactory;
import lk.pos.hospital.business.custom.BedBO;
import lk.pos.hospital.business.custom.PatientAdmitBO;
import lk.pos.hospital.business.custom.PatientBO;
import lk.pos.hospital.business.custom.WardBO;
import lk.pos.hospital.dto.BedDTO;
import lk.pos.hospital.dto.HospitalAdmitDTO;
import lk.pos.hospital.model.TBPatientAdmit;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static lk.pos.hospital.controller.util.AnimationClass.*;

public class PatientAdmitFormController implements Initializable {


    @FXML
    private Label labTitle;

    @FXML
    private JFXButton butSave;

    @FXML
    private JFXButton butDischarge;

    @FXML
    private AnchorPane butGO;

    @FXML
    private Label labGo;

    @FXML
    private JFXComboBox<String> comboWard;

    @FXML
    private JFXTextField txtPName;

    @FXML
    private JFXComboBox<String> comboPatientID;

    @FXML
    private JFXComboBox<String> comboBED;

    @FXML
    private TableView<TBPatientAdmit> tabAdmit;

    @FXML
    private JFXButton butClearField;

    @FXML
    private JFXDatePicker date;

    private PatientBO patientBO;

    private WardBO wardBO;

    private BedBO bedBO;

    @FXML
    private JFXTextField txtWName;

    private PatientAdmitBO admitBO;

   private static String getbedID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         patientBO= BOFactory.getInstance().getBo(BOFactory.BOType.PATIENT);
        admitBO=BOFactory.getInstance().getBo(BOFactory.BOType.HOSPITAL_ADMIT);
        wardBO= BOFactory.getInstance().getBo(BOFactory.BOType.WARD);
        bedBO= BOFactory.getInstance().getBo(BOFactory.BOType.BED);
        butSave.setOpacity(0.6);
        butGO.setOpacity(0.6);
        butDischarge.setOpacity(0.6);
        txtWName.setEditable(false);
        txtPName.setEditable(false);
        labGo.setVisible(false);
         date.setValue(LocalDate.now());
        tabAdmit.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("pid"));
        tabAdmit.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("pname"));
        tabAdmit.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("wid"));
        tabAdmit.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("bid"));
        tabAdmit.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("date"));
        tabAdmit.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("discharge"));

        try {
            loadTable();
            loadCombobox();
            loadSecondCombo();
        } catch (Exception e) {
            e.printStackTrace();
        }

        comboPatientID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue==null){
                return;
            }
            try {
                txtPName.setText(patientBO.searchPatient(newValue).getFullName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        comboWard.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue==null){
                return;
            }
            try {
                txtWName.setText(wardBO.searchWard(newValue).getWardName());
               bedBO.getALLBED().stream().filter(dto -> dto.getWardID().equalsIgnoreCase(newValue)&&dto.getPatientID()==null).forEach(dto -> {
                   comboBED.getItems().add(dto.getBedID());
               });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        tabAdmit.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           if(newValue==null){
               return;
           }
            date.setValue(newValue.getDate());
            comboWard.setValue(newValue.getWid());
            comboPatientID.setValue(newValue.getPid());
            comboPatientID.getSelectionModel().select(newValue.getPid());
            comboWard.getSelectionModel().select(newValue.getWid());
            comboBED.getSelectionModel().select(newValue.getBid());
            comboBED.setValue(newValue.getBid());
            getbedID=newValue.getBid();
        });

    }

    private void loadTable()throws Exception{
        ObservableList<TBPatientAdmit> list = FXCollections.observableArrayList();
        admitBO.getAllForTABLE().stream().forEach(co -> {
            list.add(new TBPatientAdmit(co.getPid(),co.getPname(),co.getWid(),co.getBid(),co.getDate(),co.getDischarchdate()));
        });
        tabAdmit.setItems(list);
    }

    private void loadCombobox() throws Exception {

        comboWard.getItems().removeAll(comboWard.getItems());
        comboBED.getItems().removeAll(comboBED.getItems());


        wardBO.getAllWard().stream().forEach(wardDTO -> {
            comboWard.getItems().add(wardDTO.getWardId());
        });


    }


    private void loadSecondCombo() throws Exception {
        comboPatientID.getItems().removeAll(comboPatientID.getItems());
        patientBO.getAllPatient().stream().forEach(dto -> {
            comboPatientID.getItems().add(dto.getPid());
        });
    }

    @FXML
    void navigateTOpatientREG(MouseEvent event) {
        try {
            navigateAll((Parent) FXMLLoader.load(this.getClass().getResource("/lk/pos/hospital/view/PatientRegistryForm.fxml")),
                    (Stage)this.butSave.getScene().getWindow(),"MEDICA HOSPITAL",1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionDischarge(ActionEvent event) {

        String pid=comboPatientID.getValue();
        String wid=comboWard.getValue();
        String bed=getbedID;
        LocalDate ld=date.getValue();
        validationOfField(pid,wid,bed,ld);
        try {


            if(bedBO.getALLBED().stream().anyMatch(dto -> dto.getWardID().equalsIgnoreCase(wid) && dto.getPatientID().equalsIgnoreCase(pid))){
                System.out.println("ok");
                admitBO.dischargePatent(new HospitalAdmitDTO(pid,wid,ld,LocalDate.now()),new BedDTO(getbedID,wid,""));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                clearField();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void validationOfField(String pid,String wid,String bed ,LocalDate ld){
    try{

            if(pid.trim().isEmpty()){
                comboPatientID.requestFocus();
                return;
            }else if(wid.trim().isEmpty()){
                comboWard.requestFocus();
                return;
            }else  if(bed.trim().isEmpty()){
                comboBED.requestFocus();
                return;
            }else if(ld.toString().isEmpty()&& ld==null){
                date.requestFocus();
                return;
            }
        }catch (NullPointerException e){
           return;
        }
    }

    @FXML
    void onActionSave(ActionEvent event) {
        String pid=comboPatientID.getValue();
        String wid=comboWard.getValue();
        String bed=comboBED.getValue();
        LocalDate ld=date.getValue();
     validationOfField(pid,wid,bed,ld);
        try {

            boolean anyMatch = admitBO.getAllPatentHospitalAdmit().stream().filter(dto -> dto.getWardId().equalsIgnoreCase(wid) && dto.getPatientId().equalsIgnoreCase(pid)).anyMatch(dto -> dto.getDischargedate()==null);
            if(!anyMatch){
               admitBO.savePatentHospitalAdmit(new HospitalAdmitDTO(pid,wid,ld),new BedDTO(bed,wid,pid));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                clearField();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void onAnimationMouseEntered(MouseEvent event) {
        Node node = (Node) event.getSource();
        switch (node.getId()){
            case"butDischarge":
                butDischarge.setOpacity(1);
                butDischarge.setStyle("-fx-border-color: White;-fx-border-width: 0px 0px 2px 0px; -fx-text-fill: white;-fx-background-color: DarkOrange");
                break;
            case "butSave":
                butSave.setOpacity(1);
                butSave.setStyle("-fx-border-color: White;-fx-border-width: 0px 0px 2px 0px; -fx-text-fill: white;-fx-background-color: green");
                break;

            case "butGO":
                butGO.setOpacity(1);
                labGo.setVisible(true);
                scaleTransition(node,1.11);
                dropShadow(node);
                break;
            case "butClearField":
                butClearField.setStyle("-fx-text-fill:#227f6f; -fx-border-color: #227f6f;-fx-border-width: 0px 0px 2px 0px ");
                break;

        }
    }

    @FXML
    void onAnimationMouseExited(MouseEvent event) {
        Node node = (Node) event.getSource();
        switch (node.getId()){
            case"butDischarge":
                butDischarge.setOpacity(0.6);
                butDischarge.setStyle("-fx-border-color: transparent;-fx-border-width: 0px 0px 0px 0px; -fx-text-fill: black;-fx-background-color: brown");
                break;
            case "butSave":
                butSave.setOpacity(0.6);
                butSave.setStyle("-fx-border-color:transparent;-fx-border-width: 0px 0px 0px 0px; -fx-text-fill: black;-fx-background-color:brown");
                break;

            case "butGO":
                butGO.setOpacity(0.6);
                labGo.setVisible(false);
                scaleTransition(node,1);
                dropShadow(node);
                break;
            case "butClearField":
                butClearField.setStyle("-fx-text-fill:White; -fx-border-color: transparent;-fx-border-width: 0px 0px 0px 0px ");
                break;

        }

    }


    @FXML
    void onActionCleanController(ActionEvent event){
        try {
            clearField();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void clearField() throws Exception {
        date.setValue(null);
        comboBED.getSelectionModel().clearSelection();
        comboWard.getSelectionModel().clearSelection();
        comboPatientID.getSelectionModel().clearSelection();;
        txtWName.clear();
        txtPName.clear();
        loadTable();
    }
}
