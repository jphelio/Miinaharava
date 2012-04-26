package gui;
import logiikka.Ruutu;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Miinaharava
 * Pelilauta -luokka luo uuden pelialueen, jossa on miinoja ja tyhjiä ruutuja. Miinat on sekoitettuna laudalla.
 * @author Juhani Heliö
 */
public class Pelilauta extends JPanel{
    private Nappula[][] taulukko;
    private Ruutu ruutu;
    private int koko;
    private static final Color[] varit={Color.blue, Color.green, Color.red, Color.BLACK, Color.CYAN, Color.orange, Color.yellow, Color.white};
    private Peli peli;
    
    /**
     * Pelilaudan konstruktori. Luo uuden pelilaudan, joka on koko*koko ja jossa on miinat määrä miinoja.
     * @param koko Kentän koko. Kenttä on neliö.
     * @param miinat Miinojen määrä.
     */
    public Pelilauta(Peli peli, int koko, int miinat){        
        this.peli=peli;
        
        this.koko=koko;
        
        taulukko = new Nappula[koko][koko];
        
        ruutu=new Ruutu(koko, miinat);
        setLayout(new GridLayout(koko, koko));
        for(int y=0;y<koko;y++){
            for(int x=0;x<koko;x++){
                taulukko[y][x]=new Nappula("");
                lisaaTapahtumaKuuntelija(taulukko[y][x], y, x);
                add(taulukko[y][x]);
            }
        }
    }
    
    /**
     * Lisää uuden actionListenerin ruutuun (y,x).
     * @param nappula Nappula, johon actionListener lisätään.
     * @param y nappulan x-koordinaatti taulukossa.
     * @param x nappulan y-koordinaatti taulukossa.
     */
    private void lisaaTapahtumaKuuntelija(JToggleButton nappula, final int y, final int x){
        nappula.addActionListener(
            new ActionListener(){
            @Override
                public void actionPerformed(ActionEvent tapahtuma){
                    ruutu.avaaRuutu(y,x);
                    if(ruutu.getTila(y, x)!=Ruutu.MIINA){
                        muotoilut(y, x);
                    }
                    if(ruutu.getNaapuriMiinat(y, x)==0){
                        avaaTyhjatRuudut(y, x);
                    }
                    poistuminen(y, x);
                 }
            }
        );
        nappula.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Nappula apu=(Nappula)e.getSource();
                if(e.getButton()==MouseEvent.BUTTON3){
                    apu.muutaTila();
                }
                else if(e.getButton()==MouseEvent.BUTTON1 && !apu.isMerkitty()){
                    apu.setAvattu(true);
                }
            }
        });
    }
    
    /**
     * Muotoilee (y,x)-kordinaateissa olevn nappulassa olevan numeron ympäröivien miinojen lukumäärän mukaan
     * @param y Muotoiltavan nappulan x-koordinaatti.
     * @param x Muotoiltavan nappulan y-koordinaatti.
     */
    private void muotoilut(int y, int x){
        int miinaLkm=ruutu.getNaapuriMiinat(y, x);
        if(miinaLkm==0){
            taulukko[y][x].setText("");
        }
        else if(ruutu.getTila(y, x)==Ruutu.MIINA){
            taulukko[y][x].setText("*");
            taulukko[y][x].setBackground(Color.magenta);
        }
        else{
            taulukko[y][x].setText(""+miinaLkm);
            taulukko[y][x].setBackground(varit[miinaLkm-1]);
        }
        taulukko[y][x].setEnabled(false);
    }
    
    /**
     * Avaa ruudun (y,x) ympärillä olevat ruudut, jos ruudun (x,y) ympärillä ei ole miinoja.
     * @param y ruudun x-koordinaatti.
     * @param x ruudun y-koordinaatti.
     */
    private void avaaTyhjatRuudut(int y, int x){
        int minY=Math.max(0, y-1);
        int maxY=Math.min(y+1, koko-1);
        int minX=Math.max(0,x-1);
        int maxX=Math.min(x+1, koko-1);
            for(int a=minY; a<=maxY; a++){
                for(int b=minX;b<=maxX;b++){
                    if(ruutu.getTila(a, b)!=Ruutu.MIINA && ruutu.getNaapuriMiinat(y, x)==0){
                        avaaRuutu(a, b);                        
                    }
                }
            }
    }
    
    /**
     * Avaa ruudun (a, b) koordinaatteista.
     * @param a x-koordinaatti
     * @param b y-koordinaatti
     */
    private void avaaRuutu(int a, int b){
            taulukko[a][b].setSelected(true);
            taulukko[a][b].setEnabled(false);
            if(ruutu.getTila(a, b)!=Ruutu.MIINA){
                muotoilut(a, b);
            }
            ruutu.avaaRuutu(a, b);
    }
    
    /**
     * Jos pelaaja häviää pelin, näyttää kaikki ruudut, joissa oli miina.
     */
    private void naytaMiinat(){
        for(int i=0; i<koko;i++){
            for(int j=0; j<koko;j++){
                if(ruutu.getTila(j, i)==Ruutu.MIINA){
                    taulukko[j][i].setText("*");
                    taulukko[j][i].setBackground(Color.red);
                }
            }
        }
    }
    /**
     * Tarkistaa, loppuuko peli, kun ruutua (y,x) painetaan.
     * @param y ruudun x-koordinaatti.
     * @param x ruudun y-koordinaatti.
     */
    private void poistuminen(int y, int x){
        if(ruutu.getTila(y, x)==Ruutu.MIINA){
            naytaMiinat();
            JOptionPane.showMessageDialog(null, "Hävisit pelin");
            peli.loppuKysymys();
        }
        else if(ruutu.voitto()){
            JOptionPane.showMessageDialog(null, "Voitit pelin!");
            peli.loppuKysymys();
        }
    }
}