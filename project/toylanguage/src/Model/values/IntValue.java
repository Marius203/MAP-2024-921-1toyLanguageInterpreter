package Model.values;

import Model.types.IType;
import Model.types.IntType;

public class IntValue implements IValue {
    int val;
    
    public IntValue(int v) {
        this.val = v;
    }
    
    public int getVal() {
        return val;
    }
    
    @Override
    public IType getType() {
        return new IntType();
    }
    
    @Override
    public String toString() {
        return Integer.toString(val);
    }
}

