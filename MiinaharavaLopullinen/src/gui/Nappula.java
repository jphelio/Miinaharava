package gui;

/*
 * Miinaharava
 * By Juhani Heliö
 */

import java.awt.Dimension;
import javax.swing.JToggleButton;

public class Nappula extends JToggleButton{
    
    private boolean merkitty=false;
    private boolean avattu=false;
    
    public Nappula(String a){
        super(a);
        this.setPreferredSize(new Dimension(50, 40));
        this.setMinimumSize(new Dimension(50, 40));
    }
    public void muutaTila(){
        if(merkitty!=true && avattu==false){
            this.setEnabled(false);
            merkitty=true;
            this.setText("x");
        }
        else if(merkitty==true && avattu==false){
            this.setEnabled(true);
            merkitty=false;
            this.setText(" ");
        }
    }
    public void setAvattu(boolean b){
        avattu=b;
    }
    public boolean isMerkitty(){
        return merkitty;
    }
}