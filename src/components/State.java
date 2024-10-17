package src.components;

import java.util.Vector;

public class State {
  private int id;
  private boolean isFinal;
  private Vector<Transition> transitions;

  public State(int id, boolean isFinal) {
    this.id = id;
    this.isFinal = isFinal;
    this.transitions = new Vector<Transition>();
  }
  public int getId() {
    return this.id;
  }
  public boolean isFinal() {
    return this.isFinal;
  }
  public void addTransition(Transition transition) {
    this.transitions.add(transition);
  }
  /*
   * Returns the transitions that can be made from this state with the given chainSymbol and stackSymbol.
   * @param chainSymbol The chain symbol to match.
   * @param stackSymbol The stack symbol to match.
   * @return A vector of transitions that can be made from this state with the given chainSymbol and stackSymbol.
   */
  public Vector<Transition> selectTransitions(Symbol chainSymbol, Symbol stackSymbol) {
    Vector<Transition> result = new Vector<Transition>();
    for (Transition transition : this.transitions) {
      if (transition.getChainSymbol().equals(chainSymbol) && transition.getStackSymbol().equals(stackSymbol)) {
        result.add(transition);
      } else if (transition.getChainSymbol().epsilon() && transition.getStackSymbol().equals(stackSymbol)) {
        // We must assure that no one symbol will be consumed from the chain.
        result.add(transition);
      }
    }
    return result;
  }
}
