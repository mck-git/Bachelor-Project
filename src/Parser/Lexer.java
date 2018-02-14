package Parser;

import java.io.*;
import java.util.ArrayList;

import DataTypes.Token;
import SharedResources.InputType;


public class Lexer {
    private static String buffer = "";
    private static ArrayList<Token> tokens = new ArrayList<>();

    private static InputType m = InputType.NORMAL;

    public static void read(String filename) throws FileNotFoundException, IOException
    {

        File file = new File(filename);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);


        String line = bufferedReader.readLine();

        while (line != null)
        {
            readString(line);
            line = bufferedReader.readLine();
        }

        fileReader.close();
    }

    public static void readString(String line)
    {

        for (char c : line.toCharArray())
        {
            // NORMAL MODE
            if (m == InputType.NORMAL)
            {
                handleInputNormal(c);
            }

            // ARGUMENT MODE
            else if (m == InputType.ARGUMENTS)
            {
                handleInputArgument(c);
            }

            // STRING MODE
            else if (m == InputType.STRING)
            {
                handleInputString(c);
            }

        }

    }

    private static void handleInputString(char c) {
        if (c == '"')
        {
            tokens.add( new Token(buffer, m) );
            buffer = "";
        }

        else
        {
            buffer += c;
        }
    }

    private static void handleInputArgument(char c) {
        if ( c == ',' && m == InputType.ARGUMENTS )
        {
            tokens.add( new Token(buffer, m) );
            buffer = "";
        }
    }

    private static void handleInputNormal(char c) {
        if ( c == ' ' )
        {
            tokens.add( new Token(buffer, m) );
            buffer = "";
        }

        else if (c == '"')
        {
            m = InputType.STRING;
        }
    }

}
