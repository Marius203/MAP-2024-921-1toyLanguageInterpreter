package Model.expressions;

import Model.adts.MyIDictionary;
import Model.exceptions.LogicException;
import Model.exceptions.VariableException;
import Model.values.IValue;

public interface IExpression {
    IValue eval(MyIDictionary<String, IValue> table) throws ArithmeticException, LogicException, VariableException;

    @Override
    String toString();
}
