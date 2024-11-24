package Model.adts;

import Model.values.IValue;

public class MyHeap implements MyIHeap{
    MyIDictionary<Integer, IValue> heap;
    int firstFree;

    public MyHeap(){
        heap = new MyDictionary<>();
        firstFree = 1;
    }

    @Override
    public int getFirstFree(){
        return firstFree;
    }

    @Override
    public int allocate(IValue value) {
        heap.put(firstFree, value);
        firstFree += 1;
        return firstFree - 1;
    }

    @Override
    public IValue read(int address) {
            return heap.get(address);
    }

    @Override
    public void write(int address, IValue value){
        heap.put(address, value);
    }

    @Override
    public void deallocate(int address){
        heap.remove(address);
    }

    @Override
    public MyIDictionary<Integer, IValue> getHeap(){
        return heap;
    }

    @Override
    public String toString(){
        return heap.toString();
    }
}
