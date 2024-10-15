package src.components;

public class Symbol {
  private char symbol;
  public Symbol(char symbol) {
    this.symbol = symbol;
  }
  public char get() {
    return this.symbol;
  }
  public boolean equals(Symbol other) {
    return this.symbol == other.get();
  }
  /*
   * Returns true if this symbol is the epsilon symbol. Has been defined the epsilon symbol as the dot ('.') character.
   * @return True if this symbol is the epsilon symbol.
   * TODO: check if this is the best way to define the epsilon symbol.
   */
  public boolean epsilon() {
    return this.symbol == '.';
  }
}
