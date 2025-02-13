package src.components;

import java.util.Vector;

public class State {
  private final String id;
  private boolean isFinal;
  private final Vector<Transition> transitions;

  public State(String id) {
    this.id = id;
    this.isFinal = false;
    this.transitions = new Vector<>();
  }
  public String getId() {
    return this.id;
  }
  public boolean isFinal() {
    return this.isFinal;
  }
  public void addTransition(Transition transition) {
    this.transitions.add(transition);
  }
  public void setFinal() {
    this.isFinal = true;
  }
  /*
   * Returns the transitions that can be made from this state with the given chainSymbol and stackSymbol.
   * @param chainSymbol The chain symbol to match.
   * @param stackSymbol The stack symbol to match.
   * @return A vector of transitions that can be made from this state with the given chainSymbol and stackSymbol.
   */
  public Vector<Transition> selectTransitions(Symbol chainSymbol, Symbol stackSymbol) {
    Vector<Transition> result = new Vector<>();
    for (Transition transition : this.transitions) {
      if (transition.chainSymbol().equals(chainSymbol) && transition.stackSymbol().equals(stackSymbol)) {
        result.add(transition);
      }
      // This adds the epsilon transitions
      if (transition.chainSymbol().epsilon() && transition.stackSymbol().equals(stackSymbol)) {
        result.add(transition);
      }
    }
    return result;
  }
  // to string
  public String toString() {
    return "State " + this.id + " is final: " + this.isFinal;
  }
}
