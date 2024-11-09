package Repository;

import Model.exceptions.MyException;
import Model.state.PrgState;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Repository implements IRepository {
    private final List<PrgState> prgList;
    String logFilePath;

    public Repository(List<PrgState> prglist, String path) {
        this.prgList = prglist;
        this.logFilePath = path;
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

    @Override
    public void logProgramState(PrgState program) {
        if (this.logFilePath != null) {
            PrintWriter logFile;
            try {
                logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logFile.println(program.toString());
            logFile.close();
        }
    }

}