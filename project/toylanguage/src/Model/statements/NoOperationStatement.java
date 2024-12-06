package Model.statements;

import Model.state.PrgState;

public class NoOperationStatement implements IStmt {
    @Override
    public String toString() {
        return "NO Operation";
    }

    @Override
    public PrgState execute(PrgState state) {
        return null;
    }

}
