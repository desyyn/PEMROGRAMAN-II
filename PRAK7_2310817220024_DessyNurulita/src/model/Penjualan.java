package model;

import java.time.LocalDate;

public class Penjualan {
    private int id;
    private int idPelanggan;
    private int idBuku;
    private int jumlah;
    private LocalDate tanggal; 
    private double totalHarga;

    public Penjualan(int id, int idPelanggan, int idBuku, int jumlah, double totalHarga, LocalDate tanggal) {
        this.id = id;
        this.idPelanggan = idPelanggan;
        this.idBuku = idBuku;
        this.jumlah = jumlah;
        this.totalHarga = totalHarga;
        this.tanggal = tanggal;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdPelanggan() { return idPelanggan; }
    public void setIdPelanggan(int idPelanggan) { this.idPelanggan = idPelanggan; }

    public int getIdBuku() { return idBuku; }
    public void setIdBuku(int idBuku) { this.idBuku = idBuku; }

    public int getJumlah() { return jumlah; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }

    public LocalDate getTanggal() { return tanggal; } 
    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }

    public double getTotalHarga() { return totalHarga; }
    public void setTotalHarga(double totalHarga) { this.totalHarga = totalHarga; }
}
