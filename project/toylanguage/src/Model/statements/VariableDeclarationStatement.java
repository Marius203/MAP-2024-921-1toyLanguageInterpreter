package Model.statements;

import Model.adts.MyIDictionary;
import Model.exceptions.MyException;
import Model.state.PrgState;
import Model.types.IType;

public class VariableDeclarationStatement  implements IStmt {
    private final String name;
    private final IType type;

    public VariableDeclarationStatement(String name, IType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "var " + name;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        state.getSymTable().put(name, type.defaultValue());
        System.out.println("Variable " + name + " of type " + type + " declared");
        return null;
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        typeEnv.put(name, type);
        return typeEnv;
    }
}