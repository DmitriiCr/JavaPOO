package FirstDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReverseChain {
    public static void main(String[] args) throws IOException {
        System.out.println("Enter word");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        StringBuilder result = new StringBuilder(word);
        int lengthWord = word.length()-1;
        for(int i = lengthWord ; i>-1 ; i--){
            result.setCharAt(i , word.charAt(lengthWord-i));
        }
        System.out.println(result.toString());
    }
}
