package DataTypes.Variables;

public abstract class Variable {

    private String name;

    Variable(String name)
    {
        this.name = name;
    }


    public String getName()
    {
        return this.name;
    }

}
