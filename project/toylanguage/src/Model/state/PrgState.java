package Model.state;

import Model.adts.MyDictionary;
import Model.adts.MyHeap;
import Model.adts.MyIDictionary;
import Model.adts.MyIList;
import Model.adts.MyIStack;
import Model.adts.MyList;
import Model.adts.MyStack;
import Model.statements.IStmt;
import Model.values.IValue;
import java.io.BufferedReader;
import Model.adts.MyIHeap;

public class PrgState {
    private final MyIStack<IStmt> exeStack;
    private final MyIDictionary<String, IValue> symTable;
    private final MyIList<IValue> out;
    private final MyIDictionary<String, BufferedReader> files;
    private final MyIHeap heap;
    private int firstFree;

    public PrgState(IStmt originalProgram) {
        exeStack = new MyStack<IStmt>();
        symTable = new MyDictionary<String, IValue>();
        out = new MyList<IValue>();
        files = new MyDictionary<String, BufferedReader>();
        heap = new MyHeap();
        firstFree = 1;
        exeStack.push(originalProgram);
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

    @Override
    public String toString() {
        return "PrgState: { " +
                "exeStack:  [ " + exeStack.toString() + " ] \n" +
                ", symTable: { " + symTable.toString() + " } \n" +
                ", out: [ " + out.toString() + " ]  \n" +
                ", files: { " + files.toString() + " }  \n" +
                ", heap: { " + heap.toString() + " }  \n" +
                "} \n";
    }
}
