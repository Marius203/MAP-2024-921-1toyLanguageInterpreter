package Model.statements;

import Model.exceptions.MyException;
import Model.state.PrgState;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;

    @Override
    String toString();
}
