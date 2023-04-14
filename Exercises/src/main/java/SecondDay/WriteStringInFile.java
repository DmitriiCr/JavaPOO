package SecondDay;

import java.io.*;

public class WriteStringInFile {
    public static void main(String[] args) throws IOException {
        System.out.println("Enter the phrase");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String chain = br.readLine();
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("src\\main", "FileWithText.txt")));
        writer.write(chain);
        writer.close();
    }
}
