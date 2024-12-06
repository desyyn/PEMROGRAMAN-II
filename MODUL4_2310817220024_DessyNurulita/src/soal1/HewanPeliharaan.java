package soal1;

import java.util.Scanner;

public class HewanPeliharaan {
	private String nama;
	private String ras;
	
	Scanner input = new Scanner(System.in);
	
	public HewanPeliharaan(String n, String r) {
		System.out.print("Nama Hewan peliharaan: ");
		n = input.nextLine();
		this.nama = n;
		System.out.print("Ras: ");
		r = input.nextLine();
		this.ras = r;
	}
	
	public void display() {
		System.out.println("\nDetail Hewan Peliharaan:");
		System.out.println("Nama hewan peliharaanku adalah: "+nama);
		System.out.println("Dengan ras: "+ras);
	}
}