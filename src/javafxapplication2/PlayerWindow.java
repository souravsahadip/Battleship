/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import Util.Data;
import Util.NetworkConnection;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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
public class PlayerWindow {
    public Gamewindow gamewindow;
     AnchorPane root = new AnchorPane();
      AnchorPane root2 = new AnchorPane();
     Scene scene = new Scene(root, 1000, 600,Color.BLACK);
     Scene scene2 ;
     Stage primaryStage;
     NetworkConnection nc;
     String s_name;
     public static String oppo= new String();
     String u_name;
     static int[] click=new int[5];
     InetAddress ipaddress;
     static int btncount=0;
     boolean startpermission;
     Text wait=new Text();
     Text accept=new Text();
    Text[] btn=new Text[5];
    //RadioButton[] btn = new RadioButton[10];
    Button start=new Button("Start!");
    Button yes=new Button("Yes");
    Button no=new Button("No");
     static double stat_y;
     public PlayerWindow(Stage primaryStage,String s_name,String u_name,InetAddress ipaddress,NetworkConnection nc){
         this.primaryStage=primaryStage;
         this.s_name = s_name;
         this.u_name = u_name;
         this.ipaddress=ipaddress;
         this.nc=nc;
     }
    Scene getscene(){
        scene.setCursor(Cursor.DEFAULT);
       
        
       wait.setText("Available Players");
       wait.setLayoutX(400);
       wait.setLayoutY(40);
       wait.setFont(Font.font(24));
       wait.setFill(Color.WHITE);
       root.getChildren().add(wait);
        for(int j=0;j<5;j++){
            btn[j]=new Text();
            btn[j].setFont(Font.font(22) );
            btn[j].setLayoutX(100);
             btn[j].setLayoutY(120+40*j);
             btn[j].setVisible(false);
        root.getChildren().add(btn[j]);
        }
       
        root.setCursor(Cursor.DEFAULT);
        System.out.println("startpermission="+startpermission);
        
        start.setLayoutX(500);
        start.setLayoutY(550);
        
        start.setVisible(false);
            start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gamewindow=new Gamewindow(primaryStage,u_name,nc,oppo);
                gamewindow.sceneshow();
            }
            });
            root.getChildren().add(start);
            
      Image image = new Image(PlayerWindow.class.getResource("back.png").toExternalForm());
        
       BackgroundImage bgi = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background imgv = new Background(bgi);
        
        root.setBackground(imgv);
        accept.setLayoutX(300);
        accept.setLayoutY(400);
        accept.setFont(Font.font(24));
        accept.setFill(Color.WHITE);
        root.getChildren().add(accept);
        yes.setLayoutX(300);
        yes.setLayoutY(450);
        yes.setFont(Font.font(20));
        yes.setVisible(false);
        yes.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                String s = accept.getText();
                System.out.println("accept.getText()="+accept.getText());
               if(s.contains("?")==true) {
                   String opponent = s.substring(18,s.indexOf("?"));
                    Data data=new Data();
            data.message=u_name+",opponent:"+opponent;
            
            oppo=opponent;
            yes.setText("");
            yes.setVisible(false);
            //no.setText("");
            no.setVisible(false);
            accept.setText("");
                try {
                    
                        nc.write(data.clone());
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(PlayerWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                }
            }
        });
        root.getChildren().add(yes);
        no.setLayoutX(500);
        no.setLayoutY(450);
        no.setFont(Font.font(20));
        no.setVisible(false);
        no.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                no.setText("");
                no.setVisible(false);
                 yes.setText("");
            yes.setVisible(false);
            accept.setText("");
            }
            
        });
        root.getChildren().add(no);
       return scene;
    }
   public void sceneshow(){
       
         primaryStage.setScene(this.getscene());
        
    }
   
   public void start(){
        startpermission=true;
         
        if(startpermission==true){
            
          System.out.println("GameWindow started");
          start.setFont(Font.font(24));
            start.setVisible(true);
       }
         
    }
    public void playwith(String s){
        String opponent = s.substring(18,s.indexOf("?"));
        if(oppo.equals(opponent)==false){
            accept.setText(s);
            no.setText("No");
            no.setVisible(true);
         yes.setText("Yes");
         yes.setVisible(true);
         
         
        }
    }
   public void update(String name){
        System.out.println("Starting Update");
        
        System.out.println(name);
        System.out.println(u_name);
      if(name.equals(u_name)==false)  {
          btn[btncount].setText(name);
          btn[btncount].setFill(Color.WHITE);
        btn[btncount].setVisible(true);
        String opponent=btn[btncount].getText();
         btn[btncount].setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                  System.out.println("clicked");
              click[btncount]++;
               Data data=new Data();
            data.message=u_name+",opponent:"+opponent;
            
            oppo=opponent;
                try {
                    if(click[btncount]<=1)
                        nc.write(data.clone());
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(PlayerWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
         });
         btncount++;
       
        }
    }
            
}
