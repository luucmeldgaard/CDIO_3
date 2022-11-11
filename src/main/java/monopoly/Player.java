package monopoly;

import gui_fields.GUI_Player;

public class Player extends GUI_Player {

    //int balance;
    int position;
    String name;
    boolean loseCondition;
    boolean jailed;

    public Player(String name, int balance, int position, boolean lose) {
        super(name, 1000);
        this.name = name;
        //this.balance = balance;
        this.position = position;
        this.loseCondition = lose;
        this.jailed = false;
    }

    public void addBalance(int add) {
        setBalance(getBalance() + add);
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

    public boolean getJailedStatus() {
        return this.jailed;
    }
    public void setJailedStatus(boolean status) {
        this.jailed = status;
    }

}
