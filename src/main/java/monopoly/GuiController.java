package monopoly;

import gui_main.GUI;

public class GuiController {

    //Board board = new Board();
    public static GUI gui;
    static GUIBoard fields;


    public GuiController(){
        fields = new GUIBoard();
        gui = new GUI(fields.setup());
    }

    protected static String buttonRequest(String msg, String a, String b, String c){
        return gui.getUserButtonPressed(msg, a, b, c);
    }


}
