package life;

import java.awt.*;

public class Evolution extends Thread {
    private Board board;

    public Evolution(Board board) {
        this.board = board;
    }

    @Override
    public void run() {
        nextGeneration();
    }

    private void nextGeneration() {
        Board boardNext = new Board(board.getSize());
        for (int x = 0; x < board.getSize(); x++) {
            for (int y = 0; y < board.getSize(); y++) {
                Point p = new Point(x, y);
                int aliveCount = board.getAliveNeighbours(p);
                Cells cell = board.getCell(p);
                cell = aliveCount < 2 || aliveCount > 3 ? Cells.EMPTY : cell;
                cell = cell == Cells.EMPTY ? aliveCount == 3 ? Cells.ALIVE : cell : cell;
                boardNext.setCell(p, cell);
            }
        }
        this.board = boardNext;
    }

    public Board getBoard(){
        return this.board;
    }
}
