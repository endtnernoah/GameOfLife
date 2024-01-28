import processing.core.PApplet;

public class Main extends PApplet {
    private int WIDTH = 800;
    private int HEIGHT = 800;

    public void settings(){
        size(WIDTH, HEIGHT);
    }
    
    public void setup(){
        size(800, 800);
        noStroke();
        background(200);
    }

    public void draw(){

    }
    public static void main(String[] args) {
        PApplet.main("Main");
    }
}