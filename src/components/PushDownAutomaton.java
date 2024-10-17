package src.components;

import java.util.Vector;

public class PushDownAutomaton {
  /*
   * Atributes of the pushdown automaton.
   */
  private Vector<State> states;
  private Alphabet sigmaAlphabet;
  private Alphabet gammaAlphabet;
  private State initialState;
  private Symbol initialStackSymbol;
  private Stack<Symbol> stack;

  /*
   * Transition function of the pushdown automaton.
   * @param state The current state of the automaton.
   * @param chainSimbol The chain symbol to match.
   * @param stackSymbol The stack symbol to match.
   * @return A vector of transitions that can be made from the current state with the given chainSymbol and stackSymbol.
   */
  private Vector<Transition> transitionFunction(State state, Symbol chainSimbol, Symbol stackSymbol) {
    return state.selectTransitions(chainSimbol, stackSymbol);
  }

  public PushDownAutomaton() {
    this.states = new Vector<State>();
  }
  public void addStates(Vector<State> states) {
    this.states = states;
  }
  public void setSigmaAlphabet(Alphabet sigmaAlphabet) {
    this.sigmaAlphabet = sigmaAlphabet;
  }
  public void setGammaAlphabet(Alphabet gammaAlphabet) {
    this.gammaAlphabet = gammaAlphabet;
  }
  public void setInitialState(State initialState) {
    this.initialState = initialState;
  }
  public void setStack(Symbol initialStackSymbol) {
    this.initialStackSymbol = initialStackSymbol;
    this.stack = new Stack<Symbol>();
    this.stack.push(initialStackSymbol);
  }
}
