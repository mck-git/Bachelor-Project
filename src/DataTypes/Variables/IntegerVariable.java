package DataTypes.Variables;

public class IntegerVariable extends Variable{

    int value;

    public IntegerVariable(String name, int value)
    {
        super(name);
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
