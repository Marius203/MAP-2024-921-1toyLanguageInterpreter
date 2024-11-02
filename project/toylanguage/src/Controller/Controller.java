package Controller;

import Model.adts.MyIStack;
import Model.exceptions.MyException;
import Model.state.PrgState;
import Model.statements.IStmt;
import Repository.IRepository;
import Repository.Repository;

public class Controller implements IController{
    public IRepository repo;


    public Controller(IRepository repo) {
        this.repo = repo;
    }

    @Override
    public PrgState executeOneStep(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        if (stk.isEmpty()) {
            throw new MyException("Program state stack is empty");
        }
        IStmt currentStmt = stk.pop();
        return currentStmt.execute(state);
    }

    @Override
    public void executeAll() {
        try{
            PrgState prg = repo.getCurrentPrgState();
            while (!prg.getExeStack().isEmpty()) {
                try {
                    executeOneStep(prg);
                } catch (MyException e) {
                    System.out.println(e.getMessage());
                }
                displayCurrentProgram();
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void displayCurrentProgram() {
        try{
            System.out.println(repo.getCurrentPrgState().toString());
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Repository getRepo() {
        return (Repository) repo;
    }
}
