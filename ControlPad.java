package CW_gol;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

/**
 * A class that extends JPanel with several buttons for controling the game
 *
 * @author Logan White
 */
public class ControlPad extends JPanel {
    private static final int ROWS = 1;
    private static final int COLS = 3;
    private static final String START_TEXT = "Start";
    private static final String PAUSE_TEXT = "Pause";
    private static final String RESET_TEXT = "Reset";

    /**
     * Creates a new ControlPad object
     *
     * @param startCallback The Callback to start the simulation
     * @param pauseCallback The Callback to pause the simulation
     * @param resetCallback The Callback to reset the simulation
     * @param buttonForeground The forground colour of the buttons
     * @param buttonBackground The background colour of the buttons
     */
    public ControlPad(ActionListener startCallback, ActionListener pauseCallback,
        ActionListener resetCallback, Color buttonForeground, Color buttonBackground) {
        super(new GridLayout(ROWS, COLS));

        JButton startButton = new JButton(START_TEXT);
        startButton.setBackground(buttonBackground);
        startButton.setForeground(buttonForeground);
        startButton.addActionListener(startCallback);
        add(startButton);

        JButton pauseButton = new JButton(PAUSE_TEXT);
        pauseButton.setBackground(buttonBackground);
        pauseButton.setForeground(buttonForeground);
        pauseButton.addActionListener(pauseCallback);
        add(pauseButton);

        JButton resetButton = new JButton(RESET_TEXT);
        resetButton.setBackground(buttonBackground);
        resetButton.setForeground(buttonForeground);
        resetButton.addActionListener(resetCallback);
        add(resetButton);
    }
}
