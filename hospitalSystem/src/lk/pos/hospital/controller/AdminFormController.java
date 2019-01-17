package lk.pos.hospital.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.pos.hospital.controller.util.AnimationClass;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static lk.pos.hospital.controller.util.AnimationClass.*;

public class AdminFormController implements Initializable {


    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private AnchorPane butWard;

    @FXML
    private Label labWard;

    @FXML
    private AnchorPane butDoctor;

    @FXML
    private Label labDoctor;

    @FXML
    private AnchorPane butPD;

    @FXML
    private Label labPD;

    @FXML
    private AnchorPane butClinic;

    @FXML
    private Label labClinic;

    @FXML
    private AnchorPane butSchedule;

    @FXML
    private Label labSchedule;

    @FXML
    private AnchorPane butSF;

    @FXML
    private Label labSF1;

    @FXML
    private Label labSF2;

    @FXML
    private Label labTitle;

    @FXML
    private Label labTitle1;

    @FXML
    private Label labField;

    @FXML
    private AnchorPane butGOBACK;

    @FXML
    private Label labGoBACK;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
      labClinic.setVisible(false);
      labDoctor.setVisible(false);
      labField.setVisible(false);
      labGoBACK.setVisible(false);
      labPD.setVisible(false);
      labSchedule.setVisible(false);
      labSF1.setVisible(false);
      labSF2.setVisible(false);
      labWard.setVisible(false);
    }

    @FXML
    void mouseEnteredAnimation(MouseEvent event) {
        AnchorPane pane=(AnchorPane)event.getSource();
        switch (pane.getId()){
            case "butClinic":
                labClinic.setVisible(true);
                labField.setText(labClinic.getText());
                labField.setTextFill(labClinic.getTextFill());
                labField.setVisible(true);
                dropShadow(pane);
                scaleTransition(pane,1.5);
                break;
            case "butDoctor":
                labDoctor.setVisible(true);
                labField.setText(labDoctor.getText());
                labField.setVisible(true);
                dropShadow(pane);
                scaleTransition(pane,1.5);
                break;
            case "butPD":
                labPD.setVisible(true);
                labField.setText(labPD.getText());
                labField.setTextFill(labPD.getTextFill());
                labField.setVisible(true);
                dropShadow(pane);
                scaleTransition(pane,1.5);
                break;
            case "butGOBACK":
                labGoBACK.setVisible(true);
                labField.setText(labGoBACK.getText());
                labField.setTextFill(labGoBACK.getTextFill());
                Paint textFill = labGoBACK.getTextFill();
                labField.setVisible(true);
                dropShadow(pane);
                scaleTransition(pane,1.5);
                break;
            case "butSchedule":
                labSchedule.setVisible(true);
                labField.setText(labSchedule.getText());
                labField.setTextFill(labSchedule.getTextFill());
                labField.setVisible(true);
                dropShadow(pane);
                scaleTransition(pane,1.5);
                break;
            case "butSF":
                labSF1.setVisible(true);
                labSF2.setVisible(true);
                labField.setText(labSF1.getText()+" "+labSF2.getText());
                labField.setVisible(true);
                labField.setTextFill(labSF1.getTextFill());
                labField.setVisible(true);
                dropShadow(pane);
                scaleTransition(pane,1.5);
                break;
            case "butWard":
                labWard.setVisible(true);
                labField.setText(labWard.getText());
                labField.setTextFill(labWard.getTextFill());
                labField.setVisible(true);
                dropShadow(pane);
                scaleTransition(pane,1.5);
                break;
            default:
             break;
        }
    }




    @FXML
    void mouseExitedAnimation(MouseEvent event) {
        AnchorPane pane=(AnchorPane)event.getSource();
        switch (pane.getId()){
            case "butClinic":
                labClinic.setVisible(false);
                labField.setVisible(false);
                pane.setEffect(null);
                scaleTransition(pane,1.0);
                break;
            case "butDoctor":
                labDoctor.setVisible(false);
                labField.setVisible(false);
                pane.setEffect(null);;
                scaleTransition(pane,1.0);
                break;
            case "butPD":
                labPD.setVisible(false);
                labField.setVisible(false);
                pane.setEffect(null);
                scaleTransition(pane,1.0);
                break;
            case "butGOBACK":
                labGoBACK.setVisible(false);
                labField.setVisible(false);
                pane.setEffect(null);
                scaleTransition(pane,1.0);
                break;
            case "butSchedule":
                labSchedule.setVisible(false);
                labField.setVisible(false);
                pane.setEffect(null);
                scaleTransition(pane,1.0);
                break;
            case "butSF":
                labSF1.setVisible(false);
                labSF2.setVisible(false);
                labField.setVisible(false);
                pane.setEffect(null);
                scaleTransition(pane,1.0);
                break;
            case "butWard":
                labWard.setVisible(false);
                labField.setVisible(false);
                pane.setEffect(null);
                scaleTransition(pane,1.0);
                break;
            default:
                break;
        }
    }

    @FXML
    void navigator(MouseEvent event) {
        AnchorPane anchorPane=(AnchorPane) event.getSource();
        switch (anchorPane.getId()){
            case "butClinic":
                goToPage(butClinic,"/lk/pos/hospital/view/ClinicForm.fxml"," MEDIC HOSPITAL \t\t\t CLINIC FORM",-1);
                break;
            case "butDoctor":
                  goToPage(butDoctor,"/lk/pos/hospital/view/DoctorRegistryForm.fxml"," MEDIC HOSPITAL \t\t\t DOCTOR REGISTRY",-1);
                break;
            case "butPD":
                goToPage(butPD,"/lk/pos/hospital/view/PatientDetailForm.fxml"," MEDIC HOSPITAL \t\t\t Patient Detail",-1);
               break;
            case "butGOBACK":
                 goToPage(butGOBACK,"/lk/pos/hospital/view/DashBoard.fxml"," MEDIC HOSPITAL",1);

                break;
            case "butSchedule":
                  goToPage(butSchedule,"/lk/pos/hospital/view/ScheduleForm.fxml"," MEDIC HOSPITAL \t\t\t SCHEDULE FORM",-1);
                break;
            case "butSF":
                  goToPage(butSF,"/lk/pos/hospital/view/SpecialFieldForm.fxml"," MEDIC HOSPITAL \t\t\t  SPECIAL FIELD",-1);
                break;
            case "butWard":
                goToPage(butWard,"/lk/pos/hospital/view/WardForm.fxml"," MEDIC HOSPITAL \t\t\t WARD FORM",-1);
                break;

            default:
                break;
        }

    }


    private void  goToPage(AnchorPane pane, String path,String title,double minusOrPlus) {
        Parent root = null;
        try {
            root = FXMLLoader.load(this.getClass().getResource(path));
            navigation(root,pane,title,minusOrPlus);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void navigation(Parent root,AnchorPane pane,String title,double minusOrPlus){

        Stage stage = (Stage) pane.getScene().getWindow();
        AnimationClass.navigateAll(root,stage,title,minusOrPlus);
    }


}
