package Parser;

import java.io.*;
import java.util.ArrayList;

import DataTypes.Token;
import SharedResources.InputType;


public class Lexer {
    private static String buffer = "";
    private static ArrayList<Token> tokens = new ArrayList<>();

    private static InputType m = InputType.NORMAL;

    public static void read(String filename) throws IOException
    {

        File file = new File(filename);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);


        String line = bufferedReader.readLine();

        while (line != null)
        {
            tokenizeString(line);

            Compile.MainCompiler.handleLine(tokens);
            line = bufferedReader.readLine();
            tokens.clear();
            m = InputType.NORMAL;
        }

        fileReader.close();
    }

    public static void tokenizeString(String line)
    {

        for (char c : line.toCharArray())
        {
            // NORMAL MODE
            if (m == InputType.NORMAL)
            {
                readInputNormal(c);
            }

            // ARGUMENT MODE
            else if (m == InputType.ARGUMENTS)
            {
                readInputArgument(c);
            }

            // STRING MODE
            else if (m == InputType.STRING)
            {
                readInputString(c);
            }

            else if (m == InputType.METHOD)
            {
                readInputMethod(c);
            }
        }
    }

    private static void readInputNormal(char c) {
        if ( c == ' ' || c == '\n' || c == ';')
        {
            tokens.add( new Token(buffer, m) );
            buffer = "";
        }

        // MATH
//        else if (c == '=' || c == '+' || c == '-' || c == '/' || c == '*')
//        {
//            tokens.add( new Token(buffer, m) );
//            buffer = "";
//        }

        else if (c == '"')
        {
            m = InputType.STRING;
        }

        else
        {
            buffer += c;
        }
    }

    private static void readInputArgument(char c) {
        if ( c == ',' && m == InputType.ARGUMENTS )
        {
            tokens.add( new Token(buffer, m) );
            buffer = "";
        }
    }

    private static void readInputString(char c) {
        if (c == '"')
        {
            tokens.add( new Token(buffer, m) );
            buffer = "";
            m = InputType.NORMAL;
        }

        else
        {
            buffer += c;
        }
    }

    private static void readInputMethod(char c) {

    }





}