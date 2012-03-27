package GUI;

/*
 * Miinaharava
 * By Juhani Heliö
 */
import Logiikka.Ruutu;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Pelilauta extends JFrame implements MouseListener{
    private Nappula[][] taulukko;
    private Ruutu ruutu;
    private int koko;
    private Pelilauta lauta;
    /**
     * Luo uuden pelilaudan
     * 
     * @param koko pelialueen koko
     * @param miinat miinojen määrä
     */    
    public Pelilauta(int koko, int miinat){
        this.koko=koko;
        
        taulukko = new Nappula[koko][koko];
        
        ruutu=new Ruutu(koko, miinat);
        
        setLayout(new GridLayout(koko, koko));
        for(int y=0;y<koko;y++){
            for(int x=0;x<koko;x++){
                taulukko[y][x]=new Nappula(" ");
                lisaaTapahtumaKuuntelija(taulukko[y][x], y, x);
                add(taulukko[y][x]);
            }
        }
    }
    /**
     * Lisää nappulaan actionListenerin
     * 
     * @param nappula nappula johon actionListener lisätään
     * @param y ruudun x-koordinaatti
     * @param x ruudun y-koordinaatti
     */
    private void lisaaTapahtumaKuuntelija(JToggleButton nappula, final int x, final int y){
        nappula.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent tapahtuma){
                    ruutu.avaaRuutu(x,y);
                    if(ruutu.getTila(x, y)!=1){
                        muotoilut(x, y);
                    }
                    if(ruutu.getNaapuriMiinat(x, y)==0){
                        avaaTyhjatRuudut(x, y);
                    }
                    poistuminen(x, y);
                }
            }
        );
        nappula.addMouseListener(this);
    }
    /**
     * Lisää muotoilut ruutuihin
     * 
     * @param y ruudun x-koordinaatti
     * @param x ruudun y-koordinaatti
     */
    private void muotoilut(int y, int x){
        if(ruutu.getNaapuriMiinat(y, x)==0){
            taulukko[y][x].setText("");
        }
        else{
            taulukko[y][x].setText(""+ruutu.getNaapuriMiinat(y, x));
        }                    
        if(ruutu.getNaapuriMiinat(y, x)==1){
            taulukko[y][x].setBackground(Color.blue);
        }
        else if(ruutu.getNaapuriMiinat(y, x)==2){
            taulukko[y][x].setBackground(Color.green);
        }
        else if(ruutu.getNaapuriMiinat(y, x)==3){
            taulukko[y][x].setBackground(Color.red);
        }
        else if(ruutu.getNaapuriMiinat(y, x)==4){
            taulukko[y][x].setBackground(Color.darkGray);
        }
        else if(ruutu.getNaapuriMiinat(y, x)==5){
            taulukko[y][x].setBackground(Color.green);
        }
        else if(ruutu.getNaapuriMiinat(y, x)==6){
            taulukko[y][x].setBackground(Color.orange);
        }
        else if(ruutu.getNaapuriMiinat(y, x)==7){
            taulukko[y][x].setBackground(Color.yellow);
        }
        else{
            taulukko[y][x].setBackground(Color.white);
        }    
        taulukko[y][x].setEnabled(false);
    }
    /**
     * Avaa tyhjät ruudut painetun ruudun ympäriltä.
     * 
     * @param y ruudun x-koordinaatti
     * @param x ruudun y-koordinaatti
     */
    public void avaaTyhjatRuudut(int y, int x){
        int minY=Math.max(0, y-1);
        int maxY=Math.min(y+1, koko-1);
        int minX=Math.max(0,x-1);
        int maxX=Math.min(x+1, koko-1);
        for(int a=minY; a<=maxY; a++){
            for(int b=minX;b<=maxX;b++){
                if(ruutu.getTila(a, b)!=1){
                    taulukko[a][b].setSelected(true);
                    taulukko[a][b].setEnabled(false);
                    if(ruutu.getTila(y, x)!=1){
                        muotoilut(a, b);
                    }
                    ruutu.avaaRuutu(a, b);
                }
            }
        }
    }
    public void mouseClicked(MouseEvent e) {
        Nappula apu=(Nappula)e.getSource();
        if(e.getButton()==e.BUTTON3){
            apu.muutaTila();
        }
        else if(e.getButton()==e.BUTTON1 && apu.getTila()!=1){
            apu.setAvattu(true);
        }
    }
    
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {};  
    
    /**
     * Tarkistaa, onko peli lopussa
     * 
     * @param y ruudun x-koordinaatti
     * @param x ruudun y-koordinaatti
     */
    public void poistuminen(int y, int x){
        if(ruutu.getTila(y, x)==1){
            JOptionPane.showMessageDialog(null, "Hävisit pelin");
            this.kysymys();
        }
        else if(ruutu.voitto()){
            JOptionPane.showMessageDialog(null, "Voitit pelin!");
            this.kysymys();
        }
    }
    /**
     * Tekee käyttäjälle kysymyksen uudesta pleistä
     * 
     */
    public void kysymys(){
        int a=JOptionPane.showConfirmDialog(rootPane, "Uusi peli?");
            if(a==0){
                this.dispose();
                uusiPeli();
            }
            else{
                System.exit(0);
            }
    }
    /**
     * Luo uuden pelin 
     */
    public static void uusiPeli(){
        int b=1;
        int d=1;
        boolean tosi=false;
        while(!tosi){
            String g=JOptionPane.showInputDialog("Anna pelialueen koko");
            try{
                 b=Integer.parseInt(g);
                 tosi=true;
            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Virhe! Koon pitää olla luku.");
            }
        }
        tosi=false;
        while(!tosi){
            String c=JOptionPane.showInputDialog("Anna miinojen määrä");
            try{
                d=Integer.parseInt(c);
                tosi=true;
            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Virhe! Miinojen määrän pitää olla luku.");
            }
        }
        Pelilauta lauta=new Pelilauta(b, d);
        lauta.setTitle("Miinaharava");
        lauta.pack();
        lauta.setDefaultCloseOperation(EXIT_ON_CLOSE);
        lauta.setVisible(true);
    }    
    
    public static void main(String[] args) {
        uusiPeli();
    }
}