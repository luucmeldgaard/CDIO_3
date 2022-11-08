package monopoly;

import gui_fields.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class CreateBoard {

    public static int numFields = 24;

    public CreateBoard() {

    }

    public GUI_Field[] setup() {

        String[][] fieldList = new String[numFields][4];

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

            fieldList[currentLine] = infoField;
            currentLine += 1;
        }
        read.close();

        System.out.println(Arrays.deepToString(fieldList));

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

}
