package src.components;

import java.util.LinkedHashSet;
import java.util.Set;

public class Alphabet {
  private final Set<Symbol> alphabet;

  public Alphabet() {
    this.alphabet = new LinkedHashSet<>();
  }
  public void add(Symbol symbol) {
    this.alphabet.add(symbol);
  }
  // to string
  public String toString() {
    StringBuilder result = new StringBuilder("Alphabet: ");
    for (Symbol symbol : this.alphabet) {
      result.append(symbol.toString()).append(" ");
    }
    return result.toString();
  }
}