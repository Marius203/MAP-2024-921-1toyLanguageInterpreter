package Model.expressions;

import Model.adts.MyIDictionary;
import Model.exceptions.MyException;
import Model.types.IntType;
import Model.values.IValue;
import Model.values.IntValue;

public class ArithmeticExpression implements IExpression {

    private final IExpression e1;
    private final IExpression e2;
    private final int op;

    public ArithmeticExpression(IExpression e1, IExpression e2, int op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> table) throws MyException {
        IValue v1, v2;
        v1 = e1.eval(table);

        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(table);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;

                int n1 = i1.getVal();
                int n2 = i2.getVal();

                return switch (op) {
                    case 1 -> new IntValue(n1 + n2);
                    case 2 -> new IntValue(n1 - n2);
                    case 3 -> new IntValue(n1 * n2);
                    case 4 -> {
                        if (n2 == 0) {
                            throw new MyException("Division by zero");
                        }
                        yield new IntValue(n1 / n2);
                    }
                    default -> throw new MyException("Invalid operation code");
                };
            } else {
                throw new MyException("Second operand is not an integer");
            }
        } else {
            throw new MyException("First operand is not an integer");
        }
    }


    @Override
    public String toString() {
        return switch (op) {
            case 1 -> e1.toString() + "+" + e2.toString();
            case 2 -> e1.toString() + "-" + e2.toString();
            case 3 -> e1.toString() + "*" + e2.toString();
            case 4 -> e1.toString() + "/" + e2.toString();
            default -> "Invalid operation code";
        };
    }
}
