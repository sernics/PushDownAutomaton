package src.components;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Vector;

public class Alphabet {
  private Set<Symbol> alphabet;

  public Alphabet() {
    this.alphabet = new LinkedHashSet<Symbol>();
  }
  public Alphabet(Vector<Symbol> rawAlphabet) {
    for (Symbol symbol : rawAlphabet) {
      this.alphabet.add(symbol);
    }
  }
  public void add(Symbol symbol) {
    this.alphabet.add(symbol);
  }
  public boolean symbolExists(Symbol symbol) {
    return this.alphabet.contains(symbol);
  }
  // to string
  public String toString() {
    String result = "Alphabet: ";
    for (Symbol symbol : this.alphabet) {
      result += symbol.toString() + " ";
    }
    return result;
  }
}