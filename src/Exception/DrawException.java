/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exception;

/**
 * clase para arrojar una excepcion cuando haya algun erorr
 * en el proceso de repintado
 * @author pavulzavala
 * 
 * 
 * this class is an exception who can be used when there is a problem
 * in the drawing processes
 */
public class DrawException extends Exception {
    private static final long serialVersionUID = -2622332918716778840L;
    
    
    public DrawException() {
        super();
    }

    public DrawException(String message) {
        super(message);
    }
    
    
}//class