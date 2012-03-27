//By Juhani Heliö

import java.awt.GridLayout;
import javax.swing.*;

public class Main extends JFrame{
    
    private JFrame ruutu;
    private Options options;
    private Pelilauta pelilauta;
    
    public Main(){
        options=new Options();
        pelilauta=new Pelilauta(10, 10);
        ruutu.setLayout(new GridLayout(2, 1));
        ruutu.add(options);
        ruutu.add(pelilauta);
    }
    
    public static void main(String[] args) {
        Main main=new Main();
        main.setTitle("Miinaharava");
        main.pack();
        main.setDefaultCloseOperation(EXIT_ON_CLOSE);
        main.setVisible(true);
    }
}
