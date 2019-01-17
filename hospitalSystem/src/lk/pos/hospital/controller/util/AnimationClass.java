package lk.pos.hospital.controller.util;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimationClass {

    public static void scaleTransition(Object pane, double v){
        ScaleTransition transition=new ScaleTransition(Duration.millis(1000), (Node) pane);
        transition.setToX(v);
        transition.setToY(v);
        transition.play();

    }

    public static void dropShadow(Node pane){

        DropShadow dropShadow=new DropShadow();
        dropShadow.setColor(Color.CORNFLOWERBLUE);
        dropShadow.setWidth(30);
        dropShadow.setHeight(30);
        dropShadow.setRadius(30);
        pane.setEffect(dropShadow);
    }

    public static void animation_maker(Parent root, double transitionbegingPlace,double fadeTime,double transitonTime){
        FadeTransition fadeTransition=new FadeTransition(Duration.millis(fadeTime),root);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(10);
        fadeTransition.play();

        TranslateTransition translateTransition=new TranslateTransition(Duration.millis(transitonTime),root);
        translateTransition.setFromX(transitionbegingPlace/2);
        translateTransition.setToX(0);
        translateTransition.play();
    }

    public static void navigateAll(Parent root, Stage stage, String title, double minusOrPlus){

          Scene scene = new Scene(root);
          stage.setScene(scene);
          stage.setTitle(title);
          stage.setResizable(false);
          stage.getIcons().add(new Image("/lk/pos/hospital/assets/icons8_Hospital_30px.png"));
          stage.show();
          animation_maker(root, (scene.getHeight() / 2)*minusOrPlus, 3000, 1000);

    }
}
