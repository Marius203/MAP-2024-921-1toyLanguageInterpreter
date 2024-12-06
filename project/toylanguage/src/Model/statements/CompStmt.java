package Model.statements;

import Model.adts.MyIStack;
import Model.exceptions.MyException;
import Model.state.PrgState;

public class CompStmt implements IStmt{
    IStmt first;
    IStmt snd;
    
    public CompStmt(IStmt first, IStmt snd) {
        this.first = first;
        this.snd = snd;
    }
    
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stack = state.getExeStack();
        stack.push(snd);
        stack.push(first);
        return null;
    }
    
    @Override
    public String toString() {
        return "(" + first.toString() + ";" + snd.toString() + ")";
    }
}

