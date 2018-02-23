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

            clearBlankTokens();

            Compile.MainCompiler.handleLine(tokens);
            line = bufferedReader.readLine();
            tokens.clear();
            m = InputType.NORMAL;
        }

        fileReader.close();
    }

    private static void tokenizeString(String line)
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

        endTokenAndSwitchType(InputType.NORMAL);
    }

    private static void readInputNormal(char c) {
        if ( c == ' ' || c == '\n' || c == ';' || c == '\t')
        {
            endTokenAndSwitchType(InputType.NORMAL);
        }

        // SYMBOLS
        else if ( c == '+' || c == '-' || c == '/' || c == '*'
                  || c == '&' || c == '|'
                  || c == '(' || c == ')'
                  || c == '{' || c == '}')
        {
            endTokenAndSwitchType(InputType.NORMAL);
            buffer += c;
            endTokenAndSwitchType(InputType.NORMAL);
        }

        else if (c == '=' && buffer.equals("="))
        {
            buffer += c;
            endTokenAndSwitchType(InputType.NORMAL);
        }

        else if (c == '"')
        {
            endTokenAndSwitchType(InputType.STRING);
        }

        else
        {
            buffer += c;
        }
    }

    private static void readInputArgument(char c) {
        if ( c == ',' && m == InputType.ARGUMENTS )
        {
            endTokenAndSwitchType(InputType.ARGUMENTS);
        }
    }

    private static void readInputString(char c) {
        if (c == '"')
        {
            endTokenAndSwitchType(InputType.NORMAL);
        }

        else
        {
            buffer += c;
        }
    }

    private static void readInputMethod(char c) {

    }

    private static void clearBlankTokens()
    {
        for (int i = tokens.size()-1; i > 0; i--)
        {
            String token = tokens.get(i).getContent().trim();

            if ( token.equals("") || token.equals("\n") || token.equals("\t") )
            {
                tokens.remove(i);
                System.out.println("Removed blank token");
            }
        }
    }

    private static void endTokenAndSwitchType(InputType it)
    {
        tokens.add( new Token(buffer, m) );
        buffer = "";
        m = it;
    }


}
