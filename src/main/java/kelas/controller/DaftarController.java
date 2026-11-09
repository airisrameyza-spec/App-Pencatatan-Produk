package kelas.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import kelas.Navigator;
import kelas.data.DataProduk;
import kelas.model.Produk;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class DaftarController {

    @FXML private TextField txtCari;
    @FXML private TableView<Produk> tabelProduk;
    @FXML private TableColumn<Produk, String> colKode;
    @FXML private TableColumn<Produk, String> colNama;
    @FXML private TableColumn<Produk, String> colKategori;
    
    @FXML private TableColumn<Produk, String> colHarga; 

    private Navigator nav;

    public void setKonteks(Navigator nav) {
        this.nav = nav;
    }

    @FXML
    public void initialize() {
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

        colKode.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().kodeProduk));
        colNama.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().namaProduk));
        colKategori.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().kategori));
        
        
        colHarga.setCellValueFactory(cellData -> {
            String hargaRupiah = formatRupiah.format(cellData.getValue().harga);
            return new SimpleStringProperty(hargaRupiah);
        });

        tabelProduk.setItems(DataProduk.getListProduk());
    }

    @FXML
    private void cariData(ActionEvent event) {
        String keyword = txtCari.getText().toLowerCase();
        
        if (keyword.isEmpty()) {
            tabelProduk.setItems(DataProduk.getListProduk());
            return;
        }

        ObservableList<Produk> hasilCari = FXCollections.observableArrayList();
        for (Produk p : DataProduk.getListProduk()) {
            if (p.namaProduk.toLowerCase().contains(keyword)) { 
                hasilCari.add(p);
            }
        }
        tabelProduk.setItems(hasilCari);
    }

    @FXML
    private void bukaForm(ActionEvent event) throws IOException {
        nav.bukaForm();
    }

    @FXML
    private void lihatDetail(ActionEvent event) throws IOException {
        Produk terpilih = tabelProduk.getSelectionModel().getSelectedItem();
        
        if (terpilih == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Peringatan");
            alert.setHeaderText(null);
            alert.setContentText("Pilih produk dari tabel terlebih dahulu sebelum melihat detail!");
            alert.showAndWait();
            return;
        }
        
        nav.bukaDetail(terpilih);
    }
}