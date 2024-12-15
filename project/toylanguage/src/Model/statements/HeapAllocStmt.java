package Model.statements;

import Model.adts.MyIDictionary;
import Model.exceptions.MyException;
import Model.expressions.IExpression;
import Model.state.PrgState;
import Model.types.IType;
import Model.types.RefType;
import Model.values.IValue;
import Model.values.RefValue;

public class HeapAllocStmt implements IStmt{
    String name;
    IExpression expression;

    public HeapAllocStmt(String name, IExpression expression) {
        this.name = name;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException{
        IValue value = expression.eval(state.getSymTable(), state.getHeap());
        state.getSymTable().put(name, new RefValue(state.getHeap().allocate(value), value.getType()));
        return null;
    }


    @Override
    public String toString() {
        return "new(" + name + ", " + expression.toString() + ")";
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        typeEnv.put(name, new RefType(expression.typeCheck(typeEnv)));
        IType typevar = typeEnv.get(name);
        IType typexp = expression.typeCheck(typeEnv);
        if(typevar != null && typevar.equals(new RefType(typexp))){
            return typeEnv;
        }
    throw new MyException("HeapAlloc: right hand side and left hand side have different types");
    }
}