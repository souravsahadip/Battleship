/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import javafxapplication2.PlayerWindow;

/**
 *
 * @author user
 */
public class Reader implements Runnable{
    public NetworkConnection netConnection;
    PlayerWindow pw;
     String[] online_players = new String[20];
   static int opcount=0; 
    public Reader(NetworkConnection nc,PlayerWindow pw){
        netConnection=nc;
        this.pw=pw;
    }
    
    @Override
    public void run() {
        while(true){
            
            Object obj=netConnection.read();
            Data dataObj=(Data)obj;
            System.out.println("Received : "+dataObj.message);
            String message=dataObj.message;
            if(message.contains("[")){
                String players=message.substring(message.indexOf("[")+1,message.indexOf("]"));
                String[] player = players.split(", ");
                for(int j=0;j<player.length;j++){
                    int flag = 1;
                        for(int k=0;k<opcount;k++){
                            if(online_players[k].equals(player[j])==true)
                                flag = 0;
                        }
                        if(flag==1)
                         pw.update(player[j]);
                         online_players[opcount] = player[j];
                        opcount++;
                }
            }
            if(message.contains("Start!")){
                pw.start();
                
            }
            if(message.contains("Fire!")){
                
                pw.gamewindow.setpermission(true);
                System.out.println("Permission has been set true.");
                
            }
            if(message.contains("Update:")){
                String update = message.substring(message.indexOf(":")+1);
                String[] coord = update.split(";");
                double xi =Double.parseDouble( coord[0]); 
                double yi =Double.parseDouble( coord[1]); 
                pw.gamewindow.update(xi, yi);
                
            }
            if(message.contains("Your+Terminate:")){
                String terminate = message.substring(message.indexOf(":")+1);
                String[] coord = terminate.split(";");
                double xi =Double.parseDouble( coord[0]); 
                double yi =Double.parseDouble( coord[1]); 
                pw.gamewindow.my_terminate(xi, yi);
                
            }
            if(message.contains("Oppo+Terminate:")){
                String terminate = message.substring(message.indexOf(":")+1);
                String[] coord = terminate.split(";");
                double xi =Double.parseDouble( coord[0]); 
                double yi =Double.parseDouble( coord[1]); 
                pw.gamewindow.oppo_terminate(xi, yi);
                
            }
            if(message.contains("You win")){
                String result = "YOU WIN!";
                pw.gamewindow.showresult(result);
                
            }
            if(message.contains("You lose")){
                String result = "YOU LOST!";
                pw.gamewindow.showresult(result);
                
            }
            if(message.contains("has been destroyed")){
                String shipstat = message;
                pw.gamewindow.showshipstat(shipstat);
                
            }
            if(message.contains("Want to play with")){
                String accept= message;
                pw.playwith(accept);
                
            }
            
        }
    }
    
}
