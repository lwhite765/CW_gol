package CW_gol;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.GridLayout;

/**
 * A class that extends JPanel that displays the grid
 *
 * @author Logan White
 */
public class Grid extends JPanel {
    private boolean[][] cellMatrix;

    /**
     * Creates a new Grid object
     *
     * @param width The width of the grid
     * @param height The height of the grid
     * @param aliveColour The colour of a cell when it is alive
     * @param deadColour The colour of a cell when it is dead 
     * @param borderColour The colour of each cell's border
     */
    Grid(int width, int height, Color aliveColour, Color deadColor, Color borderColour) {
        super(new GridLayout(width, height));

        cellMatrix = new boolean[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = new Cell(cellMatrix, i, j, aliveColour, deadColor, borderColour);
                add(cell);
            }
        }
    }

    /**
     * Returns a 2D array of booleans representing the grid that are true if the cell is
     * alive an false otherwise
     *
     * @return An 2D array of booleans representing the grid
     */
    public boolean[][] getCellMatrix() {
        return cellMatrix;
    }
}
