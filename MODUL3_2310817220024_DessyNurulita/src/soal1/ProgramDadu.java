package soal1;

public class ProgramDadu {
	private int nilai;
	
    public ProgramDadu() {
        acakNilai();
    }

    public int acakNilai() {
        nilai = 1 + (int)(Math.random() * 6) ;
        return nilai ;
    }

    public int getNilai() {
        return nilai;
    }
}
