package controller.factories;

import model.db.*;
import model.domain.Artikel;
import model.domain.Rekening;

import java.util.List;

public class DbFactory {
    private DbArtikels dbArtikels;
    private DbLog dbLogs;
    private DbRekening dbRekening;

    public DbFactory() {
        this.dbArtikels = new DbArtikelsTxt();
        this.dbLogs = new DbLogMemory();
        this.dbRekening = new DbRekeningMemory();
    }

    public DbArtikels getDbArtikels() {
        return this.dbArtikels;
    }

    public DbRekening getDbRekening(){
        return this.dbRekening;
    }


    public void addArtikelToRekening(Rekening artikel){
        if(artikel.checkVoorraad()){
            dbRekening.add(artikel);
            artikel.voorraadMinus();
            getDbArtikels().updateArtikel(artikel);
        }
    }

    public void removeArtikelFromRekening(Rekening artikel){
        dbRekening.removeArtikel(artikel);
        artikel.voorraadPlus();
        getDbArtikels().updateArtikel(artikel);
    }

    public List<Rekening> getRekening(){
        return dbRekening.get();
    }

    public List<Rekening> getListKassa(){
        return dbRekening.getListKassa();
    }

    public Double getTotaalPrijsKassa(){
        return dbRekening.getTotaalPrijs();
    }



    public List<Artikel> getArtikels(){
        return dbArtikels.getListArtikels();
    }

    public List<Artikel> getArtikelsAlphabetic(){
        return dbArtikels.getAlphabetic();
    }


    public void addArtikel(List<Artikel> artikel) {
        this.dbArtikels.save(artikel);
    }

    public Artikel getArtikelByCode(String code){
        return this.dbArtikels.getArtikelByCode(code);
    }


    public DbLog getDbLog(){
        return this.dbLogs;
    }
}