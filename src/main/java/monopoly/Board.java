package monopoly;


import gui_fields.*;
import gui_main.GUI;
import monopoly.fieldspaces.FieldSpace;
import monopoly.fieldspaces.Property;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Board {

    public static int numFields = 24;
    String[][] fieldList;

    Property[] properties;
    int startBonus;

    public Board() {
        this.fieldList = new String[numFields][4];
        this.properties = new Property[numFields];
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
        GUI.setNull_fields_allowed(false);
        GUI_Field[] fields = new GUI_Field[numFields];

        boolean firstJail = false;
        for (int i = 0; i < fields.length; i++) {
            if (fieldList[i][0].equals("START")) {
                System.out.println("START");
                fields[i] = new GUI_Start("START", "", "Field", Color.LIGHT_GRAY, Color.red);
            } else if (fieldList[i][0].equals("JAIL") || fieldList[i][0].equals("GOTOJAIL")) {
                System.out.println("JAIL");
                if (!firstJail) {
                    fields[i] = new GUI_Jail("default", "JAIL", "", "Field", Color.GRAY, Color.WHITE);
                    firstJail = true;
                }
                else {
                    fields[i] = new GUI_Jail("default", "GO TO JAIL", "", "Field", Color.GRAY, Color.WHITE);
                }
            } else if (fieldList[i][0].equals("REFUGE")) {
                System.out.println("REFUGE");
                fields[i] = new GUI_Refuge("hest.png", "Antons numse", "", "Field", Color.GRAY, Color.WHITE);
            } else if (fieldList[i][0].equals("CHANCE")) {
                System.out.println("CHANCE");
                fields[i] = new GUI_Chance("?", "Chance", "Field", Color.GRAY, Color.WHITE);
            } else {
                fields[i] = new GUI_Street(fieldList[i][0], fieldList[i][1], fieldList[i][2], fieldList[i][3], Color.DARK_GRAY, Color.WHITE);
                this.properties[i] = new Property(fieldList[i][0], fieldList[i][1], fieldList[i][2], fieldList[i][3]);
            }
        }
        return fields;
    }
    public String getField(int position) {
        return fieldList[position][0];
    }

    public void displayFieldActions(GUI gui, Player player, String field) {
        System.out.println(gui + "\n" + player + "\n" + field);
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
                    //player.setJailedStatus(false);
                    gui.getUserButtonPressed("Alright, that works", "Continue");
                }
                else {
                    // TODO Player has lost
                    String choice = gui.getUserButtonPressed("You are broke!", "Continue...");
                    System.out.println(choice);
                }
            }
            else {
                gui.getUserButtonPressed("You are visiting", "Continue...");
            }


        }
        else if (field.equals("GOTOJAIL")) {
            //player.setJailedStatus(true);
            String choice = gui.getUserButtonPressed("You have been bad!", "Go to Jail");
            for (int i = 0; i < fieldList.length; i++) {
                if (fieldList[i][0].equals("JAIL")) {
                    player.setPosition(i);
                    }
                }
            }
        else if (field.equals("REFUGE")) {
            String choice = gui.getUserButtonPressed(field, "Chill");
            System.out.println(choice);
        }
        else {
            this.properties[player.getPosition()].landOn(player, gui, field);
            // TODO if the player owns all the properties of the same color, they also gain an option to Build
        }

    }
    /*public void fieldAction(String field, String action) {
        if
    }*/

}
