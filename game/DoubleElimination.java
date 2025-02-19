package game;

import java.util.Arrays;
import java.util.Random;

public class DoubleElimination {
    Player[] players;
    Player[] upperBracket;
    Player[] lowerBracket;
    Player[] positions;
    Player[] emptyPlayers;
    int noOfPlayers;
    Random random;
    int m, n, k;
    int playerIdCnt = 1;

    public DoubleElimination(int noOfPlayers, int m, int n, int k) {
        this.noOfPlayers = noOfPlayers;
        this.m = m;
        this.n = n;
        this.k = k;
        players = new Player[noOfPlayers + countPlayers()];
        emptyPlayers = new Player[countPlayers()];
        random = new Random();
        int cnt = 0;
        while (cnt < noOfPlayers){
            int j = random.nextInt(players.length);
            if(players[j] == null){
                players[j] = new RandomPlayer();
                players[j].setID(playerIdCnt);
                playerIdCnt++;
                cnt ++;
            }
        }
        for(int i = 0; i < players.length; i ++){
            if(players[i] == null){
                players[i] = new EmptyPlayer();
                players[i].setID(playerIdCnt);
                playerIdCnt++;
                emptyPlayers[findNull(emptyPlayers)] = players[i];
            }
        }
        upperBracket = Arrays.copyOf(players, players.length);
        lowerBracket = new Player[players.length/2];
        positions = new Player[players.length];
    }

    public void playTournament(){
        if(noOfPlayers > 2){
            playFirstRound();
            playLowerRound(1);
            int losersTour = 2;
            int tour = 2;
            while(upperBracket[1] != null){
                playUpperRound(tour);
                tour++;
                if(findNull(lowerBracket) > 1 && findNull(lowerBracket) != 0){
                    playLowerRound(losersTour);
                    cleanArray();
                    losersTour++;
                }
                if(findNull(lowerBracket) > 1 && findNull(lowerBracket) != 0){
                    playLowerRound(losersTour);
                    losersTour ++;
                }
            }
            if(lowerBracket[1] != null){
                playLowerRound(losersTour);
            }
            playFinal();
        } else if (noOfPlayers == 2){
            int result = playGame(upperBracket[0], upperBracket[1]);
            if(result == 1){
                lowerBracket[0] = upperBracket[1];
            } else{
                lowerBracket[0] = upperBracket[0];
                upperBracket[0] = upperBracket[1];
            }
            playFinal();
        } else{
            positions[positions.length - 1] = upperBracket[0];
        }

    }

    public String outputResults(){
        StringBuilder sb = new StringBuilder();
        int index = positions.length - 1;
        sb.append("-------------------------").append(System.lineSeparator());
        int i = 0;
        int positionCounter = 1;
        while(i <= 3 && i < noOfPlayers){
            sb.append("| place #").append(positionCounter).append(": player ").append(positions[index].getID()).append(System.lineSeparator());
            index--;
            i++;
            positionCounter++;
        }
        int multiplier = 2;
        while(index > 0){
            int cnt = 0;
            while(cnt < multiplier && index > 0) {
                if(!(positions[index] instanceof EmptyPlayer)){
                    sb.append("| place #").append(positionCounter).append(": player ").append(positions[index].getID()).append(System.lineSeparator());
                    cnt++;
                }
                index --;
            }
            positionCounter ++;
            cnt = 0;
            while(cnt < multiplier && index > 0) {
                if(!(positions[index] instanceof EmptyPlayer)){
                    sb.append("| place #").append(positionCounter).append(": player ").append(positions[index].getID()).append(System.lineSeparator());
                    cnt++;
                }
                index --;
            }
            positionCounter ++;
            multiplier *= 2;
        }
        sb.append("-------------------------");
        return sb.toString();
    }

    private StringBuilder arrayToString(Player[] players, StringBuilder sb){
        for(Player player: players){
            if(player != null){
                sb.append(player.getID()).append(" ");
            } else{
                sb.append("null ");
            }
        }
        return sb;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("DoubleElimination{ upperBracket=");
        sb = arrayToString(upperBracket, sb);
        sb.append("} lowerBracket={");
        sb = arrayToString(lowerBracket, sb);
        sb.append("}");
        sb.append("} positions={");
        sb = arrayToString(positions, sb);
        sb.append("}");
        return sb.toString();
    }

    private int playGame(Player player1, Player player2){
        Game game = new Game(false, player1, player2);
        int result = 0;
        if(player1 instanceof EmptyPlayer && player2 instanceof EmptyPlayer){
            result = Math.random() < 0.5 ? 1 : 2;
        } else if(player1 instanceof EmptyPlayer){
            result = 2;
        } else if(player2 instanceof EmptyPlayer){
            result = 1;
        } else{
            while (result <= 0){
                result = game.play(new TicTacToeBoard(m, n, k));
            }
        }
        return result;
    }

    private void playFirstRound(){
        System.out.println("----------------------");
        System.out.println("Tour #1 Upper Bracket:");
        System.out.println("----------------------");
        for (int i = 0; i < upperBracket.length; i += 2){
            int result = playGame(upperBracket[i], upperBracket[i + 1]);
            System.out.println(outputGame(upperBracket, i, result));
            int loserResult = (result == 1) ? 2 : 1;
            lowerBracket[i/2] = players[i + loserResult - 1];
            upperBracket[i/2] = upperBracket[i + result - 1];
        }
        for(int i = upperBracket.length - 1; i >= upperBracket.length / 2; i--){
            upperBracket[i] = null;
        }
    }

    private void playFinal(){
        StringBuilder sb = new StringBuilder();
        Game gameFinal = new Game(false, lowerBracket[0], upperBracket[0]);
        int result = 0;
        while (result <= 0){
            result = gameFinal.play(new TicTacToeBoard(m, n, k));
        }
        sb.append("----------------------").append(System.lineSeparator());
        sb.append("FINAL GAME:").append(System.lineSeparator());
        sb.append("----------------------").append(System.lineSeparator());
        sb.append("player").append(lowerBracket[0].getID()).append(" vs ").append("player").append(upperBracket[0].getID()).append(System.lineSeparator());
        Player winner = (result == 1) ? lowerBracket[0] : upperBracket[0];
        sb.append("Result: player").append(winner.getID()).append(" won");
        System.out.println(sb);
        int loserResult = (result == 1) ? 2 : 1;
        if(loserResult == 1){
            positions[players.length - 2] = lowerBracket[0];
            positions[players.length - 1] = upperBracket[0];
        } else{
            positions[players.length - 2] = upperBracket[0];
            positions[players.length - 1] = lowerBracket[0];
        }
    }

    private void playLowerRound(int tour){
        System.out.println("----------------------");
        System.out.println("Tour #" + tour + " Lower Bracket:");
        System.out.println("----------------------");
        int i = 0;
        while (i < lowerBracket.length && lowerBracket[i] != null){
            int result = playGame(lowerBracket[i], lowerBracket[i + 1]);
            System.out.println(outputGame(lowerBracket, i, result));
            int loserResult = (result == 1) ? 2 : 1;
            positions[findNull(positions)] = lowerBracket[i + loserResult - 1];
            lowerBracket[i + loserResult - 1] = null;
            i += 2;
        }
    }

    private void playUpperRound(int tour) {
        int i = 0;
        System.out.println("----------------------");
        System.out.println("Tour #" + tour + " Upper Bracket:");
        System.out.println("----------------------");
        while (i < upperBracket.length && upperBracket[i] != null) {
            int result = playGame(upperBracket[i], upperBracket[i + 1]);
            System.out.println(outputGame(upperBracket, i, result));
            int loserResult = (result == 1) ? 2 : 1;
            lowerBracket[findNull(lowerBracket)] = upperBracket[i + loserResult - 1];
            upperBracket[i / 2] = upperBracket[i + result - 1];
            i += 2;
        }
        int j = findNull(upperBracket);
        for(int k = upperBracket.length - 1; k >= j / 2; k--){
            upperBracket[k] = null;
        }
    }

    private void cleanArray(){
        Player[] newArray = new Player[lowerBracket.length];
        int j = 0;
        for(int i = 0; i < lowerBracket.length; i ++){
            if(lowerBracket[i] != null){
                newArray[j] = lowerBracket[i];
                j++;
            }
        }
        lowerBracket = Arrays.copyOf(newArray, newArray.length);
    }

    private int countPlayers(){
        int powerOf2 = 1;
        while (powerOf2 < noOfPlayers) {
            powerOf2 *= 2;
        }
        return powerOf2 - noOfPlayers;
    }

    private String outputGame(Player [] array, int i, int result){
        StringBuilder sb = new StringBuilder();
        sb.append("player").append(array[i].getID()).append(" vs ").append("player").append(array[i + 1].getID()).append(System.lineSeparator());
        Player winner = (result == 1) ? array[i] : array[i + 1];
        sb.append("Result: player").append(winner.getID()).append(" won");
        return sb.toString();
    }

    private int findNull(Player[] players){
        int j = 0;
        while(players[j] != null){
            if(j >= players.length - 1){
                break;
            }
            j ++;
        }
        return j;
    }
}