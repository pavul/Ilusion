/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

/**
 * clase que tiene los comandos que envia el cliente al servidor para que este
 * los interprete y regrese un estado al cliente

 * NOTA: el cliente envia comandos al server y el server regresa estados al cliente
 * 
 * 
 * class who have a list of commands that are sent from client to server,
 * the server will recaive those command and interpret them 
 * 
 */
public class ClientRequest
{
    
    public static final String LOGIN="login";
    public static final String LOGOUT="logout";
    public static final String MOVERIGHT="moveright";
    public static final String MOVELEFT="moveleft";
    public static final String MOVEUP="moveup";
    public static final String MOVEDOWN="movedown";
    public static final String ATTACKRIGTH="attackrigth";
    public static final String ATTACKLEFT="attackleft";
    public static final String ATTACKUP="attackup";
    public static final String ATTACKDOWN="attackdown";
    public static final String JUMPRIGHT="jumprigth";
    public static final String JUMPLEFT="jumpleft";
    public static final String USEMAGIC="usemagic";
    public static final String USEITEM="useitem";
    public static final String STANDRIGTH="standrigth";
    public static final String STANDLEFT="standleft";
    public static final String STANDUP="standup";
    public static final String STANDDOWN="standdown";
    public static final String CONVERSATION="conversation";
    public static final String INVENTORYOPEN="inventoryopen";
    public static final String MESSAGE="message";
//    public static final String ="";
//    public static final String ="";
//    public static final String ="";
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}//class
