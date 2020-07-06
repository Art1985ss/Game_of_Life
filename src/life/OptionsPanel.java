package life;

import javax.swing.*;
import java.awt.*;

public class OptionsPanel extends JPanel {
    private final JToggleButton pauseButton;
    private final JLabel generationLabel;
    private final JLabel aliveLabel;
    private final JLabel speedLabel;
    private final JSlider speedSlider;
    private final JColorChooser colorChooser;
    private final JTextField fieldSize;

    private boolean pause = false;
    private boolean reset = false;

    public OptionsPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setPreferredSize(new Dimension(500, 300));
        Dimension dimension = new Dimension(150, 25);
        pauseButton = new JToggleButton("Pause");
        pauseButton.setName("PlayToggleButton");
        pauseButton.setPreferredSize(dimension);
        pauseButton.addItemListener(itemEvent -> {
            if (pauseButton.getText().equals("Pause")) {
                pauseButton.setText("Resume");
            } else {
                pauseButton.setText("Pause");
            }
            pause = !pause;
        });
        this.add(pauseButton);
        JButton resetButton = new JButton("Reset");
        resetButton.setName("ResetButton");
        resetButton.setPreferredSize(dimension);
        resetButton.addActionListener(actionEvent -> reset = !reset);
        this.add(resetButton);
        this.add(new JLabel("Enter field size : "));
        fieldSize = new JTextField();
        fieldSize.setPreferredSize(dimension);
        fieldSize.setMaximumSize(dimension);
        this.add(fieldSize);
        generationLabel = new JLabel();
        generationLabel.setName("GenerationLabel");
        this.add(generationLabel);
        aliveLabel = new JLabel();
        aliveLabel.setName("AliveLabel");
        this.add(aliveLabel);
        speedLabel = new JLabel("Speed mode : 5");
        this.add(speedLabel);
        speedSlider = new JSlider();
        speedSlider.setMaximum(10);
        speedSlider.setMinimum(1);
        speedSlider.setValue(5);
        speedSlider.addChangeListener(changeEvent ->
                speedLabel.setText("Speed mode : " + speedSlider.getValue()));
        this.add(speedSlider);
        this.add(new JLabel("Choose color : "));
        colorChooser = new JColorChooser();
        colorChooser.setColor(Color.BLACK);
        this.add(colorChooser);
    }

    public Color getColor() {
        return this.colorChooser.getColor();
    }

    public int getFieldSize() {
        int size = 50;
        try {
            size = Integer.parseInt(fieldSize.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return size;
    }

    public boolean getPause() {
        return pause;
    }

    public int waitTime() {
        return 5000 / speedSlider.getValue();
    }

    public boolean getReset() {
        if (reset) {
            reset = false;
            return true;
        }
        return false;
    }

    public void setGenerationLabel(int generation) {
        generationLabel.setText("Generation #" + generation);
    }

    public void setAliveLabel(int alive) {
        aliveLabel.setText("Alive : " + alive);
    }
}
