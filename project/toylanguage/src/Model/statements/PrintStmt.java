package Model.statements;

import Model.exceptions.MyException;
import Model.expressions.IExpression;
import Model.state.PrgState;
import Model.values.IValue;

public class PrintStmt implements IStmt {
    IExpression exp;
    
    public PrintStmt(IExpression exp) {
        this.exp = exp;
    }
    
    @Override
    public PrgState execute(PrgState state) throws MyException {
        IValue val = exp.eval(state.getSymTable());
        state.getOut().add(val);
        return state;
    }
    
    @Override
    public String toString() {
        return "print(" + exp.toString() + ")";
    }
}

