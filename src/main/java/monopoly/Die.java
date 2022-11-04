package monopoly;

import java.util.Random;

public class Die {

    public int roll(int sides) {
        Random die = new Random();
        return die.nextInt(1,sides+1);
    }

}
