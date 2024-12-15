package Model.expressions;

import Model.adts.MyIDictionary;
import Model.adts.MyIHeap;
import Model.types.IType;
import Model.values.IValue;

public class ValueExpression implements IExpression {
    private final IValue value;

    public ValueExpression(IValue value) {
        this.value = value;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> table, MyIHeap heap) {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public IType typeCheck(MyIDictionary<String, IType> typeEnv) {
        return value.getType();
    }
}
