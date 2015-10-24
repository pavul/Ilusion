/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

/**
 * clase que tiene la configuracion del juego
 * @author pavulzavala
 */
public class Config 
{
    
    //estados del juego
    public static  int STATE_LOADING=0;
    public static  int STATE_PLAYING=1;
    public static  int STATE_PAUSED=2;
    public static  int STATE_CLEARED=3;
    public static  int STATE_GAMEOVER=4;
    public static  int STATE_STOPED=5;

    
    //medidas del room y de la pantalla del juego
    //coordenadas de canvas son X = 0 e Y = 0 TOP - LEFT
    public static  int SCREEN_WIDTH=650;// el ancho de la ventana que contiene el juego
    public static  int SCREEN_HEIGHT=500;// el alto de la ventana que contiene al juego
    public static  int ROOM_WIDTH = Config.TILE_COLUMNS * Config.TILE_WIDTH;//el ancho del room
    public static  int ROOM_HEIGHT= Config.TILE_ROWS * Config.TILE_HEIGHT;//el alto del room
    
    //VALORES DE LOS TILES
    public static  int TILE_COLUMNS=28;
    public static  int TILE_ROWS=10;
    public static  int TILE_WIDTH=50;
    public static  int TILE_HEIGHT=50;
    
    //variable para la gravedad
    public static  float GRAVITY=0.98f;
    
    
    //variables que tienen los valores que regresa la funcion
    //Collision.blockRecatangle
    public static  String COLISION_TOP="TOP";
    public static  String COLISION_BOTTOM="BOTTOM";
    public static  String COLISION_LEFT="LEFT";
    public static  String COLISION_RIGHT="RIGHT";
    public static  String COLISION_NONE="NONE";
    
    
}//class
