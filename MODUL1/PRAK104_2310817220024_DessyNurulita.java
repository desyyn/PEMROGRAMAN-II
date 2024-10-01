package MODUL1;

import java.util.Scanner;

public class PRAK104_2310817220024_DessyNurulita {
	public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Input:\n");
        System.out.print("Tangan Abu: ");
        String abuInput = input.nextLine();
        System.out.print("Tangan Bagas: ");
        String bagasInput = input.nextLine();
        
        String[] pilAbu = abuInput.split(" ");
        String[] pilBagas = bagasInput.split(" ");

        int poinAbu = 0;
        int poinBagas = 0;

        for (int i = 0; i < 3; i++) {
            String abu = pilAbu[i];
            String bagas = pilBagas[i];

            if (abu.equals("B") && bagas.equals("G")) {
                poinAbu++;
            } else if (abu.equals("G") && bagas.equals("K")) {
                poinAbu++;
            } else if (abu.equals("K") && bagas.equals("B")) {
                poinAbu++;
            } else if (bagas.equals("B") && abu.equals("G")) {
                poinBagas++;
            } else if (bagas.equals("G") && abu.equals("K")) {
                poinBagas++;
            } else if (bagas.equals("K") && abu.equals("B")) {
                poinBagas++;
            }
        }

        System.out.print("Output:\n");
        if (poinAbu > poinBagas) {
            System.out.println("Abu");
        } else if (poinBagas > poinAbu) {
            System.out.println("Bagas");
        } else {
        	System.out.println("Seri");
        }

        input.close();
    }
}