/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * clase con los metodos para la comunicacion entre sockets
 * @author pavulzavala
 */
public class Communication 
{

     /**
     * metodo que envia datos, este manda los datos a manera de string
     * @param socket
     * @param message
     * @throws IOException 
     */
    public static synchronized void sendData(Socket socket, String message) throws IOException
    {
      DataOutputStream output = new DataOutputStream(socket.getOutputStream());
      output.writeUTF(message+"\n");
    }//send
    
    
    
    /**
     * metodod que recive datos de un socket, este lee los datos y regresa el string
     * del mensaje que se envio
     * @param socket
     * @return
     * @throws IOException 
     */
    public static synchronized String receiveData(Socket socket) throws IOException
    {
        DataInputStream input = new DataInputStream(socket.getInputStream());
        return input.readUTF();
    }//receive
    
    /**
     * metodo que desgloza los mensajes enviados y recibidos entre
     * clientes y el servidor
     * @param message
     * @return 
     */
    public static String[] splitData(String message)
    {
    return message.split(":");
    }//splitdata
    
    
//    
//    /**
//     * metodo que regresa el comando desglozado en un arreglo que tiene el
//     * ID del cliente ( pos 0 ) y el comando a ejecutar ( pos 1 )
//     * @return 
//     */
//    public static String[] splitCommand(String message)
//    {
//    return message.split(":");
//    }//
//    
//    
//    public static String[] splitState()
//    {
//    
//    return null;
//    }//
//    
//    
    
    
}//class
