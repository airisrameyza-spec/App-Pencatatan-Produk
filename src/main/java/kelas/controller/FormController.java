package kelas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import kelas.data.DataProduk;
import kelas.model.Produk;
import kelas.Navigator;
import java.io.File;
import java.io.IOException;

public class FormController {

    @FXML private TextField txtKode;
    @FXML private TextField txtNama;
    @FXML private ComboBox<String> cbKategori;
    @FXML private TextField txtHarga;
    @FXML private Label lblPathFoto;

    private Navigator nav;
    private String pathFotoTerpilih = null;

    public void setKonteks(Navigator nav) {
        this.nav = nav;
    }

    @FXML
    public void initialize() {
        cbKategori.getItems().addAll("Makanan", "Minuman");
        cbKategori.getSelectionModel().selectFirst();
    }

  
    @FXML
    private void pilihFoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pilih Foto Produk");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            pathFotoTerpilih = selectedFile.toURI().toString();
            lblPathFoto.setText(selectedFile.getName()); 
        }
    }

    @FXML
    private void simpanData(ActionEvent event) throws IOException {
        String kode = txtKode.getText();
        String nama = txtNama.getText();
        String kategori = cbKategori.getValue();
        
        if (nama == null || nama.trim().isEmpty()) {
            tampilkanPesanError("Nama produk tidak boleh kosong!");
            return; 
        }

        double harga = 0;
        try {
            harga = Double.parseDouble(txtHarga.getText());
            if (harga <= 0) {
                tampilkanPesanError("Harga harus lebih dari nol!");
                return;
            }
        } catch (NumberFormatException e) {
            tampilkanPesanError("Harga harus berupa angka!");
            return;
        }

        Produk produkBaru = new Produk(kode, nama, kategori, harga, pathFotoTerpilih);
        DataProduk.tambahProduk(produkBaru);

        nav.bukaDaftar(); 
    }

    @FXML
    private void kembaliKeDaftar(ActionEvent event) throws IOException {
        nav.bukaDaftar(); 
    }

    private void tampilkanPesanError(String pesan) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validasi Error");
        alert.setHeaderText(null);
        alert.setContentText(pesan);
        alert.showAndWait();
    }
}