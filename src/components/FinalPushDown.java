package src.components;

public class FinalPushDown extends PushDownAutomaton {
  @Override
  protected boolean check(State state, String chain, Stack<Symbol> stack) {
    if (chain.isEmpty()) {
      return state.isFinal();
    }
    return false;
  }
  public FinalPushDown() {
    super();
  }
}
