/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.*;
import javafx.geometry.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.WindowEvent;



/**
 *
 * @author dipce
 */



public class JavaFXApplication2 extends Application{
    boolean alive;
    
    @Override
    public void start(Stage primaryStage) {
       
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, 100, 100,Color.ALICEBLUE);
        UserName user = new UserName(primaryStage); 
        scene = user.getscene();
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.show();
         
    }
   /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
       
        launch(args);
       
    }
    public void stop() {   
       
     System.out.println("Inside the stop() method.");
        
    //Thread.yield();
    
 } 
  
    
}