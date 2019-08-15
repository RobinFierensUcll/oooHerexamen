package model.db;

import model.domain.Artikel;
import java.util.List;
import java.util.Map;

public interface DbArtikels {
    void save(List<Artikel> artikel);
    Map<String, Artikel> load();
    List<Artikel> getAlphabetic();
    Artikel getArtikelByCode(String code);
    List<Artikel> getListArtikels();
    void updateArtikel(Artikel artikel);
}
