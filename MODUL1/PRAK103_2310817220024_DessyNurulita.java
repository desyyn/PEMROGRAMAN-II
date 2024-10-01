package MODUL1;

import java.util.Scanner;

public class PRAK103_2310817220024_DessyNurulita {
	public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N, bilAwal;

        System.out.print("Input:\n");
        N = input.nextInt();
        bilAwal = input.nextInt();
        System.out.print("Output:\n");

        int i = 0;
        int deret = bilAwal;

        do {
            if (deret % 2 != 0) {
                System.out.print(deret + " ");
                i++;
            }
            deret++;
        } while (i < N);

        System.out.println();
        input.close();
    }
}