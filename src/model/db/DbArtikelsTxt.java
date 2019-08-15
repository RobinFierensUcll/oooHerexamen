package model.db;

import model.domain.Artikel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DbArtikelsTxt implements DbArtikels {


    private Map<String,Artikel> artikelen;
    private File file = new File("src/artikel.txt");

    public DbArtikelsTxt() {
        artikelen = new HashMap<>();
    }

    @Override
    public void save(List<Artikel> artikelen) {
        FileWriter fw;
        file.delete();
        try {
            fw = new FileWriter(file);
            for (Artikel artikel : artikelen) {
                fw.write(String.format(artikel.getCode() + ","));
                fw.write(String.format(artikel.getGroep() + ","));
                fw.write(String.format(artikel.getOmschrijving() + ","));
                fw.write(String.format(artikel.getVoorraad() + ","));
                fw.write(String.format(artikel.getPrijs() + ","));
                fw.write(System.lineSeparator()); //new line
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, Artikel> load() {
        artikelen.clear();

        try {
            Scanner scannerFile = new Scanner(file);
            while (scannerFile.hasNextLine()) {
                Scanner scannerLijn = new Scanner(scannerFile.nextLine());
                scannerLijn.useDelimiter(",");

                String code = scannerLijn.next();
                String omschrijving = scannerLijn.next();
                String groep = scannerLijn.next();
                int voorraad = Integer.parseInt(scannerLijn.next());

                double prijs = Double.parseDouble(scannerLijn.next());

                Artikel a = new Artikel(code, groep, omschrijving, voorraad, prijs);
                artikelen.put(code,a);
            }
            scannerFile.close();
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
        return artikelen;
    }

    @Override
    public List<Artikel> getAlphabetic(){

        Map<String, Artikel> x = new TreeMap<>();
        for(Artikel a : load().values()){
            x.put(a.getOmschrijving(),a);
        }
        List<Artikel> output = new ArrayList<>(x.values());
        return output;
    }

    @Override
    public Artikel getArtikelByCode(String code){
       for(Artikel artikel : getAlphabetic()){
           if(artikel.getCode().equals(code)){
               return artikel;
           }
       }
       return null;
    }


    public ArrayList<Artikel> getListArtikels(){
        return new ArrayList<>(artikelen.values());
  }

    @Override
    public void updateArtikel(Artikel artikel){
        List<Artikel> tmp = getListArtikels();
        int x = 0;
        for(Artikel a : tmp){
          if(a.equals(artikel)){
              tmp.set(x,artikel);
              save(tmp);
          }
          x++;
      }
    }
}
