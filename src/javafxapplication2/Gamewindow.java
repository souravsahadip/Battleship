/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;
import Util.Data;
import Util.NetworkConnection;
import javafx.application.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author dipce
 */
public class Gamewindow {
    AnchorPane root = new AnchorPane();
     //AnchorPane root2 = new AnchorPane();
     //AnchorPane root3 = new AnchorPane();
     //HBox box = new HBox(0,root,root2);
     
     
     Scene scene = new Scene(root, 1000, 600,Color.BLACK);
    boolean permission= false;
    boolean isblast=false;
    boolean fire=false;
    static int flag =0;
    static int flagbox =1;
    static int count=3;
    private double initX;
    private double initY;
    private Point2D dragAnchor;
    Point2D boxstat;
     static int i=0;
     static double[] a={25,225};
     static double[] b={25,225};
     static double x=25;
     static double y=25;
     static String u_name;
     static String s_name;
     static InetAddress ipadress;
     public String opponent;
     Stage primaryStage;
     NetworkConnection nc;
     Rectangle[] sq = new Rectangle[100];
     Rectangle[] oppsq = new Rectangle[100];
     Text result=new Text();
     Text turn = new Text();
     Text shipstat = new Text();
     public Gamewindow(Stage primaryStage,String u_name,NetworkConnection nc,String oppo){
         this.primaryStage=primaryStage;
         this.u_name=u_name;
         this.nc=nc;
         this.opponent= oppo;
        }
  
     Scene getscene(){
          Image image = new Image(Gamewindow.class.getResource("bb35.png").toExternalForm());
    ImageView[] img = new ImageView[3];
    for(int j=0;j<3;j++){
    img[j] = new ImageView(image);
    img[j].setLayoutY(500);
    img[j].setFitWidth(40+j*40);
    img[j].setFitHeight(40);
    
    }
    img[0].setLayoutX(0);
    img[1].setLayoutX(200);
    img[2].setLayoutX(400);
    root.getChildren().add(img[0]);
    root.getChildren().add(img[1]);
    root.getChildren().add(img[2]);
         HashMap<Integer,Double>shipinfox= new HashMap<Integer,Double>();
         HashMap<Integer,Double>shipinfoy= new HashMap<Integer,Double>();
           Line[] xline= new Line[11];
         for(int j=0;j<11;j++){
          xline[j] = new Line(40,40+40*j, 440,  40+40*j);
         
          xline[j].setStroke(Color.WHITE);
          xline[j].setStrokeWidth(1);
         
            root.getChildren().add(xline[j]);
         }
         Line[] yline= new Line[11];
         for(int j=0;j<11;j++){
          yline[j] = new Line(40+40*j,40,40+40*j,440);
           yline[j].setStroke(Color.WHITE);
          yline[j].setStrokeWidth(1);
            root.getChildren().add(yline[j]);
         }
         
           Ship[] ships= new Ship[3];
        ships[0]=new Ship(1,Color.TRANSPARENT,0,500);
        ships[1]=new Ship(2,Color.TRANSPARENT,200,500);
        ships[2]=new Ship(3,Color.TRANSPARENT,400,500);
        shipinfox.put(0,0.0);
        shipinfoy.put(0,500.0 );
        shipinfox.put(1,200.0);
        shipinfoy.put(1,500.0 );
        shipinfox.put(2,400.0);
        shipinfoy.put(2,500.0 );
          Rectangle[] ship=new Rectangle[3];
        for(int j=0;j<3;j++){
             ship[j]=ships[j].getShip();
             ship[j].setId(Integer.toString(j));
         ship[j].setOnMouseDragged(new EventHandler<MouseEvent>() {
             @Override  
            public void handle(MouseEvent me){
                if(flagbox==1) {{
                Rectangle rect=(Rectangle) me.getSource();
                
                int shipno=Integer.parseInt(rect.getId());
                    
               double dragX = me.getSceneX() - dragAnchor.getX();
            double dragY = me.getSceneY() - dragAnchor.getY();
            //calculate new position of the img
            double newXPosition = initX + dragX;
            double newYPosition = initY + dragY;
             double ax = newXPosition-newXPosition%40;
                   
             double ay = newYPosition-newYPosition%40;
                    System.out.println("ax="+ax+",ay="+ay);
             int sf=1;
             for(int k=0;k<3;k++){
                 if(k!=shipno){
                       /*if(((ax>=(shipinfox.get(k)-rect.getWidth()))||(ax<=(shipinfox.get(k)+40*k)))
                              &&((ay-shipinfoy.get(k))==40||(ay-shipinfoy.get(k))==-40)){
                                sf=0;
                                System.out.println("sf==0");
                         }*/
                       //if((ax>=(shipinfox.get(k)-rect.getWidth())))
                       if(ax==shipinfox.get(k)&&ay==shipinfoy.get(k)){
                                sf=0;
                                System.out.println("sf==0");
                         }
                       if(shipinfox.get(k)>ax){
                           if((ax>(shipinfox.get(k)-rect.getWidth()))){
                               if(ay>shipinfoy.get(k)){
                                   if(ay<(shipinfoy.get(k)+40)) {
                                    sf=0;
                                    System.out.println("sf==0");
                                   }
                               }
                               else if(ay<=shipinfoy.get(k)){
                                   if(ay>(shipinfoy.get(k)-40)) {
                                    sf=0;
                                    System.out.println("sf==0");
                                   }
                               }
                               
                           }
                             
                       }
                       else if(shipinfox.get(k)<=ax){
                           if(ax<(shipinfox.get(k)+40*k+40)){
                               if(ay>shipinfoy.get(k)){
                                   if(ay<(shipinfoy.get(k)+40)) {
                                    sf=0;
                                    System.out.println("sf==0");
                                   }
                               }
                               else if(ay<=shipinfoy.get(k)){
                                   if(ay>(shipinfoy.get(k)-40)) {
                                    sf=0;
                                    System.out.println("sf==0");
                                   }
                               }
                              
                           }
                             
                       }
                 }
              }
                 
            //if new position do not exceeds borders of the rectangle, translate to this position
       if(sf==1) {
           System.out.println("sf==1");
            if ((newXPosition)>=0&&(newXPosition-newXPosition%40)<=400-rect.getWidth()
                    &&newYPosition>=0&&(newYPosition-newYPosition%40)<=400-rect.getHeight()) {
               
                        rect.setTranslateX(newXPosition-newXPosition%40);
                      img[shipno].setTranslateX(newXPosition-newXPosition%40);
                      //shipinfox.put(shipno,(rect.getTranslateX()+rect.getLayoutX()));
                      //shipinfox.put(shipno,(rect.getTranslateX()));
                      shipinfox.put(shipno,newXPosition+40-newXPosition%40);
               
                System.out.println("newYPosition-newYPosition%40="+(newYPosition-newYPosition%40));
                rect.setTranslateY(newYPosition-newYPosition%40);
                img[shipno].setTranslateY(newYPosition-newYPosition%40);
                      //shipinfoy.put(shipno,(rect.getTranslateY()+rect.getLayoutY() ));
                      // shipinfoy.put(shipno,(rect.getTranslateY() ));
                      shipinfoy.put(shipno,(newYPosition+40-newYPosition%40));
            }
        } 
               }  
            }
        }
                 }
                
      );
         
         
         ship[j].setOnMouseClicked((MouseEvent me) -> {
                         if(flagbox==1) {
              
            Rectangle rect = (Rectangle) me.getSource();
            int shipno=Integer.parseInt(rect.getId());    
            rect.setLayoutX(40);
            rect.setLayoutY(40);
             img[shipno].setLayoutX(40);
             img[shipno].setLayoutY(40);
                             System.out.println(count);
             count--;
            
            //when mouse is pressed, store initial position
            initX = rect.getTranslateX();
            initY = rect.getTranslateY();
            dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
             System.out.println("Mouse pressed above " );
         }
        });
         
          root.getChildren().add(ship[j]);
     }
        
     
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
                 if(key.getCode()==KeyCode.ENTER) {
                            System.out.println("You pressed Enter");
                      if(  shipinfoy.get(0)!=500&& shipinfoy.get(1)!=500&& shipinfoy.get(2)!=500)  
                          flag=1;
                 if(flag==1){
                     flagbox=0;
                  try{
                   String message = new String();
                   
                   message = u_name+"+Final_Entry:"+shipinfox.get(0)+","+shipinfoy.get(0)+";"
                           +shipinfox.get(1)+","+shipinfoy.get(1)+";"
                           +shipinfox.get(2)+","+shipinfoy.get(2);
                    Data data=new Data();
            data.message = message;
                try {
                    nc.write(data.clone());
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(PlayerWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                 System.out.println("message=" + message+" :sent");
                 turn.setText("All ships have been set");
               }catch(Exception e){
                   System.out.println(e);
               }
          }
        }
      
    });
         scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
                 if(key.getCode()==KeyCode.C) {
                            System.out.println("You pressed C");
                 for(int k=0;k<3;k++){
             
                         System.out.println(shipinfox.get(k)+","+shipinfoy.get(k));
             }
        }
      
    });
      //Opponent's Board
       for(int j=0;j<100;j++){
           sq[j] = new Rectangle(0, 0, 40, 40);
           sq[j].setFill(Color.TRANSPARENT);
           
           root.getChildren().add(sq[j]);
       }
       for(int j=0;j<100;j++){
           oppsq[j] = new Rectangle( 40, 40);
           oppsq[j].setLayoutX(540+((int)(j/10))*40);
           oppsq[j].setLayoutY(40+((int)(j%10))*40);
           oppsq[j].setFill(Color.TRANSPARENT);
           oppsq[j].setStroke(Color.WHITE);
           oppsq[j].setStrokeWidth(1);
           oppsq[j].setOnMouseClicked(new EventHandler<MouseEvent>() {
             @Override
            public void handle(MouseEvent me) {
                if(permission==true){
                    Rectangle rect = (Rectangle) me.getSource();
                    if(rect.getFill()==Color.TRANSPARENT){
                        rect.setFill(Color.ORANGE);
                        double cordx=rect.getLayoutX();
                        double cordy=rect.getLayoutY();
                        Data data=new Data();
                        data.message=opponent+",Update:"+(cordx-500)+";"+cordy;
                        System.out.println(data.message);
                try {
                    nc.write(data.clone());
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(PlayerWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                Data data2=new Data();
                data2.message=opponent+",Fire!";
                try {
                    nc.write(data2.clone());
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(PlayerWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(data2.message+"+sent to+"+opponent);
    
                
                turn.setText("Opponent's Turn...");
                permission=false;
                    }
                }
            }
            });
             root.getChildren().add(oppsq[j]);
       }
       result.setLayoutX(100);
       result.setLayoutY(540);
       result.setFont(Font.font(30));
       result.setFill(Color.WHITE);
       root.getChildren().add(result);
      turn.setLayoutX(450);
       turn.setLayoutY(510);
       turn.setFont(Font.font(30));
       turn.setFill(Color.WHITE);
       root.getChildren().add(turn);
       shipstat.setLayoutX(450);
       shipstat.setLayoutY(550);
       shipstat.setFont(Font.font(30));
       shipstat.setFill(Color.WHITE);
       root.getChildren().add(shipstat);
       Image immage = new Image(Gamewindow.class.getResource("black.png").toExternalForm());
        
       BackgroundImage bgi = new BackgroundImage(immage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background imgv = new Background(bgi);
        
        root.setBackground(imgv);
       return scene;
     }
    public void sceneshow(){
        
          primaryStage.setScene(this.getscene());
         
        
    }
     public void update(double xi,double yi){
         double dn = (xi-40)/4+(yi-40)/40;
         int n=(int) dn;
         sq[n].setLayoutX(xi);
         sq[n].setLayoutY(yi);
         sq[n].setFill(Color.ORANGE);
          sq[n].setStroke(Color.BLACK);
           sq[n].setStrokeWidth(1);
           }
     public void setpermission(boolean value){
         turn.setText("Your Turn!");
            permission = value;
            
}
     public void setblast(){
            isblast = true;
}
     public void my_terminate(double xi,double yi){
         double dn = (xi-40)/4+(yi-40)/40;
         int n=(int) dn;
         System.out.println("dn="+dn+",n="+n);
         sq[n].setLayoutX(xi);
         sq[n].setLayoutY(yi);
         sq[n].setFill(Color.RED);
          sq[n].setStroke(Color.BLACK);
           sq[n].setStrokeWidth(1);
         
     }
     public void oppo_terminate(double xi,double yi){
         double dn = (xi-40)/4+(yi-40)/40;
         int n=(int) dn;
         System.out.println("dn="+dn+",n="+n);
        
         oppsq[n].setFill(Color.RED);
         
     }
     public void showresult(String s){
         result.setText(s);
         turn.setText("");
     }
      public void showshipstat(String s){
         
         shipstat.setText(s);
     }
}
