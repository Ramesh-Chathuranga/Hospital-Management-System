package lk.pos.hospital.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.pos.hospital.business.BOFactory;
import lk.pos.hospital.business.custom.DoctorRegBO;
import lk.pos.hospital.controller.util.AnimationClass;
import lk.pos.hospital.controller.util.Validation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static lk.pos.hospital.controller.util.AnimationClass.dropShadow;
import static lk.pos.hospital.controller.util.AnimationClass.scaleTransition;

public class DoctorVeryfyFormController implements Initializable {

    @FXML
    private Label labTitle;

    @FXML
    private Label labTitle1;

    @FXML
    private JFXComboBox<String> txtName;

    @FXML
    private JFXPasswordField txtNic;

    @FXML
    private JFXButton butLogIn;

    @FXML
    private AnchorPane butReturn;

    @FXML
    private Label labGo;

    private DoctorRegBO regBO;

    private static String nic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        regBO= BOFactory.getInstance().getBo(BOFactory.BOType.DOCTOR);
        butLogIn.setOpacity(0.6);
        labGo.setVisible(false);
        try {
            loadComboBox();
           txtName.getSelectionModel().select(0);
        } catch (Exception e) {
        //    e.printStackTrace();
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }

        txtName.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue==null){
                return;
            }
            try {
                nic=regBO.searchDoctor(newValue).getNic();
                System.out.println(nic);
            } catch (Exception e) {
             //   e.printStackTrace();
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
        });
    }


    void loadComboBox()throws Exception{
        txtName.getItems().removeAll(txtName.getItems());
        regBO.getAllDoctor().stream().forEach(dto -> {
            txtName.getItems().add(dto.getDrId());
        });
    }


    @FXML
    void OnMouseEnteredOnLogin(MouseEvent event) {
        JFXButton button = (JFXButton) event.getSource();
        if(button.getId().equalsIgnoreCase("butLogIn")){
            butLogIn.setOpacity(1);
            butLogIn.setStyle("-fx-text-fill: White;-fx-border-color: white;-fx-border-width: 0px 0px 2px 0px;");
            scaleTransition(button,1.2);
            dropShadow(button);
        }
    }

    @FXML
    void OnMouseExitedOnLogin(MouseEvent event) {
        JFXButton button = (JFXButton) event.getSource();
        if(button.getId().equalsIgnoreCase("butLogIn")){
            butLogIn.setOpacity(0.6);
            butLogIn.setStyle("-fx-text-fill: black;-fx-border-color: transparent;-fx-border-width :0px 0px 0px 0px;");
            scaleTransition(button,1);
            dropShadow(button);
        }
    }


    @FXML
    void loginOnAction(ActionEvent event) {
          if(txtName.getValue().isEmpty()){
             txtName.requestFocus();
              return;
          }
         String text=txtNic.getText();
        boolean nic1 = Validation.isNIC(text);
        if(!nic1){
            txtNic.requestFocus();
            return;
        }
            if(nic.equalsIgnoreCase(text)){
               try {
                   FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/pos/hospital/view/DoctorProfileForm.fxml"));
                   Parent root=fxmlLoader.load();
                   DoctorProfileFormController controller=fxmlLoader.getController();
                   controller.getDoctorVerificationResult(txtName.getValue());
                   Stage stage = (Stage) this.butLogIn.getScene().getWindow();
                   AnimationClass.navigateAll(root,stage,"Medic Private Hospital",-1);
               } catch (IOException e) {
                  // e.printStackTrace();
                   Logger.getLogger("").log(Level.SEVERE, null, e);

               }
           }
    }

    @FXML
    void navigateTODashBoard(MouseEvent event) {

        Stage window = (Stage) this.labGo.getScene().getWindow();
        window.close();
    }

    @FXML
    void onMouseEnteredreturnButtonAnimation(MouseEvent event) {
        AnchorPane pane = (AnchorPane) event.getSource();
        if(pane.getId().equalsIgnoreCase("butReturn")){
           labGo.setVisible(true);
           scaleTransition(pane,1.10);
           dropShadow(pane);
        }
    }

    @FXML
    void onMouseExitedretunButtonAnimation(MouseEvent event) {
        AnchorPane pane = (AnchorPane) event.getSource();
        if(pane.getId().equalsIgnoreCase("butReturn")){
            labGo.setVisible(false);
            scaleTransition(pane,1);
            dropShadow(pane);
        }
    }

}
