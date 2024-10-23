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

  protected abstract boolean finalCheck(State state, String chain);

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
    if (stack.isEmpty()) {
//    return chain.isEmpty();
      return this.finalCheck(state, chain);
    } else {
      String previousChain = chain;
      Symbol chainSymbol;
      if (chain.isEmpty()) chainSymbol = Symbol.EPSILON;
      else {
        chainSymbol = new Symbol(chain.substring(0, 1));
        chain = chain.substring(1);
      }
      Symbol stackSymbol = stack.pop();
      Vector<Transition> transitions = this.transitionFunction(state, chainSymbol, stackSymbol);
      if (transitions.isEmpty()) return false;
      for (Transition transition : transitions) {
        // Introduce the new stack symbols (inverted order because is a stack)
        Stack<Symbol> newStack = stack.copy();
        for (int i = transition.toStack().size() - 1; i >= 0; i--) {
          Symbol symbol = transition.toStack().get(i);
          // If the symbol is not the epsilon symbol, then push it to the stack.
          if (!symbol.epsilon()) {
            newStack.push(transition.toStack().get(i));
          }
        }
        String chainCopy;
        // What this code do is to check if the chain symbol is epsilon, if it is, then the chain
        // is the same as the previous chain, otherwise, the chain is the same as the chain.
        if (transition.chainSymbol().epsilon()) {
          chainCopy = previousChain;
        } else {
          chainCopy = chain;
        }
        if (recursiveRun(transition.nextState(), newStack, chainCopy)) {
          return true;
        }
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
