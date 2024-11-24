package Model.statements;

import Model.exceptions.MyException;
import Model.expressions.IExpression;
import Model.state.PrgState;
import Model.values.IValue;
import Model.values.IntValue;

public class HeapReadStmt implements IStmt{
    IExpression expression;

    public HeapReadStmt(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException{
        IValue value = expression.eval(state.getSymTable());
        if (value.getType().equals(state.getSymTable().get(expression.toString()).getType())) {
            if (state.getHeap().containsKey(((IntValue)value).getVal())) {
                state.getSymTable().put(value.toString(), state.getHeap().get(((IntValue)value).getVal()));
            } else {
                throw new MyException("The address is not in the heap");
            }
        } else {
            throw new MyException("The type of the variable is not RefType");
        }
        return state;
    }
}
