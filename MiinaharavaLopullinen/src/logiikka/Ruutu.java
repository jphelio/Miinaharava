package logiikka;

import java.util.*;
/**
 * Miinaharava
 * Ruutu on logiikkaluokka.
 * @author Juhani Heliö
 */
public class Ruutu {
    
    public static final int MIINA=1;        
    public static final int TYHJA=0;        
    private int[][] tilaTaulukko;           //pitää kirjaa ruutujen tilasta, eli onko se miina vai normaali ruutu 
    private boolean[][] aputaulu;           //pitää kirjaa siitä, onko ruutu avattu
    private int miina;                      //miinojen määrä
    private int miinojaPelissa;             //miinojen määrä
    private int koko;                       //ruudukoiden koko. Ruudukot ovat neliömäisiä 
    private int normaalejaRuutujaAvattu=0;  //pitää kirjaa montako tyhjää ruutua on avattu
    /**
     * Ruudun konstruktori. Luo taulukot, jotka pitävät kirjaa miinojen sijainneista ja avatuista ruuduista.
     * @param koko Ruudunkoiden koko. Ruudukko on koko*koko
     * @param miinat tilaTauluun sijoitettavien miinojen määrä.
     */
    public Ruutu(int koko, int miinat){
        aputaulu=new boolean[koko][koko];
        miina=miinat;
        miinojaPelissa=miinat;
        this.koko=koko;
        taulunTeko();
    }
    
    /**
     * Palauttaa tilaTulukon arvon kohdasta (x,y).
     * @param x tilaTaulun x-koordinaatti.
     * @param y tilaTaulun y-koordinsstti.
     * @return Palauttaa 1 jos (x,y) on miina tai nolla, jos (x,y) ei ole miina.
     */
    public int getTila(int x, int y){
        return tilaTaulukko[x][y];
    }
    
    /**
     * Luo taulut ja sekoittaa miinat.
     */
    private void taulunTeko(){
        List<Integer> lista=new ArrayList<Integer>(koko*koko);
        for(int i=0;i<miina;i++){
            lista.add(MIINA);
        }
        for(int i=0;i<(koko*koko-miina);i++){
            lista.add(TYHJA);
        }
        Collections.shuffle(lista);
        tilaTaulukko=new int[koko][koko];
        int a=0;
        for(int rivi=0;rivi<koko;rivi++){
            for(int sarake=0; sarake<koko;sarake++){
                tilaTaulukko[rivi][sarake]=lista.get(a);
                a++;
            }
        }
    }
    
    /**
     * Merkitsee aputauluun ruudun avatuksi. Tätä tarvitaan erityisesti, kun tarkistetaan lopetusehtoja.
     * @param x Avattavan ruudun x-koordinaatti.
     * @param y Avattavan ruudun y-koordinaatti.
     */
    public void avaaRuutu(int x, int y){
        if(!aputaulu[x][y]){
            normaalejaRuutujaAvattu++;
            aputaulu[x][y]=true;
        }
    }
    
    /**
     * Hakee avatun ruudun ympärillä olevien miinojen lukumäärän.
     * @param y avatun ruudun x-koordinaatti
     * @param x avatun ruudun y-koordinaatti
     * @return Palauttaa avatun ruudun ympärillä olevien miinjen määrän kokonaislukuna. 
     */
    public int getNaapuriMiinat(int y, int x){
        int miinojenMaara=0;
        int minY=Math.max(0, y-1);
        int maxY=Math.min(y+1, koko-1);
        int minX=Math.max(0,x-1);
        int maxX=Math.min(x+1, tilaTaulukko.length-1);
        for(int a=minY; a<=maxY; a++){
            for(int b=minX;b<=maxX;b++){
                if(!(a==y && b==x) && tilaTaulukko[a][b]==MIINA){
                    miinojenMaara++;
                }
            }
        }
        return miinojenMaara;
    }
    
    /**
     * merkitsee aputauluun ruudun (y,x) ympärillä olevat ruudut
     * @param y ruudun x-koordinaatti
     * @param x ruudun y-koordinaatti
     */
    public void avaaTyhjatRuudut(int y, int x){
        int minY=Math.max(0, y-koko);
        int maxY=Math.min(y+koko, koko-1);
        int minX=Math.max(0,x-koko);
        int maxX=Math.min(x+koko, tilaTaulukko.length-1);
        if(this.getNaapuriMiinat(y, x)==0){
            for(int a=minY; a<=maxY; a++){
                for(int b=minX;b<=maxX;b++){
                    this.avaaRuutu(x, y);
                }
            }
        }
    }
    
    /**
     * tarkistaa, onko kaikki tyjäht ruudut avattu
     * @return palauttaa true jos kaikki tyhjät ruudut on avattu ja false jos ei.
     */
    public boolean voitto(){
        int normaalit=koko*koko-miinojaPelissa;
        if(normaalejaRuutujaAvattu==normaalit){
            return true;
        }
        return false;
    }
}