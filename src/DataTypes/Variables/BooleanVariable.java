package DataTypes.Variables;

public class BooleanVariable extends Variable{
    boolean value;

    public BooleanVariable(String name, boolean value)
    {
        super(name);
        this.value = value;
    }

    public BooleanVariable(String name)
    {
        super(name);
    }

    public boolean getValue()
    {
        return this.value;
    }


}
