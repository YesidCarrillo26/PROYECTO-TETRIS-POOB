/*
package Dominio;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import java.awt.*;

public class TetrisTest
{
    Tetris t1,t2,t3;
    JPanel panel = new JPanel();
    Tetrominos x ;

    @Before
    public void setUp()
    {
        panel.setBounds(120,50,350,700);
        panel.setBackground(Color.black);
        t1 = new Tetris(panel,10);
        t1.spawnFigura();
        t1.spawnBufo();
        t2 = new Tetris(panel,10);
        t2.spawnFigura();
        t2.spawnBufo();
    }

    @Test
    public void deberiaMoverFichaDerecha()
    {
        int posiOld = t1.getFigura().getX();
        t1.moveFiguraRight();
        int posiNew = t1.getFigura().getX();

        assertTrue(posiOld < posiNew);
    }

    @Test
    public void deberiaMoverFichaIzquierda()
    {
        int posiOld = t2.getFigura().getX();
        t2.moveFiguraLeft();
        int posiNew = t2.getFigura().getX();

        assertTrue(posiOld > posiNew);
    }

    @Test
    public void deberiaMoverFichaAbajo()
    {
        int posiOld = t1.getFigura().getY();
        t1.dropFigura();
        int posiNew = t1.getFigura().getY();

        assertTrue(posiOld < posiNew);
    }

    @Test
    public void deberiaActivarBuffo()
    {
        assertTrue(t1.getBufo().getVelocidad() != 500);
    }

}
*/


