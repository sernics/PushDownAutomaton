package src;

import java.util.Scanner;

import src.components.PushDownAutomaton;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the path to the file with the automaton data: ");
    String filename = scanner.nextLine();
    System.out.println("Select one of the following automaton options:");
    System.out.println("1 - Empty stack automaton.");
    System.out.println("2 - Final state automaton.");
    System.out.print("Option: ");
    int type = scanner.nextInt();
    Parser parser = new Parser(filename, type);
    PushDownAutomaton pda = parser.getPDA();
    System.out.print("Do you want to print the trace of the automaton? (true/false): ");
    boolean isTrace = scanner.nextBoolean();
    pda.setTrace(isTrace); // The isTrace print a trace on a file called out.txt
    String chain = "";
    scanner.nextLine(); // Consume the newline left-over -> This line is because (On my case) is reading an empty line.
    while (chain != "exit") {
      System.out.print("Enter the chain to be evaluated by the automaton (exit to finish the program): ");
      chain = scanner.nextLine();
      if (chain.equals("exit")) {
        System.out.println("Finishing the program...");
        break;
      }
      if (pda.run(chain)) {
        System.out.println("The chain " + chain + " is accepted by the automaton.");
      } else {
        System.out.println("The chain " + chain + " is not accepted by the automaton.");
      }
    }
    scanner.close();
  }
}