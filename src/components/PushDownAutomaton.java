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
  private boolean isTrace;

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
  private void traceState(State state, String chain, Stack<Symbol> stack) {
    if (this.isTrace) {
      if (chain.isEmpty()) {
        System.out.print(state + ", ε, " + stack + ", [");
      } else {
        System.out.print(state + ", " + chain + ", " + stack + ", [");
      }
    }
  }

  private Symbol getChainSymbol(String chain) {
    return chain.isEmpty() ? Symbol.EPSILON : new Symbol(chain.substring(0, 1));
  }

  private String getRemainingChain(String chain) {
    return chain.isEmpty() ? chain : chain.substring(1);
  }

  private void traceTransitions(Vector<Transition> transitions) {
    if (this.isTrace) {
      for (Transition transition : transitions) {
        System.out.print(transition + " ");
      }
      System.out.println("]");
    }
  }

  private Stack<Symbol> prepareNewStack(Stack<Symbol> stack, Transition transition) {
    Stack<Symbol> newStack = stack.copy();
    for (int i = transition.toStack().size() - 1; i >= 0; i--) {
      Symbol symbol = transition.toStack().get(i);
      if (!symbol.epsilon()) {
        newStack.push(symbol);
      }
    }
    return newStack;
  }

  private String getNextChain(String chain, String remainingChain, Transition transition) {
    return transition.chainSymbol().epsilon() ? chain : remainingChain;
  }
  private boolean recursiveRun(State state, Stack<Symbol> stack, String chain) {
    if (this.check(state, chain, stack)) return true;

    traceState(state, chain, stack);

    Symbol chainSymbol = getChainSymbol(chain);
    String remainingChain = getRemainingChain(chain);
    Symbol stackSymbol = stack.pop();

    Vector<Transition> transitions = this.transitionFunction(state, chainSymbol, stackSymbol);
    traceTransitions(transitions);

    if (transitions.isEmpty()) {
      return false;
    }

    for (Transition transition : transitions) {
      Stack<Symbol> newStack = prepareNewStack(stack, transition);
      String nextChain = getNextChain(chain, remainingChain, transition);

      if (recursiveRun(transition.nextState(), newStack, nextChain)) {
        return true;
      }
    }

    return false;
  }

  public PushDownAutomaton() {
    this.states = new Vector<>();
    this.isTrace = false;
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
  public void setTrace(boolean isTrace) {
    this.isTrace = isTrace;
  }
  public boolean run(String chain) {
    if (this.isTrace) {
      System.out.println("State, Chain, Stack, Transitions");
    }
    return recursiveRun(this.initialState, this.stack, chain);
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
