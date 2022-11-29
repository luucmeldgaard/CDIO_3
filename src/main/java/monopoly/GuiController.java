package monopoly;

import gui_main.GUI;

public class GuiController {

    public static GUI gui;
    static GUIBoard fields;


    public GuiController(){
        fields = new GUIBoard();
        gui = new GUI(fields.setup());
    }

}
