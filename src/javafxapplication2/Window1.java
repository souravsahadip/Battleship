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
import java.util.Random;
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
import static javafxapplication2.MainWindow.u_name;
//import static javafxapplication2.MainWindow.u_name;

/**
 *
 * @author dipce
 */
public class Window1 {
     static String u_name;
     static String s_name;
     static InetAddress ipadress;
     static int flag=0;
     int myport;
     AnchorPane root = new AnchorPane();
     Scene scene = new Scene(root, 1000, 600,Color.BLACK);
   
     Stage primaryStage;
     public Window1(Stage primaryStage,String s){
         this.primaryStage=primaryStage;
         this.u_name= s;
        
     }
    Scene getscene(){
        scene.setCursor(Cursor.DEFAULT);
       
       
        Label IP = new Label("Server IP: ");
        IP.setLayoutX(300);
        IP.setLayoutY(150);
        IP.setMinWidth(50);
        IP.setMinHeight(50);
        IP.setFont(Font.font(24));
        IP.setTextFill(Color.WHITE);
        Label lport = new Label( "Server Port: ");
        lport.setLayoutX(300);
        lport.setLayoutY(300);
        lport.setMinWidth(50);
        lport.setMinHeight(50);
        lport.setFont(Font.font(24));
        lport.setTextFill(Color.WHITE);
        TextField ip = new TextField();
        ip.setLayoutX(450);
        ip.setLayoutY(150);
        ip.setMinWidth(100);
        ip.setMinHeight(60);
        ip.setFont(Font.font(24));
        TextField port = new TextField();
        port.setLayoutX(450);
        port.setLayoutY(300);
        port.setMinWidth(100);
        port.setMinHeight(60);
        port.setFont(Font.font(24));
        Button btn = new Button();
        btn.setText("Join");
        btn.setCursor(Cursor.DEFAULT);
        btn.setLayoutX(500);
        btn.setLayoutY(400);
        btn.setFont(Font.font(24));
        Text connect = new Text();
        connect.setLayoutX(350);
        connect.setLayoutY(500);
        connect.setFont(Font.font(24));
        connect.setFill(Color.WHITE);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
               
               
                flag=1;
                System.out.println("Join has been pressed");
               
          
             try{  
                  ipadress=InetAddress.getByName(ip.getText());
                   int serverport = Integer.parseInt(port.getText() );
                      System.out.println(s_name+" "+u_name+" "+ipadress+" "+serverport);
                  NetworkConnection nc=new NetworkConnection(ip.getText(),serverport);
                 
                nc.write(u_name+": I want to play");
                 PlayerWindow pw = new PlayerWindow(primaryStage,s_name,u_name,ipadress,nc);
                 if(nc.socket.isConnected()==true)
                     new Clientmain(pw,nc);
              
                    pw.sceneshow();
                     
                  } catch (Exception ex) {
                      System.out.println(ex);
                      connect.setText("Could not connect the Server");
            }
        
            }
        });
        
        
        
        root.setCursor(Cursor.DEFAULT);
        
      root.getChildren().addAll(btn,IP,ip,port,lport,connect);
      Image image = new Image(Window1.class.getResource("back.png").toExternalForm());
        
       BackgroundImage bgi = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background imgv = new Background(bgi);
        
        root.setBackground(imgv);
       return scene;
    }
    
     void sceneshow(){
         
        primaryStage.setScene(this.getscene());
        
    }
    
}
