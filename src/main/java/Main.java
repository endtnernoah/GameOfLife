import processing.core.PApplet;

import java.util.Random;

public class Main extends PApplet {
    private int WIDTH = 800;
    private int HEIGHT = 800;
    private int CELL_SIZE = 8;

    private int cellSpaceX = WIDTH / CELL_SIZE;
    private int cellSpaceY = HEIGHT / CELL_SIZE;


    private int[] currentGeneration = new int[cellSpaceX * cellSpaceY];
    private int[] nextGeneration = new int[cellSpaceX * cellSpaceY];


    public void settings(){
        size(WIDTH, HEIGHT);

        // Setting up current generation with random numbers
        for(int i = 0; i < currentGeneration.length; i++){
            Random random = new Random();
            currentGeneration[i] = random.nextInt(2);
        }
    }

    public void setup(){
        background(255);

    }

    public void draw(){
        // Iterating over each cell to draw it
        for(int cellIndex = 0; cellIndex < currentGeneration.length; cellIndex++){
            int cellX = cellIndex % cellSpaceX * CELL_SIZE;
            int cellY = cellIndex / cellSpaceY * CELL_SIZE;

            System.out.println(currentGeneration[cellIndex]);

            // Drawing each cell in the current generation
            fill(255 * currentGeneration[cellIndex]);
            rect(cellX, cellY, CELL_SIZE, CELL_SIZE);
        }
    }
    public static void main(String[] args) {
        PApplet.main("Main");
    }
}