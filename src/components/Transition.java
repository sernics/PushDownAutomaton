package src.components;

import java.util.Vector;

public record Transition(State nextState, Symbol chainSymbol, Symbol stackSymbol, Vector<Symbol> toStack) {
  // to string
  public String toString() {
    // String result = "Transition to " + this.nextState.getId() + " with chain symbol " + this.chainSymbol.toString() + " and stack symbol " + this.stackSymbol.toString() + " and to stack ";
    // for (Symbol symbol : this.toStack) {
    //   result += symbol.toString() + " ";
    // }
    // return result;
    return "{ " + this.nextState.getId() + " " + this.stackSymbol.toString() + " }";
  }
}
