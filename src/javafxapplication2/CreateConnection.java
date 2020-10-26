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
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class CreateConnection implements Runnable{
    
    HashMap<String,Information> clientList;
    NetworkConnection nc;
    static int count=0;
    public CreateConnection(HashMap<String,Information> cList, NetworkConnection nConnection){
        clientList=cList;
        nc=nConnection;    
    }
        
    
    @Override
    public void run() {
        Object userObj=nc.read();
        String message=(String)userObj;
        if(message.contains(": I want to play"))
        if(count<=2){
        String username = message.substring(0,message.indexOf(":"));
        
        
        System.out.println("User - "+username+" connected");
        
        clientList.put(username,new Information(username,nc));
        count++;
        new Thread(new ReaderWriterServer(username,nc,clientList)).start();
        Information info=clientList.get(username);
            Set<String> messageToSend=clientList.keySet();
               System.out.println(messageToSend);
            
             String p = messageToSend.toString();
             String players=p.substring(p.indexOf("[")+1,p.indexOf("]"));
             System.out.println(players);
           if(players.contains(", ")==true)  {
          
                String[] player = players.split(", ");
                for(int j=0;j<player.length;j++){
                    Information infos=clientList.get(player[j]);
                    Data data=new Data();
            data.message=messageToSend.toString();
                    try {
                        infos.netConnection.write(data.clone());
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(CreateConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        }
           
      
        }
        
    }
    
}
