package soal2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class MainSoal2 {
    public static void main(String[] args) {
        LinkedList<ProgramNegara> daftarNegara = new LinkedList<>();
        Scanner input = new Scanner(System.in);

        HashMap<Integer, String> nmBulan = new HashMap<>();
        nmBulan.put(1, "Januari");
        nmBulan.put(2, "Februari");
        nmBulan.put(3, "Maret");
        nmBulan.put(4, "April");
        nmBulan.put(5, "Mei");
        nmBulan.put(6, "Juni");
        nmBulan.put(7, "Juli");
        nmBulan.put(8, "Agustus");
        nmBulan.put(9, "September");
        nmBulan.put(10, "Oktober");
        nmBulan.put(11, "November");
        nmBulan.put(12, "Desember");

        System.out.println();
        int jumlahNegara = input.nextInt();
        input.nextLine(); 

        for (int i = 0; i < jumlahNegara; i++) {
            String nama = input.nextLine();
            String jenisKepemimpinan = input.nextLine();
            String namaPemimpin = input.nextLine();

            if (jenisKepemimpinan.equals("monarki")) {
                daftarNegara.add(new ProgramNegara(nama, jenisKepemimpinan, namaPemimpin));
            } else {
                int tanggal = input.nextInt();
                int bulan = input.nextInt();
                int tahun = input.nextInt();
                input.nextLine();
                daftarNegara.add(new ProgramNegara(nama, jenisKepemimpinan, namaPemimpin, tanggal, bulan, tahun));
            }
        }

        input.close();

        for (ProgramNegara identitas : daftarNegara) {
            if (identitas.isMonarki()) {
                System.out.println("Negara " + identitas.getNama() + " mempunyai Raja bernama " + identitas.getNamaPemimpin());
            } else {
                System.out.println("Negara " + identitas.getNama() + " mempunyai " + identitas.getJenisKepemimpinan() + " bernama " + identitas.getNamaPemimpin());
                System.out.println("Deklarasi Kemerdekaan pada Tanggal " + identitas.getTanggalKemerdekaan() + " " + nmBulan.get(identitas.getBulanKemerdekaan()) + " " + identitas.getTahunKemerdekaan());
            }
            System.out.println();
        }
    }
}
