/** @file UserDriver.java
 @brief User driver specification
 Done by Arnau Pujantell
 */

package test.driver;

import util.Pair;
import domain.User;
import java.util.UUID;

public class UserDriver extends Driver {

    public User currentUser;

    public UserDriver() {
        this.currentUser = null;
    }

    public void start() {
        while(true) {
            this.mainMenu();
        }
    }

    private void mainMenu() {
        String title = (this.currentUser != null ? String.format("Current: %s\n", this.currentUser.getName()) : null);
        switch (Driver.menu(title, "User Driver",
                new Pair<String, String>("1", "Create User"),
                new Pair<String, String>("2", "Get name"),
                new Pair<String, String>("3", "Set name"),
                new Pair<String, String>("4", "Get password"),
                new Pair<String, String>("5", "Set password"),
                new Pair<String, String>("6", "Get state"),
                new Pair<String, String>("7", "Set state"),
                new Pair<String, String>("8", "Get type"),
                new Pair<String, String>("9", "Get ID"),
                new Pair<String, String>("10", "Serialize User to JSON"),
                new Pair<String, String>("11", "Deserialize User from JSON"))) {
            case "1":
                this.createUser();
                break;
            case "2":
                this.getName();
                break;
            case "3":
                this.setName();
                break;
            case "4":
                this.getPassword();
                break;
            case "5":
                this.setPassword();
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
                this.serialize();
                break;
            case "11":
                this.deserialize();
                break;
        }
        Driver.pause();
    }

    private void createUser() {
        System.out.println("Take into account that UUIDs will be randomly generated because typing them in will be a hassle.\n");
        String name = Driver.input("Name?");
        String password = Driver.input("Password?");
        try {
            User user = new User("Default name", "Default password", UUID.randomUUID());
            user.setName(name);
            user.setPassword(password);
            this.currentUser = user;
            System.out.println(String.format("%s created successfully!", this.currentUser.getName()));
        } catch (Exception e) {
            System.out.println(String.format("Oh no! The execution threw an exception (EXPECTED): %s", e.getMessage()));
        }
    }

    private void serialize() {
        if(this.currentUser == null) {
            System.out.println("No current User");
            return;
        }
        System.out.println(String.format("%s's serialized to JSON is: %s", this.currentUser.getName(),
                this.currentUser.serialize().toString(2)));
    }

    private void deserialize() {
        if(this.currentUser == null) {
            System.out.println("No current User");
            return;
        }
        System.out.println(this.currentUser.serialize().toString(2));
        this.currentUser = new User(this.currentUser.serialize());
        System.out.println(String.format("\n%s's deserialized from the above JSON successfully!\n",
                this.currentUser.getName()));
        System.out.println(String.format("name:\t\t\t%s", this.currentUser.getName()));
        System.out.println(String.format("id:\t\t\t%s", this.currentUser.getID()));
        System.out.println(String.format("password:\t\t%s", this.currentUser.getPassword()));
        System.out.println(String.format("is_deleted:\t\t%s", this.currentUser.getIsDeleted()));
    }

    private void getName() {
        if(this.currentUser == null) {
            System.out.println("No current user!");
            return;
        }
        System.out.println(String.format("%s's name is: %s", this.currentUser.getName(), this.currentUser.getName()));
    }

    private void getPassword() {
        if(this.currentUser == null) {
            System.out.println("No current user!");
            return;
        }
        System.out.println(String.format("%s's password is: %s", this.currentUser.getName(), this.currentUser.getPassword()));
    }

    private void getIsDeleted() {
        if(this.currentUser == null) {
            System.out.println("No current user!");
            return;
        }
        System.out.print(String.format("%s's state is: ", this.currentUser.getName()));
        if(this.currentUser.getIsDeleted())
            System.out.println("DELETED");
        else
            System.out.println("ACTIVE");
    }

    private void getType() {
        System.out.println("User's type attribute was removed because of professor's feedback. However, this option is still here to have backwards compatibility with old delivered documents.");
    }

    private void getID() {
        if(this.currentUser == null) {
            System.out.println("No current user!");
            return;
        }
        System.out.println(String.format("%s's ID is: %s", this.currentUser.getName(), this.currentUser.getID()));
    }

    private void setName() {
        if(this.currentUser == null) {
            System.out.println("No current user!");
            return;
        }
        try {
            this.currentUser.setName(Driver.input("New name?"));
            System.out.println(String.format("%s name changed successfully!", this.currentUser.getName()));
        } catch (Exception e) {
            System.out.println(String.format("Oh no! The execution threw an exception (EXPECTED): %s", e.getMessage()));
        }
    }

    private void setPassword() {
        if(this.currentUser == null) {
            System.out.println("No current user!");
            return;
        }
        try {
            this.currentUser.setPassword(Driver.input("New password?"));
            System.out.println(String.format("%s password changed successfully!", this.currentUser.getName()));
        } catch (Exception e) {
            System.out.println(String.format("Oh no! The execution threw an exception (EXPECTED): %s", e.getMessage()));
        }
    }

    private void setIsDeleted() {
        if(this.currentUser == null) {
            System.out.println("No current user!");
            return;
        }
        if(Driver.inputBool("Do you want to delete the current user?")) {
            this.currentUser.setIsDeleted(true);
            System.out.println(String.format("%s's state has changed to DELETED!", this.currentUser.getName()));
        }
        else {
            System.out.println(String.format("%s's state has not changed!", this.currentUser.getName()));
        }
    }
}
