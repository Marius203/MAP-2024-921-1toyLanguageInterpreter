package Model.statements;

import Model.exceptions.MyException;
import Model.expressions.IExpression;
import Model.state.PrgState;
import Model.types.RefType;
import Model.values.IValue;
import Model.values.RefValue;

public class HeapWriteStmt implements IStmt{
    IExpression addressExpr;
    IExpression valueExpr;

    public HeapWriteStmt(IExpression address, IExpression expression) {
        this.addressExpr = address;
        this.valueExpr = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException{
        IValue address = addressExpr.eval(state.getSymTable(), state.getHeap());
        IValue value = valueExpr.eval(state.getSymTable(), state.getHeap());
        if (!(address.getType() instanceof RefType))
            throw new MyException("Heap should be accessed using references");
        state.getHeap().write(((RefValue) address).getAddress(), value);
        return null;
        }


    @Override
    public String toString() {
        return "writeHeap(" + addressExpr.toString() + ", " + valueExpr.toString() + ")";
    }
}
