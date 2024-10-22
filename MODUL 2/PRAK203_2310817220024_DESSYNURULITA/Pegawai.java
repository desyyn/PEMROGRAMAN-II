package PRAK203_2310817220024_DESSYNURULITA;

	//public class Employee {
	//pada baris ini error karena nama classnya berbeda dengan nama file java (Pegawai)
	public class Pegawai {
		//public char nama;
		//pada baris ini, tipe data attribute 'nama' uang awalnya 'char' harus diganti menjadi 'String' untuk menyesuaikan dengan parameter yang diterima
		public String nama;
		public String asal;
		public String jabatan;
		public int umur;
		
		public String getNama() {
			return nama;
		}
		
		public String getAsal() {
			return asal;
		 }
	
	//public void setJabatan() {
	//method ini awalnya tidak menerima parameter apapun, sehingga menghasilkan error pada constructor j
	public void setJabatan(String j) {
		 this.jabatan = j;
		 }
	
	//harus membuat getter umur agar dapat ditampilkan di output
	public void setUmur(int u) {
		this.umur = u;
		}
}