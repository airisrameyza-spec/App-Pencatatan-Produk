package kelas.model;

public class Produk {
    public String kodeProduk;
    public String namaProduk;
    public String kategori; 
    public double harga;
    public String pathFoto; 

    public Produk(String kodeProduk, String namaProduk, String kategori, double harga, String pathFoto) {
        this.kodeProduk = kodeProduk;
        this.namaProduk = namaProduk;
        this.kategori = kategori;
        this.harga = harga;
        this.pathFoto = pathFoto;
    }
}