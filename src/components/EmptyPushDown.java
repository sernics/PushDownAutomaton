package src.components;

public class EmptyPushDown extends PushDownAutomaton{
  @Override
  protected boolean check(State state, String chain, Stack<Symbol> stack) {
    if (stack.isEmpty()) {
      return chain.isEmpty();
    }
    return false;
  }
  public EmptyPushDown() {
    super();
  }
}
