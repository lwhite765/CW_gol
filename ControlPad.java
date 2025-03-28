package CW_gol;

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
    private static final int COLS = 4;
    private static final String START_TEXT = "Start";
    private static final String PAUSE_TEXT = "Pause";
    private static final String RESET_TEXT = "Reset";
    private static final String CLOSE_TEXT = "Close";

    /**
     * Creates a new ControlPad object
     *
     * @param startCallback The Callback to start the simulation
     * @param pauseCallback The Callback to pause the simulation
     * @param resetCallback The Callback to reset the simulation
     * @param closeCallback The Callback to close the simulation
     */
    public ControlPad(ActionListener startCallback, ActionListener pauseCallback,
        ActionListener resetCallback, ActionListener closeCallback) {
        super(new GridLayout(ROWS, COLS));

        JButton startButton = new JButton(START_TEXT);
        startButton.addActionListener(startCallback);
        add(startButton);

        JButton pauseButton = new JButton(PAUSE_TEXT);
        pauseButton.addActionListener(pauseCallback);
        add(pauseButton);

        JButton resetButton = new JButton(RESET_TEXT);
        resetButton.addActionListener(resetCallback);
        add(resetButton);

        JButton closeButton = new JButton(CLOSE_TEXT);
        closeButton.addActionListener(closeCallback);
        add(closeButton);
    }
}
