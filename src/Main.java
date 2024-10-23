package src;

import src.components.PushDownAutomaton;

public class Main {
  public static void main() {
    Parser parser = new Parser("./examples/APf/APf-2.txt", 2);
    PushDownAutomaton pda = parser.getPDA();
    System.out.println(pda);
    System.out.println(pda.run("aabb")); // TODO: Make sure that this chain correspond to the alphabet of the automaton.
  }
}