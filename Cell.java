package CW_gol;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;

/**
 * A class which extends JPanel and represents a cell in the grid
 *
 * @author Logan White
 */
public class Cell extends JPanel {
    private boolean cellMatrix[][];
    private int x, y;
    private Color aliveColour, deadColour;
    /**
     * Creates a new Cell object
     *
     * @param cellMatrix The cell matrix to get from
     * @param x The xPos of this cell in the matrix
     * @param y The xPos of this cell in the matrix
     * @param deadColour The colour of the cell when it is dead
     * @param aliveColour The colour of the cell when it is alive
     * @param borderColour The colour of the cell's border
     */
    public Cell(boolean[][] cellMatrix, int x, int y, Color aliveColour, Color deadColour, 
            Color borderColour) {
        this.x = x;
        this.y = y;
        this.cellMatrix = cellMatrix;
        this.aliveColour = aliveColour;
        this.deadColour = deadColour;

        this.setBorder(BorderFactory.createLineBorder(borderColour));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        // Draw alive colour if alive draw dead colour otherwise
        if (cellMatrix[x][y]) {
            g2d.setColor(aliveColour);
        }
        else {
            g2d.setColor(deadColour);
        }
        Rectangle rect = new Rectangle(0, 0, getWidth(), getHeight());
        g2d.fill(rect);
        g2d.draw(rect);
    }
}
