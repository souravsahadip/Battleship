/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author dipce
 */
public class Ship {
   double size;
   boolean bool;
   Color color;
   Rectangle ship ;
   double x=400;
   double y=400;
    public Ship(double size,Color color,double x,double y) {
        this.size = size;
        this.color=color;
        this.x=x;
        this.y=y;
    }
   public Rectangle getShip(){
      ship= new Rectangle(size*40,40,color);
      
      ship.setLayoutX(x);
      ship.setLayoutY(y);
       return ship;
   }
   
}
