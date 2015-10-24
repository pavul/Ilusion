/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Particle;

import Util.Util;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author pavulzavala
 */
public class ParticlexEmitter 
{
    
    int particleNumber; //numero de particulas totales del emitter
    
    ArrayList<Particlex> emitter;
    
    
    /**
     * constructor 1, se crea el emiter, se indica el numero de particulas y
     * la particula que contendra el emiter
     * @param particleNumber
     * @param x
     * @param y
     * @param w
     * @param h
     * @param velX
     * @param life
     * @param acelX
     * @param acelY
     * @param velY
     * @param decrementLife
     * @param color
     */
    public ParticlexEmitter( int particleNumber, int x, int y, int w, int h, 
            float velX, float velY, float acelX, float acelY, 
            float life, float decrementLife, Color color)
    {
    
        this.particleNumber=particleNumber;
        emitter= new ArrayList<>();
        
        Random rand= new Random();
        //se agregan todas las particulas al emiter
        for(int i = 0 ; i < this.particleNumber; i++)
        {
            
            Particlex particle= new Particlex
                    (x, y, w, h,
                    Util.generatRandomPositiveNegitiveValue(5, -5),
                    Util.generatRandomPositiveNegitiveValue(5, -5),
                    Util.generatRandomPositiveNegitiveValue(5, -5),
                    Util.generatRandomPositiveNegitiveValue(5, -5),
                    life, decrementLife, color);
            emitter.add(particle);
        }
        
    }//constructor
    
    
    /**
     * metodo que ejecuta el metodo update de todas las particulas,
     * es decir, se actualiza el comportamiento cada frame
     */
    public void update()
    {
        for(Particlex p: emitter)
        {
        p.update();
        }
    }//
    
    
    /**
     * 
     * @param g 
     */
    public void draw(Graphics g)
    {
        for(Particlex p: emitter)
        {
        p.draw(g);
        }
    }//
    
    
    
    
}//class
