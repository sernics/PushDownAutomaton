package src.components;

import java.util.LinkedList;
import java.util.Vector;

public class Stack<T> {
  private final LinkedList<T> list = new LinkedList<>();

  public void push(T value) {
    list.addFirst(value);
  }

  public T pop() {
    return list.removeFirst();
  }

  public boolean isEmpty() {
    return list.isEmpty();
  }

  public int size() {
    return list.size();
  }

  public Stack<T> copy() {
    // Im creating a vector, because on the other way, the stack would be copied on a inverse way
    Vector<T> copy = new Vector<>(list);
    Stack<T> result = new Stack<>();
    result.list.addAll(copy);
    return result;
  }

  public String toString() {
    String result = "[";
    for (T value : list) {
      result += value.toString() + " ";
    }
    result += "]";
    return result;
  }
}
