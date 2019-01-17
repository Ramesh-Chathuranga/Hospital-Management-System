package lk.pos.hospital.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.pos.hospital.business.BOFactory;
import lk.pos.hospital.business.custom.DoctorRegBO;
import lk.pos.hospital.controller.util.AnimationClass;
import lk.pos.hospital.controller.util.Validation;
import lk.pos.hospital.dto.DoctorDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static lk.pos.hospital.controller.util.AnimationClass.*;

public class DoctorProfileFormController implements Initializable {

    @FXML
    private Label labTitle;

    @FXML
    private Label labTitle1;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtMID;

    @FXML
    private JFXTextField txtNic;

    @FXML
    private Button butClinic;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtGender;

    @FXML
    private JFXTextField txtTEL;

    @FXML
    private JFXTextField txtEMAIL;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private Button butSave;

    @FXML
    private AnchorPane butBack;

    @FXML
    private Label labBack;

    DoctorRegBO regBO;

    private static  String wid;
    private static String fid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        regBO= BOFactory.getInstance().getBo(BOFactory.BOType.DOCTOR);
       labBack.setVisible(false);
       butClinic.setOpacity(0.6);
       butSave.setOpacity(0.6);
       txtAddress.setStyle("-fx-text-fill:#227f6f;-fx-font-size: 13px");
       txtEMAIL.setStyle("-fx-text-fill:#227f6f;-fx-font-size: 13px");
       txtGender.setStyle("-fx-text-fill: #227f6f;-fx-font-size: 13px");
       txtId.setStyle("-fx-text-fill:#227f6f;-fx-font-size: 13px");
       txtMID.setStyle("-fx-text-fill:#227f6f;-fx-font-family: 'Bell MT';-fx-font-size: 13px");
       txtName.setStyle("-fx-text-fill:#227f6f;-fx-font-size: 13px");
       txtNic.setStyle("-fx-text-fill:#227f6f;-fx-font-size: 13px");
       txtTEL.setStyle("-fx-text-fill:#227f6f;-fx-font-size: 13px");
       txtGender.setStyle("-fx-text-fill:#227f6f;-fx-font-size: 13px");
       txtId.setEditable(false);
       txtGender.setEditable(false);
       txtNic.setEditable(false);
    }

    public void getDoctorVerificationResult(String id){
        System.out.println(id);
        txtId.setText(id);
        try {
            regBO.getAllDoctor().stream().forEach(dto -> {
               if(dto.getDrId().equalsIgnoreCase(id)){
                   txtAddress.setText(dto.getAddress());
                   txtEMAIL.setText(dto.getEmail());
                   txtGender.setText(dto.getGender());
                   txtNic.setText(dto.getNic());
                   txtName.setText(dto.getFullName());
                   txtTEL.setText(dto.getTelephone());
                   txtMID.setText(dto.getMedId());
                   wid=dto.getWardId();
                   fid=dto.getFieldId();
               }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void backTOAdminForm(MouseEvent event) {
        Stage window = (Stage) labBack.getScene().getWindow();
        window.close();
    }



    @FXML
    void onActionClinic(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader=new FXMLLoader(this.getClass().getResource("/lk/pos/hospital/view/DoctorClinicForm.fxml"));
            Parent parent=fxmlLoader.load();
            DoctorClinicFormController controller=fxmlLoader.getController();
            controller.getItemFromProfile(regBO.searchDoctor(txtId.getText()));
            Stage stage = (Stage) this.txtGender.getScene().getWindow();
            AnimationClass.navigateAll(parent,stage,"Medic Private Hospital",1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionSave(ActionEvent event) {

        if( txtMID.getText().trim().isEmpty()){
            txtMID.requestFocus();
            return;
        }if(txtName.getText().trim().isEmpty()){
            txtName.requestFocus();
            return;
        }if(txtAddress.getText().trim().isEmpty()){
            txtAddress.requestFocus();
            return;
        }
        boolean isemail= Validation.isEmail(txtEMAIL.getText());
        if(!isemail){
            txtEMAIL.requestFocus();
            return;
        }
        boolean isTEL=Validation.isPhoneNumber(txtTEL.getText());
        if ((!isTEL)){
            txtTEL.requestFocus();
            return;
        }
        try {
            boolean isUpdate = regBO.updateDoctor(new DoctorDTO(txtId.getText(), txtNic.getText(), txtMID.getText(), txtName.getText(), txtAddress.getText(), txtTEL.getText(), txtEMAIL.getText(), wid, fid, txtGender.getText()));
            if(isUpdate){
                System.out.println("update");
            }else {
                System.out.println("not update");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onMouseAnimationEntered(MouseEvent event) {
        Node node = (Node) event.getSource();
        switch (node.getId()){
            case "butBack":
                labBack.setVisible(true);
                scaleTransition(node,1.2);
                dropShadow(node);
                break;
            case "butSave":
                butSave.setOpacity(1);
                butSave.setStyle("-fx-background-color: lightgreen;-fx-border-width: 0px 0px 2px 0px ; -fx-border-color: white;-fx-text-fill: white");
                break;
            case "butClinic":
                butClinic.setOpacity(1);
                butClinic.setStyle("-fx-background-color: darkred;-fx-border-width: 0px 0px 2px 0px ; -fx-border-color: white;-fx-text-fill: white");
                break;
        }
    }

    @FXML
    void onMouseAnimationExited(MouseEvent event) {
        Node node = (Node) event.getSource();
        switch (node.getId()){
            case "butBack":
                labBack.setVisible(false);
                scaleTransition(node,1);
                dropShadow(node);
                break;
            case "butSave":
                butSave.setOpacity(0.6);
                butSave.setStyle("-fx-background-color: brown;-fx-border-width: 0px 0px 0px 0px ; -fx-border-color: transparent;-fx-text-fill: black");
                break;
            case "butClinic":
                butClinic.setOpacity(0.6);
                butClinic.setStyle("-fx-background-color: brown;-fx-border-width: 0px 0px 0px 0px ; -fx-border-color: transparent;-fx-text-fill: black");
                break;
        }
    }

}
