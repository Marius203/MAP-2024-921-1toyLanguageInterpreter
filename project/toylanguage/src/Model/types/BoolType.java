package Model.types;

import Model.values.BoolValue;
import Model.values.IValue;

public class BoolType implements IType{

    @Override
    public boolean equals(Object another) {
        return another instanceof BoolType;
    }
    
    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public IValue defaultValue() {
        return new BoolValue(false);
    }
}
