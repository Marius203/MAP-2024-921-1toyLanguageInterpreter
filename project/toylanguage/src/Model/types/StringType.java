package Model.types;

import Model.values.IValue;
import Model.values.StringValue;

public class StringType implements IType{
    @Override
    public boolean equals(Object another) {
        return another instanceof StringType;
    }
    
    @Override
    public String toString() {
        return "string";
    }

    @Override
    public IValue defaultValue() {
        return new StringValue("");
    }

}
