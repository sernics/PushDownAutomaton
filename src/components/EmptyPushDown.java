package src.components;

public class EmptyPushDown extends PushDownAutomaton{
  @Override
  protected boolean finalCheck(State state, String chain) {
    return chain.isEmpty();
  }

  public EmptyPushDown() {
    super();
  }
}
