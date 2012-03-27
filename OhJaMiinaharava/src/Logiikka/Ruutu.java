package Logiikka;
/*
 * Miinaharava
 * By Juhani Heliö
 */

import java.util.*;

public class Ruutu {
    
    private int[][] tilaTaulukko;  //pitÃ¤Ã¤ kirjaa ruutujen tilasta, eli onko se miina vai normaali ruutu 
    private int[][] aputaulu;      //pitÃ¤Ã¤ kirjaa siitÃ¤, onko ruutu avattu
    private int miina;             //miinojen mÃ¤Ã¤rÃ¤
    private int miinojaPelissa;    //miinojen mÃ¤Ã¤rÃ¤
    private int koko;
    private int miinojenMaara;
    private int normaalejaRuutujaAvattu=0;    
    /**
     * Luo uuden Ruudun
     * 
     * @param koko taulukon sivujen pituudet
     * @param miinat pelissä olevat miinat
     */
    public Ruutu(int koko, int miinat){
        aputaulu=new int[koko][koko];
        miina=miinat;
        miinojaPelissa=miinat;
        this.koko=koko;
        taulunTeko();
    }
    /**
     * Paluttaa ruudun tilan
     * 
     * @param x taulukon x-koordinaatti
     * @param y taulukon y-koordinantti
     * @return  Tilataulun arvon kohdasta (x, y)
     */
    public int getTila(int x, int y){
        return tilaTaulukko[x][y];
    }
    /**
     * Sekoittaa miinat ja luo taulun, johon miinat sijoitetaan
     */
    private void taulunTeko(){
        System.out.println(koko);
        System.out.println(miina);
        List<Integer> lista=new ArrayList<Integer>(koko*koko);
        for(int i=0;i<miina;i++){
            lista.add(1);
        }
        System.out.println(koko*koko-miina);
        for(int i=0;i<(koko*koko-miina);i++){
            lista.add(0);
        }
        System.out.println(lista.size());
        System.out.println(lista);
        Collections.shuffle(lista);
        System.out.println(lista);
        tilaTaulukko=new int[koko][koko];
        int rivi=0;
        int sarake=0;
        for(int a: lista){
            System.out.println(rivi+","+sarake);
            if(sarake<koko){
//                System.out.print(a+",");
                tilaTaulukko[rivi][sarake]=a;
                sarake++;
                
            }
            else{
                System.out.println("");
                sarake=0;
                rivi++;
                tilaTaulukko[rivi][sarake]=a;
            }
        }
    }
    /**
     * Merkkaa ruudun avatuksi
     * 
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     */
    public void avaaRuutu(int x, int y){
        if(aputaulu[x][y]!=1){
            normaalejaRuutujaAvattu++;
            aputaulu[x][y]=1;
        }
    }    
    /**
     * Laskee ruudun vieressä olevin miinojen määrän
     * 
     * @param y ruudun x-koordinaatti
     * @param x ruudun y-koordinaatti
     * @return paluttaa ruudun ympärillä olevien miinojen määrän
     */
    public int getNaapuriMiinat(int y, int x){
        miinojenMaara=0;
        int minY=Math.max(0, y-1);
        int maxY=Math.min(y+1, koko-1);
        int minX=Math.max(0,x-1);
        int maxX=Math.min(x+1, tilaTaulukko.length-1);
        for(int a=minY; a<=maxY; a++){
            for(int b=minX;b<=maxX;b++){
                if(!(a==y && b==x) && tilaTaulukko[a][b]==1){
                    miinojenMaara++;
                }
            }
        }
        return miinojenMaara;
    }
//    public void avaaTyhjatRuudut(int y, int x){
//        int minY=Math.max(0, y-koko);
//        int maxY=Math.min(y+koko, koko-1);
//        int minX=Math.max(0,x-koko);
//        int maxX=Math.min(x+koko, tilaTaulukko.length-1);
//        if(this.getNaapuriMiinat(y, x)==0){
//            for(int a=minY; a<=maxY; a++){
//                for(int b=minX;b<=maxX;b++){
//                    this.avaaRuutu(x, y);
//                }
//            }
//        }
//    }
    /**
     * Trkistaa, onko voittoon tarvittavat 
     * 
     * @return paluttaa true, jos voittoehdot täyttyvät 
     */
    public boolean voitto(){
        int normaalit=koko*koko-miinojaPelissa;
        if(normaalejaRuutujaAvattu==normaalit){
            return true;
        }
        return false;
    }
}