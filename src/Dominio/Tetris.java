package Dominio;

//import Presentacion.TetrisGui;
import Presentacion.*;
import Dominio.TetrisException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase que crea el tablero para jugar el juego Tetris
 * @authors Yesid Carrillo, Juan David Parroquiano
 * @version final 13/12/2021
 */
public class Tetris extends JPanel implements Serializable
{
    private int gridFilas;
    private static int gridColumnas;
    private int gridCeldasSize;
    private Color[][] background;
    private Color[][] background2;
    private static JPanel panel;
    private Tetrominos figura;
    private Tetrominos[] figuras;
    private Bufos bufo;
    private Bufos[] bufos;
    private static Tetris estado = null;
    private int x;
    private int y;
    private int bufoX;
    private int bufoY;
    private Color color;
    private GameThread gameThread;
    private boolean isDown;
    private boolean sobrepasa=true;
    private int score;
    private int a =0 ;
    private int b = 0;
    private Integer[][] finalsPosi;


    public int getGridCeldasSize() {
        return gridCeldasSize;
    }

    public Tetris(JPanel _panel, int _columnas)
    {
        try{
            _panel.setVisible(false);
            this.panel = _panel;
            this.setBounds(panel.getBounds() );      // tamaño tablero
            this.setBackground( panel.getBackground() );  // color tablero

            gridColumnas = _columnas;
            gridCeldasSize = panel.getBounds().width / gridColumnas;
            gridFilas = panel.getBounds().height / gridCeldasSize;

            background = new Color[gridFilas][gridColumnas];
            background2 = new Color[700][350];
            figuras = new Tetrominos[]{new TetrominoClassic(), new TetrominoUseless(), new TetrmonoWinner(), new TetrominoBomb()};
            //bufos = new Bufos[]{new BuffoStopTIme(),new BuffoStopDiece(), new BuffoSlow(), new Buffo2x()};
            bufos = new Bufos[]{new BuffoStopTIme(), new BuffoSlow(), new Buffo2x() };
        }
        catch(Exception e){

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(bufo!=null){
            drawBufo(g,color);
        }
        drawBlock(g);
        drawBackground(g);
        /*if(checkBoton() && figura instanceof TetrominoBomb) {
            erraseBloock(g);
        }*/
    }

    /**
     * Metodo para dibujar una celda
     * @param g instancia de la clase Graphics
     */
    private void drawBlock(Graphics g){
        sobrepasa = true;
        for (int i = 0; i < figura.getHeight(); i++)
        {
            for (int j = 0; j < figura.getWidth();j++)
            {
                if (figura.getForma()[i][j]==1)
                {
                    int x = (figura.getX() + j) * gridCeldasSize;
                    int y = (figura.getY() + i) * gridCeldasSize;
                    a = x;
                    b = y;
                    int c = x-35;
                    int d = x+35;
                    int e = y+35;
                    //(c,d)
                    //(c,e)
                    drawCelda(g, figura.getColor(), figura.getColorBorde(), x, y, true);
                    //if(i>14 && j>14)

                    if(!checkBoton() && figura instanceof TetrominoBomb)
                    {
                        figura.getForma()[i][j] = 0;

                        //System.out.println("i:"+x+"j:"+y);

                        //finalsPosi = new Integer[][]{{x,y}};

                        //System.out.println("dibujando en "+(figura.getX()+j)+","+(figura.getY()+i));
                        //erraseBloock(g,(figura.getX()+j),(figura.getY()+i));
                        //background[figura.getY()+i][figura.getX()+j] = null;
                    }
                    //if(!checkBoton() && figura instanceof TetrominoBomb)
                      //  System.out.println("cayó"+i+","+j);
                    if(bufo != null && sobrepasa)
                        if((figura.getX() + j) == bufo.getX() && (figura.getY() + i) == bufo.getY())
                        {
                            this.isSobrepasa();
                        }
                }
            }

        }

    }
    public void posifinal()
    {
        for(Integer[] i :finalsPosi)
        {
            System.out.println(i[0]);
        }
        System.out.println("finalPosi:"+finalsPosi);
        //System.out.println(figura.getX()+","+figura.getY());
    }


    public void erraseBloock(Graphics g, int _figuraX, int _figuraY)
    {
        //figura.erraseCelda(g, gridCeldasSize, _figuraX, _figuraY, background);
    }

    /**
     *
     * @param g
     * @param c
     * @param x
     * @param y
     */
    public void drawBufo(Graphics g, Color c){
        for (int j = 0; j < bufo.getHeight();j++)
        {
            if (bufo.getForma()[j]==0){
                bufoX = (bufo.getX() + j) * gridCeldasSize;
                bufoY = (bufo.getY() + j) * gridCeldasSize;
                drawCelda(g, bufo.getColor(), figura.getColorBorde(), bufoX, bufoY, false);
            }
        }
    }

    /**
     * Metodo que maneja los tetraminos al fondo del tablero
     * @param g instancia de la clase Graphics
     */
    private void drawBackground(Graphics g){
        Color color;
        int u = 0;
        for (int i = 0; i < gridFilas; i++)
        {
            for (int j = 0; j < gridColumnas; j++)
            {
                //System.out.println("background: "+i+","+j);
                color = background[i][j];
                if (color != null){
                    int x = j * gridCeldasSize;
                    int y = i * gridCeldasSize;
                    //drawCelda(g, color, figura.getColorBorde(), x, y);
                    u ++;
                    if((figura instanceof TetrominoBomb)&& !checkBoton())
                    {

                    }
                    else {
                        drawCelda(g, color, Color.white, x, y, false);
                    }
                }
            }
        }
    }

    /**
     * Metodo encargado de validar si una figura pasa por encima de un Bufo
     */
    public void  isSobrepasa()
    {
        //System.out.println("Figura "+figura.getX()+","+figura.getY());
        //System.out.println("Bufo"+bufo.getX()+","+bufo.getY());
        //background[bufo.getY()][bufo.getX()] =  null;
        //if(background[bufo.getY()][bufo.getX()] != Color.black)
        if(bufo.getColor() != Color.black)
        {
            bufo.setColor(Color.black);
            //background[bufo.getY()][bufo.getX()] = Color.black;
            System.out.println(bufo.getY() + ";" + bufo.getX());
            bufo.instanceGamethread(this.gameThread);
            bufo.comportamiento();
            if (bufo instanceof BuffoStopDiece)
                isDown = true;
            sobrepasa = false;
            //repaint();
        }
        /*if (figura.getX() == bufo.getX() || figura.getX() + 1 == bufo.getX() || figura.getX() + 2 == bufo.getX()) {
            if (figura.getY() == bufo.getY() || figura.getY() + 1 == bufo.getY() || figura.getY() + 2 == bufo.getY()) {
                System.out.println("sik");
                if(!bufo.getColor().equals(Color.black)) {
                    changeColorBufo();
                    bufo.instanceGamethread(this.gameThread);
                    bufo.comportamiento();
                    if (bufo instanceof BuffoStopDiece)
                        isDown = true;
                    sobrepasa = true;
                }
            }
        }*/
        //return sobrepasa;
    }

    /**
     * Metodo para cambiar el color de un bufo
     */
    public void changeColorBufo(){
        bufo.setColor(Color.black);
        repaint();
    }

    /**
     * Metodo que obtiene instancia de gamwthread
     * @param _gameThread instancia de gamwthread
     */
    public void instaceGameThread(GameThread _gameThread)
    {
        this.gameThread = _gameThread;
    }

    /**
     * Metodo para mover el tetramonio a la derecha
     */
    public void moveFiguraRight(){
        if (figura == null) return;
        if (!checkFiguraRight())
            return;
        figura.moveRight();
        repaint();
    }

    /**
     *  Metodo para mover el tetramonio a la izquierda
     */
    public void moveFiguraLeft(){
        if (figura == null) return;
        if (!checkFiguraLeft())
            return;
        figura.moveLeft();
        repaint();
    }

    /**
     * Metodo para mover el tetramonio directamente hacia abajo
     */
    public void dropFigura(){
        if (figura == null) return;
        while (checkBoton()){
            figura.moveDown();
        }
        repaint();
    }

    /**
     * Metodo para obtener un bufo
     * @return un bufo
     */
    public Bufos getBufo() {
        return bufo;
    }

    /**
     * Metodo para rotar el tetramonio
     */
    public void rotarFigura(){
        if (figura == null) return;
        figura.rotarFigura();
        if (figura.getLeftBorde() < 0)
            figura.setX(0);
        if (figura.getRightBorde() >= gridColumnas)
            figura.setX(gridColumnas - figura.getWidth());
        if (figura.getBorde() >= gridFilas)
            figura.setY(gridFilas - figura.getHeight());
        repaint();
    }

    /**
     * Metodo pra limpiar una fila
     */
    public int clearFilas() {
        return figura.clearFilas(background, gridFilas, gridColumnas);
    }

    /**
     * Metodo para dibujar el Tetramino y el tablero tetris
     * @param g instancia de la clase Graphics
     * @param color color de la celda
     * @param x posicion x en el tablero
     * @param y posicion y en el tablero
     */
    private void drawCelda(Graphics g, Color color, Color _colorBorde, int x, int y, boolean _newFicha){
        g.setColor(color);
        g.fillRect(x, y, gridCeldasSize, gridCeldasSize);  // figura

        if(_newFicha){
            g.setColor(_colorBorde);
            g.drawRect(x, y, gridCeldasSize, gridCeldasSize);}  // tablero
        else
        {
            g.setColor(Color.black);
            g.drawRect(x, y, gridCeldasSize, gridCeldasSize);  // tablero
        }
    }

    /**
     * Metodo para mover el tetromino hacia abajo con thread
     * @return si se puede mover un bloque hacia abajo
     */
    public boolean moveBlockDown(){
        if (checkBoton() == false) {
            return false;
        }
        figura.moveDown();
        repaint();
        return true;
    }

    /**
     * Metodo para spawnear Tetrominos
     */
    public void spawnFigura()
    {
        Random r = new Random();
        int w = r.nextInt(figuras.length);
        figura = figuras[w]; //figuras[1] ;
        figura.spawn(gridColumnas);
    }

    /**
     * Metodo para spawnear Bufo
     */
    public void spawnBufo()
    {
        Random r = new Random();
        bufo = bufos[r.nextInt(bufos.length)];
        //bufo = bufos[3];
        bufo.spawnBufo(10, 20);
    }

    /**
     * Metodo para obtener un tetromino
     * @return figura un tetromino
     */
    public Tetrominos getFigura() {
        return figura;
    }

    /**
     * Metodo que verifica cuando se toca fondo del tablero
     * @return un boolean que indica el limite inferior del tablero
     */
    private boolean checkBoton(){
        if (figura.getBorde() == gridFilas){
            return false;
        }
        int[][]forma = figura.getForma();
        int a = figura.getWidth();
        int b = figura.getHeight();

        for (int i = 0; i < a;i++){
            for (int j = b - 1; j >= 0;j--){
                if(forma[j][i] != 0){
                    int x = i + figura.getX();
                    int y = j + figura.getY() + 1;
                    if(y < 0) break;
                    if(background[y][x] != null)
                        return false;
                    break;
                }
            }
        }
        return true;
    }

    /**
     * Metodo que verifica que no se salga el tetromino a la derecha
     * @return un boolean que indica el limite derecho del tablero
     */
    public boolean checkFiguraRight(){
        if(figura.getRightBorde() == gridColumnas)
            return false;
        int[][]forma = figura.getForma();
        int a = figura.getWidth();
        int b = figura.getHeight();

        for (int i = 0; i < b;i++){
            for (int j = a - 1; j >= 0;j--){
                if(forma[i][j] != 0){
                    int x = i + figura.getX();
                    int y = j + figura.getY();
                    if(y < 0) break;
                    if(background[y][x] != null)
                        return false;
                    break;
                }
            }
        }
        return true;
    }

    /**
     *  Metodo que verifica que no se salga el tetromino a la izquierda
     * @return un boolean que indica el limite izquierdo del tablero
     */
    private boolean checkFiguraLeft(){
        if (figura.getLeftBorde() == 0)
            return false;
        int[][]forma = figura.getForma();
        int a = figura.getWidth();
        int b = figura.getHeight();

        for (int i = 0; i < a;i++){
            for (int j = 0; j < b;j++){
                if(forma[j][i] != 0){
                    int x = i + figura.getX() - 1;
                    int y = j + figura.getY();
                    if(y < 0) break;
                    if(background[y][x] != null)
                        return false;
                    break;
                }
            }
        }
        return true;
    }

    /**
     * Metodo para mover una figura al Background
     */
    public void moveFiguraToBackground(){
        int[][] forma = figura.getForma();
        int a = figura.getHeight();
        int b = figura.getWidth();

        int posX = figura.getX();
        int posY = figura.getY();
        Color color = figura.getColor();

        for (int i = 0; i < a; i++){
            for(int j = 0; j < b; j++){
                if (forma[i][j] == 1){
                    background[i + posY][j + posX] = color;
                }
            }
        }
    }

    /**
     *  Metodo que verifica cuando los tetrominos se salen del tablero
     * @return fuera del limite superior del tablero
     */
    public boolean fueraDeLimite(){
        if (figura.getY() < 0){
            figura = null;
            return true;
        }
        return false;
    }

    /**
     * Metodo que guarda el puntaje del tetris
     * @param _score puntaje del tetris
     */
    public void score(int _score)
    {
        this.score = _score;
    }

    /**
     * Metodo que obtiene el puntaje del tetris
     * @return puntaje del tetris
     */
    public int getScore() {
        return score;
    }

    /**
     * Metodo para guardar un tetris
     * @param _file un archivo para guardar el tetris
     * @throws TetrisException
     */
    public void guarde(File _file) throws TetrisException
    {
        try
        {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(_file));
            //os.writeObject(this);
            os.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new TetrisException(TetrisException.ERROR);
        }
    }


    public void guarde1(File _file, Tetris _tetris) throws IOException {
        ObjectOutputStream archivo = new ObjectOutputStream(new FileOutputStream(_file));
        archivo.writeObject(_tetris);
        archivo.close();
    }

    public void agregar(File _file) throws IOException {
        ObjectOutputStream copiaArchivo = new ObjectOutputStream(new FileOutputStream("copiaArchivo.txt"));
        ObjectInputStream archivo = new ObjectInputStream( new FileInputStream(_file));
        try{
            while(true){
                String contacto = (String) archivo.readObject();
                copiaArchivo.writeObject(contacto);
            }
        } catch (Exception e) {
            Scanner sc = new Scanner(System.in);
            String nuevoContacto = sc.next();
            copiaArchivo.writeObject(nuevoContacto);    // copia el archivo

            copiaArchivo.close();
            archivo.close();

            File fArchivo = new File("");                           // manipular archivo fisicamente
            File fCopiaArchivo = new File("copiaArchivo.txt");

            fArchivo.delete();
            fCopiaArchivo.renameTo(fArchivo);
        }
    }

    /**
     * Metodo que permite abrir un tetris
     * @param _file el archivo de tetris a abrir
     * @throws TetrisException
     */
    public void abra(File _file) throws TetrisException
    {
        try
        {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(_file));
            //Tetris.cambieTetris((Tetris)in.readObject());
            in.close();
        }
        catch (IOException e)//|ClassNotFoundException e)
        {
            e.printStackTrace();
            throw new TetrisException(TetrisException.ERROROPEN);
        }

    }

    /**
     * Metodo que obtiene el estado del tetris
     * @param _tetris estado del tetris
     */
    public static void cambieTetris(Tetris _tetris)
    {
        estado = _tetris;
    }

    /**
     * Metodo que da un tetris si existe si no lo crea
     * @return da un tetris si existe si no lo crea
     */
    public static Tetris demeTetris(){
        if (estado==null){
            estado = new Tetris(panel, gridColumnas);
        }
        return estado;
    }


}
