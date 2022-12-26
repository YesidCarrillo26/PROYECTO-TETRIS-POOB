package Dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Color;

    public abstract class Bufos {
        private Color color;
        private int x;
        private int y;
        private int[] forma = {0};
        private int[][] formas;
        protected boolean isDown = false;
        protected GameThread gameThread;

        public Bufos(int[] _forma) {
            this.forma = _forma;
        }

        /**
         * Metodo que obtiene la forma del bufo
         * @return forma del bufo
         */
        public int[] getForma() {
            return forma;
        }

        public Bufos(Color _color){
            this.color = _color;
            spawnBufo(10,20);
        }


        public abstract void comportamiento();
        public abstract int getVelocidad();

        /**
         * Metodo qye consigue una instancia del gameThread
         * @param _gameThread instancia del gameThread
         */
        public void instanceGamethread(GameThread _gameThread){
            this.gameThread = _gameThread;
        }


        /**
         * Metodo para spawnear un bufo
         * @param gridWidth
         * @param gridHeight
         */
        public void spawnBufo(int gridWidth, int gridHeight){
            Random r = new Random();
            y = r.nextInt(gridHeight-getHeight());
            x = r.nextInt(gridWidth-getWidth());
            //color = colors[r.nextInt(colors.length)];
        }

        /**
         * Metodo para obtener el color de un bufo
         * @return color de un bufo
         */
        public Color getColor() {
            return color;
        }

        /**
         * Metodo para setear el color de un bufo
         * @param color de un bufo
         */
        public void setColor(Color color) {
            this.color = color;
        }

        /**
         * Metodo para obtener la posicion en X de un bufo
         * @return posicion en X de un bufo
         */
        public int getX() {
            return x;
        }

        /**
         * Metodo para obtener la posicion en Y de un bufo
         * @return posicion en Y de un bufo
         */
        public int getY() {
            return y;
        }

        /**
         * Metodo para sacar el alto del Tetromino
         * @return alto del tetromino
         */
        public int getHeight() {
            //System.out.println(tetris.getGridCeldasSize());
            return forma.length;
        }

        /**
         * Metodo para sacar el ancho del Tetromino
         * @return ancho del tetromino
         */
        public int getWidth() {
            return forma[0];
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

