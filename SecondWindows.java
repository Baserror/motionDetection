import processing.core.PApplet;
import processing.core.PImage;
import processing.video.*;


public class SecondWindows extends PApplet {




    Capture video;
    PImage prev;

    private int startDetectionAreaX;
    private int startDetectionAreaY;
    private int endDetectionAreaX;
    private int endDetectionAreaY;

    float threshold = 25;




    public void setup() {
        String[] cameras = Capture.list();
        printArray(cameras);
        video = new Capture(this, cameras[3] );
        video.start();
        prev = createImage(640, 360, RGB);
    }

    public void settings(){
        size(640, 360);
    }



    public void captureEvent(Capture video) {
        prev.copy(video, 0, 0, video.width, video.height, 0, 0, prev.width, prev.height);
        prev.updatePixels();
        video.read();
    }

    public void draw() {

        video.loadPixels();
        prev.loadPixels();
        image(video, 0, 0);

        threshold = 95;


        float countChangedPixels = 0;
        float countUnchangedPixels = 0;


        loadPixels();
        for (int x = 0; x < video.width; x++ ) {
            for (int y = 0; y < video.height; y++ ) {
                int loc = x + y * video.width;
                int currentColor = video.pixels[loc];
                float r1 = red(currentColor);
                float g1 = green(currentColor);
                float b1 = blue(currentColor);
                int prevColor = prev.pixels[loc];
                float r2 = red(prevColor);
                float g2 = green(prevColor);
                float b2 = blue(prevColor);

                float d = distSq(r1, g1, b1, r2, g2, b2);

                if (d > threshold*threshold) {
                    countChangedPixels++;
                    pixels[loc] = color(255);
                } else {
                    countUnchangedPixels++;
                    pixels[loc] = color(0);
                }
            }
        }
        updatePixels();
        if (countChangedPixels != 0 && countUnchangedPixels !=0){
            float pixelChangeInPercent = (countChangedPixels/((countUnchangedPixels+countChangedPixels))*100);
            //println(pixelChangeInPercent);
        }
    }

    float distSq(float x1, float y1, float z1, float x2, float y2, float z2) {
        float d = (x2-x1)*(x2-x1) + (y2-y1)*(y2-y1) +(z2-z1)*(z2-z1);
        return d;
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
