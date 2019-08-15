package model.domain;

public class Artikel{
    //VARIABELEN
    private String code, omschrijving, groep;
    private int voorraad;
    private double prijs;
    private int aantal;

    //CONSTRUCTORS
    public Artikel(String code, String omschrijving, String groep, int voorraad, double prijs){
        setCode(code);
        setOmschrijving(omschrijving);
        setGroep(groep);
        setVoorraad(voorraad);
        setPrijs(prijs);
        setAantal(0);
    }

    //SETTERS
    public void setCode(String code) {
        this.code = code;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public void setGroep(String groep) {
        this.groep = groep;
    }

    public void setVoorraad(int voorraad) {
        this.voorraad = voorraad;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public void setAantal(int aantal){
        this.aantal = aantal;
    }

    //GETTERS
    public String getCode() {
        return code;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public String getGroep() {
        return groep;
    }

    public int getVoorraad() {
        return voorraad;
    }

    public double getPrijs() {
        return prijs;
    }

    public int getAantal(){
        return aantal;
    }

    public boolean checkVoorraad(){
        if(getVoorraad()>0) return true;
        return false;
    }

    public void voorraadMinus(){
        this.voorraad -= 1;
    }

    public void voorraadPlus(){
        this.voorraad += 1;
    }

    public void aantalMinus(){
        if(aantal>0){
            this.aantal -= 1;
        }
    }

    public void aantalPlus(){
        this.aantal += 1;
    }

    @Override
    public boolean equals(Object o){
        Artikel a = (Artikel) o;
        if (a.getCode().equals(this.getCode())) return true;
        return false;
    }
}
