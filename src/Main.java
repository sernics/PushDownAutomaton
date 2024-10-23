package src;

import src.components.PushDownAutomaton;

public class Main {
  public static void main() {
    Parser parser = new Parser("./examples/APf/APf-2.txt", 2);
    PushDownAutomaton pda = parser.getPDA();
    pda.setTrace(true);
    String chain = "010101101010";
    if (pda.run(chain)) {// TODO: Make sure that this chain correspond to the alphabet of the automaton.
      System.out.println("The chain " + chain + " is accepted by the automaton.");
    } else {
      System.out.println("The chain " + chain + " is not accepted by the automaton.");
    }
  }
}