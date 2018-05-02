package DataTypes.Variables;

public class IntegerVariable extends Variable{

    private int value;

    public IntegerVariable(String name, int value)
    {
        super(name);
        this.value = value;
    }

    public IntegerVariable(String name)
    {
        super(name);
    }

    public int getValue() {
        return this.value;
    }
}
