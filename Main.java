package CW_gol;

import java.awt.Color;
import java.awt.BorderLayout;
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
        final Color DEAD_COLOUR = Color.WHITE;
        final Color ALIVE_COLOUR = Color.BLACK;
        final Color BORDER_COLOUR = Color.GRAY;
        


        Grid grid = new Grid(WIDTH, HEIGHT, ALIVE_COLOUR, DEAD_COLOUR, BORDER_COLOUR);

        // Create CellHandler's and their threads
        CellHandler cellHandler = new CellHandler(grid, 0, WIDTH,
                0, HEIGHT);
        Thread cellThread = new Thread(cellHandler);
        cellThread.start();

        // Create callbacks
        final ActionListener startCallback = (ae) -> {
            cellHandler.unpause();
        };

        final ActionListener stopCallback = (ae) -> {
            cellHandler.pause();
        };

        final ActionListener resetCallback = (ae) -> {
            boolean[][] cellMatrix = grid.getCellMatrix();
            for (int i = 0; i < cellMatrix.length; i++) {
                for (int j = 0; j < cellMatrix[0].length; j++) {
                    cellMatrix[i][j] = false;
                }
            }
            grid.repaint();
        };

        // Create JComponents
        ControlPad controlPad = new ControlPad(startCallback, stopCallback, 
                resetCallback);


        Component components[] = {
            new Component(controlPad, BorderLayout.NORTH),
            new Component(grid, BorderLayout.CENTER),
        };

        MainFrame mainFrame = new MainFrame(components, TITLE);
    }
}
