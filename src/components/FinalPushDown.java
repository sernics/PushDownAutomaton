package src.components;

public class FinalPushDown extends PushDownAutomaton {
  @Override
  protected boolean finalCheck(State state, String chain) {
    return state.isFinal();
  }
  public FinalPushDown() {
    super();
  }
}
