package monopoly;

import java.util.Random;

public class Die {

    int sides = 6;

    public int roll() {
        Random die = new Random();
        return die.nextInt(1,sides+1);
    }

}
