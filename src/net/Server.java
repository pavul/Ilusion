/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import Config.ServerResponse;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * clase que tiene metodos estaticos para enviar o recibir datos entre sockets
 * @author pavulzavala
 */
public class Server implements Runnable
{
 
    private final int SLEEPTIME=5000; //segundos que duerme el thread
    
    private ServerSocket gameServer; //socket servidor
    private List<ClientSocket> clients; //listado de clientes conectados
    
    private String ip; //ip del servidor
    private int port; //puerto del servidor
    private int maxConnections; //numero de conexiones maximas que puede aceptar el server
    
    
    private Thread thread;
    private boolean isRunning;
    
    
    
   /**
    * constructor 1 que instancia el server socket en el puerto dado
    * @param port 
     * @throws java.io.IOException 
    */
    public Server(int port) throws IOException
    {
        this.port=port;
        clients = new ArrayList<>();
        gameServer = new ServerSocket(port);
        thread = new Thread(this);
        thread.start();
    }//const1
    
    /**
     * cnostructor 2 donde se indica el puerto que escuchara el server
     * y el numero de maximo de conecciones que se pueden hacer
     * @param port
     * @param maxConnections
     * @throws IOException 
     */
     public Server(int port, int maxConnections) throws IOException
    {
        this(port);
        this.maxConnections= maxConnections;
    }//const1
    
//    public Server(String ip, int port) throws IOException
//    {
//        this.port=port;
//        this.ip=ip;
//        gameServer = new ServerSocket()
//        
//    }//const1
    
    
    /**
     * metodo que inserta el cliente conectado al arraylist de clientes
     * y regresa true si fue exitoso
     * @return 
     * @throws java.io.IOException 
     */
    public boolean connectClient() 
    {
        System.out.println("Ejecutando connect client SERVERCLASS");
        
        //si no esta creada la lista, se crea
//        if(clients == null)
//          {clients = new ArrayList<>();}
        
        try {
            ClientSocket client=null;
            
//            if(clients.size()<maxConnections)
            if(clients.size()<1)
            {
            client = new ClientSocket(gameServer.accept());
            
            clients.add(client);
            client.sendMessage(ServerResponse.SETID+":"+client.getClientId());
            
            }//
            //se agrega el socket del cliente conectado a la lista
            
            
//            System.out.println("ANTES DE ENVIAR A CLIENTEID: "+client.getClientId());
            
//            clients.add(client);
//            
//            //se envia en respuesta al cliente el ID que le creo el server
////            Communication.sendData(client.getSocket(), ServerGameState.SETID+":"+client.getClientId());
//            
//            client.sendMessage(ServerGameState.SETID+":"+client.getClientId());
            
//            System.out.println("Cliente conectado:: "+client.getSocket().getInetAddress().toString());
//            System.out.println("( clients.size() ) numeros de clientes que tiene el servidor: "+clients.size());
            System.out.println("finalizando connect server class");
//            client.closeOutput();
            
        } catch (IOException ex) {
            System.out.println("Error en B");
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }//connect
    
    
    /**
     * funcion que regresa los cleintes conectados al servidor
     * @return 
     */
    public int getConnectedClients()
    {
        if(clients == null)return 0;
        
    return clients.size();
    }//
    
    /**
     * funcion que checa si algun cliente ya no esta conectado y lo remueve de la lista
     * de clientes
     */
    public void removeDisconectedClients()
    {
    
        for(int i = 0; i < clients.size(); i++) 
        {
            if(!clients.get(i).getSocket().isConnected() )
            {
            clients.remove(i);
            }
        }//
        
    }//remove
    
   
    /**
     * funcion que cierra el socket del cliente y lo remueve de la lista de
     * clientes conectados, en caso de checar que ya no hay coneccion con dicho cliente
     * o simplemente si se le quiere forzar el cierre de la conneccion
     * @param clientId 
     * @return  
     * @throws java.io.IOException 
     */
     public boolean removeDisconectedClient(String clientId) throws IOException
    {
    
        for(int i = 0; i < clients.size(); i++) 
        {
            if(!clients.get(i).getSocket().isConnected() && clients.get(i).getClientId().equals(clientId) )
            {
                //primero se cierra el socket y posteriormente se remueve de la lista
                clients.get(i).getSocket().close();
                clients.remove(i);
                return true;
            }
            
        }//
        return false;
    }//remove
    
    
    

    public List<ClientSocket> getClients() {
        return clients;
    }

    public void setClients(List<ClientSocket> clients) {
        this.clients = clients;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public void run() 
    {
        //las instruciones puestas antes del while, se ejecutan 1 sola vez
        isRunning=true;
        
        while(isRunning)
        {
//            System.out.println(" aceptando conecciones: "+isRunning);
            
            //si los clientes conectados son mayores o iguales al
            //numero que se acepta, entonces ya no se conecta alguno
//            if(this.maxConnections >= this.getConnectedClients())
//            {return;}
            
            //se conectan cleintes periodicamente
                connectClient();
                System.out.println(" FINAL de aceptando conecciones: "+isRunning);
            //checar cuales clientes estan desconectados
//                removeDisconectedClients();
                
            //se pone a dormir el thread
        
                try {
                Thread.sleep(SLEEPTIME);
            } //while
            catch (InterruptedException ex) {
                System.out.println("Error Server.java InterruptedException");
            }
        
        }//while
        
    }//run
    
   
    
    /**
     * metodo que obtiene la peticion del cliente y regresa un arreglo de strings
     * en la posicion 0: esta el ID del cliente
     * en la posicion 1: esta el comando que ejecuta ese cliente
     * @return 
     */
//    public String[] getCommand()
//    {
//    
//        return receiveData(null)
//    }//
//    
    
}//class
