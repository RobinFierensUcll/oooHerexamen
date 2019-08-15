package model.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Log extends Artikel{
    private LocalDate dag;
    private LocalTime tijd;

    public Log(String code, String omschrijving, String groep, int voorraad, double prijs) {
        super(code, omschrijving, groep, voorraad, prijs);
        setDag();
        setTijd();
    }

    private void setDag(){
        this.dag = LocalDate.now();
    }

    private void setTijd(){
        this.tijd = LocalTime.now();
    }

    public LocalDate getDag(){
        return this.dag;
    }

    public LocalTime getTijd(){
        return this.tijd;
    }






    /*
    private Artikel artikel;
    private LocalDate datum;
    private LocalTime tijd;

    public Log(Artikel artikel){
      setArtikel(artikel);
      setDatum();
      setTijd();
    }

    private void setArtikel(Artikel artikel){
        this.artikel = artikel;
    }

    private void setDatum(){
        this.datum = LocalDate.now();
    }

    private void setTijd(){
        this.tijd = LocalTime.now();
    }

    public LocalDate getDatum(){
        return this.datum;
    }

    public LocalTime getTijd(){
        return this.tijd;
    }

    @Override
    public boolean equals(Object o){
        Log log = (Log) o;
        if(log.getDatum().isEqual(this.getDatum()) && log.getTijd().equals(this.getTijd())) return true;
        return false;
    }*/
}
