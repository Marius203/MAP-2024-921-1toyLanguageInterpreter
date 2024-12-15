package Model.expressions;

import Model.adts.MyIDictionary;
import Model.adts.MyIHeap;
import Model.exceptions.VariableException;
import Model.types.IType;
import Model.values.IValue;

public class VariableExpression implements IExpression {
    private final String id;

    public VariableExpression(String id) {
        this.id = id;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> table, MyIHeap heap) throws VariableException {
        if (!table.containsKey(id))
            throw new VariableException("Variable " + id + " is not declared.");
        return table.get(id);
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public IType typeCheck(MyIDictionary<String, IType> typeEnv) {
        return typeEnv.get(id);
    }

}
