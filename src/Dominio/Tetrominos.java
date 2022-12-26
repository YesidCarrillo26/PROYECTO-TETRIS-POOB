package Dominio;

import java.awt.*;
import java.util.Random;

public abstract class Tetrominos
{
    private int[][] forma;
    private Color color, colorBorde;
    private int x, y;
    private int[][][] formasFigura;
    private int rotacionActual;
    private Color[] colors = {Color.green, Color.blue, Color.red, Color.yellow, Color.orange, Color.magenta};

    public Tetrominos(int[][] forma) {
        this.forma = forma;
        formasFigura();
    }

    public Tetrominos(Color _color)
    {
        this.colorBorde = _color;
        dandoForma();
        formasFigura();
    }

    public abstract void comportamiento();
    public abstract int clearFilas(Color[][] background, int gridFilas, int gridColumnas);
    public abstract void erraseCelda(Graphics g, int gridCeldasSize, int _figuraX, int _figuraY, Color[][] _background);

    /**
     * Metodo encargado de elegir la forma del Tetromino
     */
    public void dandoForma()
    {
        Random i = new Random();
        int f = i.nextInt(6);
        if(f+1 == 1)
        {   // Forma I
            forma = (new int[][]{{1,1,1,1}});
        }
        else if(f+1 == 2)
        {   // Forma L
            forma = (new int[][]{{1,0},{1,0},{1,1}});
        }
        else if(f+1 == 3)
        {   // forma O
            forma = (new int[][]{{1,1},{1,1}});
        }
        else if(f+1 == 4)
        {   // Forma S
            forma = (new int[][]{{0,1,1},{1,1,0}});
        }
        else if(f+1 == 5)
        {   //Forma T
            forma = (new int[][]{{1,1,1},{0,1,0}});
        }
        else if(f+1 == 6)
        {   // Forma Z
            forma = (new int[][]{{1,1,0},{0,1,1}});
        }
    }

    /*
        @Override
    public void rotarFigura(){
        super.rotarFigura();
        if (this.getWidth()==1){
            this.setX(this.getX()+1);
            this.setY(this.getY()-1);
        }
        else{
            this.setX(this.getX()-1);
            this.setY(this.getY()+1);
        }
    }
     */

    /**
     * Metodo para obtener el color del borde de un tetromino
     * @return  color del borde de un tetromino
     */
    public Color getColorBorde() {
        return colorBorde;
    }


    /**
     * Metodo que contienes las rotaciones de un Tetromino
     */
    private void formasFigura(){
        formasFigura = new int[4][][];
        for (int i = 0; i < 4; i++){

            int a = forma[0].length;
            int b = forma.length;

            formasFigura[i] = new int[a][b];
            for (int j = 0; j < a; j++){
                for (int k = 0; k < b; k++){
                    formasFigura[i][j][k] = forma[b - k - 1][j];
                }
            }
            forma = formasFigura[i];
        }
    }

    /**
     *  Metodo para spawnear el Tetromino sobre el tablero
     * @param gridWidth Ancho del grid
     */
    public void spawn(int gridWidth){
        Random r = new Random();
        rotacionActual = r.nextInt(4);
        forma = formasFigura[rotacionActual];
        y = -getHeight();
        x = r.nextInt(gridWidth-getWidth()) ;
        color = colors[r.nextInt(colors.length)];
    }

    /**
     *  Metodo para sacar la forma del Tetromino
     * @return forma del tetromino
     */
    public int[][] getForma() {
        return forma;
    }

    /**
     *  Metodo para sacar el color del Tetromino
     * @return color del tetromino
     */
    public Color getColor() {
        return color;
    }

    /**
     * Metodo para sacar el alto del Tetromino
     * @return alto del tetromino
     */
    public int getHeight() {
        return forma.length;
    }

    /**
     * Metodo para sacar el ancho del Tetromino
     * @return ancho del tetromino
     */
    public int getWidth() {
        return forma[0].length;
    }

    /**
     * Metodo que saca la posicion x del Tetromino
     * @return posicion en x del tetromino
     */
    public int getX() {
        return x;
    }

    /**
     * Metodo que saca la pocicion y del Tetromino
     * @return posicion en y del tetromino
     */
    public int getY() {
        return y;
    }

    /**
     * Metodo para darle un valor en x
     * @param _x un nuevo valor a la posicion x del tetromino
     */
    public void setX(int _x){
        this.x = _x;
    }

    /**
     * Metodo para darle un valor en y
     * @param _y un nuevo valor a la posicion y del tetromino
     */
    public void setY(int _y){
        this.y = _y;
    }

    /**
     * Metodo que mueve el Tetromino hacia abajo
     */
    public void moveDown(){
        y++;
    }

    /**
     * Metodo que mueve el Tetromino hacia la izquierda
     */
    public void moveLeft(){
        x--;
    }

    /**
     * Metodo que mueve el Tetromino hacia la derecha
     */
    public void moveRight(){
        x++;
    }

    /**
     * Metodo que rota un tetromino
     */
    public void rotarFigura(){
        rotacionActual++;
        if (rotacionActual > 3) rotacionActual = 0;
        forma = formasFigura[rotacionActual];
    }

    /**
     * Metodo para sacar el borde de un tetromino
     * @return el borde del tetromino
     */
    public int getBorde (){
        return y + getHeight();
    }

    /**
     * Metodo que saca el Borde derecho
     * @return el borde derecho del tetromino
     */
    public int getRightBorde(){
        return x + getWidth();
    }

    /**
     * Metodo que saca el Borde izquierdo
     * @return el borde izquierdo del tetromino
     */
    public int getLeftBorde(){
        return x;
    }
}
