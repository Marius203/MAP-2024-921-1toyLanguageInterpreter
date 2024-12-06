package Model.statements;

import Model.adts.MyStack;
import Model.state.PrgState;

public class ForkStmt implements IStmt {
    private final IStmt stmt;

    public ForkStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    public IStmt getStmt() {
        return stmt;
    }

    @Override
    public PrgState execute(PrgState state){
        MyStack<IStmt> newExeStack = new MyStack<>();
        newExeStack.push(stmt);
        return new PrgState(newExeStack,
                            state.getSymTable().clone(),
                            state.getOut(),
                            state.getFiles(),
                            state.getHeap());
    }


    @Override
    public String toString() {
        return "fork(" + stmt.toString() + ")";
    }

}
