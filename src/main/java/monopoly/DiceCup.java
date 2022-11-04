package monopoly;

public class DiceCup {

    public int roll() {
        Die die1 = new Die();
        Die die2 = new Die();
        return die1.roll() + die2.roll();
    }

}
