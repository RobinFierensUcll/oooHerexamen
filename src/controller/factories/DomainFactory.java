package controller.factories;

import model.domain.Artikel;
import model.domain.Rekening;

public class DomainFactory {
    private Rekening rekening;
    private Artikel artikel;
    private DbFactory dbFactory;


    public DomainFactory() {
    }

    public void createRekening(String code, String omschrijving, String groep, int voorraad, double prijs){
        rekening = new Rekening(code,omschrijving,groep,voorraad,prijs);
    }

    public void createRekeningFromArtikel(Artikel artikel){
        rekening = new Rekening(artikel.getCode(),artikel.getOmschrijving(),artikel.getGroep(),artikel.getVoorraad(),artikel.getPrijs());
    }

    public Rekening getRekening(){
        return rekening;
    }
}