import javax.tools.Tool;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private final List<BingoCard> cards = new ArrayList<>();

    private int currentRowSize;
    private int currentColumnSize;

    public int getCurrentRowSize() {
        return currentRowSize == 0 ? Defaults.DEFAULT_NUMBER_OF_ROWS : currentRowSize;
    }

    public void setCurrentRowSize(int currentRowSize) {
        this.currentRowSize = currentRowSize;
    }

    public int getCurrentColumnSize() {
        return currentColumnSize == 0 ? Defaults.DEFAULT_NUMBER_OF_COLUMNS : currentColumnSize;
    }

    public void setCurrentColumnSize(int currentColumnSize) {
        this.currentColumnSize = currentColumnSize;
    }

    public void addNewCard(BingoCard card) {
        cards.add(card);
    }

    public void setSize() {
        setCurrentRowSize(parseInt(Toolkit.getInputForMessage(
                "Enter the number of rows for the card")));
        setCurrentColumnSize(parseInt(Toolkit.getInputForMessage(
                "Enter the number of columns for the card")));
        System.out.printf("The bingo card size is set to %d rows X %d columns%n",
                getCurrentRowSize(),
                getCurrentColumnSize());
    }

    public void createCard() {
        int numbersRequired = getCurrentColumnSize() * getCurrentRowSize();

        String[] numbers;

        boolean correctAmountOfNumbersEntered;

        do {
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
        } while (!correctAmountOfNumbersEntered);

        System.out.println("You entered");
        System.out.println(Toolkit.printArray(numbers));

        BingoCard card = new BingoCard(getCurrentRowSize(), getCurrentColumnSize());

        card.setCardNumbers(numbers);
        addNewCard(card);
    }

    public void listCards() {
        for (int i = 0; i < cards.size(); i++) {
            System.out.println(String.format("Card  %d numbers:", i));
            printCardAsGrid(cards.get(i).getCardNumbers());
        }
    }

    public void printCardAsGrid(String numbers) {
        String[] arr = numbers.split(" ");
        StringBuilder sb = new StringBuilder();
        int count = 1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length() < 2) {
                sb.append(" ");
            }
            sb.append(String.format("%s", arr[i]));

            // if not end of line add space at end
            // if end of line but not final value add new line
            if (count % getCurrentColumnSize() != 0) {
                sb.append(Defaults.getNumberSeparator());
            } else if (count != arr.length) {
                sb.append("\n");
            }
            count++;
        }

        System.out.println(sb);
    }

    public void setSeparator() {
        String sep = Toolkit.getInputForMessage("Enter the new separator");
        Defaults.setNumberSeparator(sep);
        System.out.println(String.format("Separator is '%s'", Defaults.getNumberSeparator()));
    }

    public void resetAllCards() {
        for (BingoCard card : cards) {
            card.resetMarked();
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
            menuText.append(String.format("%d: %s \n", i, menuItems[i]));
        }
        return menuText.toString();
    }

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
                    System.out.println("Invalid selection from menu. Please try again");
                    break;
            }
        } while (!finished);
    }
}
