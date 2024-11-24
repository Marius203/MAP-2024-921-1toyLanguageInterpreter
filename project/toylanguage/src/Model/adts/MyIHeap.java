package Model.adts;

import Model.values.IValue;

public interface MyIHeap {
    int allocate(IValue value);
    IValue read(int address);
    void write(int address, IValue value);
    void deallocate(int address);
    int getFirstFree();
    MyIDictionary<Integer, IValue> getHeap();
}
