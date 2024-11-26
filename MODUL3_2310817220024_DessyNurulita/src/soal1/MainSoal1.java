package soal1;

import java.util.LinkedList;
import java.util.Scanner;

public class MainSoal1 {
	public static void main(String[] args) {
        LinkedList<ProgramDadu> dadu = new LinkedList<>(); 
        Scanner input = new Scanner(System.in);
        
        System.out.println(""); 
        int jumlahDadu = input.nextInt(); 

        for (int i = 0; i < jumlahDadu; i++) {
            ProgramDadu acak = new ProgramDadu();
            dadu.add(acak); 
        }

        int total = 0;
        
        for (int i = 0; i < dadu.size(); i++) {
            int nilai = dadu.get(i).getNilai();
            System.out.println("Dadu ke-" + (i + 1) + " bernilai " + nilai);
            total += nilai; 
        }

        System.out.println("Total nilai dadu keseluruhan " + total);
        
        input.close();
    }
}