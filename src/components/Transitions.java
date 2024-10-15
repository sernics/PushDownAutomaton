package src.components;

import java.util.Vector;

public class Transitions {
  private State nextState;
  private Symbol chainSymbol;
  private Symbol stackSymbol;
  private Vector<Symbol> toStack;

  public Transitions(State nextState, Symbol chainSymbol, Symbol stackSymbol, Vector<Symbol> toStack) {
    this.nextState = nextState;
    this.chainSymbol = chainSymbol;
    this.stackSymbol = stackSymbol;
    this.toStack = toStack;
  }
  public State getNextState() {
    return this.nextState;
  }
  public Symbol getChainSymbol() {
    return this.chainSymbol;
  }
  public Symbol getStackSymbol() {
    return this.stackSymbol;
  }
  public Vector<Symbol> getToStack() {
    return this.toStack;
  }
}
