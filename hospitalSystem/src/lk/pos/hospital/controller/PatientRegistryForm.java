package lk.pos.hospital.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.pos.hospital.business.BOFactory;
import lk.pos.hospital.business.custom.PatientBO;
import lk.pos.hospital.dto.PatientDTO;
import lk.pos.hospital.model.TBPatientRegistry;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static lk.pos.hospital.controller.util.AnimationClass.*;

public class PatientRegistryForm implements Initializable {

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
    private JFXTextField txtPID;

    @FXML
    private JFXTextField txtNic;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtTEL;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXButton butClinic;

    @FXML
    private JFXButton butAdmit;

    @FXML
    private TableView<TBPatientRegistry> tabPatient;

    @FXML
    private JFXRadioButton butMale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private JFXRadioButton butFemale;

    @FXML
    private JFXButton butClearField;


    private PatientBO patientBO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        patientBO= BOFactory.getInstance().getBo(BOFactory.BOType.PATIENT);
       labGo.setVisible(false);
       butADD.setOpacity(0.6);
       butAdmit.setOpacity(0.6);
       butClinic.setOpacity(0.6);
       butRemove.setOpacity(0.6);
       butGO.setOpacity(0.6);
        tabPatient.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tabPatient.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tabPatient.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("name"));
        tabPatient.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("phone"));
        tabPatient.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("email"));
        tabPatient.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("gender"));
        tabPatient.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("address"));

        txtPID.setEditable(false);

        try {
            txtPID.setText(patientBO.getPatientRegisterID());
            loadTable();

        } catch (Exception e) {
            e.printStackTrace();
        }

        tabPatient.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue==null){
                return;
            }
            txtPID.setText(newValue.getId());
            txtNic.setText(newValue.getNic());
            txtName.setText(newValue.getName());
            txtTEL.setText(newValue.getPhone());
            txtEmail.setText(newValue.getEmail());
            txtAddress.setText(newValue.getAddress());
            String gender=newValue.getGender();
            if(butMale.getText().equalsIgnoreCase(gender)){
                butMale.setSelected(true);
            }else if(butFemale.getText().equalsIgnoreCase(gender)){
                butMale.setSelected(true);
            }
        });
    }

    private  void loadTable()throws Exception{
        ObservableList<TBPatientRegistry> list = FXCollections.observableArrayList();
        patientBO.getAllPatient().stream().forEach(dto -> {
            list.add(new TBPatientRegistry(dto.getPid(),dto.getNic(),dto.getFullName(),dto.getTelephoneNO(),dto.getEmail(),dto.getGender(),dto.getAddress()));
        });
        tabPatient.setItems(list);
    }


    @FXML
    void navigateTODashBoardForm(MouseEvent event) {
        try {
            navigateAll((Parent) FXMLLoader.load(this.getClass().getResource("/lk/pos/hospital/view/DashBoard.fxml")),
                                                                        (Stage)txtAddress.getScene().getWindow(),
                                                                        "MEDICA HOSPITAL",
                                                                        1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionAdmit(ActionEvent event) {
        try {
            navigateAll((Parent) FXMLLoader.load(this.getClass().getResource("/lk/pos/hospital/view/PatientAdmitForm.fxml")),
                    (Stage)this.butADD.getScene().getWindow(),"MEDICA HOSPITAL",1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionClinic(ActionEvent event) {
        try {
            navigateAll((Parent) FXMLLoader.load(this.getClass().getResource("/lk/pos/hospital/view/PatientClinicForm.fxml")),
                    (Stage)this.butADD.getScene().getWindow(),"MEDICA HOSPITAL",1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionDelete(ActionEvent event) {
        try {
            String id = txtPID.getText();
            if(id.trim().isEmpty()){
                txtPID.requestFocus();
                return;
            }else  if(tabPatient.getItems().stream().anyMatch(tbPatientRegistry -> tbPatientRegistry.getId().equalsIgnoreCase(id))){
                patientBO.deletePatient(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                clearSelection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onActionSave(ActionEvent event) {
        try {

                String id = txtPID.getText();
                String gnic = txtNic.getText();
                String name = txtName.getText();
                String pphone = txtTEL.getText();
                String pemail = txtEmail.getText();
                String address = txtAddress.getText();
                String gender=null;
                Pattern email=Pattern.compile("^([a-z\\d\\.-]+)@([a-z\\d]+)\\.([a-z]{2,8})(\\.[a-z]{2,8})?$");
                Pattern phone=Pattern.compile("\\(?\\d{3}\\)?[-.\\s]?\\d{7}$");
                Pattern nic=Pattern.compile("^[0-9]{9}[V|v]$");
                Matcher pnic=nic.matcher(gnic);
                Matcher mphone=phone.matcher(pphone);
                Matcher gmail=email.matcher(pemail);
                if(id.trim().isEmpty()){
                    txtPID.requestFocus();
                    return;
                }else  if(gnic.trim().isEmpty()){
                    txtNic.requestFocus();
                    return;
                }else if(!pnic.matches()){
                    txtNic.requestFocus();
                    return;
                }else if(name.trim().isEmpty()){
                    txtName.requestFocus();
                    return;
                }else if(pphone.trim().isEmpty()){
                    txtTEL.requestFocus();
                    return;
                }else if(!mphone.matches()){
                    txtTEL.requestFocus();
                    return;
                } else if ((address.trim().isEmpty())) {
                    txtAddress.requestFocus();
                    return;
                }else if(pemail.trim().isEmpty()){
                    txtEmail.requestFocus();
                    return;
                }else if (!gmail.matches()){
                    txtEmail.requestFocus();
                    return;
                }else if(butFemale.isSelected()){
                    gender=butFemale.getText();
                }else  if(butMale.isSelected()){
                    gender=butMale.getText();
                }else if(!butMale.isSelected()&&!butFemale.isSelected()){
                    butMale.requestFocus();
                    return;
                }
                if(tabPatient.getItems().stream().anyMatch(tbPatientRegistry -> tbPatientRegistry.getId().equalsIgnoreCase(id))){
                    patientBO.updatePatient(new PatientDTO(id,gnic,name,address,pphone,pemail,gender));
                    clearSelection();
                }else {
                    patientBO.savePatient(new PatientDTO(id,gnic,name,address,pphone,pemail,gender));
                    clearSelection();
                }
        } catch (Exception e) {
                e.printStackTrace();
        }finally {

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
            case "butClinic":
                butClinic.setOpacity(1);
                butClinic.setStyle("-fx-border-color: #227f6f;-fx-border-width: 0px 0px 2px 0px;-fx-text-fill: #227f6f;");
                scaleTransition(node,1.2);
                dropShadow(node);
                break;
            case "butGO":
                butGO.setOpacity(1);
                labGo.setVisible(true);
                scaleTransition(node,1.11);
                dropShadow(node);
                break;
            case "butAdmit":
                butAdmit.setOpacity(1);
                butAdmit.setStyle("-fx-border-color: #227f6f;-fx-border-width: 0px 0px 2px 0px;-fx-text-fill: #227f6f;");
                scaleTransition(node,1.2);
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
            case"butRemove":
                butRemove.setOpacity(0.6);
                butRemove.setStyle("-fx-border-color: transparent;-fx-border-width: 0px 0px 0px 0px; -fx-text-fill: black;-fx-background-color: brown");
                break;
            case "butADD":
                butADD.setOpacity(0.6);
                butADD.setStyle("-fx-border-color:transparent;-fx-border-width: 0px 0px 0px 0px; -fx-text-fill: black;-fx-background-color:brown");
                break;
            case "butClinic":
                butClinic.setOpacity(0.6);
                butClinic.setStyle("-fx-border-color: transparent;-fx-border-width: 0px 0px 0px 0px;-fx-text-fill: White;");
                scaleTransition(node,1);
                dropShadow(node);
                break;
            case "butGO":
                butGO.setOpacity(0.6);
                labGo.setVisible(false);
                scaleTransition(node,1);
                dropShadow(node);
                break;
            case "butAdmit":
                butAdmit.setOpacity(0.6);
                butAdmit.setStyle("-fx-border-color:transparent;-fx-border-width: 0px 0px 0px 0px;-fx-text-fill: white;");
                scaleTransition(node,1);
                dropShadow(node);
                break;
            case "butClearField":
                butClearField.setStyle("-fx-text-fill:White; -fx-border-color: transparent;-fx-border-width: 0px 0px 0px 0px ");
                break;
        }
    }


    @FXML
    void onActionCleanController(ActionEvent event) {
        try {
            clearSelection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearSelection() throws Exception {
       txtPID.clear();
       txtPID.setText(patientBO.getPatientRegisterID());
       tabPatient.getSelectionModel().clearSelection();
       butFemale.setSelected(false);
       butMale.setSelected(false);
       loadTable();
        txtNic.clear();
        txtName.clear();
        txtTEL.clear();
        txtAddress.clear();
        txtEmail.clear();

    }
}
