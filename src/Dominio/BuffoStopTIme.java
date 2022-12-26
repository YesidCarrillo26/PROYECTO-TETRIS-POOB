package Dominio;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase para el tipo de Buffo Stop Time el cual es de color Amarillo y
 * ace que el bloque no baje automáticamente por 3 segundos
 */
public class BuffoStopTIme extends Bufos{

    private GameThread gameThread ;
    private int velocidad = 3000;

    public BuffoStopTIme() {
        super(Color.yellow);
    }

    @Override
    public void comportamiento() {
        super.gameThread.ajusteVelocidad(3000,false);

    }

    /**
     * Metodo para obtener la velocidad
     * @return la velocidad
     */
    public int getVelocidad() {
        return velocidad;
    }
}
