package Model.expressions;

import Model.adts.MyIDictionary;
import Model.exceptions.MyException;
import Model.values.IValue;

public interface IExpression {
    IValue eval(MyIDictionary<String, IValue> table) throws MyException;

    @Override
    String toString();
}
