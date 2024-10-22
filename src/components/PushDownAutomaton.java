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
    this.stack = new Stack<Symbol>();
    this.stack.push(initialStackSymbol);
  }
  public boolean run(String chain) {
    return recursiveRun(this.initialState, this.stack, chain);
  }
  private boolean recursiveRun(State state, Stack<Symbol> stack, String chain) {
    if (chain.length() == 0) {
      if (stack.isEmpty()) {
        return true;
      }
      return false;
    } else {
      Symbol chainSymbol = new Symbol(chain.substring(0, 1));
      chain = chain.substring(1);
      if (stack.isEmpty()) {
        return false;
      }
      Symbol stackSymbol = stack.pop();
      Vector<Transition> transitions = this.transitionFunction(state, chainSymbol, stackSymbol);
      // Introduce the new stack symbols (inverted order because is a stack)
      for (Transition transition : transitions) {
        Stack<Symbol> newStack = stack.copy();
        for (int i = transition.getToStack().size() - 1; i >= 0; i--) {
          Symbol symbol = transition.getToStack().get(i);
          if (!symbol.epsilon()) {
            newStack.push(transition.getToStack().get(i));
          }
        }
        if (recursiveRun(transition.getNextState(), newStack, chain)) {
          return true;
        }
      }
    }
    return false;
  }
  // to string
  public String toString() {
    String result = "Pushdown Automaton\n";
    result += "States: ";
    for (State state : this.states) {
      result += state.toString() + " ";
    }
    result += "\nSigma Alphabet: " + this.sigmaAlphabet;
    result += "\nGamma Alphabet: " + this.gammaAlphabet;
    result += "\nInitial State: " + this.initialState;
    result += "\nInitial Stack Symbol: " + this.initialStackSymbol;
    return result;
  }
}
