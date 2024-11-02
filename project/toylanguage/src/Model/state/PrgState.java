package Model.state;

import Model.adts.MyDictionary;
import Model.adts.MyIDictionary;
import Model.adts.MyIList;
import Model.adts.MyIStack;
import Model.adts.MyList;
import Model.adts.MyStack;
import Model.statements.IStmt;
import Model.values.IValue;


public class PrgState {
    private final MyIStack<IStmt> exeStack;
    private final MyIDictionary<String, IValue> symTable;
    private final MyIList<IValue> out;

    public PrgState(IStmt originalProgram) {
        exeStack = new MyStack<IStmt>();
        symTable = new MyDictionary<String, IValue>();
        out = new MyList<IValue>();
        exeStack.push(originalProgram);
    }

    public MyIStack<IStmt> getExeStack() {
        return this.exeStack;
    }

    public MyIDictionary<String, IValue> getSymTable() {
        return this.symTable;
    }

    public MyIList<IValue> getOut() {
        return this.out;
    }

    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack.clear();
        this.exeStack.push(exeStack.pop());
    }

    public void setSymTable(MyIDictionary<String, IValue> symTable) {
        this.symTable.clear();
        for (String key : symTable.keys()) {
            this.symTable.put(key, symTable.get(key));
        }
    }

    public void setOut(MyIList<IValue> out) {
        this.out.clear();
    }

    @Override
    public String toString() {
        return "PrgState: { " +
                "exeStack:  [ " + exeStack.toString() + " ] \n" +
                ", symTable: { " + symTable.toString() + " } \n" +
                ", out: [ " + out.toString() + " ]  \n" +
                "} \n";
    }
}
