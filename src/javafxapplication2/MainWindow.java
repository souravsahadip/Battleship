/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import Util.NetworkConnection;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
public class MainWindow {
     static String u_name;
    // static String s_name;
     //static InetAddress ipadress;
     static int flag=0;
     int myport;
     AnchorPane root = new AnchorPane();
     Scene scene = new Scene(root, 1000, 600,Color.BLACK);
   boolean alive;
     Stage primaryStage;
     public MainWindow(Stage primaryStage,String s){
         this.primaryStage=primaryStage;
         this.u_name = s;
       
        
     }
    Scene getscene(){
        scene.setCursor(Cursor.DEFAULT);
        scene.setFill(Color.BLACK);
        
        Button btn1 = new Button();
        btn1.setText("Create Game");
        btn1.setCursor(Cursor.DEFAULT);
        btn1.setLayoutX(400);
        btn1.setLayoutY(200);
        btn1.setFont(Font.font(24));
        
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                try {
                    System.out.println("Button 1 has been pressed");
                    InetAddress ipadress = InetAddress.getLocalHost(); 
                    String ipadr=ipadress.toString();
                    String ip=ipadr.substring(ipadr.indexOf("/")+1);
                    ServerMain server = new ServerMain();
                    NetworkConnection nc=new NetworkConnection(ip.toString(),12345);
                    System.out.println(ip.toString());
                    nc.write(u_name+": I want to play");
                    
                    PlayerWindow pw=new PlayerWindow(primaryStage, u_name, u_name, InetAddress.getLocalHost(),nc);
                    Clientmain client = new Clientmain(pw,nc);
                    pw.sceneshow();
                   
                } catch (Exception ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
              }
        });
        
         Button btn2 = new Button();
        btn2.setText(" Join Game ");
        btn2.setCursor(Cursor.DEFAULT);
        btn2.setLayoutX(400);
        btn2.setLayoutY(400);
        btn2.setFont(Font.font(24));
        
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Window1 w1= new Window1(primaryStage,u_name);
                w1.sceneshow();
            }
        }
);
       
        root.getChildren().addAll(btn1,btn2);
        root.setCursor(Cursor.DEFAULT);
        Image image = new Image(MainWindow.class.getResource("back.png").toExternalForm());
        
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
    int getflag(){
        return flag;
    }
    void sceneshow(){
       
        primaryStage.setScene(this.getscene());
        
    }
    
}
