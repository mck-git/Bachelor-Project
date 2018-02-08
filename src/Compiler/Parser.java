package Compiler;

import java.io.*;


public class Parser {
    public static void read(String filename) throws FileNotFoundException, IOException
    {

        File file = new File(filename);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);


        String line = bufferedReader.readLine();

        while (line != null)
        {
            MainCompiler.handleLine(line);
            line = bufferedReader.readLine();
        }

        fileReader.close();
    }

}
