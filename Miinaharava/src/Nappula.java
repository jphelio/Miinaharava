//By Juhani Heliö

import javax.swing.JToggleButton;

public class Nappula extends JToggleButton{
    
    private int tila=0;
    private boolean avattu=false;
    
    public Nappula(String a){
        super(a);
    }
    public void muutaTila(){
        if(tila==0 && avattu==false){
            this.setEnabled(false);
            tila=1;
            this.setText("x");
        }
        else if(tila==1 && avattu==false){
            this.setEnabled(true);
            tila=0;
            this.setText(" ");
        }
    }
    public void setAvattu(boolean a){
        avattu=a;
    }
    public int getTila(){
        return tila;
    }
}