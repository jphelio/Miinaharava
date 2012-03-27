//By Juhani Heliö

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Options1 extends JFrame{
    
    private JButton helppo;
    private JButton keskitaso;
    private JButton vaikea;
    private JButton custom;
    private static Pelilauta lauta;
    
    public Options1(){
        helppo = new JButton("Helppo");
        helppo.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        lauta=new Pelilauta(10, 10);
                    }
                });
        
        keskitaso = new JButton("Keskitaso");
        keskitaso.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        lauta=new Pelilauta(15, 40);
                    }
                });
        
        vaikea = new JButton("Vaikea");
        vaikea.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        lauta=new Pelilauta(20, 100);
                    }
                });
        
        custom = new JButton("Custom");
        custom.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        int koko=1;
                        int miinat=0;
                        boolean tosi=false;
                        while(!tosi){
                            String g=JOptionPane.showInputDialog("Anna pelialueen koko");
                            try{
                                koko=Integer.parseInt(g);
                                tosi=true;
                            }
                            catch(NumberFormatException m){
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
                            catch(NumberFormatException m){
                            JOptionPane.showMessageDialog(null, "Virhe! Miinojen määrän pitää olla luku.");
                            }
                        }
                        lauta=new Pelilauta(koko, miinat);
                    }
                });
        setLayout(new GridLayout(4, 1));
        add(helppo);
        add(keskitaso);
        add(vaikea);
        add(custom);
    }
    public void setup(){
        Options1 valikko=new Options1();
        valikko.setTitle("Miinaharava");
        valikko.pack();
        valikko.setDefaultCloseOperation(EXIT_ON_CLOSE);
        valikko.setVisible(true);
    }
    public static void main(String[] args) {
        Options1 options=new Options1();
        options.setup();
    }
}
