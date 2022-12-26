package Dominio;

import java.awt.*;

public class TetrmonoWinner extends Tetrominos {
    private Color[][] background;
    private int gridFilas;
    private int gridColumnas;


    public TetrmonoWinner() {
        super(new Color(255,215,0)); // Color dorado

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
