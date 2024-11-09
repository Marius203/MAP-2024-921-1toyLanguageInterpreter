package Model.statements;

import Model.adts.MyIDictionary;
import Model.exceptions.MyException;
import Model.expressions.IExpression;
import Model.state.PrgState;
import Model.types.StringType;
import Model.values.IValue;
import Model.values.StringValue;
import java.io.BufferedReader;
import java.io.IOException;

public class CloseFIleStatement implements IStmt {
    
    IExpression expression;

    public CloseFIleStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IValue value = this.expression.eval(state.getSymTable());
        if (!(value.getType() instanceof StringType)) {
            throw new MyException("Filename did not evaluate to string");
        }

        MyIDictionary<String, BufferedReader> files = state.getFiles();

        if (!files.containsKey(((StringValue) value).getValue())) {
            throw new MyException("File not opened");
        }

        BufferedReader reader = files.get(((StringValue) value).getValue());
        try {
            reader.close();
            files.remove(((StringValue) value).getValue());
        } catch (IOException e) {
            throw new MyException("Error closing file");
        }

        return null;
    }

    @Override
    public String toString() {
        return "closeFile(" + expression.toString() + ")";
    }
    
}