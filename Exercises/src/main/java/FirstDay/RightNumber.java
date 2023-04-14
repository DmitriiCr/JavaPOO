package FirstDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RightNumber {
    public static void main(String[] args) throws IOException {
        int randomNumber = (int) (Math.random() * 100);
        int number;
        do {
            System.out.println("Enter number");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            number = Integer.parseInt(br.readLine());
            if (randomNumber > number) {
                System.out.println("Too Small");
            } else if (randomNumber < number) {
                System.out.println("Too Big");
            } else {
                System.out.println("Congratulations the secret number is " + randomNumber);
            }
        } while (randomNumber != number);
    }
}
