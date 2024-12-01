import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

public class Board {
    private char[][] grid;
    private int rows;
    private int cols;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = '-';
            }
        }
    }

    public Board(char[][] grid) {
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.grid = grid;
    }

    public char[][] getGrid() {
        return grid;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean isValidMove(int col) {
        if (col < 0 || col >= cols) return false;
        return grid[0][col] == '-';
    }

    public void makeMove(int col, char symbol) {
        for (int i = rows - 1; i >= 0; i--) {
            if (grid[i][col] == '-') {
                grid[i][col] = symbol;
                break;
            }
        }
    }

    public void display() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean checkWin(char symbol) {

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == symbol) {
                    if (checkDirection(row, col, 1, 0, symbol) ||   // Horizontal
                            checkDirection(row, col, 0, 1, symbol) ||   // Vertical
                            checkDirection(row, col, 1, 1, symbol) ||   // Diagonal /
                            checkDirection(row, col, 1, -1, symbol))    // Diagonal \
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkDirection(int row, int col, int dRow, int dCol, char symbol) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int r = row + i * dRow;
            int c = col + i * dCol;
            if (r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c] == symbol) {
                count++;
            } else {
                break;
            }
        }
        return count == 4;
    }

    public void saveStateToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("jatekallas.txt"))) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    writer.write(grid[i][j] + " ");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Board loadStateFromFile() {
        File file = new File("jatekallas.txt");
        if (!file.exists()) {
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            List<char[]> gridList = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                char[] row = new char[tokens.length];
                for (int i = 0; i < tokens.length; i++) {
                    row[i] = tokens[i].charAt(0);
                }
                gridList.add(row);
            }
            char[][] grid = new char[gridList.size()][gridList.get(0).length];
            return new Board(gridList.toArray(grid));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
