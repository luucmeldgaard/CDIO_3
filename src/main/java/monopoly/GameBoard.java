package monopoly;

import java.util.ArrayList;

import gui_fields.*;
import gui_main.GUI;

import java.util.Scanner;

public class GameBoard {

    GUI gui;
    ArrayList<Player> players;
    GUI_Field field;
    Scanner scan;
    Board board;

    public GameBoard() {

        this.board = new Board();
        this.gui = new GUI(this.board.setup());
        this.field = gui.getFields()[0];
        this.players = new ArrayList<>();
        this.scan = new Scanner(System.in);

        int numPlayers = Integer.parseInt(this.gui.getUserSelection("Number of players", "1", "2", "3", "4"));

        for (int i = 0; i < numPlayers; i++) {
            String name = this.gui.getUserString("Player " + (i+1) + ", please enter you name: ", 2, 12, true);
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
                if (!player.getJailedStatus()) {
                    gui.getUserButtonPressed("It's " + player.name + "'s turn", "Roll");
                    int roll = cup.roll();
                    System.out.println(roll);
                    gui.setDie(roll);
                    System.out.println("Can't move");
                    player.setPosition(roll, player.getPosition());
                }
                System.out.println(player.getCar().getPosition());
                update_GUI(player);

                // The player can choose one of following options:
                // Buy (property), Build (property), Pick Card (chance), Continue (Refuge), Roll (jail)
                // which will be handled by the Board class.
                System.out.println(player.getName() + " is at: " + this.board.getField(player.getPosition()));
                String optionField = this.board.getField(player.getPosition());
                board.displayFieldActions(this.gui, player, optionField);

            }
        }
    }
    public void update_GUI(Player player) {
        player.getCar().setPosition(this.gui.getFields()[player.getPosition()]);
    }
}
