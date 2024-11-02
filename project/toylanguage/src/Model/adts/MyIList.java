package Model.adts;

public interface MyIList<T> {
    void add(T value);
    
    T remove(int index);
    
    T get(int index);
    
    int size();
    
    @Override
    String toString();
    
    MyIList<T> clone();

    void clear();
    
}
