package Model.statements;

import Model.adts.MyIDictionary;
import Model.adts.MyStack;
import Model.exceptions.MyException;
import Model.state.PrgState;
import Model.types.IType;

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

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        return stmt.typeCheck(typeEnv);
    }

}
