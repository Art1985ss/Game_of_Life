package life;

import javax.swing.*;

public class GameOfLife extends JFrame {
    private Board board;
    private int generation = 1;
    private final OptionsPanel optionsPanel;

    public GameOfLife() {
        super("Game of life");
        this.board = Board.createRandom(150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));
        this.setSize(300, 300);
        optionsPanel = new OptionsPanel();
        this.setVisible(true);
        updateVisuals();
    }

    public void nextGeneration() {
        Evolution t = new Evolution(this.board);
        t.start();
        updateVisuals();
        System.out.println(this);
        try {
            Thread.sleep(optionsPanel.waitTime());
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        generation++;
        this.board = t.getBoard();
    }

    private void resetGame() {
        this.board = Board.createRandom(optionsPanel.getFieldSize());
        this.generation = 1;
    }

    public void advanceGenerations() {
        while (true) {
            if (optionsPanel.getPause()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            nextGeneration();
            if (optionsPanel.getReset()) {
                this.resetGame();
            }
        }

    }

    private void updateVisuals() {
        this.getContentPane().removeAll();
        optionsPanel.setAliveLabel(board.getAlive());
        optionsPanel.setGenerationLabel(generation);
        this.add(optionsPanel);
        this.add(new BoardPanel(board, optionsPanel.getColor()));
        this.repaint();
        this.pack();
        this.validate();
        this.setVisible(true);
    }

    @Override
    public String toString() {
        return String.format("Generation #%d\nAlive: %d\n%s", generation, board.getAlive(), board);
    }
}
