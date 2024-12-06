package Model.state;

import Model.adts.MyDictionary;
import Model.adts.MyHeap;
import Model.adts.MyIDictionary;
import Model.adts.MyIHeap;
import Model.adts.MyIList;
import Model.adts.MyIStack;
import Model.adts.MyList;
import Model.adts.MyStack;
import Model.exceptions.MyException;
import Model.statements.IStmt;
import Model.values.IValue;
import Model.values.RefValue;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PrgState {
    private final MyIStack<IStmt> exeStack;
    private final MyIDictionary<String, IValue> symTable;
    private final MyIList<IValue> out;
    private final MyIDictionary<String, BufferedReader> files;
    private MyIHeap heap;
    private final int id;

    private static int nextId = 1;

    public PrgState(IStmt originalProgram) {
        exeStack = new MyStack<>();
        symTable = new MyDictionary<String, IValue>();
        out = new MyList<IValue>();
        files = new MyDictionary<String, BufferedReader>();
        heap = new MyHeap();
        exeStack.push(originalProgram);
        this.id = nextId++;
    }

    public PrgState(MyIStack<IStmt> exeStack, MyIDictionary<String, IValue> symTable, MyIList<IValue> out, MyIDictionary<String, BufferedReader> files, MyIHeap heap) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.files = files;
        this.heap = heap;
        this.id = nextId++;
    }


    public int getId() {
        return this.id;
    }

    public MyIStack<IStmt> getExeStack() {
        return this.exeStack;
    }

    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack.clear();
        this.exeStack.push(exeStack.pop());
    }

    public MyIDictionary<String, IValue> getSymTable() {
        return this.symTable;
    }

    public void setSymTable(MyIDictionary<String, IValue> symTable) {
        this.symTable.clear();
        for (String key : symTable.keys()) {
            this.symTable.put(key, symTable.get(key));
        }
    }

    public List<Integer> getAddrFromSymTable(Collection<IValue> symTableValues){
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    public MyIList<IValue> getOut() {
        return this.out;
    }

    public void setOut(MyIList<IValue> out) {
        this.out.clear();
    }

    public MyIDictionary<String, BufferedReader> getFiles() {
        return this.files;
    }

    public void setFiles(MyIDictionary<String, BufferedReader> files) {
        this.files.clear();
        for (String key : files.keys()) {
            this.files.put(key, files.get(key));
        }
    }

    public MyIHeap getHeap() {
        return this.heap;
    }

    public void setHeap(MyIHeap heap) {
        this.heap = heap;
    }
    
    public boolean isNotCompleted() {
        return !exeStack.isEmpty();
    }

    public PrgState executeOneStep() throws MyException, FileNotFoundException {
        if (exeStack.isEmpty()) {
            throw new RuntimeException("Program state stack is empty");
        }
        IStmt currentStatement = exeStack.pop();
        return currentStatement.execute(this);
    }

    @Override
    public String toString() {
        return """
            PrgState: {
                id: %d
                exeStack:  [ %s ]
                , symTable: { %s }
                , out: [ %s ]
                , files: { %s }
                , heap: { %s }
            }
            """.formatted(id,exeStack.toString(), symTable.toString(), out.toString(), files.toString(), heap.toString());
    }
}
