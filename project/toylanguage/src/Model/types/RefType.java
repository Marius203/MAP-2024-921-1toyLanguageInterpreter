package Model.types;

import Model.values.IValue;
import Model.values.RefValue;

public class RefType implements IType{
    IType inner;

    public RefType(IType inner){
        this.inner = inner;
    }

    public IType getInner(){
        return inner;
    }

    @Override
    public boolean equals(Object other){
        if (other instanceof RefType){
            return inner.equals(((RefType) other).getInner());
        }
        else
            return false;
    }

    @Override
    public String toString(){
        return "Ref(" + inner.toString() + ")";
    }

    @Override
    public IValue defaultValue(){
        return new RefValue(0, inner);
    }
}
