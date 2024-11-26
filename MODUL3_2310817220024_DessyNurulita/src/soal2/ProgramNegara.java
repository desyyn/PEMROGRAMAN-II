package soal2;

public class ProgramNegara {
    private String nama;
    private String jenisKepemimpinan;
    private String namaPemimpin;
    private int tglMerdeka;
    private int blnMerdeka;
    private int thnMerdeka;

    public ProgramNegara(String nama, String jenisKepemimpinan, String namaPemimpin, int tanggal, int bulan, int tahun) {
        this.nama = nama;
        this.jenisKepemimpinan = jenisKepemimpinan;
        this.namaPemimpin = namaPemimpin;
        this.tglMerdeka = tanggal;
        this.blnMerdeka = bulan;
        this.thnMerdeka = tahun;
    }

    public ProgramNegara(String nama, String jenisKepemimpinan, String namaPemimpin) {
        this.nama = nama;
        this.jenisKepemimpinan = jenisKepemimpinan;
        this.namaPemimpin = namaPemimpin;
    }

    public String getNama() {
        return nama;
    }

    public String getJenisKepemimpinan() {
        return jenisKepemimpinan;
    }

    public String getNamaPemimpin() {
        return namaPemimpin;
    }

    public int getTanggalKemerdekaan() {
        return tglMerdeka;
    }

    public int getBulanKemerdekaan() {
        return blnMerdeka;
    }

    public int getTahunKemerdekaan() {
        return thnMerdeka;
    }

    public boolean isMonarki() {
        return jenisKepemimpinan.equals("monarki");
    }
}
