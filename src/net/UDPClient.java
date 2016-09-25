/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pavulzavala
 */
public class UDPClient implements Runnable
{
   
   private DatagramSocket socket;
   int port; //el puerto del servidor
   
   //puerto donde recibe las respuestas del servidor, este se utilioza
   //solo para el ambiente de desarrollo ya que en produccion el cliente
   //debe de utilizar el mismo puerto, sin embargo localmente no se puede
   //porque no se puede ocupar 2 veces el mismo puerto
   int receivePort;
   int bufferSize;// el tamano del buffer
   
   String ip;// ip del servidor
   String receive;//el mensaje de texto que se recibe
   String sessionId; //id de la seccion
   
    InetAddress address;
   
   Thread thread;
   
   boolean isRunning;//indica si esta ejecutandose el thread
   boolean dev;//indica si se esta en ambiente de desarrollo default true
   
   /**
    * constructor privado que sirve para setear los valores por default
    */
    private UDPClient() 
    {
    bufferSize = 1024;
    }//const 
   
   
   
   /**
    * constructor que crea el cliente donde se indica la ip y el puerto
    * del servidor, este constructor debe ser usado normalmente para el ambiente
    * de produccion
    * @param port puerto que usa el servidor para escuchar peticiones
    * @param ip la direccion ip que utiliza el servidor
    */
   public UDPClient(int port, String ip)
   {
       this();
       try {
          
               address = InetAddress.getByName(ip);
           } catch (UnknownHostException ex) {
               System.out.println("No se conoce el host: "+ip);
//           Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
           }
           this.port=port;
           this.ip=ip;
           dev=false;
          
           try{
           socket = new DatagramSocket(receivePort);
       } catch (SocketException ex) {
           ex.printStackTrace();
       }
   
   }//const
   
   /**
    * constructor que crea el cliente donde se indica la ip y el puerto
    * del servidor, este constructor debe ser usado normalmente para el ambiente
    * de desarrollo, el parametro receive port es un puerto auxiliar para que
    * el cliente escuche las respuestas que le envia en el servidor, esto
    * cuando se hacen pruebas desde la misma computadora
    * @param port puerto que usa el servidor para escuchar peticiones
     * @param receivePort puerto que utiliza el cliente para recibir las respuestas del servidor
    * @param ip la direccion ip que utiliza el servidor
    */
   public UDPClient(int port,int receivePort, String ip)
   {
       this();
       try {
           address = InetAddress.getByName(ip);
       } catch (UnknownHostException ex) {
           System.out.println("No se conoce el host: "+ip);
//           Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
       }
   this.port=port;
   this.receivePort=receivePort;
   this.ip=ip;
   dev=true;
   
       try {
           socket = new DatagramSocket(receivePort);
       } catch (SocketException ex) {
           Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
       }
   
   }//const
   
   
   

    @Override
    public void run() 
    {
        
       byte[] buffer= new byte[bufferSize];
            while(isRunning)
        {
            System.out.println("client is Running");
            
            try
            {   

            DatagramPacket receiveMsg =new DatagramPacket(buffer, bufferSize);

            socket.receive(receiveMsg);

            receive = new String(receiveMsg.getData(),0,bufferSize);

//            System.out.println(" receive-> "+receive);

            }
            catch(SocketException sex){sex.printStackTrace();}
            catch(IOException ioex){ioex.printStackTrace();}
        
            
        }//uail
            
        
        
    }//
   
   /**
    * funcion que envia la peticion al servidor
     * @param requestMsg 
     * @throws java.net.SocketException 
    */
    public void sendRequest(String requestMsg) throws SocketException, IOException
    {
        
        byte[] buffer= requestMsg.getBytes();
        
        //se crea el paquete a enviar, hacia el puerto y direccion adecuada
        DatagramPacket request
        = new DatagramPacket(buffer,buffer.length, address, port);
        
        //finalmente se envia el socket
        socket.send(request);
    }//
   
    /**
     * funcion que regresa el mensaje recibido del servidor que se procesa
     * dentro del metodo RUN
     * @return 
     */
    public String receive()
    {
        return receive;
    }//
    
//    /**
//     * funcion que resetea la variable receive, a nulo, para que 
//     * cuando se setee con algun valor
//     */
//    public void resetReceive()
//    {
//        receive=null;
//    }

    public int getPort() {
        return port;
    }

    
    /**
     * establece el puerto del servidor a donde se enviaran los mensajes
     * @param port 
     */
    public void setPort(int port) {
        this.port = port;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public String getIp() {
        return ip;
    }

    /**
     * funcion que inicia la ejecucion del nuevo thread del scket
     */
    public void start()
    {
    isRunning=true;
    thread = new Thread(this);
    thread.start();
    }//
    
   
    /**
     * getters y setters
     */
    
     /**
     * establece la ip del servidor
     * @param ip 
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

   

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public boolean isIsRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public int getReceivePort() {
        return receivePort;
    }

    public void setReceivePort(int receivePort) {
        this.receivePort = receivePort;
    }

    public boolean isDev() {
        return dev;
    }

    public void setDev(boolean dev) {
        this.dev = dev;
    }
    
    
    
}//
