package soal2;

public class Anjing extends HewanPeliharaan {
    private String warnaBulu;
    private String[] kemampuan;

    public Anjing(String r, String n, String w, String[] k) {
        super(r, n);
        this.warnaBulu = w;
        this.kemampuan = k;
    }

    
    public void displayDetailAnjing() {
        super.display(); 
        System.out.println("Memiliki warna bulu: " + warnaBulu);
        String kemampuanGabung = String.join(" ", kemampuan);
        System.out.println("Memiliki kemampuan: " + kemampuanGabung);
    }
}
