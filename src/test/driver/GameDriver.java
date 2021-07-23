/**
 * @file GameDriver.java
 * @author Alex Rodriguez
 * @brief GameDriver class specification.
 */
package test.driver;

import java.time.format.DateTimeFormatter;
import java.util.UUID;

import domain.Board.PieceType;
import domain.Game;
import domain.Game.GameState;
import util.Pair;

/**
 * @class GameDriver
 * @brief Implements the different options for the Game driver application.
 * By Alex Rodriguez.
 */
public class GameDriver extends Driver {
    /* ATTRIBUTES */

    public Game currentGame;

    /* CONSTRUCTORS */

    public GameDriver() {
        this.currentGame = null;
    }

    /* METHODS */

    public void start() {
        while (true) {
            this.mainMenu();
        }
    }

    private void mainMenu() {
        String title = (this.currentGame != null ? String.format("Current: %s\n", this.currentGame.getName()) : null);
        switch (Driver.menu(title, "Game Driver",
                new Pair<String, String>("1", "Create Game"),
                new Pair<String, String>("2", "Get name"),
                new Pair<String, String>("3", "Set name"),
                new Pair<String, String>("4", "Get player1ID"),
                new Pair<String, String>("5", "Get player2ID"),
                new Pair<String, String>("6", "Get configurationName"),
                new Pair<String, String>("7", "Set configurationName"),
                new Pair<String, String>("8", "Get turn"),
                new Pair<String, String>("9", "Set turn"),
                new Pair<String, String>("10", "Get state"),
                new Pair<String, String>("11", "Set state"),
                new Pair<String, String>("12", "Get winnerID"),
                new Pair<String, String>("13", "Get creatorID"),
                new Pair<String, String>("14", "Get createdAt"),
                new Pair<String, String>("15", "Serialize to JSON"),
                new Pair<String, String>("16", "Deserialize from JSON"),
                new Pair<String, String>("17", "Execute play"),
                new Pair<String, String>("18", "Execute surrender"),
                new Pair<String, String>("19", "Execute finish"),
                new Pair<String, String>("20", "Execute checkPlaceRights"),
                new Pair<String, String>("21", "Execute nextTurn"))) {
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
            this.getPlayer1ID();
            break;
        case "5":
            this.getPlayer2ID();
            break;
        case "6":
            this.getConfigurationName();
            break;
        case "7":
            this.setConfigurationName();
            break;
        case "8":
            this.getTurn();
            break;
        case "9":
            this.setTurn();
            break;
        case "10":
            this.getState();
            break;
        case "11":
            this.setState();
            break;
        case "12":
            this.getWinnerID();
            break;
        case "13":
            this.getCreatorID();
            break;
        case "14":
            this.getCreatedAt();
            break;
        case "15":
            this.serialize();
            break;
        case "16":
            this.deserialize();
            break;
        case "17":
            this.play();
            break;
        case "18":
            this.surrender();
            break;
        case "19":
            this.finish();
            break;
        case "20":
            this.checkPlaceRights();
            break;
        case "21":
            this.nextTurn();
            break;
        }
        Driver.pause();
    }

    private void create() {
        System.out.println(
                "Take into account that UUIDs will be randomly generated because typing them in will be a hassle.\n");
        String name = Driver.input("Name?");
        String configurationName = Driver.input("Configuration name?");
        try {
            Game game = new Game("Default name", UUID.randomUUID(), UUID.randomUUID(), "Default configurationName",
                    UUID.randomUUID());
            game.setName(name);
            game.setConfigurationName(configurationName);
            this.currentGame = game;
            System.out.println(String.format("%s created successfully!", this.currentGame.getName()));
        } catch (Exception e) {
            System.out.println(String.format("Oh no! The execution threw an exception (EXPECTED): %s", e.getMessage()));
        }
    }

    private void getName() {
        if (this.currentGame == null) {
            System.out.println("No current Game!");
            return;
        }

        System.out.println(String.format("%s's name is: %s", this.currentGame.getName(), this.currentGame.getName()));
    }

    private void setName() {
        if (this.currentGame == null) {
            System.out.println("No current Game!");
            return;
        }

        try {
            this.currentGame.setName(Driver.input("Name?"));
            System.out.println(String.format("%s's name changed successfully!", this.currentGame.getName()));
        } catch (Exception e) {
            System.out.println(String.format("Oh no! The execution threw an exception (EXPECTED): %s", e.getMessage()));
        }
    }

    private void getPlayer1ID() {
        if (this.currentGame == null) {
            System.out.println("No current Game!");
            return;
        }

        System.out.println(
                String.format("%s's player1ID is: %s", this.currentGame.getName(), this.currentGame.getPlayer1ID()));
    }

    private void getPlayer2ID() {
        if (this.currentGame == null) {
            System.out.println("No current Game!");
            return;
        }

        System.out.println(
                String.format("%s's player2ID is: %s", this.currentGame.getName(), this.currentGame.getPlayer2ID()));
    }

    private void getConfigurationName() {
        if (this.currentGame == null) {
            System.out.println("No current Game!");
            return;
        }

        System.out.println(String.format("%s's configurationName is: %s", this.currentGame.getName(),
                this.currentGame.getConfigurationName()));
    }

    private void setConfigurationName() {
        if (this.currentGame == null) {
            System.out.println("No current Game!");
            return;
        }

        try {
            this.currentGame.setConfigurationName(Driver.input("Configuration name?"));
            System.out
                    .println(String.format("%s's configurationName changed successfully!", this.currentGame.getName()));
        } catch (Exception e) {
            System.out.println(String.format("Oh no! The execution threw an exception (EXPECTED): %s", e.getMessage()));
        }
    }

    private void getTurn() {
        if (this.currentGame == null) {
            System.out.println("No current Game!");
            return;
        }

        System.out.println(String.format("%s's turn is: %s", this.currentGame.getName(), this.currentGame.getTurn()));
    }

    private void setTurn() {
        if (this.currentGame == null) {
            System.out.println("No current Game!");
            return;
        }

        switch (Driver.menu(null, "Select Turn",
                new Pair<String, String>("1", "PLAYER 1"),
                new Pair<String, String>("2", "PLAYER 2"))) {
        case "1":
            this.currentGame.setTurn(PieceType.PLAYER1);
            break;
        case "2":
            this.currentGame.setTurn(PieceType.PLAYER2);
            break;
        }
        System.out.println(String.format("%s's turn changed successfully!", this.currentGame.getName()));
    }

    private void getState() {
        if (this.currentGame == null) {
            System.out.println("No current Game!");
            return;
        }

        System.out.println(String.format("%s's state is: %s", this.currentGame.getName(), this.currentGame.getState()));
    }

    private void setState() {
        if (this.currentGame == null) {
            System.out.println("No current Game!");
            return;
        }

        switch (Driver.menu(null, "Select State",
                new Pair<String, String>("1", "NOT_STARTED"),
                new Pair<String, String>("2", "IN_PROGRESS"),
                new Pair<String, String>("3", "FINISHED"))) {
        case "1":
            this.currentGame.setState(GameState.NOT_STARTED);
            break;
        case "2":
            this.currentGame.setState(GameState.IN_PROGRESS);
            break;
        case "3":
            this.currentGame.setState(GameState.FINISHED);
            break;
        }
        System.out.println(String.format("%s's state changed successfully!", this.currentGame.getName()));
    }

    private void getWinnerID() {
        if (this.currentGame == null) {
            System.out.println("No current Game!");
            return;
        }

        System.out.println(
                String.format("%s's winnerID is: %s", this.currentGame.getName(), this.currentGame.getWinnerID()));
    }

    private void getCreatorID() {
        if (this.currentGame == null) {
            System.out.println("No current Game!");
            return;
        }

        System.out.println(
                String.format("%s's creatorID is: %s", this.currentGame.getName(), this.currentGame.getCreatorID()));
    }

    private void getCreatedAt() {
        if (this.currentGame == null) {
            System.out.println("No current Game!");
            return;
        }

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println(String.format("%s's createdAt is: %s", this.currentGame.getName(),
                this.currentGame.getCreatedAt().format(dateFormat)));
    }

    private void serialize() {
        if (this.currentGame == null) {
            System.out.println("No current Game!");
            return;
        }

        System.out.println(String.format("%s's serialized to JSON is: %s", this.currentGame.getName(),
                this.currentGame.serialize().toString(2)));
    }

    private void deserialize() {
        if (this.currentGame == null) {
            System.out.println("No current Game!");
            return;
        }

        System.out.println(this.currentGame.serialize().toString(2));
        this.currentGame = new Game(this.currentGame.serialize());
        System.out.println(
                String.format("\n%s's deserialized from the above JSON successfully!\n", this.currentGame.getName()));
        System.out.println(String.format("name:\t\t\t%s", this.currentGame.getName()));
        System.out.println(String.format("player1ID:\t\t%s", this.currentGame.getPlayer1ID()));
        System.out.println(String.format("player2ID:\t\t%s", this.currentGame.getPlayer2ID()));
        System.out.println(String.format("configurationName:\t%s", this.currentGame.getConfigurationName()));
        System.out.println(String.format("turn:\t\t\t%s", this.currentGame.getTurn()));
        System.out.println(String.format("state:\t\t\t%s", this.currentGame.getState()));
        System.out.println(String.format("winnerID:\t\t%s", this.currentGame.getWinnerID()));
        System.out.println(String.format("creatorID:\t\t%s", this.currentGame.getCreatorID()));
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println(String.format("createdAt:\t\t%s", this.currentGame.getCreatedAt().format(dateFormat)));
    }

    private void play() {
        if (this.currentGame == null) {
            System.out.println("No current Game!");
            return;
        }

        try {
            this.currentGame.play();
            System.out.println(String.format("The Game state has changed to %s", this.currentGame.getState()));
        } catch (Exception e) {
            System.out.println(String.format("Oh no! The execution threw an exception (EXPECTED): %s", e.getMessage()));
        }
    }

    private void surrender() {
        if (this.currentGame == null) {
            System.out.println("No current Game!");
            return;
        }

        try {
            switch (Driver.menu(null, "Select who surrenders",
                    new Pair<String, String>("1", "PLAYER 1"),
                    new Pair<String, String>("2", "PLAYER 2"))) {
            case "1":
                this.currentGame.surrender(this.currentGame.getPlayer1ID());
                System.out.println("PLAYER 2 has won the Game");
                break;
            case "2":
                this.currentGame.surrender(this.currentGame.getPlayer2ID());
                System.out.println("PLAYER 1 has won the Game");
                break;
            }
            System.out.println(String.format("The Game winnerID has changed to %s", this.currentGame.getWinnerID()));
            System.out.println(String.format("The Game state has changed to %s", this.currentGame.getState()));
        } catch (Exception e) {
            System.out.println(String.format("Oh no! The execution threw an exception (EXPECTED): %s", e.getMessage()));
        }
    }

    private void finish() {
        if (this.currentGame == null) {
            System.out.println("No current Game!");
            return;
        }

        try {
            switch (Driver.menu(null, "Select who wins",
                    new Pair<String, String>("1", "PLAYER 1"),
                    new Pair<String, String>("2", "PLAYER 2"),
                    new Pair<String, String>("3", "DRAW"))) {
            case "1":
                this.currentGame.finish(this.currentGame.getPlayer1ID());
                System.out.println("PLAYER 1 has won the Game");
                break;
            case "2":
                this.currentGame.finish(this.currentGame.getPlayer2ID());
                System.out.println("PLAYER 2 has won the Game");
                break;
            case "3":
                this.currentGame.finish(null);
                System.out.println("The Game has resulted in a draw");
                break;
            }
            System.out.println(String.format("The Game winnerID has changed to %s", this.currentGame.getWinnerID()));
            System.out.println(String.format("The Game state has changed to %s", this.currentGame.getState()));
        } catch (Exception e) {
            System.out.println(String.format("Oh no! The execution threw an exception (EXPECTED): %s", e.getMessage()));
        }
    }

    private void checkPlaceRights() {
        if (this.currentGame == null) {
            System.out.println("No current Game!");
            return;
        }

        UUID playerID = null;
        switch (Driver.menu(null, "Select who places",
                new Pair<String, String>("1", "PLAYER 1"),
                new Pair<String, String>("2", "PLAYER 2"))) {
        case "1":
            playerID = this.currentGame.getPlayer1ID();
            break;
        case "2":
            playerID = this.currentGame.getPlayer2ID();
            break;
        }
        try {
            switch (Driver.menu(null, "Select piece type",
                    new Pair<String, String>("1", "PLAYER 1 pieces"),
                    new Pair<String, String>("2", "PLAYER 2 pieces"))) {
            case "1":
                this.currentGame.checkPlaceRights(playerID, PieceType.PLAYER1);
                break;
            case "2":
                this.currentGame.checkPlaceRights(playerID, PieceType.PLAYER2);
                break;
            }
            System.out.println("The player did place the piece successfully");
        } catch (Exception e) {
            System.out.println(String.format("Oh no! The execution threw an exception (EXPECTED): %s", e.getMessage()));
        }
    }

    private void nextTurn() {
        if (this.currentGame == null) {
            System.out.println("No current Game!");
            return;
        }

        try {
            this.currentGame.nextTurn();
            System.out.println(String.format("The Game turn has changed to %s", this.currentGame.getTurn()));
        } catch (Exception e) {
            System.out.println(String.format("Oh no! The execution threw an exception (EXPECTED): %s", e.getMessage()));
        }
    }
}
