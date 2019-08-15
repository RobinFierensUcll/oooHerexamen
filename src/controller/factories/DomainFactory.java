package controller.factories;

import model.domain.Artikel;
import model.domain.Rekening;

public class DomainFactory {
    private Rekening rekening;
    private Artikel artikel;

    public DomainFactory() {
    }

    public void createRekening(String code, String omschrijving, String groep, int voorraad, double prijs){
        rekening = new Rekening(code,omschrijving,groep,voorraad,prijs);
    }

    public Rekening createRekeningFromArtikel(Artikel artikel){
        rekening = new Rekening(artikel.getCode(),artikel.getOmschrijving(),artikel.getGroep(),artikel.getVoorraad(),artikel.getPrijs());
        return rekening;
    }

    public Artikel createArtikel(String code, String omschrijving, String groep, int voorraad, double prijs){
        artikel = new Artikel(code,omschrijving,groep,voorraad,prijs);
        return artikel;
    }
    public Artikel getArtikel() {
        return artikel;
    }

    public Rekening getRekening(){
        return rekening;
    }
}