package src;

import java.util.Scanner;
import java.util.Vector;

import src.components.Alphabet;
import src.components.PushDownAutomaton;
import src.components.State;
import src.components.Symbol;
import src.components.Transition;

public class Parser {
  private final PushDownAutomaton pda;

  private String removeCommentaries(String line) {
    return line.split("#")[0];
  }
  private Vector<String[]> generateVectorData(String filename) {
    Vector<String[]> tokens = new Vector<>();
    try {
      Scanner scanner = new Scanner(new java.io.File(filename));
      while (scanner.hasNextLine()) {
        String line = this.removeCommentaries(scanner.nextLine());
        // Skip empty lines. Lines that was comments and was removed are empty.
        if (line.isEmpty()) {
          continue;
        }
        String[] actualTokens = line.split(" ");
        tokens.add(actualTokens);
      }
      scanner.close();
    } catch (java.io.FileNotFoundException e) { // Fail to found the file.
      System.out.println("File not found.");
    }
    return tokens;
  }
  private Vector<State> generateStates(String[] statesData) {
    Vector<State> states = new Vector<>();
    for (String stateData : statesData) {
      State state = new State(stateData);
      states.add(state);
    }
    return states;
  }
  private Alphabet generateAlphabet(String[] alphabetData) {
    Alphabet alphabet = new Alphabet();
    for (String symbolData : alphabetData) {
      Symbol symbol = new Symbol(symbolData);
      alphabet.add(symbol);
    }
    return alphabet;
  }
  private State setInitialState(Vector<State> states, String initialStateData) {
    State initialState = null;
    for (State state : states) {
      if (state.getId().equals(initialStateData)) {
        initialState = state;
      }
    }
    return initialState;
  }

  public Parser(String filename) {
    Vector<String[]> tokens = this.generateVectorData(filename);
    int i = 0;
    String[] statesData = tokens.get(i);
    Vector<State> states = this.generateStates(statesData);
    i++;
    String[] sigmaData = tokens.get(i);
    Alphabet sigmaAlphabet = this.generateAlphabet(sigmaData);
    i++;
    String[] gammaData = tokens.get(i);
    Alphabet gammaAlphabet = this.generateAlphabet(gammaData);
    i++;
    State initialState = this.setInitialState(states, tokens.get(i)[0]);
    i++;
    Symbol initialStackSymbol = new Symbol(tokens.get(i)[0]);
    i++;
    this.pda = new PushDownAutomaton();
    this.pda.setStates(states);
    this.pda.setSigmaAlphabet(sigmaAlphabet);
    this.pda.setGammaAlphabet(gammaAlphabet);
    this.pda.setInitialState(initialState);
    this.pda.setStack(initialStackSymbol);
    while (i < tokens.size()) {
      String stateId = tokens.get(i)[0];
      Symbol chainSymbol = new Symbol(tokens.get(i)[1]);
      Symbol stackSymbol = new Symbol(tokens.get(i)[2]);
      String nextStateId = tokens.get(i)[3];
      State nextState = null;
      for (State state : states) {
        if (state.getId().equals(nextStateId)) {
          nextState = state;
        }
      }
      Vector<Symbol> toStack = new Vector<>();
      String toStackData = tokens.get(i)[4];
      for (int j = 0; j < toStackData.length(); j++) {
        toStack.add(new Symbol(toStackData.substring(j, j + 1)));
      }
      for (State state : states) {
        if (state.getId().equals(stateId)) {
          Transition transition = new Transition(nextState, chainSymbol, stackSymbol, toStack);
          state.addTransition(transition);
        }
      }
      i++;
    }
  }
  public PushDownAutomaton getPDA() {
    return this.pda;
  }
}