/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Room;

import Exception.LoadException;

/**
 * interfaz que sirve para establecer los metodos de inicializacion de 
 * sprites, fondos, musica, etc.
 * 
 * 
 * this interfacae is used to establish all methos who can be
 * definiind initialization process of a level
 * 
 * @author pavulzavala
 */
public interface Initializer 
{
    
    public void initSprite() throws LoadException;
    
    public void initBackground() throws LoadException;
    
    public void initSound() throws LoadException;
    
    public void initMap() throws LoadException;
    
}//interface
