package life;

import java.awt.*;
import java.util.Random;

public class Board {
    private final int size;
    private final Cells[][] field;

    public Board(int size) {
        this.size = size;
        field = new Cells[size][size];
        fillEmpty();
    }

    public static Board createRandom(int size) {
        Random random = new Random();
        Board board = new Board(size);
        for (int x = 0; x < board.getSize(); x++) {
            for (int y = 0; y < board.getSize(); y++) {
                board.setCell(new Point(x, y),
                        random.nextBoolean() ? Cells.ALIVE : Cells.EMPTY);
            }
        }
        return board;
    }

    private void fillEmpty() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                field[x][y] = Cells.EMPTY;
            }
        }
    }

    public void setCell(Point point, Cells cell) {
        field[point.x][point.y] = cell;
    }

    public Cells getCell(Point point) {
        return field[point.x][point.y];
    }

    public int getSize() {
        return size;
    }

    public int getAlive() {
        int alive = 0;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                alive += field[x][y] == Cells.ALIVE ? 1 : 0;
            }
        }
        return alive;
    }

    public int getAliveNeighbours(Point point) {
        int numberOfAlive = 0;
        for (int x = point.x - 1; x < point.x + 2; x++) {
            for (int y = point.y - 1; y < point.y + 2; y++) {
                if (point.x == x && point.y == y) {
                    continue;
                }
                Point p = new Point(x < 0 ? size - 1 : x > size - 1 ? 0 : x, y < 0 ? size - 1 : y > size - 1 ? 0 : y);
                numberOfAlive += field[p.x][p.y] == Cells.ALIVE ? 1 : 0;
            }
        }
        return numberOfAlive;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                sb.append(field[x][y]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
