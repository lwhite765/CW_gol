package CW_gol;

import java.awt.event.ActionListener;

/**
 * The class containing the main method
 *
 * @author Logan White
 */
public class Main {
    public static void main(String[] args) {
        final String TITLE = "Conway's game of life";
        final int WIDTH = 100;
        final int HEIGHT = 100;

        // Create CellHandler's
        CellHandler cellHandler = new CellHandler(grid.getCellMatrix(), 0, WIDTH,
                0, HEIGHT);

        // Create callbacks
        final ActionListener startCallback = (ae) -> {
            cellHandler.unpause();
        };

        final ActionListener stopCallback = (ae) -> {
            cellHandler.pause();
        };

        // TODO implement reset and close
        final ActionListener resetCallback = (ae) -> {

        };

        final ActionListener closeCallback = (ae) -> {

        };

        // Create JComponents
        Grid grid = new Grid(WIDTH, HEIGHT);
        ControlPad controlPad = new ControlPad(startCallback, stopCallback, 
                resetCallback, closeCallback);


        Component components[] = {
            // TODO handle layouts
            new Component(grid, ""),
            new Component(controlPad, ""),
        };

        new MainFrame(components, TITLE);
    }
}
