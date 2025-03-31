package CW_gol;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A class for handling mouse clicks for the cells
 *
 * @author Logan White
 */
public class CellMouseHandler implements MouseListener {
    private Runnable onClick;

    /**
     * Creates a new CellMouseHandler object
     *
     * @param onClick A runnable object that is called when the mouse is clicked
     */
    public CellMouseHandler(Runnable onClick) {
        if (onClick == null) {
            this.onClick = () -> {};
        }
        else {
            this.onClick = onClick;
        }
    }

    /**
     * Calls the runnable provided in the constructor when the mouse is clicked
     *
     * @param event The mouse event
     */
    @Override
    public void mouseClicked(MouseEvent event) {
        onClick.run();
    }

    @Override
    public void mousePressed(MouseEvent event) {}

    @Override
    public void mouseReleased(MouseEvent event) {}
    
    @Override
    public void mouseEntered(MouseEvent event) {}
    
    @Override
    public void mouseExited(MouseEvent event) {}

}
