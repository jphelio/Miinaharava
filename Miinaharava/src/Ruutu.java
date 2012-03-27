/*
 *Miinaharava
 *By Juhani Heliö
*/

public class Ruutu {
    
    private int[][] tilaTaulukko;
    private int[][] aputaulu;
    private int miina;
    private int miinojaPelissa;
    private int koko;
    private int miinojenMaara;
    private int normaalejaRuutujaAvattu=0;    
    
    public Ruutu(int koko, int miinat){
        aputaulu=new int[koko][koko];
        miina=miinat;
        miinojaPelissa=miinat;
        this.koko =koko;
        tilaTaulukko=new int[koko][koko];
        for(int y=0;y<koko;y++){
            for(int x=0;x<koko;x++){
                if((Math.random())>0.85 && miina>0){
                    tilaTaulukko[y][x]=1;
                    miina--;
                }
                else{
                    tilaTaulukko[y][x]=0;
                }
            }
        }
    }
    public int getTila(int x, int y){
        return tilaTaulukko[x][y];
    }
    public void avaaRuutu(int x, int y){
        if(aputaulu[x][y]!=1){
            normaalejaRuutujaAvattu++;
            aputaulu[x][y]=1;
        }
    }    
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
    public boolean voitto(){
        int normaalit=koko*koko-miinojaPelissa;
        if(normaalejaRuutujaAvattu==normaalit){
            return true;
        }
        return false;
    }
}