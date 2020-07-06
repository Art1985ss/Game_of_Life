package life;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    private static final int CELL_SIZE = 5;
    private final int size;
    private final Board board;
    private final Color color;

    public BoardPanel(Board board, Color color) {
        this.color = color;
        this.board = board;
        this.size = CELL_SIZE * board.getSize();
        Dimension preferredSize = new Dimension(this.size, this.size);
        this.setSize(preferredSize);
        this.setPreferredSize(preferredSize);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < board.getSize() * CELL_SIZE; i += CELL_SIZE) {
            g.drawLine(i, 0, i, size);
            g.drawLine(0, i, size, i);
        }
        g.setColor(color);
        for (int x = 0; x < board.getSize(); x++) {
            for (int y = 0; y < board.getSize(); y++) {
                if (board.getCell(new Point(x, y)) == Cells.ALIVE) {
                    g.fillRect(y * CELL_SIZE, x * CELL_SIZE, CELL_SIZE + 1, CELL_SIZE + 1);
                }
            }
        }
    }
}
