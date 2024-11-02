package Model.expressions;

import Model.adts.MyIDictionary;
import Model.values.IValue;

public class ValueExpression implements IExpression {
    private final IValue value;

    public ValueExpression(IValue value) {
        this.value = value;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> table) {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
