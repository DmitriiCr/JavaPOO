package SecondDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class CountNumberOfWords {
    public static void main(String[] args) throws IOException {
        System.out.println("Enter the sentence");
        System.out.println("There should not be any space before first word");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String chain = br.readLine();
        System.out.println( ""+chain.split(" ").length);
    }
}
