package FirstDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pyramid {
    public static void main(String[] args) throws IOException {
        System.out.println("Enter number of pyramid's lines");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberStars = Integer.parseInt(br.readLine());
        int lastIndex = numberStars - 1;
        int firstIndex = numberStars - 1;
        for (int i = 0; i < numberStars; i++) {
            System.out.print(" ".repeat(firstIndex));
            if (i % 2 != 0) {
                for (int k = firstIndex; k <= lastIndex; k++) {
                    if (k % 2 == 0) {
                        System.out.print(" ");
                    } else {
                        System.out.print("*");
                    }
                }
                System.out.print(" ");
            } else {
                for (int k = firstIndex; k <= lastIndex; k++) {
                    if (k % 2 == 0) {
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
            lastIndex++;
            firstIndex--;
        }
    }
}
