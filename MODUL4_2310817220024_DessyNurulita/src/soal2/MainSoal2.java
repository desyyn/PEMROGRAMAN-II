package soal2;

import java.util.Scanner;

public class MainSoal2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Pilih jenis hewan yang ingin diinputkan:");
        System.out.println("1 = Kucing");
        System.out.println("2 = Anjing");
        System.out.print("Masukkan pilihan: ");
        int pilihan = input.nextInt();
        input.nextLine(); 

        System.out.print("Nama Hewan Peliharaan: ");
        String nama = input.nextLine();
        System.out.print("Ras: ");
        String ras = input.nextLine();
        System.out.print("Warna Bulu: ");
        String warnaBulu = input.nextLine();

        HewanPeliharaan hewan;

        if (pilihan == 1) {
            hewan = new Kucing(ras, nama, warnaBulu);
            ((Kucing) hewan).displayDetailKucing();
        } else {
            System.out.print("Kemampuan: ");
            String isi = input.nextLine();
            String[] kemampuan = isi.split(",");
            for (int i = 0; i < kemampuan.length; i++) {
                kemampuan[i] = kemampuan[i].trim(); 
            }
            hewan = new Anjing(ras, nama, warnaBulu, kemampuan);
            ((Anjing) hewan).displayDetailAnjing();
        }
        input.close();
    }
}