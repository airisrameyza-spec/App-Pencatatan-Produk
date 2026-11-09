package kelas.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import kelas.model.Produk;

public class DataProduk {
    private static final ObservableList<Produk> listProduk = FXCollections.observableArrayList();

    public static ObservableList<Produk> getListProduk() {
        return listProduk;
    }

    public static void tambahProduk(Produk produk) {
        listProduk.add(produk);
    }
}
