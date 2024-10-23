package src.components;

import java.util.Vector;

public abstract class PushDownAutomaton {
  /*
   * Attributes of the push-down automaton.
   */
  private Vector<State> states;
  private Alphabet sigmaAlphabet;
  private Alphabet gammaAlphabet;
  private State initialState;
  private Symbol initialStackSymbol;
  private Stack<Symbol> stack;

  protected abstract boolean check(State state, String chain, Stack<Symbol> stack);

  /*
   * Transition function of the push-down automaton.
   * @param state The current state of the automaton.
   * @param chainSymbol The chain symbol to match.
   * @param stackSymbol The stack symbol to match.
   * @return A vector of transitions that can be made from the current state with the given chainSymbol and stackSymbol.
   */
  private Vector<Transition> transitionFunction(State state, Symbol chainSymbol, Symbol stackSymbol) {
    return state.selectTransitions(chainSymbol, stackSymbol);
  }

  public PushDownAutomaton() {
    this.states = new Vector<>();
  }
  public void setStates(Vector<State> states) {
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
    this.stack = new Stack<>();
    this.stack.push(initialStackSymbol);
  }
  public boolean run(String chain) {
    return recursiveRun(this.initialState, this.stack, chain);
  }
  private boolean recursiveRun(State state, Stack<Symbol> stack, String chain) {
    if (this.check(state, chain, stack)) return true;

    Symbol chainSymbol = chain.isEmpty() ? Symbol.EPSILON : new Symbol(chain.substring(0, 1));
    String remainingChain = chain.isEmpty() ? chain : chain.substring(1);
    Symbol stackSymbol = stack.pop();
    Vector<Transition> transitions = this.transitionFunction(state, chainSymbol, stackSymbol);

    if (transitions.isEmpty()) {
      return false;
    }

    for (Transition transition : transitions) {
      Stack<Symbol> newStack = stack.copy();
      for (int i = transition.toStack().size() - 1; i >= 0; i--) {
        Symbol symbol = transition.toStack().get(i);
        if (!symbol.epsilon()) {
          newStack.push(symbol);
        }
      }

      String nextChain = transition.chainSymbol().epsilon() ? chain : remainingChain;
      if (recursiveRun(transition.nextState(), newStack, nextChain)) {
        return true;
      }
    }

    return false;
  }
  // to string
  public String toString() {
    StringBuilder result = new StringBuilder("Push down Automaton\n");
    result.append("States: ");
    for (State state : this.states) {
      result.append(state.toString()).append(" ");
    }
    result.append("\nSigma Alphabet: ").append(this.sigmaAlphabet);
    result.append("\nGamma Alphabet: ").append(this.gammaAlphabet);
    result.append("\nInitial State: ").append(this.initialState);
    result.append("\nInitial Stack Symbol: ").append(this.initialStackSymbol);
    return result.toString();
  }
}
