package model.domain;

public class Rekening extends Artikel{
    private int aantal;
    private double totPrijs;
    public Rekening(String code, String omschrijving, String groep, int voorraad, double prijs) {
        super(code, omschrijving, groep, voorraad, prijs);
        setAantal();
        setTotPrijs();
    }

    private void setAantal(){
        this.aantal = 0;
    }

    private void setTotPrijs(){
        this.totPrijs = getAantal()*getPrijs();
    }

    public int getAantal(){
        return this.aantal;
    }

    public double getTotPrijs(){
        return this.totPrijs;
    }

    public void verhoogAantal(){
        this.aantal +=  1;
        setTotPrijs();
    }

    public void verlaagAantal(){
        this.aantal -= 1;
        setTotPrijs();
    }

    @Override
    public boolean equals(Object o){
        Rekening r = (Rekening) o;
        if(r.getCode().equals(this.getCode())) return true;
        return false;
    }
}
