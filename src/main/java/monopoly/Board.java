package monopoly;

public class Board {

    public static void main(String[] args) {
        DiceCup test = new DiceCup();
        for (int i = 0; i < 1000; i++) {
            System.out.println(test.roll());
        }
    }

}
