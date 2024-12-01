import java.util.Scanner;

public class GameLauncher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter Player 1 name: ");
        String player1Name = scanner.nextLine();
        System.out.print("Enter Player 2 name: ");
        String player2Name = scanner.nextLine();


        Player player1 = new HumanPlayer('Y', player1Name, scanner);
        Player player2 = new AIPlayer('R', player2Name);


        Game game = new Game(6, 7, player1, player2);


        game.start();
    }
}
