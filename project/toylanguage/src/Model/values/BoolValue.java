package Model.values;

import Model.types.BoolType;
import Model.types.IType;


public class BoolValue implements IValue {
    Boolean val;
    
    public BoolValue(boolean v) {
        this.val = v;
    }
    
    public boolean getVal() {
        return val;
    }
    
    @Override
    public IType getType() {
        return new BoolType();
    }
    
    @Override
    public String toString() {
        return Boolean.toString(val);
    }

    boolean equals(BoolValue another) {
        return this.val == another.getVal();
    }

}

