package lk.pos.hospital.controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import lk.pos.hospital.business.BOFactory;
import lk.pos.hospital.business.custom.ScheduleBO;
import lk.pos.hospital.controller.util.AnimationClass;
import lk.pos.hospital.dto.DoctorDTO;
import lk.pos.hospital.dto.ScheduleDTO;
import lk.pos.hospital.model.TBSchedule;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static lk.pos.hospital.controller.util.AnimationClass.*;

public class DoctorClinicFormController implements Initializable {
    @FXML
    private Label labTitle;

    @FXML
    private AnchorPane butGO;

    @FXML
    private Label labGo;

    @FXML
    private JFXTextField txtDID;

    @FXML
    private JFXTextField txtDName;

    @FXML
    private TableView<TBSchedule> tabDoctorClinicShedule;
    ScheduleBO scheduleBO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scheduleBO= BOFactory.getInstance().getBo(BOFactory.BOType.SCHEDULE);
      labGo.setVisible(false);
       butGO.setOpacity(0.5);
       txtDID.setEditable(false);
       txtDName.setEditable(false);
        tabDoctorClinicShedule.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("scheduleid"));
        tabDoctorClinicShedule.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("day"));
        tabDoctorClinicShedule.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("date"));
        tabDoctorClinicShedule.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("starttime"));
        tabDoctorClinicShedule.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("endtime"));
        try {
            loadTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void  getItemFromProfile(DoctorDTO dto){
        txtDID.setText(dto.getDrId());
        txtDName.setText(dto.getFullName());
        try {
            loadTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTable()throws Exception{
        ObservableList<TBSchedule> list = FXCollections.observableArrayList();
        scheduleBO.getSchedulesOfDoctor(txtDID.getText()).stream().forEach(dto -> {
            list.add(new TBSchedule(dto.getsId(),dto.getScheduleName(),dto.getDay(),dto.getDate(),dto.getStartTime(),dto.getEndTime()));
        });
        tabDoctorClinicShedule.setItems(list);
    }

    @FXML
    void navigate(MouseEvent event) {
        String id=txtDID.getText();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/pos/hospital/view/DoctorProfileForm.fxml"));
            Parent root=fxmlLoader.load();
            DoctorProfileFormController controller=fxmlLoader.getController();
            controller.getDoctorVerificationResult(id);
            Stage stage = (Stage) this.txtDName.getScene().getWindow();
            AnimationClass.navigateAll(root,stage,"Medic Private Hospital",1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void onAnimationMouseEntered(MouseEvent event) {
        Node node = (Node) event.getSource();
        switch (node.getId()){
        case "butGO":
        butGO.setOpacity(1);
        labGo.setVisible(true);
        scaleTransition(node,1.12);
        dropShadow(node);
        break;

    }
    }

    @FXML
    void onAnimationMouseExited(MouseEvent event) {
        Node node = (Node) event.getSource();
        switch (node.getId()){

            case "butGO":
                butGO.setOpacity(0.5);
                labGo.setVisible(false);
                scaleTransition(node,1);
                dropShadow(node);
                break;

        }
    }
}
