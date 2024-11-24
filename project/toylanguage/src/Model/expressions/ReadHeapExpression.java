package Model.expressions;

import Model.adts.MyIDictionary;
import Model.adts.MyIHeap;
import Model.exceptions.LogicException;
import Model.exceptions.VariableException;
import Model.values.IValue;
import Model.values.RefValue;


public class ReadHeapExpression implements IExpression {
    private final IExpression expr;

    public ReadHeapExpression(IExpression expr) {
        this.expr = expr;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> table, MyIHeap heap) throws ArithmeticException, LogicException, VariableException {
        IValue value = expr.eval(table, heap);
        if (!(value.getType() instanceof RefValue))
            throw new VariableException("Expression is not a reference");
        return heap.getHeap().get(((RefValue) value).getAddress());
        }

    @Override
    public String toString() {
        return "rH(" + expr + ")";
    }

}
