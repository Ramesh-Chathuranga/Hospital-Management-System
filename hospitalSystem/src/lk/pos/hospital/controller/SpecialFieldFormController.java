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
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.pos.hospital.business.BOFactory;
import lk.pos.hospital.business.custom.SpecialFieldBO;
import lk.pos.hospital.business.custom.WardBO;
import lk.pos.hospital.controller.util.AnimationClass;
import lk.pos.hospital.dto.SpecialFieldDTO;
import lk.pos.hospital.model.TBSpecialField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static lk.pos.hospital.controller.util.AnimationClass.dropShadow;
import static lk.pos.hospital.controller.util.AnimationClass.scaleTransition;

public class SpecialFieldFormController implements Initializable {
    @FXML
    public TableView <TBSpecialField> tabSField;

    @FXML
    public JFXTextField txtWardName;

    @FXML
    private Label labTitle;

    @FXML
    private Label labTitle1;

    @FXML
    private AnchorPane butBack;

    @FXML
    private Label labBack;

    @FXML
    private JFXComboBox<String> comboWardId;

    @FXML
    private Label labSFID;

    @FXML
    private JFXTextField txtSFName;

    @FXML
    private JFXButton butAdding;

    @FXML
    private JFXButton butRemoving;

    @FXML
    private JFXButton butClearField;

    private WardBO wardBO;
    private SpecialFieldBO fieldBO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wardBO= BOFactory.getInstance().getBo(BOFactory.BOType.WARD);
        fieldBO=BOFactory.getInstance().getBo(BOFactory.BOType.SPECIAL_FIELD);
         labBack.setVisible(false);
         butAdding.setOpacity(0.4);
         butRemoving.setOpacity(0.4);
         txtWardName.setEditable(false);
         tabSField.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("fid"));
        tabSField.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("fname"));
        tabSField.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("wid"));
        try {
           labSFID.setText(fieldBO.generateSFieldID());
            loadComboBox();
            loadTable();
        } catch (Exception e) {
            e.printStackTrace();
        }

        tabSField.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue==null){return;}
            labSFID.setText(newValue.getFid());
            txtSFName.setText(newValue.getFname());
            comboWardId.setValue(newValue.getWid());
        });

        comboWardId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null==newValue||newValue.isEmpty()){return;}
            try {
                txtWardName.setText(wardBO.searchWard(newValue).getWardName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

   private  void loadComboBox() throws Exception {
        comboWardId.getItems().removeAll(comboWardId.getItems());
        wardBO.getAllWard().stream().forEach(wardDTO -> {
            comboWardId.getItems().add(wardDTO.getWardId());
        });
   }

   private  void loadTable() throws Exception {
       ObservableList<TBSpecialField> objects = FXCollections.observableArrayList();
       fieldBO.getAllSField().stream().forEach(dto -> {
           objects.add(new TBSpecialField(dto.getfId(),dto.getFieldName(),dto.getWardId()));
       });
       tabSField.setItems(objects);
   }

    @FXML
    void backTOAdmin(MouseEvent event) {
        try {
            Parent load = FXMLLoader.load(this.getClass().getResource("/lk/pos/hospital/view/AdminForm.fxml"));
            Stage stage = (Stage) this.butAdding.getScene().getWindow();
            String title="Admin Page";
            AnimationClass.navigateAll(load,stage,title,1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void butONEnteredAnim(MouseEvent event) {
        JFXButton jfxButton = (JFXButton) event.getSource();
        if(jfxButton.getId().equalsIgnoreCase("butAdding")){
            butAdding.setOpacity(1);
            butAdding.setStyle("-fx-background-color: green;-fx-text-fill: white;-fx-border-color: white;-fx-border-width: 0px 0px 3px 0px;");
        }else if(jfxButton.getId().equalsIgnoreCase("butRemoving")){
            butRemoving.setOpacity(1);
            butRemoving.setStyle("-fx-background-color: orange;-fx-text-fill: white;-fx-border-color: white;-fx-border-width: 0px 0px 3px 0px;");
        }else if(jfxButton.getId().equalsIgnoreCase("butClearField")){
            butClearField.setStyle("-fx-text-fill:#227f6f; -fx-border-color: #227f6f;-fx-border-width: 0px 0px 2px 0px ");
        }
    }

    @FXML
    void butONExitedAnim(MouseEvent event) {
        JFXButton jfxButton = (JFXButton) event.getSource();
        if(jfxButton.getId().equalsIgnoreCase("butAdding")){
            butAdding.setOpacity(0.4);
            butAdding.setStyle("-fx-background-color: red;-fx-text-fill: black;-fx-border-color: transparent;-fx-border-width: 0px 0px 0px 0px;");
        }else if(jfxButton.getId().equalsIgnoreCase("butRemoving")){
            butRemoving.setOpacity(0.4);
            butRemoving.setStyle("-fx-background-color: red;-fx-text-fill: black;-fx-border-color: transparent;-fx-border-width: 0px 0px 0px 0px;");
        }else if(jfxButton.getId().equalsIgnoreCase("butClearField")){
            butClearField.setStyle("-fx-text-fill:White; -fx-border-color: transparent;-fx-border-width: 0px 0px 0px 0px ");
        }

    }

    @FXML
    void mouseOnEnteredPane(MouseEvent event) {
        AnchorPane source = (AnchorPane) event.getSource();
        switch (source.getId()){
            case "butBack":
                labBack.setVisible(true);
                scaleTransition(source,1.15);
                dropShadow(source);
                break;
        }
    }

    @FXML
    void mouseOnExitedpane(MouseEvent event) {
        AnchorPane source = (AnchorPane) event.getSource();
        switch (source.getId()){
            case "butBack":
                labBack.setVisible(false);
                scaleTransition(source,1);
                butBack.setEffect(null);
                break;
        }
    }

    @FXML
    void onAddingSF(ActionEvent event) {
         String id=labSFID.getText();
         String name=txtSFName.getText();
         String wid=comboWardId.getValue();
         if(name.trim().isEmpty()){
             txtSFName.requestFocus();
             return;
         }else if(wid.isEmpty()){
             comboWardId.requestFocus();
             return;
         }

        try {
            if(fieldBO.searchSField(id)==null){
                fieldBO.saveSfield(new SpecialFieldDTO(id,name,wid));
            }else {
                fieldBO.updateSfield(new SpecialFieldDTO(id,name,wid));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            clearField();
        }
    }

    @FXML
    void onRemovingSF(ActionEvent event) {
        String id=labSFID.getText();
        try {
            if(fieldBO.searchSField(id)==null){
                return;
            }
            fieldBO.deleteSField(id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            clearField();
        }
    }

    @FXML
    void onActionClearWardController(ActionEvent event) {
          clearField();
    }

    private void clearField(){
        try {
            labSFID.setText(fieldBO.generateSFieldID());
            tabSField.getSelectionModel().clearSelection();
            txtSFName.clear();
            comboWardId.getSelectionModel().clearSelection();
            txtWardName.clear();
            loadTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
