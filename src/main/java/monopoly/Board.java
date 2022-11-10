package monopoly;

import gui_fields.*;
import gui_main.GUI;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Board {

    public static int numFields = 24;
    String[][] fieldList;

    FieldSpace fieldSpaces;
    int startBonus;

    public Board() {
        this.fieldList = new String[numFields][4];
        this.fieldSpaces = new FieldSpace();
        this.startBonus = 2;
    }

    public GUI_Field[] setup() {

        this.fieldList = new String[numFields][4];

        File fieldFile = new File("fields.txt");
        Scanner read = null;
        try {
            read = new Scanner(fieldFile);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
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
                } else {
                    infoField[currentFieldInfo] += letter;
                }

            }

            this.fieldList[currentLine] = infoField;
            currentLine += 1;
        }
        read.close();

        System.out.println(Arrays.deepToString(this.fieldList));

        GUI_Field[] fields = new GUI_Field[numFields];

        for (int i = 0; i < fields.length; i++) {
            if (fieldList[i][0].equals("START")) {
                System.out.println("START");
                fields[i] = new GUI_Start("START", "", "Field", Color.BLUE, Color.RED);
            } else if (fieldList[i][0].equals("JAIL")) {
                System.out.println("JAIL");
                fields[i] = new GUI_Jail("default", "JAIL", "", "Field", Color.BLUE, Color.RED);
            } else if (fieldList[i][0].equals("REFUGE")) {
                System.out.println("REFUGE");
                fields[i] = new GUI_Refuge("hest.png", "Antons numse", "", "Field", Color.BLUE, Color.RED);
            } else if (fieldList[i][0].equals("CHANCE")) {
                System.out.println("CHANCE");
                fields[i] = new GUI_Chance("?", "Chance", "Field", Color.BLUE, Color.RED);
            } else {
                fields[i] = new GUI_Street(fieldList[i][0], fieldList[i][1], fieldList[i][2], fieldList[i][3], Color.BLUE, Color.RED);
            }
        }
        return fields;
    }

    public String getField(int position) {
        return fieldList[position][0];
    }

    public void displayFieldActions(GUI gui, Player player, String field) {

        if (field.equals("START")) {
            player.addBalance(2);
            String choice = gui.getUserButtonPressed(field + "! Your balance is now: " + player.getBalance() + " ̶M̶", "Keep Grindin'");
            System.out.println(choice);
        }
        else if (field.equals("CHANCE")) {
            String choice = gui.getUserButtonPressed(field, "Pick card");
            System.out.println(choice);
        }
        else if (field.equals("JAIL")) {
            if (player.getJailedStatus()) {
                if (player.getBalance() >= 500) {
                    String choice = gui.getUserButtonPressed("You will never get out!", "Pay ransom");
                    player.addBalance(-500);
                    player.setJailedStatus(false);
                    gui.getUserButtonPressed("Alright, that works", "Continue");
                } else {
                    // TODO Player has lost
                    String choice = gui.getUserButtonPressed("You are broke!", "Continue...");
                    System.out.println(choice);
                }
            }
            else {
                player.setJailedStatus(true);
                String choice = gui.getUserButtonPressed("You have been bad!", "Go to Jail");
            }
        }
        else if (field.equals("REFUGE")) {
            String choice = gui.getUserButtonPressed(field, "Chill");
            System.out.println(choice);
        }
        else {
            String choice = gui.getUserButtonPressed(field, "Buy", "Continue");
            // TODO if the player owns all the properties of the same color, they also gain an option to Build
            System.out.println(choice);
        }

    }

    /*public void fieldAction(String field, String action) {
        if
    }*/

}
