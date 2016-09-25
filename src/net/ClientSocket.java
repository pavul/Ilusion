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
 * clase que guarda el socket conectado al servidor y un
 * id para identificar a este cliente
 * @author pavulzavala
 */
public class ClientSocket 
{
    
    private String clientId;
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    
    
    /**
     * constructor que acepta un socket y que genera automanticamente el ID del cliente
     * @param socket 
     */
    public ClientSocket(Socket socket)
    {
        this.clientId= Util.Util.generateUUID().toString();
        System.out.println("ID creada para el nuevo cliente: "+clientId);
        this.socket= socket;
    }//
    
    /**
     * constructor que acepta un socket y se le pasa el id del cliente
     * @param clientId
     * @param socket 
     */
    public ClientSocket(String clientId, Socket socket)
    {
      this.clientId=clientId;
      this.socket= socket;
    }//
    
    /**
     * funcion que obtiene el mensaje que proviene del socket y regresa el string del mensaje
     * @return 
     * @throws java.io.IOException 
     */
    public String getMessage() throws IOException
    {
        input = new DataInputStream(socket.getInputStream());
        return input.readUTF();
    }//
    
    /**
     * funcion que envia un mensaje de texto al socket receptor
     * @param message
     * @throws java.io.IOException 
     */
    public void sendMessage(String message) throws IOException
    {
       output = new DataOutputStream(socket.getOutputStream());
       output.writeUTF(message+"\n");
    }
    
    /**
     * funcion que cierra el socket y los IOStream, esta
     * se ejecuta cuando se quiere terminar la coneccion
     * @throws IOException 
     */
    public void closeClientSocket() throws IOException
    {
    socket.close();
    input.close();
    output.close();
    }//
    

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * funcion que cierra el input del stream que le el socket
     * @throws IOException 
     */
    public void closeInput()throws IOException
    {input.close();}
    
    /**
     * funcion que cierra el output del stream que le el socket
     * @throws IOException 
     */
    public void closeOutput()throws IOException
    {output.close();}

}//class
