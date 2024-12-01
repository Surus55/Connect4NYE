import java.util.Random;

public class AIPlayer extends Player {
    private final Random random = new Random();

    public AIPlayer(char symbol, String name) {
        super(symbol, name);
    }

    @Override
    public int makeMove(Board board) {
        int move;
        do {
            move = random.nextInt(board.getCols());
        } while (!board.isValidMove(move));
        return move;
    }
}
