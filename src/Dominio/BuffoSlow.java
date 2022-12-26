package Dominio;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CAlse para tipo de Buffo Slow el cual tendra un color Verde y
 * hace que Las fichas empiezan a bajar más lento durante 3 segundos.
 */
public class BuffoSlow extends Bufos {

    private int velocidad = 3000;

    public BuffoSlow() {
        super(Color.green);
    }

    @Override
    public void comportamiento() {
        super.gameThread.ajusteVelocidad(3000,true);
    }

    /**
     * Metodo que obtiene la velocidad
     * @return obtiene la velocidad
     */
    public int getVelocidad() {
        return velocidad;
    }
}