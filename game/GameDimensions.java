package game;

import java.util.Scanner;

public class GameDimensions {
    final int m;
    final int n;
    final int k;

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public int getK() {
        return k;
    }

    public GameDimensions(final Scanner scanner) {
        System.out.println("Input no of Rows");
        m = scanner.nextInt();
        System.out.println("Input no of Cols");
        n = scanner.nextInt();
        System.out.println("Input no of Cells");
        k = scanner.nextInt();
    }
}
