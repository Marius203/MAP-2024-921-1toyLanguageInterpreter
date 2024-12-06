package Controller;

import Model.exceptions.MyException;
import Model.state.PrgState;
import Repository.IRepository;
import Repository.Repository;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller implements IController{
    public IRepository repo;
    public ExecutorService executor;


    public Controller(IRepository repo) {
        this.repo = repo;
        
    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) throws MyException {
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }

    void oneStepForAllPrg(List<PrgState> prgList){
        prgList.forEach(prg -> repo.logProgramState(prg));
        List<Callable<PrgState>> callList = prgList.stream()
                                            .map((PrgState p) -> (Callable<PrgState>)(() -> {return p.executeOneStep();}))
                                            .collect(Collectors.toList());
        List<PrgState> newPrgList = null;
        try {
            newPrgList = executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        return null;
                    })
                    .filter(p -> p != null)
                    .collect(Collectors.toList()); // Missing semicolon added here
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        prgList.addAll(newPrgList);
        prgList.forEach(prg -> repo.logProgramState(prg));
        repo.setPrgList(prgList);
    }

    @Override
    public void executeAll() {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList;
        try {
            prgList = removeCompletedPrg(repo.getPrgList());
        } catch (MyException e) {
            System.err.println(e.getMessage());
            return;
        }
        while (prgList.size() > 0) {
            
            oneStepForAllPrg(prgList);
            try {
                prgList = removeCompletedPrg(repo.getPrgList());
            } catch (MyException e) {
                System.err.println(e.getMessage());
                return;
            }
        }
        executor.shutdownNow();
        repo.setPrgList(prgList);
    }

    @Override
    public Repository getRepo() {
        return (Repository) repo;
    }
}
