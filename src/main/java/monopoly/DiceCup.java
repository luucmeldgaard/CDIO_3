package monopoly;

import java.util.ArrayList;

public class DiceCup {

    int sides = 6;
    int numDies = 1;

    public int roll() {
        Die die = new Die();
        ArrayList<Integer> dies = new ArrayList<Integer>();
        for (int i = 0; i < numDies; i++) {
            dies.add(die.roll(sides));
        }
        int total = 0;
        for (int d:dies) {
            total += d;
        }
        return total;
    }

}
