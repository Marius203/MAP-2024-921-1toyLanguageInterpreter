package Model.expressions;

import Model.adts.MyIDictionary;
import Model.adts.MyIHeap;
import Model.exceptions.LogicException;
import Model.exceptions.VariableException;
import Model.types.BoolType;
import Model.types.IType;
import Model.values.BoolValue;
import Model.values.IValue;

public class LogicExpression implements IExpression {
    private final IExpression left;
    private final IExpression right;
    private final String operator;

    public LogicExpression(IExpression left, IExpression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> table, MyIHeap heap) throws LogicException, VariableException {
        IValue leftValue = left.eval(table, heap);
        if (leftValue.getType().equals(new BoolType())) {
            IValue rightValue = right.eval(table, heap);
            if (rightValue.getType().equals(new BoolType())) {
                boolean leftBool = ((BoolValue) leftValue).getVal();
                boolean rightBool = ((BoolValue) rightValue).getVal();
                switch (operator) {
                    case "&&" -> {
                        return new BoolValue(leftBool && rightBool);
                    }
                    case "||" -> {
                        return new BoolValue(leftBool || rightBool);
                    }
                    default -> throw new LogicException("Invalid operator");
                }
            } else {
                throw new LogicException("Right expression is not a boolean");
            }
        } else {
            throw new LogicException("Left expression is not a boolean");
        }
    }

    @Override
    public String toString() {
        return "(" + left + " " + operator + " " + right + ")";
    }

    @Override
    public IType typeCheck(MyIDictionary<String, IType> typeEnv) throws LogicException {
        IType type1, type2;
        type1 = left.typeCheck(typeEnv);
        type2 = right.typeCheck(typeEnv);
        if (type1.equals(new BoolType())) {
            if (type2.equals(new BoolType())) {
                return new BoolType();
            } else {
                throw new LogicException("Second operand is not a boolean");
            }
        } else {
            throw new LogicException("First operand is not a boolean");
        }
    }
}
