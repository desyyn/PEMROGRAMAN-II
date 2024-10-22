package PRAK201_2310817220024_DESSYNURULITA;

public class Buah {
	 protected String nama;
	 protected double berat;
	 protected double harga;
	 protected double jumlah;
	 protected double diskon;
	
	public Buah(String nama, double berat, double harga, double jumlah) {
		this.nama = nama;
		this.berat = berat;
		this.harga = harga;
		this.jumlah = jumlah;
	}
	
	public double hargaAwal(double jumlah, double berat, double harga) {
		return jumlah / berat * harga;
	}
	
	public double diskon(double harga, double jumlah) {
		return harga * 2 / 100 * 4 * Math.round(jumlah / 4);
	}
	
	public double hargaAkhir() {
		return hargaAwal(jumlah, berat, harga) - diskon(harga, jumlah);
	}
	
	public void info() {
	   System.out.println("Nama Buah: " + nama);
       System.out.println("Berat: " + berat + " kg");
       System.out.println("Harga per Kg: Rp" + harga);
       System.out.println("Jumlah Beli: " + jumlah + " kg");
       System.out.println("Harga Sebelum Diskon: Rp" + hargaAwal(jumlah, berat, harga));
       System.out.println("Total Diskon: Rp" + diskon(harga, jumlah));
       System.out.println("Harga Setelah Diskon: Rp" + hargaAkhir());
       System.out.println();
	}
}
