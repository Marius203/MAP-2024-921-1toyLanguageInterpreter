package Model.statements;

import Model.adts.MyIDictionary;
import Model.exceptions.MyException;
import Model.state.PrgState;
import Model.types.IType;
import java.io.FileNotFoundException;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException, FileNotFoundException;

    @Override
    String toString();

    MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException;
}
