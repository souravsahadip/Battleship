/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import Util.NetworkConnection;
import Util.Reader;
import Util.Writer;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Clientmain implements Runnable{
    PlayerWindow pw;
    String ip;
    int port;
    NetworkConnection nc;
    public Clientmain(PlayerWindow pw,NetworkConnection nc) {
        this.pw=pw;
        this.ip=ip;
        this.port=port;
        this.nc=nc;
         Thread t = new Thread(this, "Client Thread");
           
       System.out.println("Child thread: " + t);  
        t.start(); // Start the thread 
    }
    
    @Override
    public void run() {
        
        try{
            
           // NetworkConnection nc=new NetworkConnection("172.16.193.13",12345);
            //NetworkConnection nc=new NetworkConnection(ip,port);
            System.out.println("Enter your username");
          //  Scanner in=new Scanner(System.in);
            //String username=in.next();
            //nc.write(username);
            
            
            
            Thread readerThread=new Thread(new Reader(nc,pw));
            Thread writerThread=new Thread(new Writer(nc));
            
            readerThread.start();
            writerThread.start();
            
            try{
                readerThread.join();
                writerThread.join();
            }
            catch(Exception e){
                System.out.println("Thread exited");
            }
            
            
            
        }
        catch(Exception ex){
            Logger.getLogger(Clientmain.class.getName()).log(Level.SEVERE, null, ex);
        }
         
       
       
    }
}
