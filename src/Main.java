package src;

import src.components.PushDownAutomaton;

public class Main {
  public static void main(String[] args) {
    Parser parser = new Parser("./examples/APv/APv-1.txt");
    PushDownAutomaton pda = parser.getPDA();
    System.out.println(pda.run("ab"));
  }
}