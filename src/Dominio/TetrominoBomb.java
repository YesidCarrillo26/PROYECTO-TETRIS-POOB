package Dominio;

import java.awt.*;

public class TetrominoBomb extends Tetrominos{

    private Color[][] background;
    private int gridFilas;
    private int gridColumnas;
    private int gridCeldasSize;

    public TetrominoBomb() {
        super(Color.red);
    }

    @Override
    public void comportamiento() {

    }

    /**
     * Metodo pra limpiar una fila
     */
    //public void clearFilas(){
    public int clearFilas(Color[][] _background, int _gridFilas, int _gridColumnas){
        this.gridFilas = _gridFilas;
        this.gridColumnas = _gridColumnas;
        this.background = _background;
        boolean clearFila;
        int filasClear =0;
        for (int i = _gridFilas - 1; i >= 0;i--){
            clearFila = true;
            for (int j = 0; j < gridColumnas;j++){
                if(background[i][j]==null) {
                    clearFila = false;
                    break;
                }
            }
            if(clearFila ){

                filasClear += 10;
                clearFila(i);
                shiftDownLinea(i);
                clearFila(0);
                i++;
                //repaint();
            }
        }
        return filasClear;
    }

    /**
     * Metodo para limpiar una fila
     * @param i numero de filas a limpiar
     */
    public void clearFila(int i){
        for(int k = 0; k < gridColumnas;k++){
            background[i][k] = null;
        }
    }

    /**
     * Metodo para bajar toda una fila cuando la de abajo se llene
     * @param i numero de celdas para bajar
     */
    public void shiftDownLinea(int i){
        for(int k = i; k > 0;k--){
            for (int j = 0; j < gridColumnas;j++){
                background[k][j] = background[k - 1][j];
            }
        }
    }
    /*
    public void borrarCelda() {
        for (int i = 0; i < gridFilas; i++) {
            Boolean clearFila = true;
            for (int j = 0; j < gridColumnas; j++) {
                if (background[i][j] == null)
                {
                    break;
                }
                else if(background[i][j] != null && )
                {

                }

            }
        }
    }

     */
    @Override
    public void erraseCelda(Graphics _g, int _gridCeldasSize,int _figuraX, int _figuraY, Color[][] _background )
    {

        /*
        System.out.println("no es nula en:"+ _figuraX+","+_figuraY);
        //System.out.println("len de background"+_background.length+","+_background[0].length);
        //background[1][19] = null;

        ;
        gridCeldasSize = _gridCeldasSize;
        Color color;
        for (int i = 0; i < gridFilas; i++)
        {
            for (int j = 0; j < gridColumnas; j++)
            {
                System.out.println(j+"=="+_figuraX+" , "+i+"=="+_figuraY);
                if(j == _figuraX && i == _figuraY) {
                    background[j][i] = null;
                    System.out.println(i + "," + j);
                    System.out.println("----->");
                }

                /*color = background[i][j];
                if (color != null){
                    int x = j * gridCeldasSize;
                    int y = i * gridCeldasSize;

                    //drawCelda(g, color, figura.getColorBorde(), x, y);
                    clearCelda(_g, color, Color.white, x, y, false);
                }
            }

        }


        /*if (figura.getForma()[i][j]==1)
        {
            int x = (figura.getX() + j) * gridCeldasSize;
            int y = (figura.getY() + i) * gridCeldasSize;
            drawCelda(g, figura.getColor(), figura.getColorBorde(), x, y, true);
        }
       //clearCelda(_g, super.getColor(), super.getColorBorde(), x, y, true);

    }
    /*
    public void clearCelda(Graphics g, Color _color, Color _colorBorde, int _x, int _y, boolean _isNewFIcha){
        //g.setColor(_color);
        //g.fillRect(x, y, gridCeldasSize, gridCeldasSize);  // figura
        //g.clearRect(_x,_y,gridCeldasSize,gridCeldasSize);
        g = null;
        /*
        //g.setColor(Color.black);
        if(_newFicha){
            g.setColor(_colorBorde);
            g.drawRect(_x, y, gridCeldasSize, gridCeldasSize);}  // tablero
        else
        {
            g.setColor(Color.black);
            g.drawRect(x, y, gridCeldasSize, gridCeldasSize);  // tablero
        }*/
    }
}
