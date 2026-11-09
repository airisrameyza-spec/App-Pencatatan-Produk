package kelas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kelas.Navigator;
import kelas.model.Produk;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class DetailController {

    @FXML private Label lblKode;
    @FXML private Label lblNama;
    @FXML private Label lblKategori;
    @FXML private Label lblHarga;
    @FXML private ImageView imgProduk; 

    private Navigator nav;

    public void setData(Navigator nav, Produk p) {
        this.nav = nav;
        
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        
        lblKode.setText(p.kodeProduk);
        lblNama.setText(p.namaProduk);
        lblKategori.setText(p.kategori);
        
        lblHarga.setText(formatRupiah.format(p.harga));

        if (p.pathFoto != null && !p.pathFoto.isEmpty()) {
            Image image = new Image(p.pathFoto);
            imgProduk.setImage(image);
        } else {
            imgProduk.setImage(null); 
        }
    }

    @FXML
    private void bukaForm(ActionEvent event) throws IOException {
        nav.bukaForm();
    }

    @FXML
    private void kembaliKeDaftar(ActionEvent event) throws IOException {
        nav.bukaDaftar();
    }
}