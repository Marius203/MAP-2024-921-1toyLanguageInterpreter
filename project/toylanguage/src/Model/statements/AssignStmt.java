package Model.statements;

import Model.adts.MyIDictionary;
import Model.adts.MyIHeap;
import Model.exceptions.AssignException;
import Model.exceptions.LogicException;
import Model.exceptions.VariableException;
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
    public PrgState execute(PrgState state) throws AssignException, LogicException, VariableException {
        MyIDictionary<String, IValue> symTbl = state.getSymTable();
        MyIHeap heap = state.getHeap();
        if (symTbl.containsKey(id)) {
            IValue val = exp.eval(symTbl, heap);
            IType typeId = symTbl.get(id).getType();
            if (val.getType().equals(typeId))
                symTbl.update(id, val);
            else
                throw new AssignException("Incompatible types for variable " + id);
        } else {
            throw new AssignException("Variable " + id + " is not declared.");
        }
        return null;
    }
    
    @Override
    public String toString() {
        return id + "=" + exp.toString();
    }
}
