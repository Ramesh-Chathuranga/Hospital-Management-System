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
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.pos.hospital.business.BOFactory;
import lk.pos.hospital.business.custom.ClinicBO;
import lk.pos.hospital.business.custom.SpecialFieldBO;
import lk.pos.hospital.controller.util.AnimationClass;
import lk.pos.hospital.dto.ClinicDTO;
import lk.pos.hospital.dto.ClinicTimeTableDTO;
import lk.pos.hospital.dto.SpecialFieldDTO;
import lk.pos.hospital.model.TBClinicDetail;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static lk.pos.hospital.controller.util.AnimationClass.dropShadow;
import static lk.pos.hospital.controller.util.AnimationClass.scaleTransition;

public class ClinicFormController implements Initializable {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Label labTitle;

    @FXML
    private Label labTitle1;

    @FXML
    private Label labClinicID;

    @FXML
    private JFXTextField txtClinic;

    @FXML
    private JFXTextField txtClinicFee;

    @FXML
    private JFXComboBox<String> comboSFID;

    @FXML
    private JFXButton butSave;

    @FXML
    private JFXButton butDelete;

    @FXML
    private JFXButton butAddSchedule;

    @FXML
    private JFXButton butshowShedule;

    @FXML
    private AnchorPane butGO;

    @FXML
    private Label labGo;
    @FXML
    private JFXDatePicker txtDate;

    @FXML
    private TableView<TBClinicDetail> tabClinic;

    private ClinicBO clinicBO;

    @FXML
    private JFXTextField txtSchedule;

    private SpecialFieldBO fieldBO;

    @FXML
    private JFXTextField txtSFName;

    @FXML
    private JFXButton butClearField;

    private static String sfField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       clinicBO= BOFactory.getInstance().getBo(BOFactory.BOType.CLINIC);
        fieldBO=BOFactory.getInstance().getBo(BOFactory.BOType.SPECIAL_FIELD);
        try {
              labGo.setVisible(false);
              butSave.setOpacity(0.5);
              butDelete.setOpacity(0.5);
              butAddSchedule.setOpacity(0.5);
              butshowShedule.setOpacity(0.5);
              txtDate.setEditable(false);
              txtDate.setValue(LocalDate.now());
              txtSFName.setEditable(false);
              labClinicID.setStyle("-fx-text-fill: white;-fx-font-size: 13PX;");
              labClinicID.setText(clinicBO.getRegisterID());
              tabClinic.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("clinicid"));
              tabClinic.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("clinicname"));
              tabClinic.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("specialfieldid"));
              tabClinic.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("specialfield"));
              tabClinic.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("fee"));
              tabClinic.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("scheduleid"));
              loadTable();
              loadcomboBox();
        } catch (Exception e) {
            e.printStackTrace();
        }

        tabClinic.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue==null){
                return;
            }
            txtClinicFee.setText(newValue.getFee()+"");
            txtClinic.setText(newValue.getClinicname());
            comboSFID.getSelectionModel().select(newValue.getSpecialfieldid());
            labClinicID.setText(newValue.getClinicid());
            txtSchedule.setText(newValue.getScheduleid());

        });

        comboSFID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue==null){return;}
            try {
                SpecialFieldDTO dto = fieldBO.searchSField(newValue);
                txtSFName.setText(dto.getFieldName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private  void loadcomboBox()throws Exception{
        comboSFID.getItems().removeAll(comboSFID.getItems());
        fieldBO.getAllSField().stream().filter(dto -> !tabClinic.getItems().stream().anyMatch(tbClinicDetail -> tbClinicDetail.getSpecialfieldid().equalsIgnoreCase(dto.getfId()))).forEach(dto -> {

                comboSFID.getItems().add(dto.getfId());


        });
    }

     private  void  loadTable() throws Exception {
         ObservableList<TBClinicDetail> list = FXCollections.observableArrayList();
        clinicBO.getALLForTable().stream().forEach(customDTO -> {
            list.add(new TBClinicDetail(customDTO.getCid(),customDTO.getClinicName(),customDTO.getFieldId(),customDTO.getField(),customDTO.getClinicCharge(),customDTO.getSceduleid()));
        });
        tabClinic.setItems(list);
     }
    @FXML
    void navigateTOAdmin(MouseEvent event) {
        try {
            toAdmin("/lk/pos/hospital/view/AdminForm.fxml","Admin Page");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void toAdmin(String path,String title) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource(path));
        Stage stage = (Stage) mainPane.getScene().getWindow();
        AnimationClass.navigateAll(root,stage,"title",1);
    }

    @FXML
    void onActionAddSchedule(ActionEvent event) {
        try {
           Parent load = FXMLLoader.load(this.getClass().getResource("/lk/pos/hospital/view/ClinicTimeTableForm.fxml"));
            Stage stage = (Stage) this.labTitle.getScene().getWindow();
            AnimationClass.navigateAll(load,stage,"Medic Private Hospital     Clinic  Schedule Maintenance Form",-1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionDelete(ActionEvent event) {
        String cid=labClinicID.getText();
        String schId=txtSchedule.getText();
        try {
            if(clinicBO.searchClinic(cid)==null){
                return;
            }
            clinicBO.deleteClinic(new ClinicTimeTableDTO(cid,schId));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                clearField();
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
        }
    }

    @FXML
    void onActionSave(ActionEvent event) {
        String cid=labClinicID.getText();
        String cname=txtClinic.getText();
        String sf=comboSFID.getValue();
        String fee=txtClinicFee.getText();
       if (cname.trim().isEmpty()){
            txtClinic.requestFocus();
            return;
        }else if(sf.trim().isEmpty()){
            comboSFID.requestFocus();
            return;
        }else if (fee.trim().isEmpty()){
            txtClinicFee.requestFocus();
            return;
        }

        Pattern selectDouble=Pattern.compile("^([0-9]+\\.?[0-9]+)$");
        Matcher fees=selectDouble.matcher(fee);
        if(!fees.matches()){
            txtClinicFee.requestFocus();
            System.out.println("set correct Double Value");
            return;
        }

        try {
            if(clinicBO.searchClinic(cid)==null){
                boolean saveClinic = clinicBO.saveClinic(new ClinicDTO(cid, cname, sf, Double.parseDouble(fee)));
                if(saveClinic){
                    System.out.println("clinic save");
                    return;
                }
            }else {
                boolean updateClinic = clinicBO.updateClinic(new ClinicDTO(cid, cname, sf, Double.parseDouble(fee)));
                if (updateClinic){
                    System.out.println("update");
                    return;
                }
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
    void onActionShowShedule(ActionEvent event) {

    }

    @FXML
    void onAnimationMouseEntered(MouseEvent event) {
        JFXButton button = (JFXButton) event.getSource();
        switch (button.getId()){
            case "butSave":
                butSave.setOpacity(1);
                butSave.setStyle("-fx-text-fill: white;-fx-border-color: black;-fx-border-width: 0px 0px 2px 0px;-fx-background-size: cover; -fx-background-color: orange ");
                break;
            case "butDelete":
                butDelete.setOpacity(1);
                butDelete.setStyle("-fx-text-fill: white;-fx-border-color: black;-fx-border-width: 0px 0px 2px 0px;-fx-background-size: cover; -fx-background-color: orange ");
                break;
            case "butshowShedule":
                butshowShedule.setOpacity(1);
                butshowShedule.setStyle("-fx-text-fill: white;-fx-border-color: black;-fx-border-width: 0px 0px 2px 0px;-fx-background-size: cover; -fx-background-color: orange ");
                break;
            case "butAddSchedule":
                butAddSchedule.setOpacity(1);
                butAddSchedule.setStyle("-fx-text-fill: white;-fx-border-color: black;-fx-border-width: 0px 0px 2px 0px;-fx-background-size: cover; -fx-background-color: orange ");
                break;
            case "butClearField":
                butClearField.setStyle("-fx-text-fill:#227f6f; -fx-border-color: #227f6f;-fx-border-width: 0px 0px 2px 0px ");
                break;
             default:break;
        }
    }

    @FXML
    void onAnimationMouseExited(MouseEvent event) {
        JFXButton button = (JFXButton) event.getSource();
        switch (button.getId()){
            case "butSave":
                butSave.setOpacity(0.5);
                butSave.setStyle("-fx-text-fill: black;-fx-border-color: transparent;-fx-border-width: 0px 0px 0px 0px;-fx-background-size: cover; -fx-background-color: brown ");
                break;
            case "butDelete":
                butDelete.setOpacity(0.5);
                butDelete.setStyle("-fx-text-fill: black;-fx-border-color: transparent;-fx-border-width: 0px 0px 0px 0px;-fx-background-size: cover; -fx-background-color: brown ");
                break;
            case "butshowShedule":
                butshowShedule.setOpacity(0.3);
                butshowShedule.setStyle("-fx-text-fill: black;-fx-border-color:transparent;-fx-border-width: 0px 0px 0px 0px;-fx-background-size: cover; -fx-background-color: brown ");
                break;
            case "butAddSchedule":
                butAddSchedule.setOpacity(0.3);
                butAddSchedule.setStyle("-fx-text-fill: black;-fx-border-color:transparent;-fx-border-width: 0px 0px 0px 0px;-fx-background-size: cover; -fx-background-color: brown ");
                break;
            case "butClearField":
                butClearField.setStyle("-fx-text-fill:White; -fx-border-color: transparent;-fx-border-width: 0px 0px 0px 0px ");
                break;
            default:break;
        }
    }

    @FXML
    void onMouseEnteredGOButtonAnimation(MouseEvent event) {
        AnchorPane anchorPane=(AnchorPane)event.getSource();
        labGo.setVisible(true);
       scaleTransition(anchorPane,1.2);
       dropShadow(anchorPane);
    }

    @FXML
    void onMouseExitedGOButtonAnimation(MouseEvent event) {
        AnchorPane anchorPane=(AnchorPane)event.getSource();
        labGo.setVisible(false);
        scaleTransition(anchorPane,1);
        anchorPane.setEffect(null);
    }


    @FXML
    void onActionClearController(ActionEvent event) {
        try {
            clearField();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearField() throws Exception {
        labClinicID.setText(clinicBO.getRegisterID());
        tabClinic.getSelectionModel().clearSelection();
        comboSFID.getSelectionModel().clearSelection();
        txtSFName.clear();
        txtClinicFee.clear();
        txtClinic.clear();
        txtSchedule.clear();
        loadTable();
    }
}
