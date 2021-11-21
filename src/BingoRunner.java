public class BingoRunner {
    public static void main(String[] args) {
        BingoController controller = new BingoController();
        controller.run();
        System.out.println(Toolkit.GOODBYEMESSAGE);
    }
}
