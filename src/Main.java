package src;

import src.components.PushDownAutomaton;

public class Main {
  public static void main(String[] args) {
    Parser parser = new Parser("./examples/APv/APv-2.txt");
    PushDownAutomaton pda = parser.getPDA();
    System.out.println(pda.run("0110")); // TODO: Make sure that this chain correspond to the alphabet of the automaton.
  }
}