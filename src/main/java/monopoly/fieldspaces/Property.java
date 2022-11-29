package monopoly.fieldspaces;

import monopoly.GuiController;
import monopoly.Player;

public class Property extends FieldSpace {

    String name;
    String subText;
    String title;
    int rent;
    Player owner;
    public Property(String name, String subText, String tilte, String rent) {
        this.name = name;
        this.title = tilte;
        this.subText = subText;
        this.rent = Integer.parseInt(rent);
        this.owner = null;
    }

    public void landOn(Player player, String field) {
        if (this.owner != null) {
            System.out.println("Owner name: " + this.owner.getName());
            System.out.println(!this.owner.getName().equals(player.getName()));
            if (!this.owner.getName().equals(player.getName())) {
                GuiController.gui.getUserButtonPressed("this field is owned by " + this.owner.getName() + ". Pay " + this.rent + " for your stay!", "Pay " + this.owner.getName());
                player.addBalance(-this.rent);
                this.owner.addBalance(this.rent);
            }
        }
        else {
            String choice = GuiController.gui.getUserButtonPressed(field, "Buy", "Continue");
            if (choice.equals("Buy")) {buy(player); }
            }
    }

    public void buy(Player player) {
        String purchase = GuiController.gui.getUserButtonPressed("Purchase " + this.name + " for " + this.rent, "Yes", "No");
        if (player.getBalance() >= this.rent) {
            if (purchase.equals("Yes") && player.getBalance() >= this.rent) {
                player.addBalance(-this.rent);
                this.owner = player;
                player.getCar().getPosition().setBackGroundColor(player.getPrimaryColor()); }
            else {GuiController.gui.getUserButtonPressed("You have insufficient funds to purchase this property", "Continue..."); }
        }
    }
}
