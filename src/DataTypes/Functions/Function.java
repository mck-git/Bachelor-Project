package DataTypes.Functions;

import DataTypes.Token;

import java.util.ArrayList;

public abstract class Function {

    private String name;
    private ArrayList< ArrayList<Token> > linesOfCodeInMethod;


    public Function(String name, ArrayList< ArrayList<Token> > linesOfCodeInMethod)
    {
        this.name = name;
        this.linesOfCodeInMethod = linesOfCodeInMethod;
    }

    public abstract void store();


    public void addLineOfCode(ArrayList<Token> tokens)
    {
        linesOfCodeInMethod.add( (ArrayList<Token>) tokens.clone() );
    }

    public ArrayList<ArrayList<Token>> getLinesOfCodeInMethod()
    {
        return linesOfCodeInMethod;
    }

    public String getName()
    {
        return name;
    }

}
