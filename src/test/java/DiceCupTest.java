import monopoly.DiceCup;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class DiceCupRandomTest { //Tests whether the average roll value is what is expected
    @Test
    void AvgValue(){
        double sum = 0;
        double avgsum;
        double expected = 3.5; //Expected average of (1+2+3+4+5+6)/6=3.5
        int runs = 10000; //Lower runs => higher delta from chance
        DiceCup cup = new DiceCup();
        for(int i=0;i<runs;i++){
            sum += cup.roll(); //Sum of all rolls
        }
        avgsum = sum/runs;
        assertEquals(expected,avgsum,0.05); //Delta of 5%
    }

    @Test
    void RollInts(){
        int runs = 10000;
        DiceCup cup = new DiceCup();
        int num;
        for(int i=0;i<runs;i++) {
            num = cup.roll();
            assertFalse(num != 1 && num != 2 &&num != 3 &&num != 4 &&num != 5 && num != 6);
        }
    }
}
