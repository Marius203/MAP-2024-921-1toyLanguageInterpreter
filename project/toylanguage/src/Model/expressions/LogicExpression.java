package Model.expressions;

import Model.adts.MyIDictionary;
import Model.exceptions.MyException;
import Model.types.BoolType;
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
    public IValue eval(MyIDictionary<String, IValue> table) throws MyException {
        IValue leftValue = left.eval(table);
        if (leftValue.getType().equals(new BoolType())) {
            IValue rightValue = right.eval(table);
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
                    default -> throw new MyException("Invalid operator");
                }
            } else {
                throw new MyException("Right expression is not a boolean");
            }
        } else {
            throw new MyException("Left expression is not a boolean");
        }
    }

    @Override
    public String toString() {
        return "(" + left + " " + operator + " " + right + ")";
    }

}