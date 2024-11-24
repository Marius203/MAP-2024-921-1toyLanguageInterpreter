package Model.expressions;

import Model.adts.MyIDictionary;
import Model.adts.MyIHeap;
import Model.exceptions.LogicException;
import Model.exceptions.VariableException;
import Model.types.IntType;
import Model.values.BoolValue;
import Model.values.IValue;
import Model.values.IntValue;

public class RationalExpression implements IExpression{
    private final IExpression e1;
    private final IExpression e2;
    private final int op;

    public RationalExpression(IExpression e1, IExpression e2, int op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> table, MyIHeap heap) throws LogicException, VariableException {
        IValue v1, v2;
        v1 = e1.eval(table, heap);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(table, heap);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;

                int n1 = i1.getVal();
                int n2 = i2.getVal();

                return switch (op) {
                    case 1 -> new BoolValue(n1 < n2);
                    case 2 -> new BoolValue(n1 <= n2);
                    case 3 -> new BoolValue(n1 > n2);
                    case 4 -> new BoolValue(n1 >= n2);
                    case 5 -> new BoolValue(n1 == n2);
                    case 6 -> new BoolValue(n1 != n2);
                    
                    default -> throw new LogicException("Invalid operation code");
                };
            } else {
                throw new LogicException("Second operand is not an integer");
            }
        } else {
            throw new LogicException("First operand is not an integer");
        }
    }

    @Override
    public String toString() {
        return switch (op) {
            case 1 -> e1.toString() + "<" + e2.toString();
            case 2 -> e1.toString() + "<=" + e2.toString();
            case 3 -> e1.toString() + ">" + e2.toString();
            case 4 -> e1.toString() + ">=" + e2.toString();
            case 5 -> e1.toString() + "==" + e2.toString();
            case 6 -> e1.toString() + "!=" + e2.toString();
            default -> "Invalid operation code";
        };
    }

}
