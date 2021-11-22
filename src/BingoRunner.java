public class BingoRunner {
    public static void main(String[] args) {
        BingoController controller = new BingoController(); //create new bingo controller object
        controller.run(); //invoke run method
        System.out.println(Toolkit.GOODBYEMESSAGE); //print goodbye message
    }
}
