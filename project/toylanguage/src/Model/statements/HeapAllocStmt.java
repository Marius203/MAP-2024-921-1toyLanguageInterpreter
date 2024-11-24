package Model.statements;

import Model.exceptions.MyException;
import Model.expressions.IExpression;
import Model.state.PrgState;
import Model.values.IValue;
import Model.values.RefValue;

public class HeapAllocStmt implements IStmt{
    String name;
    IExpression expression;

    public HeapAllocStmt(String name, IExpression expression) {
        this.name = name;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException{
        IValue value = expression.eval(state.getSymTable());
        state.getSymTable().put(name, new RefValue(state.getHeap().allocate(value), value.getType()));
        return null;
    }


    @Override
    public String toString() {
        return "new(" + name + ", " + expression.toString() + ")";
    }
}