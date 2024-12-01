import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    private final Board board;
    private final Player[] players;
    private int currentPlayer;

    public Game(int rows, int cols, Player player1, Player player2) {
        Board loadedBoard = Board.loadStateFromFile();
        if (loadedBoard != null) {
            this.board = loadedBoard;
        } else {
            this.board = new Board(rows, cols);
        }
        this.players = new Player[]{player1, player2};
        this.currentPlayer = 0;
    }

    public void start() {
        board.display();

        while (true) {
            System.out.println();
            System.out.println(players[currentPlayer].getName() + "'s turn (" + players[currentPlayer].getSymbol() + ")");


            int move = players[currentPlayer].makeMove(board);


            if (move == -1) {
                System.out.println("Exiting the game and saving state...");
                board.saveStateToFile();
                return;
            }


            if (board.isValidMove(move)) {
                board.makeMove(move, players[currentPlayer].getSymbol());
                board.display();


                if (board.checkWin(players[currentPlayer].getSymbol())) {
                    System.out.println(players[currentPlayer].getName() + " wins! Congratulations!");
                    players[currentPlayer].incrementWins();


                    List<Player> playerList = new ArrayList<>(Arrays.asList(players));
                    GameStateSaver.saveGameState(playerList);


                    System.out.println("Press ENTER to view High Score Table...");
                    try {
                        System.in.read();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    List<Player> highScores = GameStateSaver.loadHighScores();
                    GameStateSaver.printHighScores(highScores);

                    return;
                }


                currentPlayer = 1 - currentPlayer;
            } else {
                System.out.println("Invalid move, try again.");
            }
        }
    }
}
