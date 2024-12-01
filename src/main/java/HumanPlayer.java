import java.util.Scanner;

public class HumanPlayer extends Player {
    private Scanner scanner;

    public HumanPlayer(char symbol, String name, Scanner scanner) {
        super(symbol, name);
        this.scanner = scanner;
    }

    @Override
    public int makeMove(Board board) {
        char moveChar;
        int move;

        while (true) {
            System.out.print("Enter your move (A to G), or 'exit' to quit: ");
            String input = scanner.next().toUpperCase();

            if (input.equals("EXIT")) {
                return -1;
            }

            if (input.length() == 1 && input.charAt(0) >= 'A' && input.charAt(0) <= 'G') {
                move = input.charAt(0) - 'A';
                if (move >= 0 && move < board.getCols() && board.isValidMove(move)) {
                    return move;
                }
            }
            System.out.println("Invalid input. Please enter a valid move.");
        }
    }
}
