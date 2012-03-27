//By Juhani Heliö

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Pelilauta extends JFrame implements MouseListener{
    private Nappula[][] taulukko;
    private Ruutu ruutu;
    private int koko;
    private static Pelilauta lauta;
    
    
    public Pelilauta(int koko, int miinat){
        
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
    
    public void lisaaTapahtumaKuuntelija(JToggleButton nappula, final int y, final int x){
        nappula.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent tapahtuma){
                    ruutu.avaaRuutu(y,x);
                    poistuminen(y, x);
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
            }
        );
        nappula.addMouseListener(this);
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
    
    public void poistuminen(int y, int x){
        if(ruutu.getTila(y, x)==1){
            JOptionPane.showMessageDialog(null, "Hävisit pelin");
        }
        else if(ruutu.voitto()){
            JOptionPane.showMessageDialog(null, "Voitit pelin!");
        }
    }
    public void setup(){
        this.setTitle("miinaharava");
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}