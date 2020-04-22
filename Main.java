import processing.core.PApplet;
import processing.core.PImage;
import processing.video.*;




public class Main extends PApplet {


    private int startDetectionAreaX;
    private int startDetectionAreaY;
    private int endDetectionAreaX;
    private int endDetectionAreaY;

    public static void main(String[] args) {


        PApplet.main("Main");


    }

    Capture video;

    public void settings(){
        size(640, 360);

    }

    public void setup(){
        video = new Capture(this, 640, 480);
        video.start();
    }

    public void draw(){
        background(0);
        image(video, 0,0 );
    }

    public void captureEvent(Capture video) {
        video.read();
    }

    public void mousePressed() {
        setStartDetectionAreaX(mouseX);
        setStartDetectionAreaY(mouseY);
    }

    public void mouseReleased(){
        setEndDetectionAreaX(mouseX);
        setEndDetectionAreaY(mouseY);
        PApplet.main("SecondWindows");
    }


    public int getStartDetectionAreaX() {
        return startDetectionAreaX;
    }

    public int getStartDetectionAreaY() {
        return startDetectionAreaY;
    }

    public int getEndDetectionAreaX() {
        return endDetectionAreaX;
    }

    public int getEndDetectionAreaY() {
        return endDetectionAreaY;
    }

    public void setStartDetectionAreaX(int startDetectionAreaX) {
        this.startDetectionAreaX = startDetectionAreaX;
    }

    public void setStartDetectionAreaY(int startDetectionAreaY) {
        this.startDetectionAreaY = startDetectionAreaY;
    }

    public void setEndDetectionAreaX(int endDetectionAreaX) {
        this.endDetectionAreaX = endDetectionAreaX;
    }

    public void setEndDetectionAreaY(int endDetectionAreaY) {
        this.endDetectionAreaY = endDetectionAreaY;
    }

}

