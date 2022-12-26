package Dominio;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase para el tipo de Buffo Stop Diece el cual tendra un color morado y
 * Detiene el bloque y seguirá bajando cuando el jugador presione la tecla de
 * bajar
 */
public class BuffoStopDiece extends Bufos{

    private int velocidadNormal = 500;
    private int velocidadPause = 100000;

    public BuffoStopDiece() {
        super(new Color(128,0,128)); // color morado
    }

    @Override
    public void comportamiento() {
        super.gameThread.ajusteVelocidad(velocidadPause,true);
        /*
        if(super.isDown) {
            super.gameThread.ajusteVelocidad(velocidadNormal,false);
        }
        else {
            super.gameThread.ajusteVelocidad(velocidadPause,false);
        }/*
        try {
            if(super.isdown) {
                super.gameThread.ajusteVelocidad(500,false);
                }
            else {
                Thread.sleep(100000);
            }
            //super.gameThread.ajusteVelocidad(3000,false);
        } catch (InterruptedException in) {
            Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, in);
        }*/
        //super.gameThread.ajusteVelocidad(100000,false);
        //if(super.isdown)desbloqueo();
        System.out.println("Hacer comportamiento de StopDiece (Morado)");
    }

    public int getVelocidadNormal() {
        return velocidadNormal;
    }

    /**
     * Metodo para obtener velocidad del tetris
     * @return velocidad del tetris
     */
    public int getVelocidad() {
        return velocidadPause;
    }

    public void desbloqueo()
    {
        super.gameThread.ajusteVelocidad(500,false);
    }

}