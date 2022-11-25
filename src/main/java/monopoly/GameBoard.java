package monopoly;

import java.awt.*;
import java.util.*;


public class GameBoard {

    ArrayList<Player> players;
    Scanner scan;

    public GameBoard() {

        this.players = new ArrayList<>();
        this.scan = new Scanner(System.in);

        int numPlayers = Integer.parseInt(GuiController.buttonRequest("Number of players", "2", "3", "4"));

        //ArrayList<String> nameList = new ArrayList<String>();

        String[] colorNames = new String[] {"Red", "Blue", "Green", "Pink"};
        Color[] colorCodes = new Color[] {Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA};

        Map<String, Color> colors = new HashMap<>();
        for (int i = 0; i < colorNames.length; i++) {
            colors.put(colorNames[i], colorCodes[i]);
        }

        for (int i = 0; i < numPlayers; i++) {
            String[] hello = colors.keySet().toArray(new String[0]);
            String name = GuiController.gui.getUserString("Player " + (i+1) + ", please enter you name: ", 2, 12, true);
            System.out.println(name);
            String color = GuiController.gui.getUserSelection("Please choose a color", hello);
            this.players.add(new Player(name,1000, 0, false));
            System.out.println(Color.getColor(color));
            players.get(i).getCar().setPrimaryColor(colors.get(color));
            colors.remove(color);
        }

        GuiController.gui.getUserButtonPressed("Are you ready, " + this.players.get(0).name + "?", "Start");
        for (Player player:this.players) {
            GuiController.gui.addPlayer(player);
            player.getCar().setPosition(GuiController.gui.getFields()[0]);
        }
    }

    public void play() {
        DiceCup cup = new DiceCup();
        while (inGameCondition()) {
            for (Player player : this.players) {
                System.out.println("new play by " + player.getName());
                if (!player.getJailedStatus()) {
                    GuiController.gui.getUserButtonPressed("It's " + player.getName() + "'s turn", "Roll");
                    int roll = cup.roll();
                    System.out.println(roll);
                    GuiController.gui.setDie(roll);
                    player.setPosition(roll, player.getPosition());
                }
                System.out.println(player.getCar().getPosition());

                // The player can choose one of following options:
                // Buy (property), Build (property), Pick Card (chance), Continue (Refuge), Roll (jail)
                // which will be handled by the Board class.
                update_GUI(player);

                System.out.println(player.getName() + " is at: " + GuiController.fields.getField(player.getPosition()));
                String optionField = GuiController.fields.getField(player.getPosition());
                System.out.println("optionField: " + optionField);
                GuiController.fields.displayFieldActions(player, optionField);

                update_GUI(player);
            }
        }
    }

    public boolean inGameCondition() {
        for (Player player:this.players) {
            if (player.getBalance() < 0) {
                GuiController.gui.getUserButtonPressed(player.name + " has lost the game!", "Continue...");
                displayWinner(players);
                return false;
            }
        }
        return true;
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
        GuiController.gui.getUserButtonPressed(winner + " wins the game!", "END");
        System.exit(0);

    }
    private void update_GUI(Player player) {
        player.getCar().setPosition(GuiController.gui.getFields()[player.getPosition()]);
    }
}
