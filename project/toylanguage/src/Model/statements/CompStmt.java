package Model.statements;

import Model.adts.MyIDictionary;
import Model.adts.MyIStack;
import Model.exceptions.MyException;
import Model.state.PrgState;
import Model.types.IType;

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

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        return snd.typeCheck(first.typeCheck(typeEnv));
    }
}

