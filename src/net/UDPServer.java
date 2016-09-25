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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pavulzavala
 */
public class UDPServer implements Runnable
{
   
    private DatagramSocket serverUDPSocket;
    private List< Map<String, String> > clients;
    
    private int port; //puerto que escucha el server
    private String receive;
    
    Thread thread;
    private boolean isRunning;
            
    private int bufferSize;
    
    //variable para establecer el tiempo en que se espera comunicacion
    //del cliente, si pasa este tiempo el cliente no se comunica con el server
    //entonces se desconecta
    private int timeOut;  
    
    /**
     * limite de clientes que acepta el servidor, si el valor es cero
     * se aceptan clientes indefinidamente, por default 0
     */
    private int clientLimit;
    
    /**
     * variable que indica si se ejecuta el metodo para conectar clientes
     */
    private boolean acceptConnections;
    
    private UDPServer()
    {
    timeOut = 5000; //5 segundos de tiempo limite de conexion
    clientLimit=0;
    bufferSize=1024;
    acceptConnections=true;
    clients = new ArrayList<>();//  List<Map<String, String>>() {} ;
    }//
    
    public UDPServer(int port) throws SocketException
    {
    this();
    this.port=port;
    serverUDPSocket= new DatagramSocket(this.port);
    }// 

    @Override
    public void run() 
    {
    
        while(isRunning)
        {
           
            try
            {
//                DatagramSocket socket = new DatagramSocket();

                byte[] buffer= new byte[bufferSize];
                DatagramPacket receiveMsg =new DatagramPacket(buffer, bufferSize);

                serverUDPSocket.receive(receiveMsg);

                receive = new String(receiveMsg.getData(),0,bufferSize);
            
//                System.out.println("receive: "+receive+" : ");
                
                if(clients.size() >= clientLimit && clientLimit > 0)
                {
                    //se puede hacer directo con el setter
                acceptConnections=false;
                }//
                
                //agregar clientes
                if( acceptConnections )
                {
                addClient(receiveMsg.getPort(),
                          receiveMsg.getAddress().getHostAddress());
                }//
                
            }
            catch(SocketException sex){}
            catch(IOException ioex){}
        
        }//uail
    
    }//run
    
    
//    public void receiveData()
//    {
//    
//    }//receiv
    
    public void sendData(String response, String ip, int port) 
            throws UnknownHostException, IOException
    {
    
        byte[] buffer= response.getBytes();
        InetAddress add = InetAddress.getByName( ip );
    DatagramPacket sendPacket = 
                    new DatagramPacket(buffer, buffer.length,add,port);
            serverUDPSocket.send(sendPacket);
            
//            System.out.println("sendData-> "+response+" : "+add+" : "+port);
    
    }//send
    
    /**
     * funcion que envia los datos a todos los clientes
     * @param response
     * @throws java.io.IOException
     */
    public void sendDataToAll(String response) throws IOException
    {
        
        byte[] buffer= response.getBytes();
        
        for (Map client : clients) 
        {
            InetAddress add = InetAddress.getByName( (String)client.get("ip") );
            int p= Integer.valueOf( (String)client.get("port") );
            
            DatagramPacket sendPacket = 
                    new DatagramPacket(buffer, buffer.length,add,p);
            serverUDPSocket.send(sendPacket);
            
//            System.out.println("sendToAll-> "+add+" : "+p);
            
        }//
        
    }//
    
    /**
     * funcion que agrega un nuevo cliente al listado
     * @param port
     * @param ip
     */
    public void addClient(int port, String ip)
    {
//        System.out.println("ADDCLIENT "+port+" : "+ip);
        
        //se checa primero si existe el cliente a agrear
        if(clients.size() > 0 )
        {
            System.out.println("contador de clientes: "+clients.size());
            boolean register=true;
            
            //si hay clientes checar si ya se tiene la ip entrante en algun registro
            for( Map m:clients )
            {
                System.out.println("ip a checar: "+m.get("ip") +" con: "+ip);
                
                if(m.get("ip").equals(ip))
                {
                    System.out.println("cliente ya registrado: "+clients.size());
                    
                    //ya se tiene este cliente agregado
                    register=false;
                    break;
                }
                
            }//
            
            //significa que no se encontro esta ip, entonces hay que registrar
            if(register)
            {System.out.println(" se registra muchas veces");
            setClient(Util.Util.generateUUID().toString(), ip, port);
            }//
           
        }//
         else
            {
                System.out.println("Primera vez que se registra");
            setClient(Util.Util.generateUUID().toString(), ip, port);
            }
      
        
    }//addclient
    
    
    public String getReceive()
    {return receive;}
    
    /**
     * funcion que regresa los clientes conectados
     * @return 
     */
    public final int getClientCount()
    {return clients.size();}
    
    /**
     * funcion que inicia la ejecucion del nuevo thread del scket
     */
    public void start()
    {
    isRunning=true;
    thread = new Thread(this);
    thread.start();
    }//
    
    
    private void setClient(String id, String ip, int port)
    {
            Map<String, String> map = new HashMap<>();
                                map.put("sessionId", id);
                                map.put("ip", ip);
                                map.put("port", ""+port);
            clients.add(map);
            
                try {
                    //se manda la respuesta al cliente
                    sendData("sessionId:"+id,ip,port);
                    System.out.println("SE ENVIA SESSIONID");
                } 
                catch (IOException ex) 
                {
                ex.printStackTrace();
                }
    
    }//
    
    
}//class
