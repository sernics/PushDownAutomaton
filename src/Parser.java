package src;

import java.util.Scanner;

public class Parser {
  private String removeCommentaries(String line) {
    return line.split("#")[0];
  }

  public Parser(String filename) {
    try {
      Scanner scanner = new Scanner(new java.io.File(filename));
      String data = "";
      while (scanner.hasNextLine()) {
        String line = this.removeCommentaries(scanner.nextLine());
        // Skip empty lines.
        if (line.isEmpty()) {
          continue;
        }
        data += line + "\n";
      }
      // Remove the last break line.
      data = data.substring(0, data.length() - 1);
      System.out.println(data);
      scanner.close();
    } catch (java.io.FileNotFoundException e) { // Fail to found the file.
      System.out.println("File not found.");
    }
  }
}
