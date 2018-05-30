package DataTypes.Lists;

import java.util.ArrayList;

public class StringList extends List {

    ArrayList<String> listValues;

    public StringList(String name)
    {
        super(name);
    }

    public void add(String string)
    {
        listValues.add(string);
    }

    public String get(int index)
    {
        return listValues.get(index);
    }

    public int length()
    {
        return listValues.size();
    }

}
