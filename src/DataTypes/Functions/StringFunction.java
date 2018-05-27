package DataTypes.Functions;

import Compile.Mapper;
import DataTypes.Token;

import java.util.ArrayList;

public class StringFunction extends Function{

    public StringFunction(String name, ArrayList<ArrayList<Token>> linesOfCodeInMethod)
    {
        super(name,linesOfCodeInMethod);
    }

    public void store()
    {
        Mapper.storeStringFunction(this);
    }


}
