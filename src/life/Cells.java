package life;

public enum Cells {
    ALIVE('O'), EMPTY(' ');
    private final char symbol;

    Cells(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
