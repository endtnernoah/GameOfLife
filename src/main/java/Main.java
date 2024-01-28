import processing.core.PApplet;

public class Main extends PApplet {
    private int WIDTH = 800;
    private int HEIGHT = 800;
    private int CELL_SIZE = 8;

    private int cellSpaceX = WIDTH / CELL_SIZE;
    private int cellSpaceY = HEIGHT / CELL_SIZE;


    private boolean[] cells = new boolean[cellSpaceX * cellSpaceY];

    public void settings(){
        size(WIDTH, HEIGHT);
    }

    public void setup(){
        background(255);
        for(int cellIndex = 0; cellIndex < cells.length; cellIndex++){
            int cellX = cellIndex % cellSpaceX * CELL_SIZE;
            int cellY = cellIndex / cellSpaceY * CELL_SIZE;

            fill(255);
            rect(cellX, cellY, CELL_SIZE, CELL_SIZE);
        }
    }

    public void draw(){

    }
    public static void main(String[] args) {
        PApplet.main("Main");
    }
}