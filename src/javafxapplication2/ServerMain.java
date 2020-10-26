/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import Util.Information;
import Util.NetworkConnection;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ServerMain implements Runnable {
Thread t;
    public ServerMain() {
        Thread t = new Thread(this, "Server Thread");
           
       System.out.println("Child thread: " + t);  
        t.start(); // Start the thread 
    }
    
    @Override
    public void run()  {
        
        try {
            ServerSocket serverSocket=new ServerSocket(12345);
            
            HashMap<String,Information> clientList=new HashMap<String,Information>(); 
            
            
            while(true){
                Socket socket=serverSocket.accept();
                NetworkConnection nc=new NetworkConnection(socket);
                new Thread(new CreateConnection(clientList,nc)).start();
                
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
