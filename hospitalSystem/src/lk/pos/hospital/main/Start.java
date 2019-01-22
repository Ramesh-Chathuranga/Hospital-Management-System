package lk.pos.hospital.main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import lk.pos.hospital.controller.util.AnimationClass;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Start extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent parent= FXMLLoader.load(this.getClass().getResource("/lk/pos/hospital/view/DashBoard.fxml"));
        Scene scene = navegation(parent, primaryStage);
        AnimationClass.animation_maker(parent,-scene.getWidth(),4000,2000);
    }

    private Scene navegation(Parent parent,Stage primaryStage){
     //   loaderrorReport();
        Scene scene=new Scene(parent);
        primaryStage.getIcons().add(new Image("/lk/pos/hospital/assets/icons8_Hospital_30px.png"));
        primaryStage.setTitle(" MEDIC HOSPITAL");
        primaryStage.setScene(scene);
        primaryStage.show();
        return scene;
    }

//    private void loaderrorReport(){
//        FileHandler fileHandler = null;
//        try {
//            fileHandler = new FileHandler("hospitalSystem/error report/error.log", true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        fileHandler.setFormatter(new SimpleFormatter());
//        Logger.getLogger("").addHandler(fileHandler);
//    }
}
