package CW_gol;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.util.function.BooleanSupplier;

/**
 * A class which extends JPanel and represents a cell in the grid
 *
 * @author Logan White
 */
public class Cell extends JPanel {
    private BooleanSupplier aliveSupplier;
    private Color aliveColour, deadColour;
    /**
     * Creates a new Cell object
     *
     * @param aliveSupplier A boolean supplier which says if the cell is alive or not
     * @param deadColour The colour of the cell when it is dead
     * @param aliveColour The colour of the cell when it is alive
     */
    public Cell(BooleanSupplier aliveSupplier, Color deadColour, Color aliveColour) {
        this.aliveSupplier = aliveSupplier; 
        this.aliveColour = aliveColour;
        this.deadColour = deadColour;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        // Draw alive colour if alive draw dead colour otherwise
        if (aliveSupplier.getAsBoolean()) {
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
