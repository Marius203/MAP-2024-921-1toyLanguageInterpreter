package Model.expressions;

import Model.adts.MyIDictionary;
import Model.exceptions.VariableException;
import Model.values.IValue;

public class VariableExpression implements IExpression {
    private final String id;

    public VariableExpression(String id) {
        this.id = id;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> table) throws VariableException {
        if (!table.containsKey(id))
            throw new VariableException("Variable " + id + " is not declared.");
        return table.get(id);
    }

    @Override
    public String toString() {
        return id;
    }

}
