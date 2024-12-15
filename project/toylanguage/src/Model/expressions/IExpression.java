package Model.expressions;

import Model.adts.MyIDictionary;
import Model.adts.MyIHeap;
import Model.exceptions.LogicException;
import Model.exceptions.VariableException;
import Model.types.IType;
import Model.values.IValue;

public interface IExpression {
    IValue eval(MyIDictionary<String, IValue> table, MyIHeap heap) throws ArithmeticException, LogicException, VariableException;

    @Override
    String toString();
    IType typeCheck(MyIDictionary<String, IType> typeEnv) throws LogicException;
}
