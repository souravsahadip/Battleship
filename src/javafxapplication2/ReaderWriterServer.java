/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import Util.Data;
import Util.Information;
import Util.NetworkConnection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ReaderWriterServer implements Runnable{
    String username;
    NetworkConnection nc;
    HashMap<String,Information> clientList;
    static int playerno=0;
    static int startcount=0;
    static String[] player= new String[20];
    static String[] opponent= new String[20];
     static HashMap<String,Integer>playersship= new HashMap<String,Integer>();
     static   HashMap<Integer,Double>shipinfox[] = new HashMap[2];
     static   HashMap<Integer,Double>shipinfoy[] = new HashMap[2];
      static int sendstart = 1;
     static int[] health = {6,6};
     static int[][] shiphealth = {{1,2,3},{1,2,3}};
      static int refire=0;
      
    public ReaderWriterServer(String user, NetworkConnection netConnection, HashMap<String,Information> cList){
        username=user;
        nc=netConnection;
        clientList=cList;
        
    }
    
    @Override
    public void run() {
        int port;
       int m = 0;
       for(int j=0;j<2;j++){
        shipinfox[j] = new HashMap<Integer,Double>();
        shipinfoy[j] = new HashMap<Integer,Double>();
        shipinfox[j].put(0,0.0);
        shipinfoy[j].put(0,500.0 );
        shipinfox[j].put(1,200.0);
        shipinfoy[j].put(1,500.0 );
        shipinfox[j].put(2,400.0);
        shipinfoy[j].put(2,500.0 );
        }
           System.out.println("Server started running");
        while(true){
            Object obj=nc.read();
            Data dataObj=(Data)obj;
            String format=dataObj.message;
            System.out.println(format);
            if(format.contains("Update:")==true){
                String opponent = format.substring(0, format.indexOf(","));
                String update = format.substring( format.indexOf(",")+1);
                int plno =playersship.get(opponent);
      
        {
                   Information info=clientList.get(opponent);
            
            Data data=new Data();
            data.message=update;
            info.netConnection.write(data);
            System.out.println("update has been sent to client");
            String up_date = update.substring(update.indexOf(":")+1);
                String[] coord = up_date.split(";");
                double xi =Double.parseDouble( coord[0]); 
                double yi =Double.parseDouble( coord[1]); 
               for(int j=0;j<3;j++){
                   double x = shipinfox[plno].get(j);
                   double y = shipinfoy[plno].get(j);
                   if((xi-x)>=0&&(xi-x)<=j*40&&y==yi){
                       health[plno]--;
                       shiphealth[plno][j]--;
                       Data terminate=new Data();
                        terminate.message="Your+"+"Terminate:"+Double.toString(xi)+";"+Double.toString(yi);
                        Information info0 = clientList.get(player[plno]);
                        Information info1 = clientList.get(player[1-plno]);
                        info0.netConnection.write(terminate);
                        terminate.message="Oppo+"+"Terminate:"+Double.toString(xi)+";"+Double.toString(yi);
                        info1.netConnection.write(terminate);
                        Data fire=new Data();
                        fire.message=player[1-plno]+",Fire!";
                        info1.netConnection.write(fire);
                        
                        if(shiphealth[plno][j]<=0){
                            Data destroyed=new Data();
                            destroyed.message="Enemy ship has been destroyed";
                            info1.netConnection.write(destroyed);
                            destroyed.message="Your ship has been destroyed";
                            info0.netConnection.write(destroyed);
                        }
                            
                        if(health[plno]<=0){
                            Data result=new Data();
                            result.message="You win";
                            info1.netConnection.write(result);
                            result.message="You lose";
                            info0.netConnection.write(result);
                        }
                   }
                   else{
                       Information info0 = clientList.get(player[plno]);
                       Data fire=new Data();
                        fire.message=player[plno]+",Fire!";
                        info0.netConnection.write(fire);
                   }
               }
            
               }
               
            }
            
         
            
            
           if(format.contains("Final_Entry:")==true){
               String player = format.substring(0, format.indexOf("+"));
               String Final_Entry = format.substring(format.indexOf(":")+1);
               String[] entries = Final_Entry.split(";");
               for(int j=0;j<entries.length;j++){
                   String[] entry= entries[j].split(",");
                   System.out.println("playersship.get(player)="+playersship.get(player)+
                           "+Double.parseDouble(entry[0])="+Double.parseDouble(entry[0])+
                                   "+Double.parseDouble(entry[1])="+Double.parseDouble(entry[1]));
                    shipinfox[playersship.get(player)].put(j,Double.parseDouble(entry[0]));
                    shipinfoy[playersship.get(player)].put(j,Double.parseDouble(entry[1]));
               }
               startcount++;
               if(startcount==2)  
               {
                    Information info=clientList.get(player);
            String messageToSend=player+",Fire!";
            Data data=new Data();
            data.message=messageToSend;
            info.netConnection.write(data);
            System.out.println("Fire player2");
           } 
               
           }
           if(format.contains("opponent:")==true){
               player[playerno]=format.substring(format.indexOf(":")+1);
                opponent[playerno]=format.substring(0,format.indexOf(","));
                System.out.println("player["+playerno+"]="+player[playerno]+",opponent["
                        +playerno+"]="+opponent[playerno]);
                
                System.out.println(playerno);
                Data data=new Data();
            data.message="Want to play with "+opponent[playerno]+"?";
            Information info=clientList.get(player[playerno]);
            info.netConnection.write(data);
            playerno++;
           }
           if(playerno==2&&sendstart==1){
               System.out.println(player[0]+"+"+opponent[1]+"+"+player[1]+"+"+opponent[0]);
               if(player[0].equals(opponent[1])&&player[1].equals(opponent[0])){
                   Information info0=clientList.get(player[0]);
            Information info1=clientList.get(player[1]);
            String messageToSend="Start!";
            Data data=new Data();
            data.message=messageToSend;
            
            info0.netConnection.write(data);
            System.out.println("Sent to player1");
            info1.netConnection.write(data);
            System.out.println("Sent to player2");
            playersship.put(player[0],0);
            playersship.put(player[1],1);
            sendstart = 0;
               }
        }
            }
        
    }
    
}
