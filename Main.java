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
        final int THREADS_PER_X = 10; // Best if WIDTH can be evenly divided by this number
        final int THREADS_PER_Y = 10; // Best if HEIGHT can be evenly divided by this number

        final Color DEAD_COLOUR = Color.WHITE;
        final Color ALIVE_COLOUR = Color.BLACK;
        final Color BORDER_COLOUR = Color.GRAY;
        


        Grid grid = new Grid(WIDTH, HEIGHT, ALIVE_COLOUR, DEAD_COLOUR, BORDER_COLOUR);
        CellHandler[] cellHandlers = new CellHandler[THREADS_PER_X * THREADS_PER_Y];

        // Create CellHandler's and their threads
        final int X_JUMP = WIDTH/THREADS_PER_X;
        final int Y_JUMP = HEIGHT/THREADS_PER_Y;
        for (int i = 0; i < THREADS_PER_X; i++) {
            for (int j = 0; j < THREADS_PER_Y; j++) {
                // Conver 2D index into 1D index
                cellHandlers[i + j * THREADS_PER_X] = new CellHandler(grid, i * X_JUMP, 
                    (i + 1) * X_JUMP, j * Y_JUMP, (j + 1) * Y_JUMP);
                Thread cellThread = new Thread(cellHandlers[i + j * THREADS_PER_X]);
                cellThread.start();
            }
        }
        // Create callbacks
        final ActionListener startCallback = (ae) -> {
            for (int i = 0; i < cellHandlers.length; i++) {
                cellHandlers[i].unpause();
            }
        };

        final ActionListener stopCallback = (ae) -> {
            for (int i = 0; i < cellHandlers.length; i++) {
                cellHandlers[i].pause();
            }
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
            new Component(grid, BorderLayout.SOUTH),
        };

        MainFrame mainFrame = new MainFrame(components, TITLE);
    }
}
