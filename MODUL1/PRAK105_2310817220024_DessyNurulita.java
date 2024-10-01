package MODUL1;

import java.util.Locale;
import java.util.Scanner;

public class PRAK105_2310817220024_DessyNurulita {
    public static final double PHI = 3.14;

    public static void main(String[] args) {
    	Locale.setDefault(Locale.US);
        Scanner input = new Scanner(System.in).useLocale(Locale.US);

        double jari2, tinggi, volume;

        System.out.print("Input:\n");
        System.out.print("Masukkan jari-jari: ");
        jari2 = input.nextDouble();
        System.out.print("Masukkan tinggi: ");
        tinggi = input.nextDouble();

        System.out.println();

        volume = PHI * jari2 * jari2 * tinggi;

        System.out.print("Output:\n");
        System.out.printf("Volume tabung dengan jari-jari %.1f cm dan tinggi %.1f cm adalah %.3f m3", jari2, tinggi, volume);

        input.close();
    }
}