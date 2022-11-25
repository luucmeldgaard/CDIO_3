package monopoly;

import gui_fields.GUI_Player;

import java.util.ArrayList;
import java.util.List;

public class Player extends GUI_Player {

    //int balance;
    int position;
    String name;
    boolean loseCondition;
    boolean jailed;
    ArrayList<String> properties;

    public Player(String name, int balance, int position, boolean lose) {
        super(name, 1000);
        this.name = name;
        //this.balance = balance;
        this.position = position;
        this.loseCondition = lose;
        this.jailed = false;
        this.properties = new ArrayList<String>();
    }


    public void addBalance(int add) {
        setBalance(getBalance() + add);
    }

    public void setPosition(int position, int add) {
        this.position = (position + add) % GUIBoard.numFields;
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

    public boolean getOwnedStatus(String propertyName) {
        return this.properties.contains(propertyName);
    }

    public void setOwnedStatus(String propertyName) {
        this.properties.add(propertyName);
    }
}
