package Model.expressions;

import Model.adts.MyIDictionary;
import Model.exceptions.MyException;
import Model.values.IValue;

public class VariableExpression implements IExpression {
    private final String id;

    public VariableExpression(String id) {
        this.id = id;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> table) throws MyException {
        return table.get(id);
    }

    @Override
    public String toString() {
        return id;
    }

}
