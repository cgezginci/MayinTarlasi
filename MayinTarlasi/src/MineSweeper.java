import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    Scanner input = new Scanner(System.in);
    Random rastgele = new Random();
    int satir, sutun, boyut;
    String[][] mayin;
    String[][] oyun;
    int count = 0;
    boolean isWin = true;

    public void gameStart() { // Değerlendirme Formu 7. Kullanıcıdan alınan bilgilerle mayın tarlasının alanı oluşturulur.
        System.out.print("Kaç satırlık bir oyun oluşturmak istersiniz? ");
        this.satir = input.nextInt();
        System.out.print("Kaç sütunluk bir oyun oluşturmak istersiniz? ");
        this.sutun = input.nextInt();
        this.mayin = new String[satir][sutun];
        this.oyun = new String[satir][sutun];
        this.boyut = satir * sutun;
    }

    public void prepare() { //Değerlendirme Formu 8 Diziye boyutunun 4 te 1 i kadar mayın aşağıdaki metod ile yerleştiriliyor.
        for (int i = 0; i < mayin.length; i++) {
            for (int j = 0; j < mayin[i].length; j++) {
                mayin[i][j] = "-";
                oyun[i][j] = "-";
            }
        }
        while (count != boyut / 4) {
            int randSatir = rastgele.nextInt(satir);
            int randSutun = rastgele.nextInt(sutun);
            if (!mayin[randSatir][randSutun].equals("*")) {
                mayin[randSatir][randSutun] = "*";
                count++;
            }
        }
    }

    public void checkMine(int r, int c) { //Değerlendirme Formu 9-11-12// Kullanıcıdan alınan koordinatlar ile mayın kontrolü yapılır.
        int noMines = 0;
        int mineCount = 0;
        if (mayin[r][c].equals("-")) {
            if ((r < satir - 1) && (mayin[r + 1][c].equals("*"))) {
                mineCount++;
            }
            if ((c < sutun - 1) && (mayin[r][c + 1].equals("*"))) {
                mineCount++;
            }
            if ((r > 0) && (mayin[r - 1][c].equals("*"))) {
                mineCount++;
            }
            if ((c > 0) && (mayin[r][c - 1].equals("*"))) {
                mineCount++;
            }
            if (r > 0 && c < sutun - 1 && (mayin[r - 1][c + 1].equals("*"))) {
                mineCount++;
            }
            if (r > 0 && c > 0 && (mayin[r - 1][c - 1].equals("*"))) {
                mineCount++;
            }
            if (r < satir - 1 && c < sutun - 1 && (mayin[r + 1][c + 1].equals("*"))) {
                mineCount++;
            }
            if (r < satir - 1 && c > 0 && (mayin[r + 1][c - 1].equals("*"))) {
                mineCount++;
            }
            if (oyun[r][c].equals("-")) {
                oyun[r][c] = String.valueOf(noMines);
                mayin[r][c] = String.valueOf(noMines);
            }
            if (mineCount > 0) {
                oyun[r][c] = String.valueOf(mineCount);
                mayin[r][c] = String.valueOf(mineCount);
            }
        }
    }

    public void run() { // Değerlendirme Formu 6-13-14-15 Kullanıcının girdiği koordinatlarla oyunu kazanıp kazanamadığının kontrolü yapılır.
        gameStart();
        prepare();
        System.out.println("Mayınların Konumu:");
        print(mayin);
        System.out.println("------------");
        System.out.println("Mayın Tarlasına Hoşgeldiniz!");
        playGame();
        if (isWin) {
            System.out.println("Tebrikler oyunu kazandınız.");
            print(oyun);
        } else {
            System.out.println("KAYBETTİNİZ.");
        }

    }

    public void playGame() { /* Değerlendirme Formu 10
                            Eğer yanlış koordinat girerse tekrardan girmesi istenir.
                            Eğer kullanıcı aynı koordinatı seçerse farklı bir koordinat girilmesi istenir.*/
        int row, col;
        while (isWin) {
            print(oyun);
            System.out.print("Bir satır seçin: ");
            row = input.nextInt();
            System.out.print("Bir sütun seçin: ");
            col = input.nextInt();
            System.out.println("------------");
            if (row < 0 || row >= satir) {
                System.out.println("Geçersiz Koordinat!");
                continue;
            }
            if (col < 0 || col >= sutun) {
                System.out.println("Geçersiz Koordinat!");
                continue;
            }
            if (!mayin[row][col].equals("*")) {
                if (mayin[row][col].equals("-")) {
                    checkMine(row, col);
                    if (isGameFinish(mayin)) {
                        isWin = true;
                        break;
                    }

                } else {
                    System.out.println("Aynı noktayı tekrar seçemezsiniz! ");
                }

            } else {
                isWin = false;
            }

        }
    }

    public void print(String[][] arr) { //Oyun alanını ekrana yazdırır.
        for (String[] row : arr) {
            for (String col : row) {
                System.out.print(col + "  ");
            }
            System.out.println();
        }
    }

    public boolean isGameFinish(String[][] arr) { //Oyun bitti mi kontrolü yapar.
        for (String[] row : arr) {
            for (String col : row) {
                if (col.equals("-")) {
                    return false;
                }
            }

        }
        return true;
    }


}