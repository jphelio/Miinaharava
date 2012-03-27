package GUI;

/*
 * Miinaharava
 * By Juhani Heliö
 */

import javax.swing.JToggleButton;

public class Nappula extends JToggleButton{
    
    private int tila=0;
    private boolean avattu=false;
    /**
     * Luo nappulan nimellä a
     * 
     * @param a nappulan nimi
     */
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
    /**
     * Asettaa  
     * 
     * @param b 
     */
    public void setAvattu(boolean b){
        avattu=b;
    }
    /**
     * 
     * 
     * @return palauttaa 1 jos 
     */
    public int getTila(){
        return tila;
    }
}