package Model.statements;

import Model.adts.MyIDictionary;
import Model.exceptions.MyException;
import Model.expressions.IExpression;
import Model.state.PrgState;
import Model.types.IType;
import Model.types.StringType;
import Model.values.IValue;
import Model.values.IntValue;
import Model.values.StringValue;
import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements IStmt{
    IExpression expression;
    String name;

    public ReadFileStatement(IExpression expression, String name) {
        this.expression = expression;
        this.name = name;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        
        IValue value = this.expression.eval(state.getSymTable(), state.getHeap());
        if (!(value.getType() instanceof StringType)) {
            throw new MyException("Filename did not evaluate to string");
        }
        
        MyIDictionary<String, BufferedReader> files = state.getFiles();
        if (!files.containsKey(((StringValue) value).getValue())) {
            throw new MyException("File not opened");
        }

        BufferedReader reader = files.get(((StringValue) value).getValue());
        try {
            String line = reader.readLine();
            IValue val;
            if (line == null) {
                val = new IntValue(0);
            } else {
                val = new IntValue(Integer.parseInt(line));
            }
            state.getSymTable().put(this.name, val);
        } catch (IOException e) {
            throw new MyException("Error reading from file");
        }
        return null;
    }

    @Override
    public String toString() {
        return "readFile(" + expression.toString() + ")";
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, Model.types.IType> typeEnv) throws MyException {
        Model.types.IType type = expression.typeCheck(typeEnv);
        if (type instanceof StringType) {
            return typeEnv;
        } else {
            throw new MyException("ReadFile: " + expression.toString() + " is not a StringType");
        }
    }
}