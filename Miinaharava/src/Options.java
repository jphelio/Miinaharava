//By Juhani Heliö

import java.awt.event.*;
import javax.swing.*;

public class Options extends JFrame{
    private JMenuBar palkki=new JMenuBar();
    private JMenu peli;
    private JMenuItem uusiPeli;
    private JMenuItem lopeta;
    private Options1 valikko;
    
    public Options(){
        peli=new JMenu("Peli");
        
        uusiPeli=new JMenuItem("Uusi peli");
        uusiPeli.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                valikko.setup();
            }
        });
        lopeta=new JMenuItem("Lopeta");
        uusiPeli.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        peli.add(uusiPeli);
        peli.add(lopeta);
        
        palkki.add(peli);
    }
}
