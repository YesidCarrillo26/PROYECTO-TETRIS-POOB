package Dominio;

import javax.swing.*;

public class TetrisException extends Exception {

    public TetrisException(String message){
        super(message);
    }
    public final static String VACIO="";
    public final static String ERROR="Tipo erroneo";
    public final static String ERRORSAVE="Error salvar";
    public final static String ERROROPEN="Error al abrir";
    JFileChooser file = new JFileChooser();
}
