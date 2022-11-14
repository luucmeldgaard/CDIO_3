package monopoly;

import java.awt.*;
import java.util.*;

import gui_fields.*;
import gui_main.GUI;

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

        int numPlayers = Integer.parseInt(this.gui.getUserSelection("Number of players", "2", "3", "4"));

        //ArrayList<String> nameList = new ArrayList<String>();

        String[] colorNames = new String[] {"Red", "Blue", "Green", "Pink"};
        Color[] colorCodes = new Color[] {Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA};

        Map<String, Color> colors = new HashMap<>();
        for (int i = 0; i < colorNames.length; i++) {
            colors.put(colorNames[i], colorCodes[i]);
        }

        for (int i = 0; i < numPlayers; i++) {
            String[] hello = colors.keySet().toArray(new String[0]);
            String name = this.gui.getUserString("Player " + (i+1) + ", please enter you name: ", 2, 12, true);
            System.out.println(name);
            String color = this.gui.getUserSelection("Please choose a color", hello);
            this.players.add(new Player(name,1000, 0, false));
            System.out.println(Color.getColor(color));
            players.get(i).getCar().setPrimaryColor(colors.get(color));
            colors.remove(color);
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
                System.out.println("new play by " + player.getName());
                if (!player.getJailedStatus()) {
                    gui.getUserButtonPressed("It's " + player.name + "'s turn", "Roll");
                    int roll = cup.roll();
                    System.out.println(roll);
                    gui.setDie(roll);
                    player.setPosition(roll, player.getPosition());
                }
                System.out.println(player.getCar().getPosition());

                // The player can choose one of following options:
                // Buy (property), Build (property), Pick Card (chance), Continue (Refuge), Roll (jail)
                // which will be handled by the Board class.
                update_GUI(player);

                System.out.println(player.getName() + " is at: " + this.board.getField(player.getPosition()));
                String optionField = this.board.getField(player.getPosition());
                System.out.println("optionField: " + optionField);
                while (true) {
                    try {
                        this.board.displayFieldActions(this.gui, player, optionField);
                        break;
                    } catch (Exception e) {
                        System.out.println("Error");
                    }
                }
                update_GUI(player);
                if (player.getBalance() < 0) {
                    this.gui.getUserButtonPressed(player.name + " has lost the game!", "Continue...");
                    displayWinner(players);
                }
            }
        }
    }

    private void displayWinner(ArrayList<Player> players) {
        String winner = "";
        int highestAmount = 0;
        for (Player player:players) {
            if (player.getBalance() >= highestAmount) {
                winner = player.getName();
                highestAmount = player.getBalance();
            }
            //TODO What if two players win the game?
        }
        this.gui.getUserButtonPressed(winner + " wins the game!", "END");
        System.exit(0);

    }
    private void update_GUI(Player player) {
        player.getCar().setPosition(this.gui.getFields()[player.getPosition()]);
    }
}
