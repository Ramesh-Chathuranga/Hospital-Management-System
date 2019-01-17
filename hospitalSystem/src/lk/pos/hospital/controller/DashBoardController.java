package lk.pos.hospital.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.pos.hospital.controller.util.AnimationClass;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static lk.pos.hospital.controller.util.AnimationClass.*;

public class DashBoardController implements Initializable {


    @FXML
    private AnchorPane mainanchorPane;

    @FXML
    private Label labtitle;

    @FXML
    private AnchorPane butAdmin;

    @FXML
    private Label labAdmin;

    @FXML
    private AnchorPane butDoctor;

    @FXML
    private Label labDoctor;

    @FXML
    private AnchorPane butpatient;

    @FXML
    private Label labPatient;

    @FXML
    private Label lablField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labDoctor.setVisible(false);
        labPatient.setVisible(false);
        labAdmin.setVisible(false);
        lablField.setVisible(false);
    }

    @FXML
   private void mouseEnteredAnimation(MouseEvent event) {
        AnchorPane anchorPane=(AnchorPane)event.getSource();
        switch (anchorPane.getId()){
            case "butAdmin":
                lablField.setVisible(true);
                labAdmin.setVisible(true);
                lablField.setText(labAdmin.getText());
                scaleTransition(anchorPane,1.8);
                dropShadow(anchorPane);
                break;
            case "butDoctor":
                lablField.setVisible(true);
                labDoctor.setVisible(true);
                lablField.setText(labDoctor.getText());
                scaleTransition(anchorPane,1.8);
                dropShadow(anchorPane);
                break;
            case "butPatient":
                lablField.setVisible(true);
                labPatient.setVisible(true);
                lablField.setText(labPatient.getText());
                scaleTransition(anchorPane,1.8);
                dropShadow(anchorPane);
                break;
            default:break;
        }
    }

    @FXML
    private void mouseExitedAnimation(MouseEvent event) {
        AnchorPane anchorPane=(AnchorPane)event.getSource();
        switch (anchorPane.getId()){
            case "butAdmin":
                labAdmin.setVisible(false);
                lablField.setVisible(false);
                scaleTransition(anchorPane,1.0);
                anchorPane.setEffect(null);
                break;
            case "butDoctor":
                labDoctor.setVisible(false);
                lablField.setVisible(false);
                scaleTransition(anchorPane,1.0);
                anchorPane.setEffect(null);
                break;
            case "butPatient":
                labPatient.setVisible(false);
                lablField.setVisible(false);
                scaleTransition(anchorPane,1.0);
                anchorPane.setEffect(null);
                break;
            default:break;
        }

    }

    @FXML
    private void navigator(MouseEvent mouseEvent) {
        AnchorPane anchorPane=(AnchorPane)mouseEvent.getSource();
        switch (anchorPane.getId()){
            case "butAdmin":
                 goToPage(anchorPane,"/lk/pos/hospital/view/AdminForm.fxml","Admin Page");
                break;
            case "butDoctor":
//                 goToPage(anchorPane,"/lk/pos/hospital/view/DoctorVeryfyForm.fxml","Verification");
                try {
                    Parent parent=FXMLLoader.load(this.getClass().getResource("/lk/pos/hospital/view/DoctorVeryfyForm.fxml"));
                    Scene scene=new Scene(parent);
                    Stage stage=new Stage();
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.setTitle("Verification");
                    stage.getIcons().add(new Image("/lk/pos/hospital/assets/icons8_Hospital_30px.png"));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case "butPatient":

                    goToPage(anchorPane,"/lk/pos/hospital/view/PatientRegistryForm.fxml","MEDICA HOSPITAL");

                break;
            default:break;
        }
    }

        private void  goToPage(Pane pane, String path, String title){
        Parent root= null;
        try {
            root = FXMLLoader.load(this.getClass().getResource(path));
            Stage stage= (Stage) pane.getScene().getWindow();
            AnimationClass.navigateAll(root,stage,title,-1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
