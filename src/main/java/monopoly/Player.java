package monopoly;

import java.util.ArrayList;

public class Player {

    int balance;
    int position;
    String name;
    boolean loseCondition;

    public Player(String name, int balance, int position, boolean lose) {
        this.name = name;
        this.balance = balance;
        this.position = position;
        this.loseCondition = lose;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return this.balance;
    }

    public void addBalance(int add) {
        this.balance += add;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return this.position;
    }

}
