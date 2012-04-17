package gui;

import java.awt.event.*;
import javax.swing.*;
/**
 * Miinaharava
 * @author Juhani Heliö
 */
public class Peli extends JFrame{
    private JMenuBar palkki;
    private JMenu peli;
    private JMenu uusiPeli;
    private JMenuItem helppo;
    private JMenuItem keskitaso;
    private JMenuItem vaikea;
    private JMenuItem custom;
    private JMenuItem lopetus;
    private Pelilauta lauta;
    
    /**
     * Luo uuden pelialueen, johon liitetään ruudukko ja valikot.
     * @param koko pelialueen ruudukon koko koko
     * @param miinat ruukossa olevien miinojen määrä
     */
    public Peli(int koko, int miinat){        
        lauta=new Pelilauta(this, koko, miinat);
        
        palkki = new JMenuBar();
        
        peli = new JMenu("Peli");
        
        uusiPeli=new JMenu("Uusi peli");
        
        helppo=new JMenuItem("Helppo");
        helppo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                uusiLauta(10, 10);
            }
        });
        
        keskitaso=new JMenuItem("Keskitaso");
        keskitaso.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                uusiLauta(15,50);
            }
        });
        
        vaikea=new JMenuItem("Vaikea");
        vaikea.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                uusiLauta(20,100);
            }
        });
        
        custom=new JMenuItem("Custom");
        custom.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                uusiPeli();
            }
        });
        
        lopetus=new JMenuItem("Lopetus");
        lopetus.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                System.exit(0);
            }
        });
        uusiLauta(koko, miinat);
        palkki.add(peli);
        peli.add(uusiPeli);
        uusiPeli.add(helppo);
        uusiPeli.add(keskitaso);
        uusiPeli.add(vaikea);
        uusiPeli.add(custom);
        peli.add(lopetus);
        this.setJMenuBar(palkki);
        
        this.setTitle("Miinaharava");
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    /**
     * Luo uuden pelilaudan
     * @param koko pelilaudan koko
     * @param miinat pelilaudalla olevat miinat
     */
    private void uusiLauta(int koko, int miinat){
        this.remove(lauta);
        lauta=new Pelilauta(this, koko, miinat);
        this.add(lauta);
        this.pack();
    }
    
    public void keksiNimi(){
        Object[] options = {"Kyllä", "Ei"};
        int n = JOptionPane.showOptionDialog(this, "Uusi peli?", "", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if(n==0){
            uusiLauta(10, 10);
        }
        else{
            System.exit(0);
        }
    }
    /**
     * Kysyy käyttäjältä koon ja miinojen määrän ja luo uuden pelin niiden avulla.
     */
    public void uusiPeli(){
        int koko=1;
        int miinat=0;
        boolean tosi=false;
        while(!tosi){
            String g=JOptionPane.showInputDialog("Anna pelialueen koko");
            try{
                 koko=Integer.parseInt(g);
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
                miinat=Integer.parseInt(c);
                tosi=true;
            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Virhe! Miinojen määrän pitää olla luku.");
            }
        }
        uusiLauta(koko, miinat);
    }    
}