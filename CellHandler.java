package CW_gol;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/** 
 * A class used for handling a thread that manages a block
 * of cells
 *
 * @author Logan White
 * @author Anupriya Shaju
 */
public class CellHandler implements Runnable {
    private boolean isPaused;
    private boolean[][] cellMatrix;
    private Grid mainGrid;
    private int xMin, xMax, yMin, yMax;
    private final CyclicBarrier bar;

    // Short for cell matrix copy
    private boolean[][] cellMatCpy;

    /**
     * Creates a new CellHandler object
     *
     * @param mainGrid   The programs main Grid
     * @param xMin       The start of the range (inclusive) of cells to handle on the x-axis
     * @param xMax       The end of the range (exclusive) of cells to handle on the x-axis
     * @param yMin       The start of the range (inclusive) of cells to handle on the y-axis
     * @param yMax       The end of the range (exclusive) of cells to handle on the y-axis
     * @param bar
     */
    public CellHandler(Grid mainGrid, int xMin, int xMax, int yMin, int yMax, CyclicBarrier bar) {
        isPaused = true; 
        this.cellMatrix = mainGrid.getCellMatrix();
        this.mainGrid = mainGrid;
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.bar = bar;

        if (cellMatCpy == null) {
            copyCellMatrix();
        }
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
        // Run forever
        while (true) {
            synchronized (this) {
                // Handle pause
                if (isPaused) {
                    try {
                        wait();
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                copyCellMatrix();
                for (int i = xMin; i < xMax; i++) {
                    for (int j = yMin; j < yMax; j++) {
                        int livingCount = getLivingNeighbourCount(cellMatCpy, i, j);
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
                try{
                    bar.await();
                }
                catch (InterruptedException | BrokenBarrierException e){
                    throw new RuntimeException(e);
                }

                //mainGrid.repaint();
                // TODO replace with something perminent
                /*
                */
            }
        }
    }

    // Does exactly what the name says
    // Cells that are null are counted as zero 
    private int getLivingNeighbourCount(boolean[][] matrix, int x, int y) {
        int count = 0;
        // Count upper three cells
        for (int i = x - 1; i <= x + 1; i++) {
            if (getCB(matrix, i, y + 1)) {
                count++;
            }
        }
        
        // Count lower three cells
        for (int i = x - 1; i <= x + 1; i++) {
            if (getCB(matrix, i, y - 1)) {
                count++;
            }
        }

        // Count cells directly left and right
        if (getCB(matrix, x - 1, y)) {
            count++;
        }
        
        if (getCB(matrix, x + 1, y)) {
            count++;
        }

        return count;
    }

    // Method name is short for get while checking bounds
    // it returns false if the cell DNE
    private boolean getCB(boolean[][] matrix, int x, int y) {
        if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length) {
            return false;
        }
        else {
            return matrix[x][y];
        }
    }

    // Copys the contents of CellMatrix to CellMatCpy
    public void copyCellMatrix() {
        cellMatCpy = new boolean[cellMatrix.length][];
        for (int i = 0; i < cellMatCpy.length; i++) {
            cellMatCpy[i] = cellMatrix[i].clone();
        }
    }
}
