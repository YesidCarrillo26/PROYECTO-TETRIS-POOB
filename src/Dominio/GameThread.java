package Dominio;

import Presentacion.TetrisGUI;

import java.sql.SQLOutput;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GameThread extends Thread
{
    private Tetris tetris;
    private Bufos bufo;
    private TetrisGUI gui;
    private int velocidad = 500;
    //private boolean speed = false;
    private boolean v = false;
    private int cont, puntaje=0;
    private int puntos;
    private int score;
    private boolean ret;
    private boolean createBufo;
    private Random random = new Random();
    private int s;

    public GameThread(Tetris _tetris, TetrisGUI _gui)
    {
        this.tetris = _tetris;
        this.gui = _gui;
    }

    /**
     * Metodo para sacar la velocidad
     * @param _velocidad velocidad del tetris
     */
    public void velocidad(boolean _velocidad)
    {
        this.v = _velocidad;
        //this.velocidad = _velocidad ? 100 : 500;
    }

    /**
     * Metodo para ajustar la velocidad del tetris
     * @param _newVelocidad nueva velocidad
     * @param _v un boolean
     */
    public void ajusteVelocidad(int _newVelocidad, boolean _v)
    {
        this.velocidad = _newVelocidad;
        this.v = _v;
    }

    /**
     * Metodo para dormir el juego de tetris
     */
    public void sleep()
    {
        try {
            Thread.sleep(velocidad);
        } catch (InterruptedException in) {
            Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, in);
        }
    }

    @Override
    public void run() {
        //puntaje = 0;
        while (true) {
            ret = true;
            tetris.spawnFigura();
            createBufo = random.nextBoolean();
            if(createBufo)tetris.spawnBufo();
            cont = 0;
            s = 0;
            while (tetris.moveBlockDown()) {
                if(velocidad < 10000 && v){
                    sleep();
                }
                if(!v){
                    sleep();
                    ajusteVelocidad(500,true);
                    sleep();
                }
                cont ++;
                if((cont == 10 || cont == 20) && v && velocidad>= 10)
                {
                    velocidad -= 10;
                }
                puntaje += 1;
                if(velocidad == 250 && s>3)
                {
                    velocidad = 500;
                }
                s++;
                tetris.score(puntaje);
                gui.updateScore();
            }
            velocidad = 500;
            if(tetris.fueraDeLimite()){
                break;
            }
            tetris.moveFiguraToBackground();
            score += tetris.clearFilas();
            tetris.score(score);
            gui.updateScore();
        }
    }
}