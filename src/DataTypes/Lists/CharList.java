package DataTypes.Lists;

import java.util.ArrayList;

public class CharList extends List {

    ArrayList<Character> listValues;

    public CharList(String name)
    {
        super(name);
        listValues = new ArrayList<>();
    }

    public void add(char c)
    {
        listValues.add(c);
    }

    public char get(int index)
    {
        return listValues.get(index);
    }

    public int length()
    {
        return listValues.size();
    }

}
