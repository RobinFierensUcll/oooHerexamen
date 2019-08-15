package model.db;

import model.domain.Rekening;

import java.util.List;

public interface DbRekening {
    void add(Rekening rekening);
    List<Rekening> get();
    List<Rekening> getListKassa();
    double getTotaalPrijs();
    void removeArtikel(Rekening rekening);
}
