package Parser;

import java.io.*;
import java.util.ArrayList;

import Compile.Translator;
import DataTypes.Token;
import Errors.InvalidSyntaxException;
import SharedResources.InputType;


public class Lexer {
    private static String buffer = "";
    private static ArrayList<Token> tokens = new ArrayList<>();

    private static InputType m = InputType.NORMAL;

    private static int lineNumber = 0;

    public static void read(String filename) throws IOException, InvalidSyntaxException
    {

        File file = new File(filename);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();

        while (line != null)
        {
            lineNumber++;
            tokenizeString(line);

            clearBlankTokens();

            Translator.handleLine(tokens);
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
                readInputNormal(c);

            // ARGUMENT MODE
            else if (m == InputType.ARGUMENTS)
                readInputArgument(c);

            // STRING MODE
            else if (m == InputType.STRING)
                readInputString(c);
        }

        endTokenAndSwitchType(InputType.NORMAL);
    }

    private static void readInputNormal(char c) {
        if ( c == ' ' || c == '\n' || c == ';' || c == '\t')
            endTokenAndSwitchType(InputType.NORMAL);

        // SYMBOLS
        else if ( c == '+' || c == '-' || c == '/' || c == '*'
                  || c == '&' || c == '|'
                  || c == '(' || c == ')'
                  || c == '{' || c == '}'
                  || c == '!' || c == ','
                  || c == '[' || c == ']')
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
            endTokenAndSwitchType(InputType.STRING);

        else
            buffer += c;
    }

    private static void readInputArgument(char c) {
        if ( c == ',' && m == InputType.ARGUMENTS )
            endTokenAndSwitchType(InputType.ARGUMENTS);
    }

    private static void readInputString(char c) {
        if (c == '"')
            endTokenAndSwitchType(InputType.NORMAL);
        else
            buffer += c;
    }

    private static void clearBlankTokens()
    {
        int i = 0;

        while ( i < tokens.size() )
        {
            if (tokens.get(i).getContent().equals(""))
                tokens.remove(i);

            else
                i++;
        }

    }

    private static void endTokenAndSwitchType(InputType it)
    {
        tokens.add( new Token(buffer, m) );
        buffer = "";
        m = it;
    }

    public static int getLineNumber()
    {
        return lineNumber;
    }
}
