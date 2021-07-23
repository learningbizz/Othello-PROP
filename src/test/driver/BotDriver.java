/** @file BotDriver.java
 @brief Bot driver specification
 Done by Arnau Pujantell
 */


package test.driver;

import util.Pair;
import domain.Bot;
import java.util.UUID;

public class BotDriver extends Driver {

    public Bot currentBot;

    public BotDriver() {
        this.currentBot = null;
    }

    public void start() {
        while(true) {
            this.mainMenu();
        }
    }

    private void mainMenu() {
        String title = (this.currentBot != null ? String.format("Current: %s\n", this.currentBot.getName()) : null);
        switch (Driver.menu(title, "Bot Driver",
                new Pair<String, String>("1", "Create Bot"),
                new Pair<String, String>("2", "Get name"),
                new Pair<String, String>("3", "Set name"),
                new Pair<String, String>("4", "Get difficulty"),
                new Pair<String, String>("5", "Set difficulty"),
                new Pair<String, String>("6", "Get state"),
                new Pair<String, String>("7", "Set state"),
                new Pair<String, String>("8", "Get type"),
                new Pair<String, String>("9", "Get ID"),
                new Pair<String, String>("10", "Get creatorID"),
                new Pair<String, String>("11", "Serialize Bot to JSON"),
                new Pair<String, String>("12", "Deserialize Bot from JSON"))) {
            case "1":
                this.createBot();
                break;
            case "2":
                this.getName();
                break;
            case "3":
                this.setName();
                break;
            case "4":
                this.getDifficulty();
                break;
            case "5":
                this.setDifficulty();
                break;
            case "6":
                this.getIsDeleted();
                break;
            case "7":
                this.setIsDeleted();
                break;
            case "8":
                this.getType();
                break;
            case "9":
                this.getID();
                break;
            case "10":
                this.getCreatorID();
                break;
            case "11":
                this.serialize();
                break;
            case "12":
                this.deserialize();
                break;

        }
        Driver.pause();
    }

    private void createBot() {
        System.out.println("Take into account that UUIDs will be randomly generated because typing them in will be a hassle.\n");
        String name = Driver.input("Name?");
        Integer difficulty = Driver.inputInt("Difficulty? (From 1 to 10)");
        try {
            Bot bot = new Bot("Default name", 0, UUID.randomUUID(), UUID.randomUUID());
            bot.setName(name);
            bot.setDifficulty(difficulty);
            this.currentBot = bot;
            System.out.println(String.format("%s created successfully!", this.currentBot.getName()));
        } catch (Exception e) {
            System.out.println(String.format("Oh no! The execution threw an exception (EXPECTED): %s", e.getMessage()));
        }
    }

    private void serialize() {
        if(this.currentBot == null) {
            System.out.println("No current Bot");
            return;
        }
        System.out.println(String.format("%s's serialized to JSON is: %s", this.currentBot.getName(),
                this.currentBot.serialize().toString(2)));
    }


    private void deserialize() {
        if(this.currentBot == null) {
            System.out.println("No current Bot");
            return;
        }
        System.out.println(this.currentBot.serialize().toString(2));
        this.currentBot = new Bot(this.currentBot.serialize());
        System.out.println(String.format("\n%s's deserialized from the above JSON successfully!\n",
                this.currentBot.getName()));
        System.out.println(String.format("name:\t\t\t%s", this.currentBot.getName()));
        System.out.println(String.format("id:\t\t\t%s", this.currentBot.getID()));
        System.out.println(String.format("difficulty:\t\t%s", this.currentBot.getDifficulty()));
        System.out.println(String.format("is_deleted:\t\t%s", this.currentBot.getIsDeleted()));
        System.out.println(String.format("creator_id:\t\t%s", this.currentBot.getCreatorID()));

    }

    private void getName() {
        if(this.currentBot == null) {
            System.out.println("No current bot!");
            return;
        }
        System.out.println(String.format("%s's bot name is: %s", this.currentBot.getName(), this.currentBot.getName()));
    }

    private void getDifficulty() {
        if(this.currentBot == null) {
            System.out.println("No current bot!");
            return;
        }
        System.out.println(String.format("%s's difficulty is: %s", this.currentBot.getName(), this.currentBot.getDifficulty()));
    }

    private void getIsDeleted() {
        if(this.currentBot == null) {
            System.out.println("No current bot!");
            return;
        }
        System.out.print(String.format("%s's state is: ", this.currentBot.getName()));
        if(this.currentBot.getIsDeleted())
            System.out.println("DELETED");
        else
            System.out.println("ACTIVE");
    }

    private void getType() {
        System.out.println("Bot's type attribute was removed because of professor's feedback. However, this option is still here to have backwards compatibility with old delivered documents.");
    }

    private void getID() {
        if(this.currentBot == null) {
            System.out.println("No current bot!");
            return;
        }
        System.out.println(String.format("%s's ID is: %s", this.currentBot.getName(), this.currentBot.getID()));
    }

    private void getCreatorID() {
        if(this.currentBot == null) {
            System.out.println("No current bot!");
            return;
        }
        System.out.println(String.format("%s's CreatorID is: %s", this.currentBot.getName(), this.currentBot.getCreatorID()));

    }

    private void setName() {
        if (this.currentBot == null) {
            System.out.println("No current bot!");
            return;
        }
        try {
            this.currentBot.setName(Driver.input("New name?"));
            System.out.println(String.format("%s name changed successfully!", this.currentBot.getName()));
        } catch (Exception e) {
            System.out.println(String.format("Oh no! The execution threw an exception (EXPECTED): %s", e.getMessage()));
        }
    }

    private void setDifficulty() {
        if (this.currentBot == null) {
            System.out.println("No current bot!");
            return;
        }
        try {
            this.currentBot.setDifficulty(Driver.inputInt("Choose a difficulty level from 1 to 10"));
            System.out.println(String.format("%s's difficulty has been changed successfully!", this.currentBot.getName()));
        } catch (Exception e) {
            System.out.println(String.format("Oh no! The execution threw an exception (EXPECTED): %s", e.getMessage()));
            setDifficulty();
        }
    }

    private void setIsDeleted() {
        if(this.currentBot == null) {
            System.out.println("No current bot!");
            return;
        }
        if(Driver.inputBool("Do you want to delete the current bot?")) {
            this.currentBot.setIsDeleted(true);
            System.out.println(String.format("%s's state has changed to DELETED!", this.currentBot.getName()));
        }
        else {
            System.out.println(String.format("%s's state has not changed!", this.currentBot.getName()));
        }
    }
}
