/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkConnection {
    public Socket socket;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    
    public NetworkConnection(Socket sock) throws IOException{
        socket=sock;
        oos=new ObjectOutputStream(socket.getOutputStream());
        ois=new ObjectInputStream(socket.getInputStream());
    }
    
    public NetworkConnection(String ip,int port){
        try {
            socket=new Socket(ip, port);
            oos=new ObjectOutputStream(socket.getOutputStream());
            ois=new ObjectInputStream(socket.getInputStream());
        } catch (Exception ex) {
            System.out.println("Could not connect server.");
        }
    }
    
    public void write(Object obj){
        try {
            oos.writeObject(obj);
        } catch (Exception ex) {
            System.out.println("Failed to write");
            //throw ex;
        }
    }
    
    public Object read(){
        Object obj;
        try {
            obj = ois.readObject();
        } catch (Exception ex) {
            System.out.println("Failed to read");
            return null;
        }
        return obj;
    }
}
