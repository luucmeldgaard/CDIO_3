package monopoly;

public class Play {

    public static void main(String[] args) {
        new GuiController();
        GameBoard gameBoard = new GameBoard();
        gameBoard.play();
    }
}
