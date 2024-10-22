package PRAK203_2310817220024_DESSYNURULITA;

public class Soal3Main {
	public static void main(String[] args) {
		Pegawai p1 = new Pegawai();
		//p1.nama = "Roi"
		//pada baris ini terjadi error karena tidak ada titik koma (;)
		p1.nama = "Roi";
		//p1.asal = "Kingdom of Orvel";
		//pada baris ini error karerna tipe data methodnya adalah char, seharusnya menggunakan String
		p1.asal = "Kingdom of Orvel";
		p1.setJabatan("Assasin");
		p1.setUmur(17);
		System.out.println("Nama Pegawai: " + p1.getNama());
		System.out.println("Asal: " + p1.getAsal());
		System.out.println("Jabatan: " + p1.jabatan);
		//System.out.println("Umur: " + p1.umur);
		//menambah " tahun" untuk menyesuaikan dengan output di soal
		System.out.println("Umur: " + p1.umur + " tahun");
	}
}