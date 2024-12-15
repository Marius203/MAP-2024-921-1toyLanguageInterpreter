package Model.statements;

import Model.adts.MyIDictionary;
import Model.exceptions.MyException;
import Model.expressions.IExpression;
import Model.state.PrgState;
import Model.types.IType;
import Model.types.RefType;

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

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType type = expression.typeCheck(typeEnv);
        if (type instanceof RefType){
            return typeEnv;
        }
        else
            throw new MyException("HeapRead: " + expression.toString() + " is not a RefType");
    }
}
