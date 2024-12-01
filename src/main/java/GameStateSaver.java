import java.io.*;
import java.util.*;

public class GameStateSaver {

    private static final String FILE_PATH = "highscores.txt";


    public static void saveGameState(List<Player> players) {

        List<Player> existingPlayers = loadHighScores();


        for (Player newPlayer : players) {
            boolean found = false;
            for (Player existingPlayer : existingPlayers) {
                if (existingPlayer.getName().equals(newPlayer.getName())) {
                    existingPlayer.incrementWins(newPlayer.getWins());
                    found = true;
                    break;
                }
            }
            if (!found) {
                existingPlayers.add(newPlayer);
            }
        }


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Player player : existingPlayers) {
                writer.write(player.getName() + " " + player.getWins());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<Player> loadHighScores() {
        List<Player> players = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String name = parts[0];
                int wins = Integer.parseInt(parts[1]);


                Player player = new HumanPlayer('X', name, null);
                player.incrementWins(wins);
                players.add(player);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return players;
    }


    public static void printHighScores(List<Player> players) {
        if (players.isEmpty()) {
            System.out.println("No high scores yet.");
            return;
        }


        Map<String, Integer> scoreMap = new HashMap<>();
        for (Player player : players) {
            scoreMap.put(player.getName(), scoreMap.getOrDefault(player.getName(), 0) + player.getWins());
        }


        List<Map.Entry<String, Integer>> sortedScores = new ArrayList<>(scoreMap.entrySet());
        sortedScores.sort((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()));

        System.out.println("High Score Table:");
        for (Map.Entry<String, Integer> entry : sortedScores) {
            System.out.println(entry.getKey() + " - " + entry.getValue() + " wins");
        }
    }
}
