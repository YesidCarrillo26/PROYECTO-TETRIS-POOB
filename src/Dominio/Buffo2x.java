package Dominio;

import java.awt.*;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase para formar el buffo tipo Slow el cual tiene color de Naranja y hace que
 * Durante 3 segundos la ficha bajará al doble de velocidad normal.
 */
public class Buffo2x extends  Bufos implements Serializable
{

    private int velocidad = 150;

    public Buffo2x() {
        super(Color.orange);
    }

    /**
     * Metodo para obtener la velocidad
     * @return velocidad del tetris
     */
    public int getVelocidad() {
        return velocidad;
    }

    @Override
    public void comportamiento() {
        try {
            Thread.sleep(150);
            super.gameThread.ajusteVelocidad(150,true);
        } catch (InterruptedException in) {
            Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, in);
        }
    }
}