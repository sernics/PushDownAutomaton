package src.components;

import java.util.Vector;

public class Chain {
  private Vector<Symbol> chain;

  public Chain() {
    this.chain = new Vector<Symbol>();
  }
  public Chain(Vector<Symbol> chain) {
    this.chain = chain;
  }
  public void add(Symbol symbol) {
    this.chain.add(symbol);
  }
  public Symbol get(int index) {
    return this.chain.get(index);
  }
  public int size() {
    return this.chain.size();
  }
}
