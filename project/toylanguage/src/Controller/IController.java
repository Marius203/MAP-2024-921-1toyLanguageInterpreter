package Controller;

import Model.exceptions.MyException;
import Model.state.PrgState;
import Repository.Repository;
import java.io.FileNotFoundException;

public interface IController {
    PrgState executeOneStep(PrgState state) throws MyException,FileNotFoundException;
    void executeAll();
    void displayCurrentProgram();
    Repository getRepo();
}
