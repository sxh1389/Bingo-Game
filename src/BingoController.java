import javax.tools.Tool;
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

    /* TODO
          complete constants attached to mainMenuItems
     */
    private final String OPTION_EXIT = "0";
    private final String OPTION_PLAY = "1";
    private final String OPTION_SEPARATOR = "2";
    private final String OPTION_CREATE_CARD = "3";
    private final String OPTION_LIST_CARDS = "4";
    private final String OPTION_SIZE = "5";

    /* TODO
          complete default size of rows / columns as specified in the Defaults class
          create an arraylist of BingoCard cards
          include getters and setters for row / column sizes
     */
    private int currentRowSize;
    private int currentColumnSize;

    /* TODO
          create an ArrayList of BingoCard cards
     */

   //implement code here
    private final List<BingoCard> cards = new ArrayList<>();

    /* TODO
          implement getters and setters for currentRowSize / currentColumnSize
     */
    public int getCurrentRowSize() {
        /* TODO
              change the return from 0 to the appropriate return
     */
        return Defaults.DEFAULT_NUMBER_OF_ROWS;
    }

    public void setCurrentRowSize(int currentRowSize) {
       /* TODO
             implement code here
     */
    }

    public int getCurrentColumnSize() {
        /* TODO
              change the return from 0 to the appropriate return
     */
        return Defaults.DEFAULT_NUMBER_OF_COLUMNS;
    }

    public void setCurrentColumnSize(int currentColumnSize) {
       /* TODO
             implement code here
     */
    }

    /* TODO
          add a new BingoCard card
     */
    public void addNewCard(BingoCard card) {
        //implement code here
        cards.add(card);
    }

    /* TODO
          include an appropriate message to the the number of rows as well as the number of columns
     */
    public void setSize() {
        setCurrentRowSize(parseInt(Toolkit.getInputForMessage(
                "")));
        setCurrentColumnSize(parseInt(Toolkit.getInputForMessage(
                "")));
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

    /* TODO
         this method goes with printCardAsGrid() seen below
         when option 4 is chosen to list existing cards it prints each card accordingly
         for example, it should show the following
         Card 0 numbers:
         1  2
         3  4 (check with expected output files)
    */
    public void listCards() {
        /* TODO
              insert code here to find all cards to be printed accordingly
         */
        /* TODO
              call printCardAsGrid() method here, Hint: use getCardNumbers() when getting cards
         */
        }

    /* TODO
          this is for option 4, list existing cards where all the cards are printed as a grid
          of rows / columns, so numbers 3 4 5 6 will be printed as follows:
          3  4
          5  6
          it is a follow on method from listCards() and ensures that the grid structure is printed
     */
    public void printCardAsGrid(String numbers) {
        //insert code here to print numbers as a grid
    }

    /* TODO
          use Toolkit.getInputForMessage to enter a new separator
          print a message what the new separator is
     */
    public void setSeparator() {
        String sep = Toolkit.getInputForMessage("Enter the new separator");
        /* TODO
              make use of setNumberSeparator() and getNumberSeparator()
         */
    }

    /* TODO
         reset all BingoCards using resetMarked (to false)
     */
    public void resetAllCards() {
        //insert code here
    }

    /* TODO
          mark off a number that was called when it equals one of the numbers on the BingoCard
     */
    public void markNumbers(int number) {
        //insert code here
    }

   /* TODO
         make use of isWinner() to determine who the winner is
         the method should return the index of who the winner is
    */
    public int getWinnerId() {
        //insert code here
        return 0;
    }

    /* TODO
          please take note that the game will not end until there is a winning sequence
     */
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
    /* TODO
        change this method so it prints a proper numbered menu
        analyse the correct format from the ExpectedOutput files
        menuText is returned
     */
        StringBuilder menuText = new StringBuilder();
        for (int i = 0; i < menuItems.length; i++) {
            menuText.append(String.format("%d: %s \n", i, menuItems[i]));
        }
        return menuText.toString();
    }
    /* TODO
          complete the menu using switch to call the appropriate method calls
     */
    public void run() {
        boolean finished = false;
        do {
            switch (Toolkit.getInputForMessage(getMenu(mainMenuItems))) {
                case OPTION_EXIT:
                    finished = true;
                case OPTION_CREATE_CARD:
                    createCard();
                    finished = true;
            }
        } while (!finished);
    }
}
