package monopoly;

import gui_main.GUI;

public class GuiController {

    public static GUI gui;
    static GUIBoard fields;


    public GuiController(){
        fields = new GUIBoard();
        gui = new GUI(fields.setup());
    }

    protected static String buttonRequest(String c){
        return gui.getUserButtonPressed("Number of players", "2", "3", c);
    }


}
