import java.util.Arrays;

public class BingoCard {
  /*
    The two arrays are private and their structure is NEVER exposed to another
    class, which is why the getCardNumbers returns a String that needs
    further processing.

    While this is not computationally efficient, it is good programming
    practice to hide data structures (information hiding).
   */
  private int[][] numbers;
  private boolean[][] markedOff;

  private int numberOfRows;
  private int numberOfColumns;

  public BingoCard(int numberOfRows, int numberOfColumns) {
    setNumberOfRows(numberOfRows);
    setNumberOfColumns(numberOfColumns);

    numbers   = new int[numberOfRows][numberOfColumns];
    markedOff = new boolean[numberOfRows][numberOfColumns];
    resetMarked();
  }

  public void resetMarked() {
    /* TODO
          Reset the data structure to be entirely false. Java defaults booleans
          to false so you can make use of that.
     */
    markedOff = new boolean[getNumberOfRows()][getNumberOfColumns()];
  }
     /* TODO
           implement the getters and setters for rows / columns as seen below
     */
  public int getNumberOfRows() {
    return this.numberOfRows;
  }

  public void setNumberOfRows(int numberOfRows) {
    this.numberOfRows = numberOfRows;
  }

  public int getNumberOfColumns() {
    return this.numberOfColumns;
  }

  public void setNumberOfColumns(int numberOfColumns) {
    this.numberOfColumns = numberOfColumns;
  }

  public String getCardNumbers() {
    /* TODO
        flatten the numbers array into a single string with each number separated by the currently required separator
        but no leading or trailing copies of that separator.
        For example, if the separator were currently a single space,
        then no extra spaces before the first number nor after the last number.
     */

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < getNumberOfRows(); i++) {
      for (int j = 0; j < getNumberOfColumns(); j++) {
        sb.append(String.format("%d ", numbers[i][j])); // come back to this -  don't add space at end
      }
    }
    /* TODO
          all the cards are stored as a grid ([][] numbers) of rows / columns, so for example, numbers 3 4 5 6 will be
          printed as follows:
          3  4
          5  6
     */
    /* TODO
          return the grid as a string
     */
    return sb.toString();
  }

  public void setCardNumbers(String[] numbersAsStrings) {
    int[] numbersList =
        Arrays.stream(numbersAsStrings).mapToInt(Integer::parseInt).toArray();

    int count = 0;
    for (int i = 0; i < numberOfRows; i++) {
      for (int j = 0; j < numberOfColumns; j++) {
        numbers[i][j] = numbersList[count];
        count++;
      }
    }
  }

  public void markNumber(int number) {
    /* TODO
          make use of the [][] markedOff to mark off numbers from [][] numbers as they match
          if not matching an appropriate message must be printed, verify against expected output files
     */
    boolean isMarked = false;
    for (int i = 0; i < getNumberOfRows(); i++) {
      for (int j = 0; j < getNumberOfColumns(); j++) {
        if (numbers[i][j] == number) {
          isMarked = true;
          markedOff[i][j] = true;
        }
      }
    }

    if (isMarked) {
      System.out.println(String.format("Marked off %d", number));
    } else {
      System.out.println(String.format("Number %d not on this card", number));
    }

  }

  public boolean isWinner() {
    for (int i = 0; i < getNumberOfRows(); i++) {
      for (int j = 0; j < getNumberOfColumns(); j++) {
        if (!markedOff[i][j]) {
          return false;
        }
      }
    }
    return true ;
  }
}