package Model.statements;

import Model.adts.MyIDictionary;
import Model.state.PrgState;
import Model.types.IType;

public class NoOperationStatement implements IStmt {
    @Override
    public String toString() {
        return "NO Operation";
    }

    @Override
    public PrgState execute(PrgState state) {
        return null;
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) {
        return typeEnv;
    }
}
