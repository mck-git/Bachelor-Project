package DataTypes.Lists;

import java.util.ArrayList;

public class BooleanList extends List {

    ArrayList<Boolean> listValues;

    public BooleanList(String name)
    {
        super(name);
        listValues = new ArrayList<>();
    }

    public void add(boolean bool)
    {
        listValues.add(bool);
    }

    public boolean get(int index)
    {
        return listValues.get(index);
    }

    public int length()
    {
        return listValues.size();
    }



}
