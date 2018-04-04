package DataTypes.Functions;

public class FunctionContainer {

    String type;
    Function function;

    public FunctionContainer(String type, Function function)
    {
        this.type = type;
        this.function = function;
    }

    public String getType() {
        return type;
    }

    public Function getFunction() {
        return function;
    }





}
