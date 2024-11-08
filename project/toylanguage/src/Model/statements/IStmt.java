package Model.statements;

import Model.exceptions.MyException;
import Model.state.PrgState;
import java.io.FileNotFoundException;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException, FileNotFoundException;

    @Override
    String toString();
}
