import processing.core.PApplet;

import java.util.Random;

public class Main extends PApplet {
    private final int WIDTH = 800;
    private final int HEIGHT = 800;
    private final int CELL_SIZE = 8;

    private final int cellSpaceX = WIDTH / CELL_SIZE;
    private final int cellSpaceY = HEIGHT / CELL_SIZE;
    private int[] currentGeneration = new int[cellSpaceX * cellSpaceY];
    private final int[] nextGeneration = new int[cellSpaceX * cellSpaceY];
    private final Random random = new Random();
    private float cellColor = 0;
    private float targetColor = 0;

    public void settings(){
        size(WIDTH, HEIGHT);

        // Setting up current generation with random numbers
        for(int i = 0; i < currentGeneration.length; i++){
            currentGeneration[i] = random.nextDouble() < 0.10 ? 1 : 0;
        }
    }

    public void setup(){
        colorMode(HSB, 360);
        background(0, 255, 0);

        // Iterating over each cell to draw it
        for(int cellIndex = 0; cellIndex < currentGeneration.length; cellIndex++) {
            int cellX = cellIndex % cellSpaceX * CELL_SIZE;
            int cellY = cellIndex / cellSpaceY * CELL_SIZE;

            int currentCell = currentGeneration[cellIndex];

            // Drawing each cell in the current generation
            fill(currentCell * cellColor, 255, currentCell * 255);
            rect(cellX, cellY, CELL_SIZE, CELL_SIZE);
        }
    }

    public void draw(){
        // Iterating over each cell to draw it
        for(int cellIndex = 0; cellIndex < currentGeneration.length; cellIndex++){
            int cellX = cellIndex % cellSpaceX * CELL_SIZE;
            int cellY = cellIndex / cellSpaceY * CELL_SIZE;

            int currentCell = currentGeneration[cellIndex];

            // Drawing each cell in the current generation
            fill(currentCell * cellColor, 255, currentCell * 255);
            rect(cellX, cellY, CELL_SIZE, CELL_SIZE);

            // Checking calculating amount of neighbors
            int n = countNonZeroNeighbors(cellIndex);

            // Rule 1: currentCell == 0 & n == 3: currentCell -> 1
            if (currentCell == 0 && n == 3) { nextGeneration[cellIndex] = 1; }
            // Rule 2: currentCell == 1 & n < 2: currentCell -> 0
            else if (currentCell != 0 && n < 2){ nextGeneration[cellIndex] = 0; }
            // Rule 3: currentCell == 1 & n < 4: currentCell -> 1
            else if (currentCell != 0 && n < 4){ nextGeneration[cellIndex] = 1; }
            // Rule 4: currentCell == 1 & n >= 4: currentCell -> 0
            else if (currentCell != 0){ nextGeneration[cellIndex] = 0; }
        }

        // Updating generation
        currentGeneration = nextGeneration;

        // Sleeping
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if(targetColor == cellColor){
            targetColor = 360 - cellColor;
        }

        if(targetColor == 360) cellColor += 1;
        if(targetColor == 0) cellColor -= 1;
    }

    private int countNonZeroNeighbors(int cellIndex) {
        int currentRow = cellIndex / cellSpaceY;
        int currentCol = cellIndex % cellSpaceX;

        int count = 0;

        // Check each of the 8 neighbors
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue; // Skip the cell itself

                int neighborRow = currentRow + i;
                int neighborCol = currentCol + j;

                // Check boundaries
                if (neighborRow >= 0 && neighborRow < cellSpaceY && neighborCol >= 0 && neighborCol < cellSpaceX) {
                    int neighborIndex = neighborRow * cellSpaceX + neighborCol;

                    // Check if the neighbor has a non-zero value
                    if (currentGeneration[neighborIndex] != 0) {
                        count++;
                    }
                } else {
                    count -= 1;
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
        PApplet.main("Main");
    }
}