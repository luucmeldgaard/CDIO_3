package monopoly;

import gui_fields.GUI_Player;

import java.util.ArrayList;

public class Player extends GUI_Player {

    int balance;
    int position;
    String name;
    boolean loseCondition;

    public Player(String name, int balance, int position, boolean lose) {
        super(name, 1000);
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

    public void setPosition(int position, int add) {
        this.position = (position + add) % Board.numFields;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return this.position;
    }

}
