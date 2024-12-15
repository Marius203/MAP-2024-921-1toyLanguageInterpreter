package Model.statements;

import Model.adts.MyIDictionary;
import Model.exceptions.MyException;
import Model.expressions.IExpression;
import Model.state.PrgState;
import Model.types.BoolType;
import Model.types.IType;
import Model.values.BoolValue;
import Model.values.IValue;


public class IfStmt implements IStmt {
    IExpression exp;
    IStmt thenS;
    IStmt elseS;
    
    public IfStmt(IExpression exp, IStmt thenS, IStmt elseS) {
        this.exp = exp;
        this.thenS = thenS;
        this.elseS = elseS;
    }
    
    @Override
    public PrgState execute(PrgState state) throws MyException {
        IValue cond = exp.eval(state.getSymTable(), state.getHeap());
        if (cond instanceof BoolValue boolCond) {
            boolean boolCondValue = boolCond.getVal();
            state.getExeStack().push(boolCondValue ? thenS : elseS);
        } else {
            throw new MyException("Condition is not boolean");
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "(IF(" + exp.toString() + ") THEN(" + thenS.toString() + ") ELSE(" + elseS.toString() + "))";
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType typeExp = exp.typeCheck(typeEnv);
        if (typeExp.equals(new BoolType())){
            thenS.typeCheck(typeEnv.clone());
            elseS.typeCheck(typeEnv.clone());
            return typeEnv;
        }
        else
            throw new MyException(this.toString() + " : Condition is not boolean");
    }
}

