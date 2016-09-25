

import Level.GameLevel;
import Room.Room;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pavulzavala
 */
public class Game //extends Application
{
    
    static JFrame frame;
    
    
    
    public static void main(String []args)
    {
  
        FirstLevel lvl1 = new FirstLevel(640, 1600, 640, 480, 20, 50, 32, 32 );
        SecondLevel lvl2 = new SecondLevel(640, 480, 640, 480, null );
        ThirdLevel lvl3 = new ThirdLevel(640, 480, 640, 480, null );
        
        
        //se agrega el nivel al stack de niveles
        Map<String, GameLevel> levelStack =  new HashMap<>();
        levelStack.put("first", lvl1);
        levelStack.put("second", lvl2);
        levelStack.put("third", lvl3);
        
//        se crea el room, este contiene a todos los niveles
        Room room= new Room("first", levelStack);
        
        room.setFocusable(true);
        room.setIgnoreRepaint(true);
        frame= new JFrame("titulo del juego");
        frame.add(room);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(640,480);
        frame.setVisible(true);
        frame.setResizable(false);
        
        //este va al final, porque se debe de renderizar a lo ultim
        room.start( );
        
    }//main
    
}//
