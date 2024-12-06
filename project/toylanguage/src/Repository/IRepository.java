package Repository;

import Model.exceptions.MyException;
import Model.state.PrgState;
import java.util.List;

public interface IRepository {
    void logProgramState(PrgState program);
    List<PrgState> getPrgList() throws MyException;
    void setPrgList(List<PrgState> prgList);
}
