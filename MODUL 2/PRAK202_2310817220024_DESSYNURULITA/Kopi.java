package PRAK202_2310817220024_DESSYNURULITA;

public class Kopi {
    protected String namaKopi;
    protected String ukuran;
    protected double harga;
    protected String pembeli;

    public String getPembeli() {
        return pembeli;
    }

    public void setPembeli(String namaPembeli) {
        pembeli = namaPembeli;
    }

    public double getPajak() {
        return harga * 11 / 100; 
    }

    public void info() {
        System.out.println("Nama Kopi: " + namaKopi);
        System.out.println("Ukuran: " + ukuran);
        System.out.println("Harga: Rp. " + harga);
    }
}