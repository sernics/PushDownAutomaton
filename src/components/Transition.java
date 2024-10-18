package src.components;

import java.util.Vector;

public class Transition {
  private State nextState;
  private Symbol chainSymbol;
  private Symbol stackSymbol;
  private Vector<Symbol> toStack;

  public Transition(State nextState, Symbol chainSymbol, Symbol stackSymbol, Vector<Symbol> toStack) {
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
  // to string
  public String toString() {
    String result = "Transition to " + this.nextState.getId() + " with chain symbol " + this.chainSymbol.toString() + " and stack symbol " + this.stackSymbol.toString() + " and to stack ";
    for (Symbol symbol : this.toStack) {
      result += symbol.toString() + " ";
    }
    return result;
  }
}
