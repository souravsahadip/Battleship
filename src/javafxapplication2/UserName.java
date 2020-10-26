/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author dipce
 */
public class UserName {
     static String u_name;
   
     AnchorPane root = new AnchorPane();
     Scene scene = new Scene(root, 1000, 600,Color.ALICEBLUE);
   
     Stage primaryStage;
     double x=0;
    int c = -200;
   ImageView i1;
   ImageView i2;
     public void updatecover(long time){
         c += 2;
        if(x<=1000&&c>0) {
            x+=2;
          i1.setTranslateX(-x);
          i2.setTranslateX(x);
         }
        }
     public UserName(Stage primaryStage){
         this.primaryStage=primaryStage;
         
       
     }
    Scene getscene(){
         
        scene.setCursor(Cursor.DEFAULT);
        scene.setFill(Color.BLACK);
        Label r = new Label( "Your Name: ");
        r.setLayoutX(300);
        r.setLayoutY(200);
        r.setMinWidth(50);
        r.setMinHeight(50);
        r.setFont(Font.font(24));
        r.setTextFill(Color.WHITE);
        TextField to = new TextField();
        to.setLayoutX(450);
        to.setLayoutY(200);
        to.setMinWidth(100);
        to.setMinHeight(60);
        to.setFont(Font.font(24));
        Text w = new Text();
        w.setLayoutX(350);
        w.setLayoutY(400);
        w.setFont(Font.font(24));
        w.setFill(Color.WHITE);
        Button btn = new Button();
        btn.setText("Start");
        btn.setCursor(Cursor.DEFAULT);
        btn.setLayoutX(500);
        btn.setLayoutY(300);
        btn.setFont(Font.font(24));
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                
                 
               u_name=to.getText();
              if(u_name.startsWith(" ")==false&&u_name.length()>0) { 
                MainWindow main = new MainWindow(primaryStage,u_name);
                main.sceneshow();
               }
              else{
                 w.setText("Invalid Username");
              }
        
                 
            }
        });
        
        root.getChildren().addAll(btn,r,to,w);
        root.setCursor(Cursor.DEFAULT);
        try{
            Image image1 = new Image(UserName.class.getResource("battleship1.png").toExternalForm());
           
    i1 = new ImageView(image1);
    i1.setFitHeight(600);
    i1.setFitWidth(500);
    i1.setLayoutX(0);
        root.getChildren().add(i1);
        }catch(Exception e){
            System.out.println(e);
        }
        try{
            Image image2 = new Image(UserName.class.getResource("battleship2.png").toExternalForm());
           
    i2 = new ImageView(image2);
    i2.setFitHeight(600);
    i2.setFitWidth(500);
     i2.setLayoutX(500);
        root.getChildren().add(i2);
        }catch(Exception e){
            System.out.println(e);
        }
        
       AnimationTimer timer=new AnimationTimer() {
       
            @Override
            public void handle(long now) {
                updatecover(now);
            }
        };
        timer.start();
        Image image = new Image(UserName.class.getResource("back.png").toExternalForm());
        
       BackgroundImage bgi = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background imgv = new Background(bgi);
        
        root.setBackground(imgv);
       return scene;
    }
    String getserver(){
       return Window1.s_name;
    }
    String getuser(){
        return Window1.u_name;
    }
    InetAddress getip(){
        return Window1.ipadress;
    }
    
    
}
