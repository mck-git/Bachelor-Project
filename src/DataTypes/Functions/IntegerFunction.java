package DataTypes.Functions;

import Compile.Mapper;
import DataTypes.Token;

import java.util.ArrayList;

public class IntegerFunction extends Function {

    public IntegerFunction(String name, ArrayList< ArrayList<Token> > linesOfCodeInMethod)
    {
        super(name, linesOfCodeInMethod);
    }

    public void store()
    {
        Mapper.storeIntFunction(this);
    }

}
