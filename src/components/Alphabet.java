package src.components;

import java.util.Set;
import java.util.Vector;

public class Alphabet {
  private Set<State> alphabet;

  public Alphabet() {}
  public Alphabet(Vector<State> rawAlphabet) {
    for (State state : rawAlphabet) {
      this.alphabet.add(state);
    }
  }
  public void add(State state) {
    this.alphabet.add(state);
  }
}