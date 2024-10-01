package MODUL1;

import java.util.Scanner;

public class PRAK102_2310817220024_DessyNurulita {
	public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Input:\n");
        int angka = input.nextInt();
        int banyakAngka = 0;
        
        System.out.print("Output:\n");
        while (banyakAngka < 11) {
            if (angka % 5 == 0) {
                System.out.print((angka / 5) - 1 + " ");
            } else {
                System.out.print(angka + " ");
            }
            
            angka++;
            banyakAngka++;
        }
        input.close();
    }
}