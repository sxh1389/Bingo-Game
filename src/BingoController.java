import java.util.ArrayList;
import java.util.List;
import static java.lang.Integer.parseInt;

public class BingoController {

    private final String[] mainMenuItems = {"Exit",
            "Play bingo",
            "Set number separator",
            "Create a bingo card",
            "List existing cards",
            "Set bingo card size"};

    private final String OPTION_EXIT = "0";
    private final String OPTION_PLAY = "1";
    private final String OPTION_SEPARATOR = "2";
    private final String OPTION_CREATE_CARD = "3";
    private final String OPTION_LIST_CARDS = "4";
    private final String OPTION_SIZE = "5";
    private ArrayList<BingoCard> cards = new ArrayList<>(); // create an array list of bingo cards stored in variable called cards

    private int currentRowSize;
    private int currentColumnSize;

    //
    public int getCurrentRowSize() {
        if(currentRowSize == 0) { //if row size is 0
            return Defaults.DEFAULT_NUMBER_OF_ROWS; //use the default set rows
        }
        else
            return currentRowSize; //otherwise, use user inputted row value
    }

    public void setCurrentRowSize(int currentRowSize) {
        this.currentRowSize = currentRowSize;
    }

    public int getCurrentColumnSize() {
        if(currentColumnSize == 0) {
            return Defaults.DEFAULT_NUMBER_OF_COLUMNS;
        }
        else
            return currentColumnSize;
    }

    public void setCurrentColumnSize(int currentColumnSize) {
        this.currentColumnSize = currentColumnSize;
    }

    public void addNewCard(BingoCard card) { //adding new card to the array list of cards
        cards.add(card);
    }

    public void setSize() {
        setCurrentRowSize(parseInt(Toolkit.getInputForMessage( //use the getInputForMessage method in the Toolkits class to allow user to input size
                "Enter the number of rows for the card"))); //parseInt - changes data type from int to string
        setCurrentColumnSize(parseInt(Toolkit.getInputForMessage(
                "Enter the number of columns for the card")));
        System.out.printf("The bingo card size is set to %d rows X %d columns%n",
                getCurrentRowSize(),
                getCurrentColumnSize());
    }

    public void createCard() {
        int numbersRequired = getCurrentColumnSize() * getCurrentRowSize(); //numbers required by user is row x column

        String[] numbers;

        boolean correctAmountOfNumbersEntered;

        do { //do once
            numbers = Toolkit.getInputForMessage(
                            String.format(
                                    "Enter %d numbers for your card (separated by " +
                                            "'%s')",
                                    numbersRequired,
                                    Defaults.getNumberSeparator()))
                    .trim()
                    .split(Defaults.getNumberSeparator());

            correctAmountOfNumbersEntered = numbers.length == numbersRequired;

            if (!correctAmountOfNumbersEntered) {
                System.out.println(String.format("Try again: you entered %d numbers instead of %d", numbers.length, numbersRequired));
            }
        } while (!correctAmountOfNumbersEntered); //exit loop if this condition has not been met

        System.out.printf("You entered%n");
        System.out.println(Toolkit.printArray(numbers));

        BingoCard card = new BingoCard(getCurrentRowSize(), getCurrentColumnSize());

        card.setCardNumbers(numbers);
        addNewCard(card);
    }

    public void listCards() {
        for (int i = 0; i < cards.size(); i++) { //for the array list size (all the elements)
            System.out.printf("Card  %d numbers:", i); //print the card number using its index and the numbers in the grid
            printCardAsGrid(cards.get(i).getCardNumbers());
        }
    }

    public void printCardAsGrid(String numbers) {
        String[] arr = numbers.split(" "); //split at white space
        StringBuilder sb = new StringBuilder();
        int count = 1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length() < 2) { //if the value of the number is less than two digits
                sb.append(" "); //add a space
            }
            sb.append(String.format("%s", arr[i]));

            // if not end of row, add space at end
            // if end of row, but not final of card, add new line
            if (count % getCurrentColumnSize() != 0) {
                sb.append(Defaults.getNumberSeparator());
            } else if (count != arr.length) {
                sb.append("\n");
            }
            count++; //increment counter
        }

        System.out.println(sb);
    }

    public void setSeparator() {
        String sep = Toolkit.getInputForMessage("Enter the new separator");
        Defaults.setNumberSeparator(sep);
        System.out.printf("Separator is '%s'", Defaults.getNumberSeparator());
    }

    public void resetAllCards() {
        for (BingoCard card : cards) { //enhanced for loop - for each (BingoCard class) card in array list cards
            card.resetMarked(); //invoke reset method to reset all cards
        }
    }

    public void markNumbers(int number) {
        for (int i = 0; i < cards.size(); i++) {
            System.out.println(String.format("Checking card %d for %d", i, number));
            cards.get(i).markNumber(number);
        }
    }

    public int getWinnerId() {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).isWinner()) {
                return i;
            }
        }
        return -1;
    }

    public void play() {
        System.out.println("Eyes down, look in!");
        resetAllCards();

        boolean weHaveAWinner;
        do {
            markNumbers(parseInt(
                    Toolkit.getInputForMessage("Enter the next number")
                            .trim()));

            int winnerID = getWinnerId();
            weHaveAWinner = winnerID != Defaults.NO_WINNER;
            if (weHaveAWinner)
                System.out.printf("And the winner is card %d%n", winnerID);
        } while (!weHaveAWinner);
    }

    public String getMenu(String[] menuItems) {
        StringBuilder menuText = new StringBuilder();
        for (int i = 0; i < menuItems.length; i++) {
            menuText.append(String.format(" %d: %s\n", i, menuItems[i]));
        }
        return menuText.toString(); //returns object as String
    }

    //using switch statement to run through each of the options of the menu and invoking the correct methods
    public void run() {
        boolean finished = false;
        do {
            switch (Toolkit.getInputForMessage(getMenu(mainMenuItems))) {
                case OPTION_EXIT:
                    finished = true;
                    break;
                case OPTION_PLAY:
                    play();
                    break;
                case OPTION_SEPARATOR:
                    setSeparator();
                    break;
                case OPTION_CREATE_CARD:
                    createCard();
                    break;
                case OPTION_LIST_CARDS:
                    listCards();
                    break;
                case OPTION_SIZE:
                    setSize();
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        } while (!finished);
    }
}
