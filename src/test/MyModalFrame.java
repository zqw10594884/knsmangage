package test;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class MyModalFrame extends JFrame implements WindowListener{
    private JFrame frame = null;
    private boolean modal = false;
    private String title = null;

    public MyModalFrame(){
        this(null, false);
    }

    public MyModalFrame(JFrame frame){
        this(frame, false);
    }

    public MyModalFrame(JFrame frame, boolean modal){
        this(frame, modal, "");
    }

    public MyModalFrame(JFrame frame, boolean modal, String title){
        super(title);
        this.frame = frame;
        this.modal = modal;
        this.title = title;
        this.init();
    }

    private void init(){
        if(modal)
            frame.setEnabled(false);
        this.addWindowListener(this);
    }

    public void windowOpened(WindowEvent windowEvent){
    }

    public void windowClosing(WindowEvent windowEvent){
        if(modal)
            frame.setEnabled(true);
    }

    public void windowClosed(WindowEvent windowEvent){
    }

    public void windowIconified(WindowEvent windowEvent){
    }

    public void windowDeiconified(WindowEvent windowEvent){
    }

    public void windowActivated(WindowEvent windowEvent){
    }

    public void windowDeactivated(WindowEvent windowEvent){
        if(modal)
            this.requestFocus();
    }
}