package Model.statements;

import Model.exceptions.MyException;
import Model.expressions.IExpression;
import Model.state.PrgState;
import Model.types.BoolType;
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

}
