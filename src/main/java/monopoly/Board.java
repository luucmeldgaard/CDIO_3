package monopoly;

import java.util.ArrayList;

import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_main.GUI;
import java.util.Scanner;


public class Board {

    GUI gui;
    ArrayList<Player> players;
    GUI_Field field;
    Scanner scan;

    public Board() {
        this.gui = new GUI();
        this.field = gui.getFields()[0];
        this.players = new ArrayList<>();
        this.scan = new Scanner(System.in);

        int numPlayers = Integer.parseInt(this.gui.getUserSelection("Number of players", "1", "2", "3", "4"));

        for (int i = 0; i < numPlayers; i++) {
            String name = this.gui.getUserString("Player " + i + ", please enter you name: ", 2, 12, true);
            System.out.println(name);
            this.players.add(new Player(name,1000, 0, false));
        }
        this.gui.getUserButtonPressed("Are you ready, " + this.players.get(0).name + "?", "Start");
        for (Player player:this.players) {
            this.gui.addPlayer(player);
            player.getCar().setPosition(this.gui.getFields()[0]);
        }
    }

    public void play() {
        DiceCup cup = new DiceCup();
        while (true) {
            for (Player player : this.players) {
                int roll = cup.roll();
                System.out.println(roll);
                gui.setDie(roll);
                System.out.println(player.getCar().getPosition());
                player.setPosition(roll, player.getPosition());
                update_GUI(player);
            }
        }
    }

    public void update_GUI(Player player) {
        player.getCar().setPosition(this.gui.getFields()[player.getPosition()]);
        gui.getUserButtonPressed("Press Continue to continue", "Continue");
    }

}
