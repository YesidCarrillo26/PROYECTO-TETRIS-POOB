package Presentacion;
import Dominio.*;
import Dominio.TetrisException;

/*import Dominio.GameThread;
import Dominio.Tetris;
import Dominio.TetrisException;
*/
import javax.swing.*;
import javax.swing.KeyStroke;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TetrisGUI extends JFrame // implements KeyListener
{
    private static TetrisGUI instance;
    private GameThread gameThread;
    private Bufos bufo;
    // JPANEL
    private JPanel inicio = new JPanel();
    private JPanel panel = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel options = new JPanel();
    private JPanel optionsMulti = new JPanel();
    private JPanel jugador1 = new JPanel();
    private JPanel jugador2 = new JPanel();
    private JPanel modo = new JPanel();
    private JPanel panelIndividual = new JPanel();
    private JPanel panelMultijugador = new JPanel();
    private JPanel panelMaquina = new JPanel();
    private JPanel scoreGlobal = new JPanel();
    // TETRIS
    public Tetris tetris1;
    public Tetris tetris2;
    // JBUTTON
    private JButton exit = new JButton();
    private JButton play = new JButton();
    private JButton modos= new JButton();
    private JButton pause = new JButton();
    private JButton player1 = new JButton();
    private JButton multijugador = new JButton();
    private JButton maquina = new JButton();
    private JButton playGame = new JButton();
    private JButton playGameSolo = new JButton();
    private JButton playGameMaquina = new JButton();
    private JButton start = new JButton();
    private JButton menu = new JButton();
    //J TEXT FIELD
    private JTextField nombre1 = new JTextField();
    private JTextField nombre2 = new JTextField();
    // JLABEL
    private JLabel label1 = new JLabel();
    private JLabel label2 = new JLabel();
    private JLabel imagen = new JLabel();
    private JLabel fondoMenu = new JLabel();
    private JLabel puntaje = new JLabel();
    private JLabel puntaje2 = new JLabel();
    private JLabel player = new JLabel();
    private JLabel player2= new JLabel();

    private FondoPanel fondo = new FondoPanel();
    // PERSISTENCIA
    private JMenuBar menuBar;
    private JMenu menu1;
    private JMenuItem abrir, guardar;
    private JFileChooser fileChooser = new JFileChooser();
    private JComboBox comboBox = new JComboBox();
    private String archivo = "Tetris1";
    private String archivo2 = "Tetris2";
    // KEY LISTENER
    private KeyListener evento;
    private KeyListener listen;
    // BOOLEAN
    private boolean modoIndividual;
    private boolean modoMultijugador;
    private boolean modoMaquina;
    private boolean acelerado ;
    private boolean isStoped;



    public static void main(String[] args) {
        TetrisGUI gui = new TetrisGUI();

        gui.setVisible(true);
    }

    /**
     * Metodo para tener la instancia de un TetrisGUI
     * @return instancia tetrisGUI
     */
    public static TetrisGUI getInstance()
    {
        if(instance == null)
        {
            instance = new TetrisGUI();
        }
        return instance;
    }

    public TetrisGUI()
    {
        super("Tetris");
        this.setContentPane(fondo);
        prepareElementosInicio(); // Menu
        prepareOpcionesMenu();
        prepareAcciones();        // botones menu
        controles();
        prepareAccionesOpcionesMenu();
    }

    /**
     * Metodo para preparar elemnetos menu inicio
     */
    public void prepareElementosInicio()
    {
        setLayout(null);
        setLocation(130,0);
        setSize(1220,1200);
        play = new JButton("Play");
        exit = new JButton("Exit");

        inicio.setBounds(440,50,350,700);
        inicio.setBackground(Color.lightGray);
        inicio.setLayout(null);
        play.setBounds(70,425,200,60);
        play.setBackground(Color.green);
        exit.setBounds(70,575,200,60);
        exit.setBackground(Color.red);

        fondoMenu = new JLabel(new ImageIcon("fondomenu.jpg"));
        fondoMenu.setBounds(0,0,350,700);
        imagen = new JLabel(new ImageIcon("tetris1.jpg"));
        imagen.setBounds(20,20,300,300);

        inicio.add(imagen);
        inicio.add(exit);
        inicio.add(play);
        inicio.add(fondoMenu);
        inicio.setVisible(true);

        add(inicio);
        setVisible(true);
    }

    /**
     * Metodo para preparar botones del menu inicio
     */
    public void prepareAcciones()
    {
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                salga();
            }
        });

        play.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                modos();
                prepareAcciones2();
            }
        });
    }

    /**
     * Metodo que prepara las opciones de persistencia
     */
    public void prepareOpcionesMenu(){
        JSeparator s = new JSeparator();
        s.setOrientation(SwingConstants.HORIZONTAL);
        JSeparator s2 = new JSeparator();
        s2.setOrientation(SwingConstants.HORIZONTAL);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menu1=new JMenu("Menu");
        menuBar.add(menu1);

        abrir = new JMenuItem("Abrir");
        menu1.add(abrir);
        menu1.add(s);

        guardar = new JMenuItem("Guardar");
        menu1.add(guardar);
        menu1.add(s2);

        menuBar.add(menu1);
    }

    /**
     * Metodo que prepara las acciones de persistencia
     */
    public void prepareAccionesOpcionesMenu(){
        abrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opcionAbrir();
            }
        });
        guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    opcionGuardar();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    /**
     * Metodo para seleccionar el modo de juego
     */
    public void modos()
    {
        this.remove(inicio);
        repaint();

        player1 = new JButton("Un solo jugador");
        multijugador= new JButton("Multi jugador");
        maquina = new JButton("Maquina");
        modo.setLayout(null);

        player1.setBounds(70,300,200,50);
        player1.setBackground(Color.orange);
        multijugador.setBounds(70,400,200,50);
        multijugador.setBackground(Color.orange);
        maquina.setBounds(70,500,200,50);
        maquina.setBackground(Color.orange);

        modo.setBounds(440,50,350,700);

        modo.add(player1);
        modo.add(multijugador);
        modo.add(maquina);

        modo.add(fondoMenu);
        modo.setVisible(true);
        modo.setBounds(440,50,350,700);

        add(modo);
    }

    /**
     * Metodo para preparar las acciones de los modos de juego
     */
    public void prepareAcciones2(){
        player1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                modoIndividual();
                prepareAcciones3();
            }
        });

        multijugador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                modoMultijugador();
                prepareAcciones3();
            }
        });

        maquina.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                modoMaquina();
                prepareAcciones3();
            }
        });
    }

    /**
     * Metodo que inicia el panel de modo individual
     */
    public void modoIndividual()
    {
        remove(modo);
        repaint();

        panelIndividual.setBounds(440,50,350,700);
        panelIndividual.setVisible(true);
        panelIndividual.setLayout(null);

        nombre1 = new JTextField();
        nombre1.setBounds(70,100,200,40);
        nombre1.setBackground(Color.orange);

        JLabel jugador = new JLabel("Jugador");
        jugador.setBounds(70,70,200,60);

        String [] velocidades = {"Uniforme","Acelerado"};
        comboBox = new JComboBox(velocidades);
        comboBox.setBounds(70,200,200,60);
        comboBox.setBackground(Color.orange);

        playGameSolo = new JButton("Play");
        playGameSolo.setBounds(70,400,200,60);
        playGameSolo.setBackground(Color.green);

        panelIndividual.add(nombre1);
        panelIndividual.add(comboBox);
        panelIndividual.add(playGameSolo);
        panelIndividual.add(fondoMenu);

        add(panelIndividual);
    }

    /**
     * Metodo que inicia el panel de modo multijugador
     */
    public void modoMultijugador()
    {
        modo.setVisible(false);
        nombre1 = new JTextField();
        nombre2 = new JTextField();

        nombre1.setBounds(70,100,200,40);
        nombre1.setBackground(Color.orange);
        nombre2.setBounds(70,250,200,40);
        nombre2.setBackground(Color.orange);

        playGame = new JButton("Play");
        playGame.setBounds(70,400,200,60);
        playGame.setBackground(Color.green);

        panelMultijugador.setLayout(null);
        panelMultijugador.add(nombre1);
        panelMultijugador.add(nombre2);
        panelMultijugador.add(playGame);
        panelMultijugador.add(fondoMenu);
        panelMultijugador.setVisible(true);
        panelMultijugador.setBounds(440,50,350,700);

        add(panelMultijugador);
    }

    /**
     * Metodo que inicia el panel de modo maquina
     */
    public void modoMaquina()
    {
        modo.setVisible(false);
        JTextField nombre = new JTextField();
        String [] velocidades = {"Principiante","Experto"};
        JComboBox comboBox = new JComboBox(velocidades);
        comboBox.setBounds(70,200,200,60);

        comboBox.setBackground(Color.orange);

        playGameMaquina = new JButton("Play");
        playGameMaquina.setBounds(70,400,200,50);
        playGameMaquina.setBackground(Color.green);

        panelMaquina.setLayout(null);
        panelMaquina.add(comboBox);
        panelMaquina.add(playGameMaquina);
        panelMaquina.add(fondoMenu);
        panelMaquina.setVisible(true);
        panelMaquina.setBounds(440,50,350,700);

        add(panelMaquina);
    }

    /**
     * Metodo para iniciar las acciones para imiciar el juego
     */
    public void prepareAcciones3(){
        playGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                remove(panelMultijugador);
                repaint();
                prepareElementos1();
                prepareElementos2();
                opciones(530, 50);   // elementos opciones (play, pause y menu)
                prepareAccionesJuego();     // metodos listener de los botones play, pause y menu
                String s = (String)comboBox.getSelectedItem();
                if(s == "Acelerado")
                {
                    acelerado = true;
                }
                startGame(tetris1);
                startGame(tetris2);
                revalidate();
                modoMultijugador = true;
                revalidate();
            }
        });

        playGameSolo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                remove(panelIndividual);
                repaint();

                prepareElementosSolo();     // elementos panel 1 jugador (tetris y title name)
                opciones(810, 50);   // elementos opciones (play, pause y menu)
                prepareAccionesJuego();     // metodos listener de los botones play, pause y menu
                String s = (String)comboBox.getSelectedItem();
                if(s == "Acelerado")
                {
                    acelerado = true;
                }
                startGame(tetris1);
                revalidate();
                modoIndividual = true;
            }
        });

        playGameMaquina.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                remove(panelMaquina);
                repaint();
                prepareElementos1();
                prepareElementos2();
                opciones(600, 50);   // elementos opciones (play, pause y menu)
                prepareAccionesJuego();     // metodos listener de los botones play, pause y menu
                startGame(tetris1);
                startGame(tetris2);
                revalidate();
                modoMaquina = true;
                revalidate();
            }
        });
    }

    /**
     * Metodo para preparar elementos del juego tetris 1 para modo multijugador
     */
    public void prepareElementos1()
    {
        panel.setBounds(120,50,350,700);
        panel.setBackground(Color.black);
        setVisible(true);

        tetris1 = new Tetris(panel, 10);
        String jug1 = nombre1.getText();
        label1 = new JLabel(jug1);
        setLayout(null);

        jugador1.setBounds(120,10,350,30);
        label1.setBounds(100, 7,300,30);

        scoreGlobal.setBounds(550,200,60,100);
        scoreGlobal.setBackground(Color.orange);

        player = new JLabel("Player 1");
        player.setBounds(810,270,40,30);
        puntaje.setBounds(810,300,40,30);

        scoreGlobal.add(player);
        scoreGlobal.add(puntaje);
        add(scoreGlobal);

        jugador2.add(label2);
        add(jugador2);
        add(options);
        jugador1.add(label1);
        add(jugador1);
    }

    /**
     * Metodo para preparar elementos del juego tetris 2 para modo multijugador
     */

    public void prepareElementos2()
    {
        panel2.setBounds(700,50,350,700);
        panel2.setBackground(Color.black);
        setVisible(true);

        tetris2 = new Tetris(panel2, 10);
        String jug2 = nombre2.getText();
        label2 = new JLabel(jug2);
        setLayout(null);

        jugador2.setBounds(700,10,350,30);
        label2.setBounds(190, 7,300,20);

        player2 = new JLabel("Player 2");
        player2.setBounds(810,330,40,30);
        puntaje2.setBounds(810,360,40,30);

        scoreGlobal.add(player2);
        scoreGlobal.add(puntaje2);

        add(scoreGlobal);
        jugador2.add(label2);
        add(jugador2);
    }

    /**
     * Metodo que prepara el juego para un solo jugador
     */
    public void prepareElementosSolo(){
        panel.setBounds(450,50,350,700);
        panel.setBackground(Color.black);
        setVisible(true);

        tetris1 = new Tetris(panel, 10);
        String jug1 = nombre1.getText();
        label1 = new JLabel(jug1);
        //setLayout(null);

        jugador1.setBounds(450,10,350,30);
        jugador1.setBackground(Color.lightGray);
        label1.setBounds(450, 7,300,20);

        scoreGlobal.setBounds(810,200,60,55);
        scoreGlobal.setBackground(Color.orange);

        player = new JLabel("Player 1");
        player.setBounds(810,220,40,30);
        puntaje.setBounds(810,250,40,30);

        scoreGlobal.add(player);
        scoreGlobal.add(player2);
        scoreGlobal.add(puntaje);

        jugador1.add(label1);
        add(scoreGlobal);
        add(jugador1);
        panel.add(tetris1);
        add(panel);
    }

    /**
     * Metodo para preparar boton pause
     */
    public void opciones(int _x, int _y){

        setLayout(null);
        options.setBounds(_x, _y, 80,95);
        options.setBackground(Color.black);
        options.setVisible(true);

        start = new JButton("Volver");
        start.setBounds(_x,_y,10,50);
        start.setBackground(Color.green);

        pause = new JButton("Pause");
        pause.setBounds(_x,_y,10,50);
        pause.setBackground(Color.orange);

        menu = new JButton("Menu");
        menu.setBounds(_x,_y,10,50);
        menu.setBackground(Color.red);

        options.add(start);
        options.add(pause);
        options.add(menu);

        add(options);
    }

    /**
     * Metodo para controlar los botones del teclado
     */
    public void controlesIniciales()
    {
        evento = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                if( e.getKeyCode() == KeyEvent.VK_D )
                {
                    tetris1.moveFiguraRight();
                }
                else if(e.getKeyCode() == KeyEvent.VK_A)
                {
                    tetris1.moveFiguraLeft();
                }
                else if(e.getKeyCode() == KeyEvent.VK_W)
                {
                    tetris1.rotarFigura();
                }
                else if(e.getKeyCode() == KeyEvent.VK_S)
                {
                    tetris1.dropFigura();
                }
                if(tetris2 != null)
                {
                    if( e.getKeyCode() == KeyEvent.VK_RIGHT )
                    {
                        tetris2.moveFiguraRight();
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_LEFT)
                    {
                        tetris2.moveFiguraLeft();
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_UP)
                    {
                        tetris2.rotarFigura();
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_DOWN)
                    {
                        tetris2.dropFigura();
                    }
                }
            }
        };
        this.addKeyListener(evento);
        setFocusable(true);
    }

    /**
     * Metodo para salir de la app
     */
    public void salga()
    {
        int valor = JOptionPane.showConfirmDialog(this,
                "¿Esta seguro en cerrar la aplicación?",
                "Advertencia",
                JOptionPane.YES_NO_OPTION);
        if(valor == JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }

    /**
     * Metodo para preparar elementos del juego tetris
     */
    public void prepareElementos(){
        opcionesJuego();
    }

    /**
     * Metodo para preparar boton pause
     */
    public void opcionesJuego(){
        options.setBounds(400, 50, 200,750);
        options.setVisible(true);
        options.setLayout(null);

        start = new JButton("Start");
        start.setBounds(400,90,10,50);
        start.setBackground(Color.green);

        pause = new JButton("Pause");
        pause.setBounds(410,300,70,50);
        pause.setBackground(Color.orange);

        menu = new JButton("Menu");
        menu.setBounds(410,500,70,50);
        menu.setBackground(Color.red);

        options.add(start);
        options.add(menu);

        add(options);
    }

    /**
     * Metodo para iniciar el juego
     */
    public void startGame(Tetris _tetris)
    {
        gameThread = new GameThread(_tetris, this);
        _tetris.instaceGameThread(gameThread);
        gameThread.velocidad(acelerado);
        gameThread.start();
        this.add( _tetris );
    }


    /**
     * Metodo para manejar los controles del juego
     */
    public void controles()
    {
        listen = new MyKeyListener();
        addKeyListener(listen);
        setFocusable(true);
    }

    /**
     * Clase que implementa los botones del teclado
     */
    public class MyKeyListener implements KeyListener
    {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
            if( e.getKeyCode() == KeyEvent.VK_D )
            {
                tetris1.moveFiguraRight();
            }
            else if(e.getKeyCode() == KeyEvent.VK_A)
            {
                tetris1.moveFiguraLeft();
            }
            else if(e.getKeyCode() == KeyEvent.VK_W)
            {
                tetris1.rotarFigura();
            }
            else if(e.getKeyCode() == KeyEvent.VK_S)
            {
                tetris1.dropFigura();
            }
            if(tetris2 != null)
            {
                if( e.getKeyCode() == KeyEvent.VK_RIGHT )
                {
                    tetris2.moveFiguraRight();
                }
                else if(e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    tetris2.moveFiguraLeft();
                }
                else if(e.getKeyCode() == KeyEvent.VK_UP)
                {
                    tetris2.rotarFigura();
                }
                else if(e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    tetris2.dropFigura();
                }
            }
        }
    }

    /**
     * Metodo para los controles del teclado
     */
    public void controlesIniciales1()
    {
        InputMap in = this.getRootPane().getInputMap();         // key strokes (accion con el teclado)
        ActionMap ac = this.getRootPane().getActionMap();

        in.put(KeyStroke.getKeyStroke("RIGHT"),"right");    // label right
        in.put(KeyStroke.getKeyStroke("LEFT"),"left");
        in.put(KeyStroke.getKeyStroke("UP"),"up");
        in.put(KeyStroke.getKeyStroke("DOWN"),"down");


        ac.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tetris1.moveFiguraRight();
            }
        });

        ac.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tetris1.moveFiguraLeft();
            }
        });

        ac.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tetris1.rotarFigura();
            }
        });

        ac.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tetris1.dropFigura();
            }
        });
    }

    /**
     * Metodo para los controles del teclado jugador 2
     */
    public void controlesIniciales2()
    {
        InputMap in = this.getRootPane().getInputMap();         // key strokes (accion con el teclado)
        ActionMap ac = this.getRootPane().getActionMap();

        in.put(KeyStroke.getKeyStroke("D"),"d");
        in.put(KeyStroke.getKeyStroke("A"),"a");
        in.put(KeyStroke.getKeyStroke("W"),"w");
        in.put(KeyStroke.getKeyStroke("S"),"s");


        ac.put("d", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tetris2.moveFiguraRight();
            }
        });

        ac.put("a", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tetris2.moveFiguraLeft();
            }
        });

        ac.put("w", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tetris2.rotarFigura();
            }
        });

        ac.put("s", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tetris2.dropFigura();
            }
        });
    }

    /**
     * Metodo para preparar acciones durante el juego tetris
     */
    public void prepareAccionesJuego(){
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if(modoIndividual )
                {
                    getContentPane().remove(options);
                    getContentPane().remove(jugador1);
                    getContentPane().remove(tetris1);
                    getContentPane().remove(scoreGlobal);
                    nombre1.setText(null);
                    nombre1.setText(null);
                    puntaje.setText(null);
                    revalidate();
                    repaint();
                    modoIndividual();
                    modoIndividual = false;
                }
                else if (modoMultijugador )
                {
                    getContentPane().remove(options);
                    getContentPane().remove(jugador1);
                    getContentPane().remove(tetris1);
                    getContentPane().remove(jugador2);
                    getContentPane().remove(tetris2);
                    getContentPane().remove(scoreGlobal);
                    nombre1.setText(null);
                    nombre2.setText(null);
                    player.setText(null);
                    player2.setText(null);
                    puntaje.setText(null);
                    puntaje2.setText(null);
                    revalidate();
                    repaint();
                    modoMultijugador();
                    modoMultijugador = false;
                }
                else if (modoMaquina)
                {
                    getContentPane().remove(options);
                    getContentPane().remove(jugador1);
                    getContentPane().remove(tetris1);
                    getContentPane().remove(jugador2);
                    getContentPane().remove(tetris2);
                    getContentPane().remove(scoreGlobal);
                    nombre1.setText(null);
                    nombre2.setText(null);
                    player.setText(null);
                    player2.setText(null);
                    puntaje.setText(null);
                    puntaje2.setText(null);
                    revalidate();
                    repaint();
                    modoMaquina();
                    modoMaquina = false;
                }

            }
        });

        pause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if(!isStoped)
                    gameThread.stop();
                isStoped = true;
            }
        });

        menu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if(modoIndividual )
                {
                    getContentPane().remove(options);
                    getContentPane().remove(jugador1);
                    getContentPane().remove(tetris1);
                    getContentPane().remove(scoreGlobal);
                    nombre1.setText(null);
                    revalidate();
                    repaint();
                    prepareElementosInicio();
                    modoIndividual = false;
                }
                else if (modoMultijugador )
                {
                    getContentPane().remove(options);
                    getContentPane().remove(jugador1);
                    getContentPane().remove(tetris1);
                    getContentPane().remove(jugador2);
                    getContentPane().remove(tetris2);
                    getContentPane().remove(scoreGlobal);
                    nombre1.setText(null);
                    nombre2.setText(null);
                    revalidate();
                    repaint();
                    prepareElementosInicio();
                    modoMultijugador = false;
                }
                else if (modoMaquina )
                {
                    getContentPane().remove(options);
                    getContentPane().remove(jugador1);
                    getContentPane().remove(tetris1);
                    getContentPane().remove(jugador2);
                    getContentPane().remove(tetris2);
                    getContentPane().remove(scoreGlobal);
                    nombre1.setText(null);
                    nombre2.setText(null);
                    revalidate();
                    repaint();
                    prepareElementosInicio();
                    modoMaquina = false;
                }
            }
        });
    }

    /**
     * Metodo que actualiza el valor del puntaje
     * @param score puntaje del juego
     */
    public void updateScore()
    {
        puntaje.setText("Score " + tetris1.getScore());
        if(tetris2 != null)
            puntaje2.setText("Score " + tetris2.getScore());
    }

    /**
     * Metodo que    da un Tetris
     *//*
    private void opcionGuardar()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setFileFilter(new FileNameExtensionFilter("DAT File",".dat"));
        try
        {
            int seleccion = fileChooser.showSaveDialog(this);
            File file = new File("");
            if(seleccion == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
            }
            Tetris.demeTetris().guarde(file);
        }
        catch (TetrisException a)
        {
            JOptionPane.showMessageDialog(null, a.getMessage());
        }
    }*/
    private void opcionGuardar() throws IOException {
        /*
        tetris1.guarde1(new File(archivo), tetris1);
        tetris2.guarde1(new File(archivo), tetris2);
         */
        tetris1.guarde1(new File(archivo), tetris1);
        tetris2.guarde1(new File(archivo), tetris2);
    }


    /**
     * Metodo para abrir un nuevo archivo
     */
    private void opcionAbrir()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        try
        {
            int seleccion = fileChooser.showOpenDialog(this);
            File file = new File("");
            if (seleccion == JFileChooser.APPROVE_OPTION)
            {
                file = fileChooser.getSelectedFile();
            }
            Tetris.demeTetris().abra(file);
        }
        catch ( TetrisException a)
        {
            JOptionPane.showMessageDialog(null, a.getMessage());
        }
    }

    /**
     * Clase para manejar la imagen de fondo del panel
     */
    class FondoPanel extends JPanel implements Serializable
    {
        private Image fondo;

        /**
         * Metodo para pintar la imagen de fondo del panel
         * @param g atributo g de la clase Graphics
         */
        @Override
        public void paint(Graphics g) {
            fondo = new ImageIcon(getClass().getResource("/Image/fondo2.jpg")).getImage();
            g.drawImage(fondo,0,0,getWidth(),getHeight(),this);
            setOpaque(false);
            super.paint(g);

        }
    }

}

