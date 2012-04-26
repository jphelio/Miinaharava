package logiikka;

/*
* Miinaharava
* By Juhani Heliö
*/

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RuutuTest {
    
    private Ruutu ruutu;
    
    @BeforeClass
    public static void setUpClass() throws Exception {}
    @AfterClass
    public static void tearDownClass() throws Exception {}
    @Before
    public void setUp() {}
    @After
    public void tearDown() {}
    @Test
    public void getNaapuriMiinatToimiiKulmissa(){
      ruutu=new Ruutu(10, 0);
      assertEquals(ruutu.getNaapuriMiinat(0, 0), 0);
    }
    @Test
    public void getNaapuriMiinatToimiiReunoilla(){
      ruutu=new Ruutu(10, 0);
      assertEquals(ruutu.getNaapuriMiinat(5, 0), 0);
    }
    @Test
    public void getNaapuriMiinatToimiiKeskellä(){
      ruutu=new Ruutu(10, 0);
      assertEquals(ruutu.getNaapuriMiinat(2, 2), 0);
    }
    @Test
    public void getTilaToimiiNormaalilleRuudulle(){
        ruutu=new Ruutu(10, 0);
        assertEquals(ruutu.getTila(5, 5), 0);
    }
    @Test
    public void getTilaToimiiMiinoille(){
        ruutu=new Ruutu(10, 100);
        assertEquals(1, ruutu.getTila(5, 5), 1);
    }
    @Test
    public void voittoToimii(){
        ruutu=new Ruutu(1, 0);
        ruutu.avaaRuutu(0, 0);
        assertEquals(ruutu.voitto(), true);
    }
}