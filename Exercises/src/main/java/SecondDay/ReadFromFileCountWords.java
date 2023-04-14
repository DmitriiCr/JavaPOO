package SecondDay;

import java.io.*;

public class ReadFromFileCountWords {

    public static void main(String[] args) throws IOException {
        System.out.println("Enter file path");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String chain = br.readLine();
        boolean canGoAhead = false;
        FileReader fileReader = null;
        while(!canGoAhead){
            try{
                fileReader = new FileReader(chain);
                canGoAhead = true;
            }catch (FileNotFoundException e){
                System.out.println("Enter file path");
                chain = br.readLine();
            }
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String curLine;
        while ((curLine = bufferedReader.readLine()) != null){
            //process the line as required
            System.out.println(curLine);
        }
        bufferedReader.close();
    }
}
