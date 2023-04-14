package FirstDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EvenOddPrime {
    public static void main(String[] args) throws IOException {
        System.out.println("Enter number");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(br.readLine());
        if (number % 2 == 0) {
            System.out.println("Even");
        } else if (number % 2 != 0) {
            System.out.println("Odd");
            boolean isPrime = true;
            int m = (int) Math.sqrt((double) number);
            for (int i = 2; i < m; i++) {
                if (number % i == 0) {
                    System.out.println("The Number is not Prime");
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.println("The Number is Prime");
            }
        }
    }
}
