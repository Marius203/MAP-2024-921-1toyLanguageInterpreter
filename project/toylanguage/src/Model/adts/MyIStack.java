package Model.adts;

public interface MyIStack<T> {
    void push(T value);
    
    T pop();
    
    boolean isEmpty();
    
    @Override
    String toString();
    
    MyIStack<T> clone();

    void clear();
    
}
