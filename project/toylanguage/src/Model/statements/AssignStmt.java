package Model.statements;

import Model.adts.MyIDictionary;
import Model.exceptions.MyException;
import Model.expressions.IExpression;
import Model.state.PrgState;
import Model.types.IType;
import Model.values.IValue;

public class AssignStmt implements IStmt {
    String id;
    IExpression exp;
    
    public AssignStmt(String id, IExpression exp) {
        this.id = id;
        this.exp = exp;
    }
    
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, IValue> symTbl = state.getSymTable();
        if (symTbl.containsKey(id)) {
            IValue val = exp.eval(symTbl);
            IType typeId = symTbl.get(id).getType();
            if (val.getType().equals(typeId))
                symTbl.update(id, val);
            else
                throw new MyException("Incompatible types for variable " + id);
        } else {
            throw new MyException("Variable " + id + " is not declared.");
        }
        return state;
    }
    
    @Override
    public String toString() {
        return id + "=" + exp.toString();
    }
}
