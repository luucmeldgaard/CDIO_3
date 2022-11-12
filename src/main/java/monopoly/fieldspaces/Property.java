package monopoly.fieldspaces;

import gui_main.GUI;
import monopoly.Player;

public class Property extends FieldSpace {

    String name;
    String subText;
    String title;
    int rent;
    Player owner;
    public Property(String name, String subText, String tilte, String rent) {
        this.name = name;
        this.subText = subText;
        this.title = tilte;
        this.rent = Integer.parseInt(rent);
        this.owner = null;
    }

    public void landOn(Player player, GUI gui, String field) {
        if (this.owner != null) {
            if (!this.owner.getName().equals(player.getName())) {
                String payToPlayer = gui.getUserButtonPressed("this field is owned by " + this.owner.getName() + ". Pay " + this.rent + "for your stay!");
                player.addBalance(-this.rent);
                this.owner.addBalance(this.rent);
            }
        }
        else {
            String choice = gui.getUserButtonPressed(field, "Buy", "Continue");
            if (choice.equals("Buy")) {buy(player, gui); }
            }
    }

    public void buy(Player player, GUI gui) {
        System.out.println(this.name + "________________________" + this.rent);
        String purchase = gui.getUserButtonPressed("Purchase " + this.name + " for " + this.rent, "Yes", "No");
        if (player.getBalance() >= this.rent)
            if (purchase.equals("Yes")) {
                player.addBalance(-this.rent);
                player.setOwnedStatus(this.name);
                this.owner = player;
                player.getCar().getPosition().setBackGroundColor(player.getPrimaryColor());
            }
        else {
            gui.getUserButtonPressed("You have insufficient funds to purchase this property", "Continue...");
            }
    }

}
