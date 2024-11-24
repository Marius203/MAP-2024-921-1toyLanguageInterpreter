package Model.statements;

import Model.exceptions.MyException;
import Model.expressions.IExpression;
import Model.state.PrgState;
import Model.types.RefType;
import Model.values.IValue;

public class HeapAllocStmt implements IStmt{
    String name;
    IExpression expression;

    public HeapAllocStmt(String name, IExpression expression) {
        this.name = name;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException{
        // check if the variable is in the symbol table
        if (state.getSymTable().containsKey(name)) {
            // check if the type is RefType
            IValue value = expression.eval(state.getSymTable());
            if (value.getType() instanceof RefType){
                int address = state.getFirstFree();
                state.getHeap().put(address, value);
                state.getSymTable().put(name, state.getHeap().get(address));
                state.incrementFirstFree();
            } else {
                throw new MyException("The type of the variable is not RefType");
            }
        }
        else {
            throw new MyException("The variable is not in the symbol table");
        }
        return state;
    }

    @Override
    public String toString() {
        return "new(" + name + ", " + expression.toString() + ")";
    }
}