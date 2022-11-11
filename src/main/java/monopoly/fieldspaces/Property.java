package monopoly.fieldspaces;

import gui_main.GUI;
import monopoly.Player;

public class Property extends FieldSpace {

    String name;
    String subText;
    String title;
    int rent;
    public Property(String name, String subText, String tilte, String rent) {
        this.name = name;
        this.subText = subText;
        this.title = tilte;
        this.rent = Integer.parseInt(rent);
    }

    public void buy(Player player, GUI gui) {
        String purchase = gui.getUserButtonPressed("Would you like to purchase " + this.name + " for " + this.rent + " ̶M̶?", "Yes", "No");
        if (player.getBalance() >= this.rent)
            if (purchase.equals("Yes")) {
                player.addBalance(-this.rent);
                player.getCar().getPosition().setBackGroundColor(player.getPrimaryColor());
            }
        else {
            gui.getUserButtonPressed("You have insufficient funds to purchase this property", "Continue...");
            }
    }

}
