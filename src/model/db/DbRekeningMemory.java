package model.db;

import model.domain.Rekening;

import java.util.ArrayList;
import java.util.List;

public class DbRekeningMemory implements DbRekening {
    private List<Rekening> artikels;
    private List<Rekening> kassa;
    private double totaalPrijs;

    public DbRekeningMemory(){
        artikels = new ArrayList<>();
        kassa = new ArrayList<>();
        setTotaalPrijs();
    }

    @Override
    public void add(Rekening rekening) {
        if(artikels.contains(rekening)){
            int x = 0;
            for(Rekening r : artikels){
                if(rekening.equals(r)){
                    r.verhoogAantal();
                    artikels.set(x,r);
                }
                x++;
            }
        }else {
            rekening.verhoogAantal();
            artikels.add(rekening);
        }
        setTotaalPrijs();
        kassa.add(rekening);
    }

    @Override
    public List<Rekening> get() {
        return artikels;
    }

    private void setTotaalPrijs(){
        totaalPrijs = 0;
        for (Rekening rekening : get()){
            totaalPrijs += rekening.getPrijs() * rekening.getAantal();
        }
    }

    @Override
    public double getTotaalPrijs() {
        setTotaalPrijs();
        return totaalPrijs;
    }

    @Override
    public void removeArtikel(Rekening artikel) {
        List<Rekening> tmp = kassa;
        int x = 0;
        boolean next = true;
        while(next) {
            Rekening r = tmp.get(x);
            if (r.equals(artikel)) {
                if (tmp.size() != 1) {
                    tmp.remove(x);
                    kassa = tmp;
                } else {
                    kassa = new ArrayList<>();
                }
                next = false;
            }
            x++;

        }

        tmp = artikels;
        x = 0;
        next = true;
        while(next){
            Rekening r = tmp.get(x);
            if(r.equals(artikel)){
                r.verlaagAantal();
                if(r.getAantal() != 0){
                    tmp.set(x,r);
                    artikels = tmp;
                }else{
                    if(tmp.size() == 1){
                        artikels = new ArrayList<>();
                    }else{
                        tmp.remove(x);
                        artikels = tmp;
                    }
                }
                next = false;
            }
            x++;
        }

    }

    @Override
    public List<Rekening> getListKassa(){
        return kassa;
    }

}
