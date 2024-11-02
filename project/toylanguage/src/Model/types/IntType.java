package Model.types;

import Model.values.IValue;
import Model.values.IntValue;

public class IntType implements IType{
    @Override
    public boolean equals(Object another) {
        return another instanceof IntType;
    }
    
    @Override
    public String toString() {
        return "int";
    }

    @Override
    public IValue defaultValue() {
        return new IntValue(0);
    }
}
