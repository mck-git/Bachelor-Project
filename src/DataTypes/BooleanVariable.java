package DataTypes;

public class BooleanVariable {
    String name;
    boolean value;

    public BooleanVariable(String name, boolean value)
    {
        this.name = name;
        this.value = value;
    }

    public String getName()
    {
        return this.name;
    }

    public boolean getValue()
    {
        return this.value;
    }


}
