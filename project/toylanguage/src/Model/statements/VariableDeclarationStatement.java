package Model.statements;

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
        return state;
    }

}
