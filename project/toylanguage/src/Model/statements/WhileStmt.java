package Model.statements;

import Model.adts.MyIDictionary;
import Model.exceptions.MyException;
import Model.expressions.IExpression;
import Model.state.PrgState;
import Model.types.BoolType;
import Model.types.IType;
import Model.values.BoolValue;
import Model.values.IValue;


public class WhileStmt implements IStmt {
    IExpression condition;
    IStmt statement;

    public WhileStmt(IExpression condition, IStmt statement) {
        this.statement = statement;
        this.condition = condition;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IValue value = condition.eval(state.getSymTable(), state.getHeap());
        if (!(value.getType() instanceof BoolType)){
            throw new MyException("Condition is not a boolean");
        }
        if (((BoolValue) value).getVal()){
            state.getExeStack().push(this);
            state.getExeStack().push(statement);
        }
        return null;
    }

    @Override
    public String toString() {
        return "while(" + condition.toString() + "){" + statement.toString() + "}";
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType typeCond = condition.typeCheck(typeEnv);
        if (typeCond.equals(new BoolType())){
            statement.typeCheck(typeEnv.clone());
            return typeEnv;
        }
        else
            throw new MyException(this.toString() + " : Condition is not boolean");
    }
}
