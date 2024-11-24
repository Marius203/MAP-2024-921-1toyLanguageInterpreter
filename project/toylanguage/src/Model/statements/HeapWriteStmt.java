package Model.statements;

import Model.exceptions.MyException;
import Model.expressions.IExpression;
import Model.state.PrgState;
import Model.types.RefType;
import Model.values.IValue;
import Model.values.RefValue;

public class HeapWriteStmt implements IStmt{
    String varName;
    IExpression expression;

    public HeapWriteStmt(String varName, IExpression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException{
        if (state.getSymTable().containsKey(varName)) {
            IValue value = state.getSymTable().get(varName);
            if (value.getType() instanceof RefType) {
                if (state.getHeap().containsKey(((RefValue)value).getAddress())) {
                    state.getHeap().put(((RefValue)value).getAddress(), expression.eval(state.getSymTable()));
                } else {
                    throw new MyException("The address is not in the heap");
                }
            } else {
                throw new MyException("The type of the variable is not RefType");
            }
        } else {
            throw new MyException("The variable is not in the symbol table");
        }
        return state;
    }

    @Override
    public String toString() {
        return "writeHeap(" + varName + ", " + expression.toString() + ")";
    }
}
