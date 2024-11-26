package soal3;

import java.util.ArrayList;
import java.util.Scanner;

public class MainSoal3 {
	public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<ProgramMahasiswa> daftarMahasiswa = new ArrayList<>();
        int pilihan;

        do {
            System.out.println("Menu:");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Hapus Mahasiswa berdasarkan NIM");
            System.out.println("3. Cari Mahasiswa berdasarkan NIM");
            System.out.println("4. Tampilkan Daftar Mahasiswa");
            System.out.println("0. Keluar");
            System.out.print("Pilihan: ");
            
            pilihan = input.nextInt();
            input.nextLine(); 

            switch (pilihan) {
                case 1: 
                    System.out.print("Masukkan Nama Mahasiswa: ");
                    String nama = input.nextLine();
                    System.out.print("Masukkan NIM Mahasiswa (harus unik): ");
                    String nim = input.nextLine();

                    boolean nimAda = false;
                    for (ProgramMahasiswa m1 : daftarMahasiswa) {
                        if (m1.getNim().equals(nim)) {
                        	nimAda = true;
                            break;
                        }
                    }
                    if (nimAda) {
                        System.out.println("NIM sudah terdaftar, coba lagi.");
                        System.out.println();
                    } else {
                        daftarMahasiswa.add(new ProgramMahasiswa(nama, nim));
                        System.out.println("Mahasiswa " + nama + " ditambahkan.");
                        System.out.println();
                    }
                    break;
                case 2:
                    System.out.print("Masukkan NIM Mahasiswa yang akan dihapus: ");
                    String hapus = input.nextLine();
                    boolean ditemukan = false;
                    for (int i = 0; i < daftarMahasiswa.size(); i++) {
                        if (daftarMahasiswa.get(i).getNim().equals(hapus)) {
                            daftarMahasiswa.remove(i);
                            System.out.println("Mahasiswa dengan NIM " + hapus + " dihapus.");
                            System.out.println();
                            ditemukan = true;
                            break;
                        }
                    }
                    if (!ditemukan) {
                        System.out.println("Mahasiswa dengan NIM tersebut tidak ditemukan.");
                        System.out.println();
                    }
                    break;
                case 3: 
                	System.out.print("Masukkan NIM Mahasiswa yang dicari: ");
                    String cari = input.nextLine();
                    ProgramMahasiswa mahasiswaDitemukan = null;

                    for (ProgramMahasiswa m1 : daftarMahasiswa) {
                        if (m1.getNim().equals(cari)) {
                            mahasiswaDitemukan = m1;
                            break;
                        }
                    }
                    if (mahasiswaDitemukan != null) {
                        System.out.println("Mahasiswa ditemukan: NIM: " + mahasiswaDitemukan.getNim() + ", Nama: " + mahasiswaDitemukan.getNama());
                        System.out.println();
                    } else {
                        System.out.println("Mahasiswa dengan NIM tersebut tidak ditemukan.");
                        System.out.println();
                    }
                    break;
                case 4: 
                    if (daftarMahasiswa.isEmpty()) {
                        System.out.println("Daftar mahasiswa masih kosong.");
                        System.out.println();
                    } else {
                        System.out.println("Daftar Mahasiswa:");
                        for (ProgramMahasiswa m1 : daftarMahasiswa) {
                            System.out.println("NIM: " + m1.getNim() + ", Nama: " + m1.getNama());
                        }
                        System.out.println();
                    }
                    break;
                case 0:
                	System.out.println("Terima Kasih!");
                	break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    break;
            }
        } while (pilihan != 0);

        input.close();
    }
}
