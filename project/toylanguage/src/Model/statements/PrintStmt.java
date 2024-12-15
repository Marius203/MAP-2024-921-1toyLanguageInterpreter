package Model.statements;

import Model.adts.MyIDictionary;
import Model.exceptions.MyException;
import Model.expressions.IExpression;
import Model.state.PrgState;
import Model.types.IType;
import Model.values.IValue;

public class PrintStmt implements IStmt {
    IExpression exp;
    
    public PrintStmt(IExpression exp) {
        this.exp = exp;
    }
    
    @Override
    public PrgState execute(PrgState state) throws MyException {
        IValue val = exp.eval(state.getSymTable(), state.getHeap());
        state.getOut().add(val);
        return null;
    }
    
    @Override
    public String toString() {
        return "print(" + exp.toString() + ")";
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        exp.typeCheck(typeEnv);
        return typeEnv;
    }
}

