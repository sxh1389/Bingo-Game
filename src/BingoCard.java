import java.util.Arrays;

public class BingoCard {
    private int[][] numbers;
    private boolean[][] markedOff;

    private int numberOfRows;
    private int numberOfColumns;

    public BingoCard(int numberOfRows, int numberOfColumns) {
        setNumberOfRows(numberOfRows);
        setNumberOfColumns(numberOfColumns);

        numbers = new int[numberOfRows][numberOfColumns];
        markedOff = new boolean[numberOfRows][numberOfColumns];
        resetMarked();
    }

    public void resetMarked() {
        markedOff = new boolean[getNumberOfRows()][getNumberOfColumns()];
    }

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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getNumberOfRows(); i++) {
            for (int j = 0; j < getNumberOfColumns(); j++) {
                sb.append(String.format("%d ", numbers[i][j]));
            }
        }
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
        return true;
    }
}