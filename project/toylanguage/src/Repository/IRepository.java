package Repository;

import Model.exceptions.MyException;
import Model.state.PrgState;

public interface IRepository {
    PrgState getCurrentPrgState() throws MyException;
    void logProgramState(PrgState program);
}
