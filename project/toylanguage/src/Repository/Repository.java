package Repository;

import Model.exceptions.MyException;
import Model.state.PrgState;
import java.util.List;

public class Repository implements IRepository {
    private final List<PrgState> prgList;

    public Repository(List<PrgState> prglist) {
        this.prgList = prglist;
    }

    @Override
    public PrgState getCurrentPrgState() throws MyException {
        if(prgList.isEmpty()){
            throw new MyException("Program list is empty. Execution finished!");
            }
        PrgState currentPrg = prgList.get(prgList.size()-1);
//        prgList.remove(prgList.size() - 1);
        return currentPrg;
    }

}
