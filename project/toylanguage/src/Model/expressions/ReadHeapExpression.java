package Model.expressions;

import Model.adts.MyIDictionary;
import Model.adts.MyIHeap;
import Model.exceptions.LogicException;
import Model.exceptions.VariableException;
import Model.types.IType;
import Model.types.RefType;
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
        System.out.println("Value: " + value + " Type: " + value.getType());
        if (!(value.getType() instanceof RefType))
            throw new VariableException("Expression is not a reference");

        return heap.read(((RefValue) value).getAddress());
    }

    @Override
    public String toString() {
        return "rH(" + expr + ")";
    }

    @Override
    public IType typeCheck(MyIDictionary<String, IType> typeEnv) throws LogicException {
        IType typ = expr.typeCheck(typeEnv);
        if (typ instanceof RefType refType) {
            return refType.getInner();
        } else {
            throw new LogicException("The rH argument is not a RefType");
        }
    }

}
