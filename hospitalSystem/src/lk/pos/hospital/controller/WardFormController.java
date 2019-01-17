package lk.pos.hospital.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ObservableValue;
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
import lk.pos.hospital.business.custom.WardBO;
import lk.pos.hospital.controller.util.AnimationClass;
import lk.pos.hospital.dto.WardDTO;
import lk.pos.hospital.model.TBWard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static lk.pos.hospital.controller.util.AnimationClass.dropShadow;
import static lk.pos.hospital.controller.util.AnimationClass.scaleTransition;

public class WardFormController implements Initializable {

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtNAME;

    @FXML
    private TableView<TBWard> tableWARD;

    @FXML
    private JFXButton butBED;

    @FXML
    private JFXButton butSave;

    @FXML
    private JFXButton butDELETE;

    @FXML
    private AnchorPane butGO;

    @FXML
    private Label labGo;

    private WardBO wardBO;

    @FXML
    private JFXButton butClearField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       wardBO= BOFactory.getInstance().getBo(BOFactory.BOType.WARD);
      butBED.setOpacity(0.3);
      butDELETE.setOpacity(0.3);
      butSave.setOpacity(0.3);
      labGo.setVisible(false);
      txtID.setEditable(false);
        try {
            txtID.setText(wardBO.getGenerateWardID());
        } catch (Exception e) {
            e.printStackTrace();
        }
        tableWARD.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("wid"));
      tableWARD.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("wname"));
      loadTable();

      tableWARD.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends TBWard> observable, TBWard oldValue, TBWard newValue)->{
          if(newValue==null){
              return;
          }
          txtID.setText(newValue.getWid());
          txtNAME.setText(newValue.getWname());
      });

    }

    private void loadTable(){
        try {
            ObservableList<TBWard> list = FXCollections.observableArrayList();
            ArrayList<WardDTO> wardDTOS = (ArrayList<WardDTO>) wardBO.getAllWard();
            for (WardDTO dto:wardDTOS) {
                list.add(new TBWard(dto.getWardId(),dto.getWardName()));
            }
          tableWARD.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goToBED(ActionEvent event) {
        System.out.println("ok bn");
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/pos/hospital/view/BEDForm.fxml"));
            Stage stage=(Stage)this.mainAnchorPane.getScene().getWindow();
            AnimationClass.navigateAll(root,stage,"BED FORM",-1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void mouseAnimationOnEnter(MouseEvent event) {
        JFXButton source = (JFXButton) event.getSource();
        switch (source.getId()){
            case "butBED":
                butBED.setOpacity(1);
                butBED.setStyle("-fx-border-color: black;-fx-border-width: 0px 0px 2px 0px");
                break;
            case "butDELETE":
                butDELETE.setOpacity(1);
                butDELETE.setStyle("-fx-border-color: black;-fx-border-width: 0px 0px 2px 0px");
                break;
            case "butSave":
                butSave.setOpacity(1);
                butSave.setStyle("-fx-border-color: black;-fx-border-width: 0px 0px 2px 0px");
                break;
            case "butClearField":
                butClearField.setStyle("-fx-text-fill:#227f6f; -fx-border-color: #227f6f;-fx-border-width: 0px 0px 2px 0px ");
                break;
        }
    }

    @FXML
    void mouseAnimationOnExited(MouseEvent event) {
        JFXButton source = (JFXButton) event.getSource();
        switch (source.getId()){
            case "butBED":
                butBED.setOpacity(0.3);
                butBED.setStyle("-fx-border-color: transparent;-fx-border-width: 0px 0px 0px 0px");
                break;
            case "butDELETE":
                butDELETE.setOpacity(0.3);
                butDELETE.setStyle("-fx-border-color:transparent;-fx-border-width: 0px 0px 0px 0px");
                break;
            case "butSave":
                butSave.setOpacity(0.3);
                butSave.setStyle("-fx-border-color:transparent;-fx-border-width: 0px 0px 0px 0px");
                break;
            case "butClearField":
                butClearField.setStyle("-fx-text-fill:White; -fx-border-color: transparent;-fx-border-width: 0px 0px 0px 0px ");
                break;
        }
    }

    @FXML
    void onActionDeleteWard(ActionEvent event) {
        try {
              if(tableWARD.getItems().isEmpty()){
               return;
               }else if(wardBO.searchWard(txtID.getText())==null){
                       return;
               }
            boolean isDelete = wardBO.deleteWard(txtID.getText());
              if(isDelete){
                  System.out.println("delete");
              }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            loadTable();
            clear();
        }
    }

    @FXML
    void onActionSaveWard(ActionEvent event) {
        String id = txtID.getText().trim();
        String name=txtNAME.getText().trim();
        if(id.isEmpty()){
            txtID.requestFocus();
            return;
        }else if(name.isEmpty()){
            txtNAME.requestFocus();
            return;
        }
        try {
            List<WardDTO> allWard = wardBO.getAllWard();
            if(allWard.isEmpty()){
                boolean isADD = wardBO.addWard(new WardDTO(id, name));
                if(isADD){
                    //message
                    System.out.println("ok");
                }
            }else{
                WardDTO wardDTO = wardBO.searchWard(id);
                if(wardDTO==null){
                    boolean isADD = wardBO.addWard(new WardDTO(id, name));
                    if(isADD){
                        //message
                        System.out.println("ok");
                    }
                }else {
                    boolean isUpdate = wardBO.upDateWard(new WardDTO(id, name));
                    if(isUpdate){
                        //message
                        System.out.println("update ok");
                    }
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            loadTable();
            clear();
        }

    }


    @FXML
    void onMouseEntered(MouseEvent event) {
       labGo.setVisible(true);
        AnchorPane pane = (AnchorPane) event.getSource();
        scaleTransition(pane,1.2);
        dropShadow(pane);
    }

    @FXML
    void onMouseExited(MouseEvent event) {
       labGo.setVisible(false);
        AnchorPane pane = (AnchorPane) event.getSource();
        scaleTransition(pane,1.0);
        pane.setEffect(null);
    }

    @FXML
    void navigateTOAdminForm(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/pos/hospital/view/AdminForm.fxml"));
            Stage stage = (Stage) this.mainAnchorPane.getScene().getWindow();
            AnimationClass.navigateAll(root,stage,"Admin Page",1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onActionClearWardController(ActionEvent event) {
        clear();
    }

    private void clear(){
        txtNAME.clear();
        txtID.clear();
        try {
            txtID.setText(wardBO.getGenerateWardID());
        } catch (Exception e) {
            e.printStackTrace();
        }
        tableWARD.getSelectionModel().clearSelection();
    }
}
