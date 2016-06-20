
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SDA3214 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Membaca dimensi peta
        int tinggi = Integer.valueOf(scanner.nextLine());
        int lebar = Integer.valueOf(scanner.nextLine());
        PetaLapangan petaLap = new PetaLapangan(tinggi, lebar);
        Posisi start = new Posisi();
        //Membaca Peta
        for (int i = 0; i < tinggi; i++) {
            String str = scanner.nextLine();
            for (int j = 0; j < lebar; j++) {
                petaLap.peta[i][j] = str.charAt(j);
                if (str.charAt(j) == 'S') {
                    start.y = i;
                    start.x = j;
                }
            }
        }
        petaLap.cetakPeta();
        //Membaca Korban
        int urutan = 0;
        while (scanner.hasNextLine()) {
            urutan++;
            String strKorban = scanner.nextLine();
            String[] arrKorban = strKorban.split(" ", 4);
            String[] strPos = arrKorban[0].split(",");

            Posisi pos = new Posisi(Integer.valueOf(strPos[0]), Integer.valueOf(strPos[1]));
            String nama = arrKorban[1];
            String usia = arrKorban[2];
            String kondisi = "";
            if (arrKorban.length >= 4) {
                kondisi = arrKorban[3];
            }
            Korban korban = new Korban(pos, nama, usia, kondisi, urutan);
            petaLap.addKorban(pos, korban);
        }

        Perjalanan perjalanan = new Perjalanan();
        perjalanan.petaLap = petaLap;
        perjalanan.posisiSekarang = start;


    }
}

class Korban implements Comparable<Korban> {

    public String nama = "";
    public Posisi pos = new Posisi();
    public Integer kategoriKondisi = 0;
    public Integer kategoriUsia = 0;
    public Integer urutan = 0;

    public Korban(Posisi pos, String nama, String usia, String kondisi, int urutan) {
        this.pos = pos;
        this.nama = nama;
        this.setUsia(usia);
        this.setKondisi(kondisi);
        this.urutan = urutan;
    }

    public Korban() {
    }

    @Override
    public int compareTo(Korban o) {
        if (this.kategoriKondisi.equals(o.kategoriKondisi)) {
            if (this.kategoriUsia.equals(o.kategoriUsia)) {
                if (this.urutan.equals(o.urutan)) {
                    return this.nama.compareToIgnoreCase(o.nama);
                } else {
                    return this.urutan.compareTo(o.urutan);
                }
            } else {
                return this.kategoriUsia.compareTo(o.kategoriUsia);
            }
        } else {
            return this.kategoriKondisi.compareTo(o.kategoriKondisi);
        }
    }

    public void setKondisi(String kondisi) {
        if (kondisi.equalsIgnoreCase("luka berat")) {
            this.kategoriKondisi = 1;
        } else if (kondisi.equalsIgnoreCase("luka ringan")) {
            this.kategoriKondisi = 2;
        } else if (kondisi.equalsIgnoreCase("panik")) {
            this.kategoriKondisi = 3;
        } else {
            this.kategoriKondisi = 4;
        }
    }

    public void setUsia(String usia) {
        int umur = 0;
        int idxMin = usia.indexOf('-');
        if (usia.startsWith("<=") || usia.startsWith(">=")) {
            umur = Integer.valueOf(usia.substring(2));
        } else if (usia.startsWith("<")) {
            umur = Integer.valueOf(usia.substring(1)) - 1;
        } else if (usia.startsWith(">")) {
            umur = Integer.valueOf(usia.substring(1)) + 1;
        } else if (usia.indexOf('-') != -1) {
            int awal = Integer.valueOf(usia.substring(0, idxMin));
            umur = awal;
        }

        if (umur < 5) {
            //balita
            this.kategoriUsia = 1;
        } else if (umur <= 12) {
            //anak
            this.kategoriUsia = 2;
        } else if (umur <= 19) {
            //remaja
            this.kategoriUsia = 4;
        } else if (umur <= 64) {
            //dewasa
            this.kategoriUsia = 5;
        } else {
            //lansia
            this.kategoriUsia = 3;
        }
    }

    public String toString() {
        return this.nama;
    }
}

class PetaLapangan {

    public char[][] peta;
    public int tinggi;
    public int lebar;
    public HashMap<Posisi, ArrayList<Korban>> listKorban = new HashMap<Posisi, ArrayList<Korban>>();

    public PetaLapangan(int tinggi, int lebar) {
        this.peta = new char[tinggi][lebar];
        this.tinggi = tinggi;
        this.lebar = lebar;
    }

    public void addKorban(Posisi pos, Korban k) {
        if (this.listKorban.containsKey(pos)) {
            this.listKorban.get(pos).add(k);
        } else {
            ArrayList<Korban> lstKorban = new ArrayList<Korban>();
            lstKorban.add(k);
            this.listKorban.put(pos, lstKorban);
        }
    }

    public void cetakPeta() {
        for (int i = 0; i < this.tinggi; i++) {
            for (int j = 0; j < this.lebar; j++) {
                System.out.print(this.peta[i][j]);
            }
            System.out.println("");
        }
    }
}

class Posisi {

    public int x;
    public int y;

    public Posisi() {
    }

    public Posisi(int x, int y) {
        this.x = x;
        this.y = y;

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.x;
        hash = 67 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        Posisi other = (Posisi) o;
        return (this.x == other.x) && (this.y == other.y);
    }
}

class Perjalanan {

    public PriorityQueue<Korban> korbanDibawa = new PriorityQueue<Korban>();
    public PetaLapangan petaLap;
    public Posisi posisiSekarang = new Posisi();

    public void jalan() {
        if(this.atas()){
            jalan();
        }else if(this.kanan()){
            
        }else if(this.bawah()){
            
        }else if(this.kiri()){
            
        }
    }
    
    public void cekLokasi(){
        if(petaLap.peta[posisiSekarang.y][posisiSekarang.x]=='W'){
            //this.korbanDibawa.addAll(c);
        }
    }

    public boolean atas() {
        posisiSekarang.y--;
        if (posisiSekarang.y >= 0) {
            if (petaLap.peta[posisiSekarang.y][posisiSekarang.x] != '#' && petaLap.peta[posisiSekarang.y][posisiSekarang.x] != '*') {
                return true;
            } else {
                posisiSekarang.y++;
                return false;
            }
        } else {
            posisiSekarang.y++;
            return false;
        }
    }
    
    public boolean kanan() {
        posisiSekarang.x++;
        if (posisiSekarang.x < petaLap.lebar) {
            if (petaLap.peta[posisiSekarang.y][posisiSekarang.x] != '#' && petaLap.peta[posisiSekarang.y][posisiSekarang.x] != '*') {
                return true;
            } else {
                posisiSekarang.x--;
                return false;
            }
        } else {
            posisiSekarang.x--;
            return false;
        }
    }
    
    public boolean bawah() {
        posisiSekarang.y++;
        if (posisiSekarang.y < petaLap.tinggi) {
            if (petaLap.peta[posisiSekarang.y][posisiSekarang.x] != '#' && petaLap.peta[posisiSekarang.y][posisiSekarang.x] != '*') {
                return true;
            } else {
                posisiSekarang.y--;
                return false;
            }
        } else {
            posisiSekarang.y--;
            return false;
        }
    }
    
    public boolean kiri() {
        posisiSekarang.x--;
        if (posisiSekarang.x >= 0) {
            if (petaLap.peta[posisiSekarang.y][posisiSekarang.x] != '#' && petaLap.peta[posisiSekarang.y][posisiSekarang.x] != '*') {
                return true;
            } else {
                posisiSekarang.x++;
                return false;
            }
        } else {
            posisiSekarang.x++;
            return false;
        }
    }
}