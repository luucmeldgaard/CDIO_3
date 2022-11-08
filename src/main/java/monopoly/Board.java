package monopoly;

import java.awt.*;
import java.util.ArrayList;

import gui_fields.*;
import gui_main.GUI;
import java.util.Scanner;
import java.awt.Color;


public class Board {

    GUI gui;
    ArrayList<Player> players;
    GUI_Field field;
    Scanner scan;

    public Board() {

        GUI_Field[] fields = {new GUI_Start(), new GUI_Street("Din mor", "tekst", "Et felt som er meget flot", "5000", Color.RED, Color.BLUE)};
        // GUI_Street [ownerName=null, bgColor=java.awt.Color[r=153,g=153,b=153], fgColor=java.awt.Color[r=0,g=0,b=0], title=<html><center>Bernstorffsvej, subText=Pris:  180, description=Bernstorffsvej]
        this.gui = new GUI(fields);
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
        gui.getUserButtonPressed("It's " + player.name + "'s turn", "Roll");
    }
}
