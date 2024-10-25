package src;

import java.util.Scanner;
import java.util.Vector;

import src.components.*;

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
    boolean isInitialState = false;
    for (State state : states) {
      if (state.getId().equals(initialStateData)) {
        initialState = state;
        isInitialState = true;
      }
    }
    if (!isInitialState) {
      throw new RuntimeException("The initial state was not found.");
    }
    return initialState;
  }

  public boolean checkAlphabet(Alphabet alphabet, Symbol symbol) {
    for (Symbol alphabetSymbol : alphabet.getAlphabet()) {
      if (alphabetSymbol.equals(symbol)) {
        return true;
      }
    }
    if (symbol.equals(Symbol.EPSILON)) {
      return true;
    }
    return false;
  }

  public boolean checkStates(Vector<State> states, String stateId) {
    for (State state : states) {
      if (state.getId().equals(stateId)) {
        return true;
      }
    }
    return false;
  }

  public Parser(String filename, int type) {
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
    boolean isInitialState = false;
    for (State state : states) {
      if (state.getId().equals(initialState.getId())) {
        isInitialState = true;
      }
    }
    if (!isInitialState) {
      throw new RuntimeException("The initial state was not found.");
    }
    i++;
    if (type == 1) {
      this.pda = new EmptyPushDown();
    } else if (type == 2) {
      String[] finalStates = tokens.get(i);
      boolean isFinalState = false;
      for (String finalState : finalStates) {
        for (State state : states) {
          if (state.getId().equals(finalState)) {
            state.setFinal();
            isFinalState = true;
          }
        }
      }
      if (!isFinalState) {
        throw new RuntimeException("The final state was not found.");
      }
      i++;
      this.pda = new FinalPushDown();
    } else throw new RuntimeException("Wrong type option");
    this.pda.setStates(states);
    this.pda.setSigmaAlphabet(sigmaAlphabet);
    this.pda.setGammaAlphabet(gammaAlphabet);
    this.pda.setInitialState(initialState);
    this.pda.setStack(initialStackSymbol);
    while (i < tokens.size()) {
      String stateId = tokens.get(i)[0];
      if (!this.checkStates(states, stateId)) {
        throw new RuntimeException("The state " + stateId + " was not found.");
      }
      Symbol chainSymbol = new Symbol(tokens.get(i)[1]);
      if (!this.checkAlphabet(sigmaAlphabet, chainSymbol)) {
        throw new RuntimeException("The symbol " + chainSymbol + " was not found in the sigma alphabet.");
      }
      Symbol stackSymbol = new Symbol(tokens.get(i)[2]);
      if (!this.checkAlphabet(gammaAlphabet, stackSymbol)) {
        throw new RuntimeException("The symbol " + stackSymbol + " was not found in the gamma alphabet.");
      }
      String nextStateId = tokens.get(i)[3];
      State nextState = null;
      boolean isNextState = false;
      for (State state : states) {
        if (state.getId().equals(nextStateId)) {
          nextState = state;
          isNextState = true;
        }
      }
      if (!isNextState) {
        throw new RuntimeException("The next state " + nextStateId + " was not found.");
      }
      Vector<Symbol> toStack = new Vector<>();
      String toStackData = tokens.get(i)[4];
      for (int j = 0; j < toStackData.length(); j++) {
        String data = toStackData.substring(j, j + 1);
        if (!this.checkAlphabet(gammaAlphabet, new Symbol(data)) && !data.equals(".")) {
          throw new RuntimeException("The symbol " + data + " was not found in the gamma alphabet.");
        }
        toStack.add(new Symbol(data));
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