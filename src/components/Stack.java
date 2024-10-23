package src.components;

import java.util.LinkedList;

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
    Stack<T> newStack = new Stack<>();
    for (T value : list) {
      newStack.push(value);
    }
    return newStack;
  }

  public String toString() {
    return list.toString();
  }
}
