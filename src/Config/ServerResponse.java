/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

/**
 * clase que tiene un listado de todos los diferente estados que puede
 * regresar el servidor al cliente, en caso de juegos online multiplayer
 
 *NOTA: el cliente envia comandos al server y el server regresa estados al cliente
 * @author pavulzavala
 * 
 * this class have a list of all diferent states or responses who are returned 
 * by the server
 */
public class ServerResponse
{
    
    public static final String XPOS="xpos"; //posicion x  
    public static final String YPOS="ypos"; //posicion y
    public static final String HP="hp"; //valor del hp
    public static final String MP="mp"; //valor de mp
    public static final String FALLING="falling"; //sprite cayendo
    public static final String DAMAGED="damaged"; //sprite danado
    public static final String DEAD="dead"; //sprite muerto
    public static final String LVLUP="lvlup"; //subir de nivel
    public static final String SETID="setid"; //establecer el id del cliente
    public static final String CHANGELVL="changelvl"; //cambiar de nivel
    public static final String CREATEPLAYER="createplayer"; //crear los jugadores totales en cada cliente
    public static final String CREATEBULLET="createbullet"; //crear las balas totales en cada cliente
    public static final String CREATEENEMY="createenemy"; //crear los enemigos totales en cada cliente
    public static final String CREATEITEM="createitem"; //crear los items totales en cada cliente
    public static final String CREATESPELL="createspell"; //crear los spells totales en cada cliente
    public static final String CONNECTED="conected"; //el server indica que se conecto exitosamente el cliente
//    public static final String CLIENTID="clientid"; //el server indica que se conecto exitosamente el cliente

    
}//class
