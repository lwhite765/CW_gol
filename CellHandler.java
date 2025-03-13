package CW_gol;

/** 
 * A class used for handling a thread that manages a block
 * of cells
 *
 * @author Logan White
 */
public class CellHandler implements Runnable {
    private boolean isPaused;
    private boolean[][] cellMatrix;
    private int xMin, xMax, yMin, yMax;

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
        this.cellMatrix = cellMatrix;
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
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
    public synchronized void unpause() {
        isPaused = false;
        notify();
    }

    /**
     * The overwritten run method used to run the CellHandler
     */
    @Override
    public void run() {
        synchronized (cellMatrix) {
            // Handle pause
            if (isPaused) {
                wait();
            }
            for (int i = 0; i < cellMatrix.length; i++) {
                for (int j = 0; j < cellMatrix[0].length; j++) {
                    int livingCount = getLivingNeighbourCount(i, j);
                    // Die via over and under population
                    if (livingCount < 2 || livingCount > 3) {
                        cellMatrix[i][j] = false;
                    }
                    // Make alive if exactly 3 neighbours
                    else if (livingCount == 3) {
                        cellMatrix[i][j] = true;
                    }
                }
            }
        }
    }

    // Does exactly what the name says
    // Cells that are null are counted as zero 
    private int getLivingNeighbourCount(int x, int y) {
        int count = 0;
        // Count upper three cells
        for (int i = x - 1; i <= x + 1; i++) {
            if (getCB(i, y + 1)) {
                count++;
            }
        }
        
        // Count lower three cells
        for (int i = x - 1; i <= x + 1; i++) {
            if (getCB(i, y - 1)) {
                count++;
            }
        }

        // Count cells directly left and right
        if (getCB(x - 1, y)) {
            count++;
        }
        
        if (getCB(x + 1, y)) {
            count++;
        }

        return count;
    }

    // Method name is short for get while checking bounds
    // it returns false if the cell DNE
    private boolean getCB(int x, int y) {
        if (x < 0 || y < 0 || x >= cellMatrix.length || y >= cellMatrix[0].length) {
            return false;
        }
        else {
            return cellMatrix[x][y];
        }
    }
}
