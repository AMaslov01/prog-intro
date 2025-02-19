package game;

public class Game {
    private final boolean log;
    private final Player player1, player2;

    public Game(final boolean log, final Player player1, final Player player2) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
    }

    public int play(final Board board) {
        while (true) {
            // :NOTE: toString
            log("Position:\n" + board.getPosition().toString());
            final int result1 = move(board, player1, 1);
            if (result1 != -1) {
                log("Position:\n" + board.getPosition().toString());
                return result1;
            }
            log("Position:\n" + board.getPosition().toString());
            final int result2 = move(board, player2, 2);
            if (result2 != -1) {
                log("Position:\n" + board.getPosition().toString());
                return result2;
            }
        }
    }

    private int move(final Board board, final Player player, final int no) {
        final Move move = player.move(board.getPosition(), board.getTurn());
        final Result result = board.makeMove(move);
        if (result == Result.WIN) {
            log("Player " + no + " won");
            return no;
        } else if (result == Result.LOSE) {
            log("Player " + no + " lose");
            return 3 - no;
        } else if (result == Result.LOSE_BY_DEBUT) { // :NOTE: ??
            log("Player " + no + " lose due to not matching debut rule");
            return 3 - no;
        }else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else {
            return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
