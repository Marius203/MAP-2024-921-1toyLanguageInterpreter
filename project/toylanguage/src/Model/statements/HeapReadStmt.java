package Model.statements;

import Model.exceptions.MyException;
import Model.expressions.IExpression;
import Model.state.PrgState;

public class HeapReadStmt implements IStmt{
    IExpression expression;

    public HeapReadStmt(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException{
        expression.eval(state.getSymTable(), state.getHeap());
        return null;
    }

    @Override
    public String toString() {
        return "readHeap(" + expression.toString() + ")";
    }
}
