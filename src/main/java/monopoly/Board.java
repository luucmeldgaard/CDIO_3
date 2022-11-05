package monopoly;

import java.util.ArrayList;
import gui_fields.GUI_Player;
import gui_main.GUI;

public class Board {

    public Board() {
        GUI gui = new GUI();

        int numPlayers = Integer.parseInt(gui.getUserSelection("Number of players", "1", "2", "3", "4"));

        ArrayList<GUI_Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            String name = gui.getUserString("Player " + i + ", please enter you name: ", 2, 12, true);
            System.out.println(name);
            players.add(new GUI_Player(name,1000));
        }
        for (GUI_Player p:players) {
            gui.addPlayer(p);
        }
    }

}
