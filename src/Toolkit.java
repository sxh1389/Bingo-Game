import java.util.Scanner;

public class Toolkit {
  private static final Scanner stdIn = new Scanner(System.in);

  /* TODO
        complete the GOODBYEMESSAGE
   */

  public static final String GOODBYEMESSAGE = "Thank you for playing";

  public static String getInputForMessage(String message) {
    System.out.println(message);
    return stdIn.nextLine().trim();
  }

  public static String printArray(String[] array) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < array.length; i++) {
      sb.append(array[i]);
      if (i < array.length - 1) {
        sb.append(", ");
      }
    }
    return sb.toString();
  }
}
