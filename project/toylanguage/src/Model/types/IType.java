package Model.types;

import Model.values.IValue;

public interface IType {
    
    @Override
    public boolean equals(Object another);

    @Override
    public String toString();

    public IValue defaultValue();
}
