/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exception;

/**
 * Excepcion que se lanza cuando no se puede cargar bien algun sprite, fondo,
 * musica, etc
 *
 * @author pavulzavala
 */
public class LoadException extends Exception {
    private static final long serialVersionUID = -3704627405466797453L;

    public LoadException() {
        super();
    }

    public LoadException(String message) {
        super(message);
    }

}//class
