package src.components;

public class Symbol {
  public static final Symbol EPSILON = new Symbol("EPSILON");
  private String symbol;
  public Symbol(String symbol) {
    this.symbol = symbol;
  }
  public Symbol() {
    this.symbol = ".";
  }
  public String get() {
    return this.symbol;
  }
  public boolean equals(Symbol other) {
    return this.symbol.equals(other.get());
  }
  /*
   * Returns true if this symbol is the epsilon symbol. Has been defined the epsilon symbol as the dot ('.') character.
   * @return True if this symbol is the epsilon symbol.
   */
  public boolean epsilon() {
    return this.symbol.equals(".");
  }
  // to string
  public String toString() {
    return "" + this.symbol;
  }
}
