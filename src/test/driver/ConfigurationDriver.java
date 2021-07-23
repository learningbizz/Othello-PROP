/**
 * @file ConfigurationDriver.java
 * @author Alex Rodriguez
 * @brief ConfigurationDriver class specification.
 */
package test.driver;

import java.util.UUID;

import domain.Configuration;
import util.Pair;

/**
 * @class ConfigurationDriver
 * @brief Implements the different options for the Configuration driver application.
 * By Alex Rodriguez.
 */
public class ConfigurationDriver extends Driver {
    /* ATTRIBUTES */

    public Configuration currentConfiguration;

    /* CONSTRUCTORS */

    public ConfigurationDriver() {
        this.currentConfiguration = null;
    }

    /* METHODS */

    public void start() {
        while (true) {
            this.mainMenu();
        }
    }

    private void mainMenu() {
        String title = (this.currentConfiguration != null
                ? String.format("Current: %s\n", this.currentConfiguration.getName())
                : null);
        switch (Driver.menu(title, "Configuration Driver",
                new Pair<String, String>("1", "Create Configuration"),
                new Pair<String, String>("2", "Get name"),
                new Pair<String, String>("3", "Set name"),
                new Pair<String, String>("4", "Get creatorID"),
                new Pair<String, String>("5", "Get canEatHorizontally"),
                new Pair<String, String>("6", "Set canEatHorizontally"),
                new Pair<String, String>("7", "Get canEatVertically"),
                new Pair<String, String>("8", "Set canEatVertically"),
                new Pair<String, String>("9", "Get canEatDiagonally"),
                new Pair<String, String>("10", "Set canEatDiagonally"),
                new Pair<String, String>("11", "Serialize to JSON"),
                new Pair<String, String>("12", "Deserialize from JSON"))) {
        case "1":
            this.create();
            break;
        case "2":
            this.getName();
            break;
        case "3":
            this.setName();
            break;
        case "4":
            this.getCreatorID();
            break;
        case "5":
            this.getCanEatHorizontally();
            break;
        case "6":
            this.setCanEatHorizontally();
            break;
        case "7":
            this.getCanEatVertically();
            break;
        case "8":
            this.setCanEatVertically();
            break;
        case "9":
            this.getCanEatDiagonally();
            break;
        case "10":
            this.setCanEatDiagonally();
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

    private void create() {
        System.out.println(
                "Take into account that UUIDs will be randomly generated because typing them in will be a hassle.\n");
        String name = Driver.input("Name?");
        boolean canEatHorizontally = Driver.inputBool("Can eat horizontally?");
        boolean canEatVertically = Driver.inputBool("Can eat vertically?");
        boolean canEatDiagonally = Driver.inputBool("Can eat diagonally?");
        try {
            Configuration configuration = new Configuration("Default name", UUID.randomUUID(), true, true, true);
            configuration.setName(name);
            configuration.setCanEatHorizontally(canEatHorizontally);
            configuration.setCanEatVertically(canEatVertically);
            configuration.setCanEatDiagonally(canEatDiagonally);
            this.currentConfiguration = configuration;
            System.out.println(String.format("%s created successfully!", this.currentConfiguration.getName()));
        } catch (Exception e) {
            System.out.println(String.format("Oh no! The execution threw an exception (EXPECTED): %s", e.getMessage()));
        }
    }

    private void getName() {
        if (this.currentConfiguration == null) {
            System.out.println("No current Configuration!");
            return;
        }

        System.out.println(String.format("%s's name is: %s", this.currentConfiguration.getName(),
                this.currentConfiguration.getName()));
    }

    private void setName() {
        if (this.currentConfiguration == null) {
            System.out.println("No current Configuration!");
            return;
        }

        try {
            this.currentConfiguration.setName(Driver.input("Name?"));
            System.out.println(String.format("%s's name changed successfully!", this.currentConfiguration.getName()));
        } catch (Exception e) {
            System.out.println(String.format("Oh no! The execution threw an exception (EXPECTED): %s", e.getMessage()));
        }
    }

    private void getCreatorID() {
        if (this.currentConfiguration == null) {
            System.out.println("No current Configuration!");
            return;
        }

        System.out.println(String.format("%s's creatorID is: %s", this.currentConfiguration.getName(),
                this.currentConfiguration.getCreatorID()));
    }

    private void getCanEatHorizontally() {
        if (this.currentConfiguration == null) {
            System.out.println("No current Configuration!");
            return;
        }

        System.out.println(String.format("%s's canEatHorizontally is: %s", this.currentConfiguration.getName(),
                this.currentConfiguration.getCanEatHorizontally()));
    }

    private void setCanEatHorizontally() {
        if (this.currentConfiguration == null) {
            System.out.println("No current Configuration!");
            return;
        }

        try {
            this.currentConfiguration.setCanEatHorizontally(Driver.inputBool("Can eat horizontally?"));
            System.out.println(String.format("%s's canEatHorizontally changed successfully!",
                    this.currentConfiguration.getName()));
        } catch (Exception e) {
            System.out.println(String.format("Oh no! The execution threw an exception (EXPECTED): %s", e.getMessage()));
        }
    }

    private void getCanEatVertically() {
        if (this.currentConfiguration == null) {
            System.out.println("No current Configuration!");
            return;
        }

        System.out.println(String.format("%s's canEatVertically is: %s", this.currentConfiguration.getName(),
                this.currentConfiguration.getCanEatVertically()));
    }

    private void setCanEatVertically() {
        if (this.currentConfiguration == null) {
            System.out.println("No current Configuration!");
            return;
        }

        try {
            this.currentConfiguration.setCanEatVertically(Driver.inputBool("Can eat vertically?"));
            System.out.println(
                    String.format("%s's canEatVertically changed successfully!", this.currentConfiguration.getName()));
        } catch (Exception e) {
            System.out.println(String.format("Oh no! The execution threw an exception (EXPECTED): %s", e.getMessage()));
        }
    }

    private void getCanEatDiagonally() {
        if (this.currentConfiguration == null) {
            System.out.println("No current Configuration!");
            return;
        }

        System.out.println(String.format("%s's canEatDiagonally is: %s", this.currentConfiguration.getName(),
                this.currentConfiguration.getCanEatDiagonally()));
    }

    private void setCanEatDiagonally() {
        if (this.currentConfiguration == null) {
            System.out.println("No current Configuration!");
            return;
        }

        try {
            this.currentConfiguration.setCanEatDiagonally(Driver.inputBool("Can eat diagonally?"));
            System.out.println(
                    String.format("%s's canEatDiagonally changed successfully!", this.currentConfiguration.getName()));
        } catch (Exception e) {
            System.out.println(String.format("Oh no! The execution threw an exception (EXPECTED): %s", e.getMessage()));
        }
    }

    private void serialize() {
        if (this.currentConfiguration == null) {
            System.out.println("No current Configuration!");
            return;
        }

        System.out.println(String.format("%s's serialized to JSON is: %s", this.currentConfiguration.getName(),
                this.currentConfiguration.serialize().toString(2)));
    }

    private void deserialize() {
        if (this.currentConfiguration == null) {
            System.out.println("No current Configuration!");
            return;
        }

        System.out.println(this.currentConfiguration.serialize().toString(2));
        this.currentConfiguration = new Configuration(this.currentConfiguration.serialize());
        System.out.println(String.format("\n%s's deserialized from the above JSON successfully!\n",
                this.currentConfiguration.getName()));
        System.out.println(String.format("name:\t\t\t%s", this.currentConfiguration.getName()));
        System.out.println(String.format("creatorID:\t\t%s", this.currentConfiguration.getCreatorID()));
        System.out.println(String.format("canEatHorizontally:\t%s", this.currentConfiguration.getCanEatHorizontally()));
        System.out.println(String.format("canEatVertically:\t%s", this.currentConfiguration.getCanEatVertically()));
        System.out.println(String.format("canEatDiagonally:\t%s", this.currentConfiguration.getCanEatDiagonally()));
    }
}
