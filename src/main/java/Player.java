public abstract class Player {
    protected char symbol;
    protected String name;
    private int wins;

    public Player(char symbol, String name) {
        this.symbol = symbol;
        this.name = name;
        this.wins = 0;
    }

    public char getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public void incrementWins() {
        wins++;
    }


    public void incrementWins(int additionalWins) {
        this.wins += additionalWins;
    }

    public abstract int makeMove(Board board);
}
