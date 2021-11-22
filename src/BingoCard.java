import java.util.Arrays;

public class BingoCard {
    private int[][] numbers;
    private boolean[][] markedOff;

    private int numberOfRows;
    private int numberOfColumns;

    //method: to set the number of rows and columns of the bingo card using arrays
    public BingoCard(int numberOfRows, int numberOfColumns) { //overloaded constructor passing parameters in brackets
        setNumberOfRows(numberOfRows); //using setter
        setNumberOfColumns(numberOfColumns); //using setter

        numbers = new int[numberOfRows][numberOfColumns]; //array of integers for rows and columns, default is 0
        markedOff = new boolean[numberOfRows][numberOfColumns]; //array of boolean for rows and columns, default is false
        resetMarked(); //invoking method to reset the marked cards
    }

    //method: array of boolean for rows and columns, defaults to false - i.e. resets the grid/rows and columns of the card
    public void resetMarked() {
        markedOff = new boolean[getNumberOfRows()][getNumberOfColumns()]; //
    }

    /*getter and setter methods: used to retrieve private data fields
    setter sets value and getter returns value
    setter uses 'this' keyword to refer to current object in constructor/method
    */
    public int getNumberOfRows() { //getter - number of rows
        return numberOfRows; //can also write return this.numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) { //setter - number of rows
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfColumns() { //getter - number of columns
        return numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) { //setter - number of columns
        this.numberOfColumns = numberOfColumns;
    }

    /*method: using nested loops and StringBuilder
    iterates through the numbers inputted by user to get card numbers
    separates numbers with a single space
    uses number of rows and columns
     */
    public String getCardNumbers() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getNumberOfRows(); i++) {
            for (int j = 0; j < getNumberOfColumns(); j++) {
                sb.append(String.format("%d ", numbers[i][j])); //String.format allows use of placeholders such as %d
            }
        }
        return sb.toString(); //returns String representation of the object
    }

    /*method: set the card numbers using nested loops
     */
    public void setCardNumbers(String[] numbersAsStrings) {
        int[] numbersList =
                Arrays.stream(numbersAsStrings).mapToInt(Integer::parseInt).toArray(); //ask what this means in SGS

        int count = 0; //counter begins at 0
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                numbers[i][j] = numbersList[count];
                count++; //incrementing the counter
            }
        }
    }

    /*method: marking off cards if number is presented
    using nested loops and boolean
     */
    public void markNumber(int number) {
        boolean isMarked = false;
        for (int i = 0; i < getNumberOfRows(); i++) {
            for (int j = 0; j < getNumberOfColumns(); j++) {
                if (numbers[i][j] == number) { //if user's value is present in the row or column
                    isMarked = true; //then the boolean isMarked is true
                    markedOff[i][j] = true; //and the boolean markedOff is also true
                }
            }
        }

        if (isMarked) { //if isMarked is evaluated as true, then print the following
            System.out.printf("Marked off %d %n", number); //println((String.format("Marked off %d", number);
        } else { //default if isMarked is not true
            System.out.printf("Number %d not on this card %n", number); //println(String.format("Number %d not on this card", number));
        }

    }

    /*method: checking if player is a winner by using nested loops and boolean
     */
    public boolean isWinner() {
        for (int i = 0; i < getNumberOfRows(); i++) {
            for (int j = 0; j < getNumberOfColumns(); j++) {
                if (!markedOff[i][j]) {
                    return false;
                    /*if markedOff is false -
                    so if a single value is not marked off, this is instantly not a winner
                    so no need to look at the rest of the cards
                    */
                }
            }
        }
        return true; //if all values are marked off, then return true and isWinner is true
    }
}
