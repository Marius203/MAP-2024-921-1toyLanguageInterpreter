package Controller;

import Model.adts.MyIStack;
import Model.exceptions.MyException;
import Model.state.PrgState;
import Model.statements.IStmt;
import Repository.IRepository;
import Repository.Repository;
import java.io.FileNotFoundException;

public class Controller implements IController{
    public IRepository repo;


    public Controller(IRepository repo) {
        this.repo = repo;
        
    }

    @Override
    public PrgState executeOneStep(PrgState state) throws MyException, FileNotFoundException {
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
            repo.logProgramState(prg);
            while (!prg.getExeStack().isEmpty()) {
                try {
                    try {
                        executeOneStep(prg);
                    } catch (FileNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                } catch (MyException e) {
                    System.out.println(e.getMessage());
                }
                repo.logProgramState(prg);
                prg.getHeap().setHeap(prg.getHeap().safeGarbageCollector(
                                            prg.getAddrFromSymTable(prg.getSymTable().getContent().values()),
                                            prg.getHeap().getHeap()));
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
