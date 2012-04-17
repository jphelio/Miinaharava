package gui;

import java.awt.Dimension;
import javax.swing.JToggleButton;
/**
 * Miinaharava
 * @author Juhani Heliö
 */
public class Nappula extends JToggleButton{
    
    private boolean merkitty=false;
    private boolean avattu=false;
    
    /**
     * Luo uuden Nappulan käyttäen JToggleButtonin konstruktoria
     * @param teksti Nappulassa oleva teksti
     */
    public Nappula(String teksti){
        super(teksti);
        this.setPreferredSize(new Dimension(50, 40));
        this.setMinimumSize(new Dimension(50, 40));
    }
    
    /**
     * Tarkistaa onko ruutua avattu, jos on ei tee mitään, jos ei asettaa ruutuun lipun ja laittaa sen pois päältä.
     */
    public void merkitse(){
        if(merkitty!=true && avattu==false){
            this.setEnabled(false);
            merkitty=true;
            this.setText("x");
        }
        else if(merkitty==true && avattu==false){
            this.setEnabled(true);
            merkitty=false;
            this.setText("");
        }
    }
    /**
     * Asettaa ruudun avatuksi tai suljetuksi
     * @param tosi jos true niin avattu, jos false niin suljettu
     */
    public void setAvattu(boolean tosi){
        avattu=tosi;
    }
    
    /**
     * Tarkistaa onko ruutu markitty
     * @return palauttaa arvonaan true jos ruutu on merkitty ja false jos sitä ei ole merkitty.
     */
    public boolean isMerkitty(){
        return merkitty;
    }
}