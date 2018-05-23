package DataTypes.Functions;

import DataTypes.Token;
import DataTypes.Variables.Variable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Function {

    private String name;
    private ArrayList< ArrayList<Token> > linesOfCodeInMethod;

    private ArrayList<Variable> argumentVariables;

    public Function(String name, ArrayList< ArrayList<Token> > linesOfCodeInMethod)
    {
        this.name = name;
        this.linesOfCodeInMethod = linesOfCodeInMethod;
        this.argumentVariables = new ArrayList<>();
    }

    public abstract void store();

    public void addLineOfCode(ArrayList<Token> tokens)
    {
        linesOfCodeInMethod.add( (ArrayList<Token>) tokens.clone() );
    }

    public void addArguments(ArrayList<Variable> arguments)
    {
        argumentVariables.addAll( (ArrayList<Variable>) arguments.clone() );
    }

    public void addArgument(Variable argument)
    {
        argumentVariables.add(argument);
    }

    public ArrayList<ArrayList<Token>> getLinesOfCodeInMethod()
    {
        return linesOfCodeInMethod;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<Variable> getArgumentVariables() {
        return argumentVariables;
    }
}
