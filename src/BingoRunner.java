public class BingoRunner {
  public static void main(String[] args) {
    /* TODO
          create and execute a new BingoController that starts the game
          invoke run()
          include the Thank you for playing once the game exits (GOODBYEMESSAGE)
     */
      BingoController controller = new BingoController();
      controller.run();
      System.out.println(Toolkit.GOODBYEMESSAGE);
  }
}
// C:\Users\Safwa\Desktop\Java Assignments\Assignment 2\Assignment212\src\BingoCard.java