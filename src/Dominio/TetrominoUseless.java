package Dominio;

import java.awt.*;

public class TetrominoUseless extends Tetrominos
{
    private Color[][] background;
    private int gridFilas;
    private int gridColumnas;

    public TetrominoUseless() {
        super(new Color(192,192,192)); // Color dorado

    }

    @Override
    public void comportamiento() {

    }

    /**
     * Metodo pra limpiar una fila
     */
    public int clearFilas(Color[][] _background, int _gridFilas, int _gridColumnas){
        this.gridFilas = _gridFilas;
        this.gridColumnas = _gridColumnas;
        this.background = _background;
        boolean clearFila;
        int filasClear =0;
        return filasClear;
    }

    @Override
    public void erraseCelda(Graphics g, int gridCeldasSize, int _figuraX, int _figuraY, Color[][] _background) {

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

}
