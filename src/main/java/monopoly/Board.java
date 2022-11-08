package monopoly;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import gui_fields.*;
import gui_main.GUI;

import java.util.Arrays;
import java.util.Scanner;
import java.awt.Color;
import java.io.IOException;

public class Board {

    GUI gui;
    ArrayList<Player> players;
    GUI_Field field;
    Scanner scan;



    public Board() {


        String[][] fieldList = new String[24][4];

        try {
            File fieldFile = new File("fields.txt");
            Scanner read = new Scanner(fieldFile);
            int currentLine = 0;
            while (read.hasNextLine()) {
                String field = read.nextLine();
                //System.out.println(field);
                String[] infoField = new String[4];
                Arrays.fill(infoField, "");

                int currentFieldInfo = 0;
                for (int i = 0; i < field.length(); i++) {
                    char letter = field.charAt(i);
                    if (letter == ',') {
                        //System.out.println("Found new info for field" + i);
                        currentFieldInfo += 1;
                        i += 1;
                    }

                    else {
                        infoField[currentFieldInfo] += letter;
                    }

                    }

                fieldList[currentLine] = infoField;
                currentLine += 1;
                }
            read.close();
            }
        catch (FileNotFoundException e) {
            System.out.println("Error");
        }

        System.out.println(Arrays.deepToString(fieldList));

        GUI_Field[] fields = new GUI_Field[16];

        for (int i = 0; i < fields.length; i++) {
            if (fieldList[i][0].equals("START")) {
                System.out.println("START");
                fields[i] = new GUI_Start();
            }

            else {
                fields[i] = new GUI_Street(fieldList[i][0], fieldList[i][1], fieldList[i][2], fieldList[i][3], Color.BLUE, Color.RED);
            }
        }
        //Lars Tyndskid, tekst, Et felt som er meget flot, 5000

        // GUI_Street [ownerName=null, bgColor=java.awt.Color[r=153,g=153,b=153], fgColor=java.awt.Color[r=0,g=0,b=0], title=<html><center>Bernstorffsvej, subText=Pris:  180, description=Bernstorffsvej]
        this.gui = new GUI(fields);
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
                gui.getUserButtonPressed("It's " + player.name + "'s turn", "Roll");
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
    }
}
