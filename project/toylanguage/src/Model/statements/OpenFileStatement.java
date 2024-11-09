package Model.statements;

import Model.adts.MyIDictionary;
import Model.exceptions.MyException;
import Model.expressions.IExpression;
import Model.state.PrgState;
import Model.types.StringType;
import Model.values.IValue;
import Model.values.StringValue;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenFileStatement implements IStmt {
    IExpression expression;

    public OpenFileStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, FileNotFoundException {
        IValue value = this.expression.eval(state.getSymTable());
        if (!(value.getType() instanceof StringType)) {
            throw new MyException("Filename did not evaluate to string");
        }
        MyIDictionary<String, BufferedReader> files = state.getFiles();
        if (files.containsKey(((StringValue) value).getValue())) {
            throw new MyException("File already opened");
        }
        else{
            BufferedReader reader = new BufferedReader(new FileReader(((StringValue) value).getValue()));
            files.put(((StringValue) value).getValue(), reader);
        }
        return null;
    }

    @Override
    public String toString() {
        return "openFile(" + expression.toString() + ")";
    }
    
}