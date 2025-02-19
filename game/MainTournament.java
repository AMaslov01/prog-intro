package game;

import java.util.Scanner;

public class MainTournament {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        TournamentDimensions tournamentDimensions = new TournamentDimensions(scanner);
        DoubleElimination doubleElimination = new DoubleElimination(tournamentDimensions.getNo(), tournamentDimensions.getM(),
                tournamentDimensions.getN(), tournamentDimensions.getK());
        doubleElimination.playTournament();
        System.out.println(doubleElimination.outputResults());
    }
}
