package Controller;

import Model.exceptions.MyException;
import Model.state.PrgState;
import Repository.Repository;

public interface IController {
    PrgState executeOneStep(PrgState state) throws MyException;
    void executeAll();
    void displayCurrentProgram();
    Repository getRepo();
}
