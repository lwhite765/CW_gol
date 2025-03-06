package CW_gol;

/**
 * A class used for handling a thread that manages a block
 * of cells
 *
 * @author Logan White
 */
public class CellHandler implements Runnable {
    private boolean isPaused;
    /**
     * Creates a new CellHandler object
     *
     * @param cellMatrix A matrix of switches for each cell
     * @param xMin The start of the range (inclusive) of cells to handle on the x-axis
     * @param xMax The end of the range (inclusive) of cells to handle on the x-axis
     * @param yMin The start of the range (inclusive) of cells to handle on the y-axis
     * @param yMax The end of the range (inclusive) of cells to handle on the y-axis
     */
    public CellHandler(boolean[][] cellMatrix, int xMin, int xMax, int yMin, int yMax) {
        isPaused = true; 
    }

    /**
     * Stops the thread from doing work until unpause() is called
     */
    public void pause() {
        isPaused = true;
    }

    /**
     * Tells the thread to do work until pause() is called
     */
    public void unpause() {
        isPaused = false;
    }

    @Override
    public void run() {

    }
}
