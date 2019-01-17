package lk.pos.hospital.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import lk.pos.hospital.business.custom.BedBO;
import lk.pos.hospital.business.custom.WardBO;
import lk.pos.hospital.controller.util.AnimationClass;
import lk.pos.hospital.dto.BedDTO;
import lk.pos.hospital.dto.WardDTO;
import lk.pos.hospital.model.TBBed;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static lk.pos.hospital.controller.util.AnimationClass.dropShadow;
import static lk.pos.hospital.controller.util.AnimationClass.scaleTransition;

public class BEDFormController implements Initializable {

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private JFXComboBox<String> txtWard;

    @FXML
    private JFXTextField txtBed;

    @FXML
    private TableView<TBBed> tabWardBED;

    @FXML
    private JFXButton butADD;

    @FXML
    private JFXButton butDelete;

    @FXML
    private AnchorPane butGO;

    @FXML
    private Label labGo;

    private BedBO bedBO;

    private WardBO wardBO;

    @FXML
    private JFXTextField txtWardName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            bedBO= BOFactory.getInstance().getBo(BOFactory.BOType.BED);
            wardBO= BOFactory.getInstance().getBo(BOFactory.BOType.WARD);
            labGo.setVisible(false);
            butADD.setOpacity(0.3);
            butDelete.setOpacity(0.3);
            txtBed.setEditable(false);
            txtWardName.setEditable(false);
            txtBed.setText(bedBO.generateBEDID());
            tabWardBED.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("wardid"));
            tabWardBED.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("wardname"));
            tabWardBED.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("bedid"));
            loadTable();
            loadComboBox();
            txtWard.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue==null){
                    return;
                }
                String id=newValue;
                try {
                    txtWardName.setText(wardBO.searchWard(id).getWardName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            tabWardBED.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue==null){
                    return;
                }
                txtBed.setText(newValue.getBedid());
                txtWard.getSelectionModel().select(newValue.getWardid());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadTable() throws Exception {
        ObservableList<TBBed> list = FXCollections.observableArrayList();

            List<BedDTO> allbed = bedBO.getALLBED();
            for (BedDTO bedDTO:allbed) {
                list.add(new TBBed(bedDTO.getWardID(),wardBO.searchWard(bedDTO.getWardID()).getWardName(),bedDTO.getBedID()));
            }
            tabWardBED.setItems(list);

    }

    private void loadComboBox() throws Exception {
        txtWard.getItems().removeAll(tabWardBED.getItems());
        List<WardDTO> allWard = wardBO.getAllWard();
        for (WardDTO dto:allWard) {
            txtWard.getItems().add(dto.getWardId());
        }
    }

    @FXML
    void bedRemove(ActionEvent event) {
        String bid=txtBed.getText();
        try {
            BedDTO bedDTO = bedBO.searchBED(bid);
            if(bedDTO==null){
                System.out.println("this bed no not exist");
                return;
            }else {
                if(bedDTO.getPatientID()==null){
                    bedBO.deleteBED(bid);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
           filter();
        }
    }

    public void BedSave(ActionEvent actionEvent) {
      String bid=txtBed.getText();
      String wardId=txtWard.getValue();
     try {
         if (bid.isEmpty()) {
             txtBed.requestFocus();
             return;
         } else if (wardId.isEmpty()) {
             txtWard.requestFocus();
             return;
         }

         if(bedBO.getALLBED().isEmpty()){
             boolean isSave = bedBO.addBED(new BedDTO(bid, wardId,""));
             if(isSave){
                 //message
                 System.out.println("save bed");
             }
         }

         if (bedBO.searchBED(bid)==null) {
             System.out.println(2);
             boolean isSave = bedBO.addBED(new BedDTO(bid, wardId,""));
             if(isSave){
                 //message
                 System.out.println("save bed");
             }else {
                 System.out.println(3);
             }
         }else {
            if(bedBO.searchBED(bid).getPatientID()==null){
                boolean isupDateBED = bedBO.upDateBEDWithOUTPatient(new BedDTO(bid, wardId,""));
                if(isupDateBED){
                    System.out.println("bed update");
                }
            }
         }
     }catch (Exception e) {
          e.printStackTrace();
     }finally {
       filter();
     }
    }

    private void filter(){

        try {
            loadTable();
            txtWard.getSelectionModel().clearSelection();
            txtWardName.clear();
            txtBed.clear();
            txtBed.setText(bedBO.generateBEDID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void navigateTOWard(MouseEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(this.getClass().getResource("/lk/pos/hospital/view/WardForm.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) this.mainAnchorPane.getScene().getWindow();
        AnimationClass.navigateAll(root,stage," MEDIC HOSPITAL \t\t\t WARD FORM",1);
    }

    @FXML
    void mouseEnteredAnimation(MouseEvent event) {
        JFXButton source = (JFXButton) event.getSource();
         switch (source.getId()){
             case "butDelete":
                 butDelete.setOpacity(1);
                 butDelete.setStyle("-fx-border-color: black;-fx-border-width: 0px 0px 2px 0px");
                 break;
             case "butADD":
                 butADD.setOpacity(1);
                 butADD.setStyle("-fx-border-color: black;-fx-border-width: 0px 0px 2px 0px");
                 break;
         }
    }

    @FXML
    void mouseExitedAnimation(MouseEvent event) {
        JFXButton source = (JFXButton) event.getSource();
        switch (source.getId()){
            case "butDelete":
                butDelete.setOpacity(0.3);
                butDelete.setStyle("-fx-border-color: black;-fx-border-width: 0px 0px 0px 0px");
                break;
            case "butADD":
                butADD.setOpacity(0.3);
                butADD.setStyle("-fx-border-color: black;-fx-border-width: 0px 0px 0px 0px");
                break;
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



}
