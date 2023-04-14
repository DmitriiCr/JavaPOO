package SecondDayRectangleClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Rectangle {
    private int width;
    private int height;

    public Rectangle(int height , int width) throws IOException {
        if(this instanceof Square){
            while(height != width){
                System.out.println("The instance of square should have height equal to width");
                System.out.println("Introduce equal values");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                height = Integer.parseInt(br.readLine());
                width = Integer.parseInt(br.readLine());
            }
        }
        this.height = height;
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public Rectangle setWidth(int width) {
        this.width = width;
        return this;
    }

    public void displayRectangle() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print('#');
            }
            System.out.println();
        }
    }

    public int getHeight() {
        return height;
    }

    public Rectangle setHeight(int height) {
        this.height = height;
        return this;
    }
}
