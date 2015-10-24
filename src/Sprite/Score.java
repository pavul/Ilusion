/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprite;

/**
 * clase que sirve para crear diferentes puntajes, ya sea 
 * SCORE, HP, MP, LIVES, etc
 * @author Ilusion
 */
public class Score 
{
    private int max_value;
    private int value;
    
    
    /**
     * contructor 1, setea el valor maximo a las variables value y max_value
     * @param max_value 
     */
    public Score(int max_value)
    {
    this.max_value=max_value;
    this.value=max_value;
    }

    /**
     * regresa el valor maximo
     * @return 
     */
    public int getMax_value() {
        return max_value;
    }

    
    /**
     * setea el valor maximo
     * @param max_value 
     */
    public void setMax_value(int max_value) {
        this.max_value = max_value;
    }

    
    /**
     * regresa el valor 
     * @return 
     */
    public int getValue() {
        return value;
    }

    
    /**
     * seta el valor de la clase
     * @param value 
     */
    public void setValue(int value) {
        this.value = value;
    }
    
    
    
}//class
