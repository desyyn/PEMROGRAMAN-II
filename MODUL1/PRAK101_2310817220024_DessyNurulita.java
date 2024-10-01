package MODUL1;
import java.util.Locale;
import java.util.Scanner;

public class PRAK101_2310817220024_DessyNurulita {

	public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        input.useLocale(Locale.US); 

        String nama, ttl;
        int tanggal, bulan, tahun, tinggi;
        double berat;

        System.out.print("Masukkan Nama Lengkap: ");
        nama = input.nextLine();
        System.out.print("Masukkan Tempat Lahir: ");
        ttl = input.nextLine();
        System.out.print("Masukkan Tanggal Lahir: ");
        tanggal = input.nextInt();
        System.out.print("Masukkan Bulan Lahir: ");
        bulan = input.nextInt();
        System.out.print("Masukkan Tahun Lahir: ");
        tahun = input.nextInt();
        System.out.print("Masukkan Tinggi Badan: ");
        tinggi = input.nextInt();
        input.nextLine();
        System.out.print("Masukkan Berat Badan: ");
        berat = input.nextDouble();

        String namaBulan;
        switch (bulan) {
            case 1: namaBulan = "Januari"; break;
            case 2: namaBulan = "Februari"; break;
            case 3: namaBulan = "Maret"; break;
            case 4: namaBulan = "April"; break;
            case 5: namaBulan = "Mei"; break;
            case 6: namaBulan = "Juni"; break;
            case 7: namaBulan = "Juli"; break;
            case 8: namaBulan = "Agustus"; break;
            case 9: namaBulan = "September"; break;
            case 10: namaBulan = "Oktober"; break;
            case 11: namaBulan = "November"; break;
            case 12: namaBulan = "Desember"; break;
            default: namaBulan = "Angka tidak valid"; break;
        }

        System.out.println("");
        System.out.println("Nama Lengkap " + nama + ", Lahir di " + ttl + " pada tanggal " + tanggal + " " + namaBulan + " " + tahun + " Tinggi badan " + tinggi + " cm dan Berat Badan " + berat + " kg");

        input.close();
    }
}