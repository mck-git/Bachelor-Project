package Maps.Variables;

import DataTypes.Variables.StringVariable;
import DataTypes.Variables.Variable;

import java.util.HashMap;

public class TemporaryVariablesMap {

    private static HashMap tempVarMap = new HashMap<String, Variable>();


    public static void add(String name, Variable variable)
    {
        tempVarMap.put(name, variable);
    }

    public static void edit(String name, Variable variable)
    {
        tempVarMap.replace(name, variable);
    }

    public static Variable find(String name)
    {
        return (Variable) tempVarMap.get(name);
    }

    public static int size()
    {
        return tempVarMap.size();
    }

    /**
     * FOR TESTING PURPOSES ONLY! DO NOT USE
     */
    public static void clear()
    {
        tempVarMap.clear();
    }

}
