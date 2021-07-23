/**
 * @file PairDriver.java
 * @author Alex Rodriguez
 * @brief PairDriver class specification.
 */
package test.driver;

import util.Pair;

/**
 * @class PairDriver
 * @brief Implements the different options for the Pair driver application.
 * By Alex Rodriguez.
 */
public class PairDriver extends Driver {
    /* ATTRIBUTES */

    public Pair<String, String> currentStrPair;
    public Pair<Integer, Integer> currentIntPair;
    public Pair<String, Integer> currentStrIntPair;

    /* CONSTRUCTORS */

    public PairDriver() {
        this.currentStrPair = null;
        this.currentIntPair = null;
        this.currentStrIntPair = null;
    }

    /* METHODS */

    public void start() {
        while (true) {
            this.mainMenu();
        }
    }

    private void mainMenu() {
        String title = (this.currentPair() != null ? String.format("Current: %s\n", this.currentPair()) : null);
        switch (Driver.menu(title, "Pair Driver",
                new Pair<String, String>("1", "Create String:String Pair"),
                new Pair<String, String>("2", "Create Integer:Integer Pair"),
                new Pair<String, String>("3", "Create String:Integer Pair"),
                new Pair<String, String>("4", "Get first"),
                new Pair<String, String>("5", "Get second"),
                new Pair<String, String>("6", "Compare current Pair with another"))) {
        case "1":
            this.createStrPair();
            break;
        case "2":
            this.createIntPair();
            break;
        case "3":
            this.createStrIntPair();
            break;
        case "4":
            this.getFirst();
            break;
        case "5":
            this.getSecond();
            break;
        case "6":
            this.comparePair();
            break;
        }
        Driver.pause();
    }

    private Object currentPair() {
        if (this.currentStrPair != null)
            return this.currentStrPair;

        if (this.currentIntPair != null)
            return this.currentIntPair;

        if (this.currentStrIntPair != null)
            return this.currentStrIntPair;

        return null;
    }

    private void resetPairs() {
        this.currentStrPair = null;
        this.currentIntPair = null;
        this.currentStrIntPair = null;
    }

    private void createStrPair() {
        this.resetPairs();
        this.currentStrPair = new Pair<String, String>(Driver.input("First value?"), Driver.input("Second value?"));
        System.out.println(String.format("%s created successfully!", this.currentStrPair));
    }

    private void createIntPair() {
        this.resetPairs();
        this.currentIntPair = new Pair<Integer, Integer>(Driver.inputInt("First value?"),
                Driver.inputInt("Second value?"));
        System.out.println(String.format("%s created successfully!", this.currentIntPair));
    }

    private void createStrIntPair() {
        this.resetPairs();
        this.currentStrIntPair = new Pair<String, Integer>(Driver.input("First value?"),
                Driver.inputInt("Second value?"));
        System.out.println(String.format("%s created successfully!", this.currentStrIntPair));
    }

    private void getFirst() {
        if (this.currentPair() == null) {
            System.out.println("No current Pair!");
            return;
        }

        System.out.print(String.format("%s's first is: ", this.currentPair()));

        if (this.currentStrPair != null)
            System.out.println(this.currentStrPair.first);

        if (this.currentIntPair != null)
            System.out.println(this.currentIntPair.first);

        if (this.currentStrIntPair != null)
            System.out.println(this.currentStrIntPair.first);
    }

    private void getSecond() {
        if (this.currentPair() == null) {
            System.out.println("No current Pair!");
            return;
        }

        System.out.print(String.format("%s's second is: ", this.currentPair()));

        if (this.currentStrPair != null)
            System.out.println(this.currentStrPair.second);

        if (this.currentIntPair != null)
            System.out.println(this.currentIntPair.second);

        if (this.currentStrIntPair != null)
            System.out.println(this.currentStrIntPair.second);
    }

    private void comparePair() {
        if (this.currentPair() == null) {
            System.out.println("No current Pair!");
            return;
        }

        if (this.currentStrPair != null) {
            Pair<String, String> toCompare = new Pair<String, String>(Driver.input("First value of Pair to compare?"),
                    Driver.input("Second value of Pair to compare?"));
            if (this.currentStrPair.equals(toCompare))
                System.out.println(String.format("%s and %s are equal", this.currentStrPair, toCompare));
            else
                System.out.println(String.format("%s and %s are not equal", this.currentStrPair, toCompare));
        }

        if (this.currentIntPair != null) {
            Pair<Integer, Integer> toCompare = new Pair<Integer, Integer>(
                    Driver.inputInt("First value of Pair to compare?"),
                    Driver.inputInt("Second value of Pair to compare?"));
            if (this.currentIntPair.equals(toCompare))
                System.out.println(String.format("%s and %s are equal", this.currentIntPair, toCompare));
            else
                System.out.println(String.format("%s and %s are not equal", this.currentIntPair, toCompare));
        }

        if (this.currentStrIntPair != null) {
            Pair<String, Integer> toCompare = new Pair<String, Integer>(Driver.input("First value of Pair to compare?"),
                    Driver.inputInt("Second value of Pair to compare?"));
            if (this.currentStrIntPair.equals(toCompare))
                System.out.println(String.format("%s and %s are equal", this.currentStrIntPair, toCompare));
            else
                System.out.println(String.format("%s and %s are not equal", this.currentStrIntPair, toCompare));
        }
    }
}
