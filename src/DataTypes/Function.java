package DataTypes;

import java.util.ArrayList;

public interface Function {

//    String name;
//    ArrayList<ArrayList<Token>> linesOfCodeInMethod;

//    Function (String name, ArrayList< ArrayList<Token> > linesOfCodeInMethod);
//    {
//        this.name = name;
//        this.linesOfCodeInMethod = linesOfCodeInMethod;
//    }

    void addLineOfCode(ArrayList<Token> tokens);
//    {
//        linesOfCodeInMethod.add(tokens);
//    }

    void store();

}
