package DataTypes.Functions;

import Compile.Mapper;
import DataTypes.Token;

import java.util.ArrayList;

public class CharFunction extends Function {

    public CharFunction(String name, ArrayList<ArrayList<Token>> linesOfCodeInMethod)
    {
        super(name, linesOfCodeInMethod);
    }

    public void store()
    {
        Mapper.storeCharFunction(this);
    }

}
