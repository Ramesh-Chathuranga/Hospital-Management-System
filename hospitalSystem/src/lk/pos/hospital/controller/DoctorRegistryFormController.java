package lk.pos.hospital.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.pos.hospital.business.BOFactory;
import lk.pos.hospital.business.custom.DoctorRegBO;
import lk.pos.hospital.business.custom.SpecialFieldBO;
import lk.pos.hospital.business.custom.WardBO;
import lk.pos.hospital.controller.util.AnimationClass;
import lk.pos.hospital.controller.util.Validation;
import lk.pos.hospital.dto.DoctorDTO;
import lk.pos.hospital.model.TBDoctorRegistry;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static lk.pos.hospital.controller.util.AnimationClass.*;

public class DoctorRegistryFormController implements Initializable {

    @FXML
    private AnchorPane mainAnchorpane;

    @FXML
    private Label labTitle;

    @FXML
    private Label labTitle1;

    @FXML
    private JFXTextField txtNIC;

    @FXML
    private Label labRegID;

    @FXML
    private JFXTextField txtMID;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXButton butClearField;

    @FXML
    private JFXTextField txtTEL;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXComboBox<String> comboWardName;

    @FXML
    private JFXComboBox<String> comboSFName;

    @FXML
    private JFXButton butSave;

    @FXML
    private JFXButton butDelete;

    @FXML
    private TableView<TBDoctorRegistry> tabDOCTOR;

    @FXML
    private JFXRadioButton butMale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private JFXRadioButton butFemale;


    @FXML
    private AnchorPane butGO;

    @FXML
    private Label labGo;

    @FXML
    private JFXButton butClinic;

    private DoctorRegBO regBO;
    private WardBO wardBO;
    private SpecialFieldBO fieldBO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        regBO= BOFactory.getInstance().getBo(BOFactory.BOType.DOCTOR);
        wardBO = BOFactory.getInstance().getBo(BOFactory.BOType.WARD);
        fieldBO=BOFactory.getInstance().getBo(BOFactory.BOType.SPECIAL_FIELD);
       labGo.setVisible(false);
       butDelete.setOpacity(0.3);
       butSave.setOpacity(0.3);
       butClinic.setOpacity(0.5);
       tabDOCTOR.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("rid"));
        tabDOCTOR.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tabDOCTOR.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("mid"));
        tabDOCTOR.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("name"));
        tabDOCTOR.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("address"));
        tabDOCTOR.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("phone"));
        tabDOCTOR.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("email"));
        tabDOCTOR.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("wardid"));
        tabDOCTOR.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("wardname"));
        tabDOCTOR.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("sfield"));
        tabDOCTOR.getColumns().get(10).setCellValueFactory(new PropertyValueFactory<>("gender"));
        try {
            labRegID.setText(regBO.autoDocRegIdDoctor());
            loadComboBoxes();
            loadTable();
        } catch (Exception e) {
            e.printStackTrace();
        }

        comboWardName.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue==null){
                return;
            }
           comboSFName.getItems().removeAll(comboSFName.getItems());
            try {
                regBO.getAllDetailsByWid(newValue).stream().forEach(customDTO -> {
                    comboSFName.getItems().add(customDTO.getFieldId());
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        tabDOCTOR.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue==null){
                return;
            }
            txtTEL.setText(newValue.getPhone());
            txtNIC.setText(newValue.getNic());
            txtName.setText(newValue.getName());
            txtMID.setText(newValue.getMid());
            txtEmail.setText(newValue.getEmail());
            txtAddress.setText(newValue.getAddress());
            labRegID.setText(newValue.getRid());
            String gender=newValue.getGender();
            comboWardName.getSelectionModel().select(newValue.getWardid());
            if(gender.equalsIgnoreCase(butFemale.getText())){
                butFemale.setSelected(true);
            }else if(gender.equalsIgnoreCase(butMale.getText())){
                butMale.setSelected(true);
            }
        });
    }

    @FXML
    void onActionDelete(ActionEvent event) {
        String rid=labRegID.getText();
        try {
            if(regBO.getAllDoctor().stream().anyMatch(dto -> dto.getDrId().equalsIgnoreCase(rid))){
                regBO.deleteDoctor(rid);
                cleanField();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionSave(ActionEvent event) {
       try{
           String rid=labRegID.getText();
           String address = txtAddress.getText();
           String mail = txtEmail.getText();
           String mid = txtMID.getText();
           String name = txtName.getText();
           String nic = txtNIC.getText();
           String phone = txtTEL.getText();
           String gender=null;
           String fieldid=comboSFName.getValue();
           String wid=comboWardName.getValue();
           if(rid.trim().isEmpty()){
               return;
           }else  if(address.trim().isEmpty()){
               txtAddress.requestFocus();
               return;
           }else  if(mail.trim().isEmpty()){
               txtEmail.requestFocus();
               return;
           }else  if(mid.trim().isEmpty()){
               txtMID.requestFocus();
               return;
           }else  if(name.trim().isEmpty()){
               txtName.requestFocus();
               return;
           }else if (nic.trim().trim().isEmpty()){
               txtNIC.requestFocus();
               return;
           }else if (phone.trim().isEmpty()){
               txtTEL.requestFocus();
               return;
           } else if ((fieldid.trim().isEmpty())) {
               comboSFName.requestFocus();
               return;
           }else if(wid.isEmpty()){
               comboWardName.requestFocus();
               return;
           }else if(butFemale.isSelected()){
               gender=butFemale.getText();
           }else if(butMale.isSelected()){
               gender=butMale.getText();
           }
           boolean isemail = Validation.isEmail(mail);
           boolean isnic = Validation.isNIC(nic);
           boolean isphone = Validation.isPhoneNumber(phone);
           if (!isemail){
               txtEmail.requestFocus();
               return;
           }else  if(!isnic){
               txtNIC.requestFocus();
               return;
           }else if(!isphone){
               txtTEL.requestFocus();
               return;
           }else if(!butFemale.isSelected()&&!butMale.isSelected()){
               butMale.requestFocus();
               return;
           }


           else if(tabDOCTOR.getItems().stream().anyMatch(tb ->tb.getRid().equalsIgnoreCase(rid) )){
               System.out.println("match");
               regBO.updateDoctor(new DoctorDTO(rid,nic,mid,name,address,phone,mail,wid,fieldid,gender));
               cleanField();
               return;
           }else {
               regBO.saveDoctor(new DoctorDTO(rid,nic,mid,name,address,phone,mail,wid,fieldid,gender));
               cleanField();
               return;
           }
       }catch (Exception e){
           e.getStackTrace();
       }

    }

    @FXML
    void onMouseEnteredAnimation(MouseEvent event) {
        JFXButton button = (JFXButton) event.getSource();
        switch (button.getId()){
            case "butSave":
                butSave.setOpacity(1);
                butSave.setStyle("-fx-border-color: White;-fx-border-width: 0px 0px 2px 0px; -fx-text-fill: white;-fx-background-color: green");
                break;
            case "butDelete":
                butDelete.setOpacity(1);
                butDelete.setStyle("-fx-border-color: white;-fx-border-width: 0px 0px 2px 0px; -fx-text-fill: white");

                break;
            case "butClinic":
                 butClinic.setOpacity(1);
                 butClinic.setStyle("-fx-border-color: #227f6f;-fx-border-width: 0px 0px 2px 0px;-fx-text-fill: #227f6f;");
                 scaleTransition(button,1.2);
                 dropShadow(button);
                break;
            case "butClearField":
                butClearField.setStyle("-fx-text-fill:#227f6f; -fx-border-color: #227f6f;-fx-border-width: 0px 0px 2px 0px ");
                break;
        }
    }

    @FXML
    void onMouseExitedAnimation(MouseEvent event) {

        JFXButton button = (JFXButton) event.getSource();
        switch (button.getId()){
            case "butSave":
                butSave.setOpacity(0.3);
                butSave.setStyle("-fx-border-color:transparent;-fx-border-width: 0px 0px 0px 0px; -fx-text-fill: black;-fx-background-color: brown");
                break;
            case "butDelete":
                butDelete.setOpacity(0.3);
                butDelete.setStyle("-fx-border-color: transparent;-fx-border-width: 0px 0px 0px 0px; -fx-text-fill: black");
                break;
            case "butClinic":
                butClinic.setOpacity(0.5);
                butClinic.setStyle("-fx-border-color: transparent;-fx-border-width: 0px 0px 0px 0px;-fx-text-fill:black;");
                button.setEffect(null);
                break;
            case "butClearField":
                butClearField.setStyle("-fx-text-fill:White; -fx-border-color: transparent;-fx-border-width: 0px 0px 0px 0px ");
                break;
        }
 }


    @FXML
    void onMouseEnteredGOButton(MouseEvent event) {
        AnchorPane anchorPane = (AnchorPane) event.getSource();
        if (anchorPane.getId().equalsIgnoreCase("butGO")) {
            labGo.setVisible(true);
            scaleTransition(anchorPane,1.2);
            AnimationClass.dropShadow(anchorPane);
        }
    }

    @FXML
    void onMouseExitedGOButton(MouseEvent event) {
        AnchorPane anchorPane = (AnchorPane) event.getSource();
        if (anchorPane.getId().equalsIgnoreCase("butGO")) {
            labGo.setVisible(false);
            scaleTransition(anchorPane,1);
            butGO.setEffect(null);
        }
    }


    @FXML
    void navigateTOAdmin(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/pos/hospital/view/AdminForm.fxml"));
            Stage stage = (Stage) mainAnchorpane.getScene().getWindow();
            AnimationClass.navigateAll(root,stage,"Admin Page",1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void onActiontoClinicAdding(ActionEvent event) {
        try{
            navigateAll((Parent)FXMLLoader.load(this.getClass().getResource("/lk/pos/hospital/view/AddScheduleTODOCTORForm.fxml")),(Stage)this.butClinic.getScene().getWindow(),"Doctor  Schedule",-1);
        }catch (IOException e){

        }
    }


    @FXML
    void onActionCleanController(ActionEvent event) {
        try {
            cleanField();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void cleanField() throws Exception {
        txtAddress.clear();
        txtEmail.clear();
        txtMID.clear();
        txtName.clear();
        txtNIC.clear();
        txtTEL.clear();
        tabDOCTOR.getSelectionModel().clearSelection();
        labRegID.setText(regBO.autoDocRegIdDoctor());
        loadTable();
        butFemale.setSelected(false);
        butMale.setSelected(false);
        comboSFName.getSelectionModel().clearSelection();
    }

    private void loadTable()throws Exception {
        ObservableList<TBDoctorRegistry> list = FXCollections.observableArrayList();
        regBO.getAllDoctor().stream().forEach(dto -> {
            String wname=null;
            try {
                 wname=wardBO.searchWard(dto.getWardId()).getWardName();
            } catch (Exception e) {
                e.printStackTrace();
            }
            list.add(new TBDoctorRegistry(dto.getDrId(),dto.getNic(),dto.getMedId(),dto.getFullName(),dto.getAddress(),dto.getTelephone(),dto.getEmail(),dto.getWardId(),wname,dto.getFieldId(),dto.getGender()));
        });
        tabDOCTOR.setItems(list);
    }

    private  void loadComboBoxes()throws Exception{
        comboWardName.getItems().removeAll(comboWardName.getItems());
        comboSFName.getItems().removeAll( comboSFName.getItems());
        wardBO.getAllWard().stream().forEach(wardDTO -> {
            comboWardName.getItems().add(wardDTO.getWardId());
        });
    }

}
