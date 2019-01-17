package lk.pos.hospital.controller;

import com.jfoenix.controls.JFXTextArea;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.pos.hospital.business.BOFactory;
import lk.pos.hospital.business.custom.PatientBO;
import lk.pos.hospital.controller.util.AnimationClass;
import lk.pos.hospital.model.TBPatientInfo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static lk.pos.hospital.controller.util.AnimationClass.dropShadow;
import static lk.pos.hospital.controller.util.AnimationClass.scaleTransition;

public class PatientDetailFormController implements Initializable {

    @FXML
    private Label labTitle;

    @FXML
    private Label labTitle1;

    @FXML
    private AnchorPane butBack;

    @FXML
    private Label labBack;

    @FXML
    private JFXTextField txtPID;

    @FXML
    private JFXTextField txtClinicID;

    @FXML
    private JFXTextArea txtDescription;

    @FXML
    private TableView<TBPatientInfo> tabPatientDetail;

    private PatientBO patientBO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        patientBO= BOFactory.getInstance().getBo(BOFactory.BOType.PATIENT);
     labBack.setVisible(false);
     txtClinicID.setEditable(false);
     txtDescription.setEditable(false);
     txtPID.setEditable(false);

        tabPatientDetail.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("cid"));
        tabPatientDetail.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("pid"));
        tabPatientDetail.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));
        try {
            loadTable();
        } catch (Exception e) {
            e.printStackTrace();
        }

        tabPatientDetail.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           if (newValue==null){
               return;
           }
           txtPID.setText(newValue.getPid());
           txtClinicID.setText(newValue.getCid());
           txtDescription.setText(newValue.getDescription());
        });
    }
   void  loadTable()throws Exception{
       ObservableList<TBPatientInfo> list = FXCollections.observableArrayList();
       patientBO.getAllPatientDetail().stream().forEach(dto -> {
           list.add(new TBPatientInfo(dto.getPatientId(),dto.getClinicId(),dto.getNote()));
       });
       tabPatientDetail.setItems(list);
   }

    @FXML
    void backTOAdminForm(MouseEvent event) {
        try {
            Parent load = FXMLLoader.load(this.getClass().getResource("/lk/pos/hospital/view/AdminForm.fxml"));
            AnimationClass.navigateAll(load,(Stage)txtPID.getScene().getWindow(),"Admin Page",1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void mouseOnEnteredPaneAnimation(MouseEvent event) {
        AnchorPane source = (AnchorPane) event.getSource();
        switch (source.getId()){
            case "butBack":
                labBack.setVisible(true);
                scaleTransition(source,1.6);
                dropShadow(source);
                break;
            default:break;
        }
    }

    @FXML
    void mouseOnExitedpaneAnimation(MouseEvent event) {
        AnchorPane source = (AnchorPane) event.getSource();
        switch (source.getId()){
            case "butBack":
                labBack.setVisible(false);
                scaleTransition(source,1);
                source.setEffect(null);
                break;
            default:break;
        }
    }


}
