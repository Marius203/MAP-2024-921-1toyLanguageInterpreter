package Model.adts;

import java.util.LinkedList;
import java.util.List;

public class MyList<T> implements MyIList<T>{
    private final List<T> stack;

    public MyList() {
        stack = new LinkedList<>();
    }

    @Override
    public void add(T value) {
        stack.add(value);
    }

    @Override
    public T remove(int index) {
        return stack.remove(index);
    }

    @Override
    public T get(int index) {
        return stack.get(index);
    }


    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (T value : stack) {
            result.append(value).append(",   ");
        }
        return result.toString();
    }

    @Override
    public MyIList<T> clone() {
        MyIList<T> clone = new MyList<>();
        for (T value : stack) {
            clone.add(value);
        }
        return clone;
    }
    
    @Override
    public void clear() {
        stack.clear();
    }
}
