package game;

import java.util.Scanner;

public class TournamentDimensions {
    final int m;
    final int n;
    final int k;
    final int no;

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public int getK() {
        return k;
    }

    public int getNo() {
        return no;
    }

    public TournamentDimensions(final Scanner scanner) {
        System.out.println("Input no of Players");
        no = scanner.nextInt();
        System.out.println("Input no of Rows");
        m = scanner.nextInt();
        System.out.println("Input no of Cols");
        n = scanner.nextInt();
        System.out.println("Input no of Cells");
        k = scanner.nextInt();
    }
}
