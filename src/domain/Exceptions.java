/**
 * @file Exceptions.java
 * @author Alex Rodriguez
 * @brief Exceptions classes specifications.
 */
package domain;

/**
 * @class Exceptions
 * @brief Holds all the different custom Exceptions used in the whole project.
 * By Alex Rodriguez.
 */
public class Exceptions {
    /**
    * @class ExistingPlayerException
    * @brief There is already a player with the same name in the system.
    * By Alex Rodriguez.
    */
    public static class ExistingPlayerException extends Exception {
        public ExistingPlayerException() {
            super("ERR_EXISTING_PLAYER");
        }
    }

    /**
    * @class InvalidNameException
    * @brief The entered name is null, empty or blank.
    * By Alex Rodriguez.
    */
    public static class InvalidNameException extends Exception {
        public InvalidNameException() {
            super("ERR_INVALID_NAME");
        }
    }

    /**
    * @class InvalidPasswordException
    * @brief The entered password is null, empty or blank.
    * By Alex Rodriguez.
    */
    public static class InvalidPasswordException extends Exception {
        public InvalidPasswordException() {
            super("ERR_INVALID_PASSWORD");
        }
    }

    /**
    * @class BadConfirmationException
    * @brief The entered confirmation password doesn't match the user's password.
    * By Alex Rodriguez.
    */
    public static class BadConfirmationException extends Exception {
        public BadConfirmationException() {
            super("ERR_BAD_CONFIRMATION");
        }
    }

    /**
    * @class InexistingPlayerException
    * @brief There isn't any player with the entered name.
    * By Alex Rodriguez.
    */
    public static class InexistingPlayerException extends Exception {
        public InexistingPlayerException() {
            super("ERR_INEXISTING_PLAYER");
        }
    }

    /**
    * @class InexistingConfigurationException
    * @brief There isn't any configuration with the entered name.
    * By Alex Rodriguez.
    */
    public static class InexistingConfigurationException extends Exception {
        public InexistingConfigurationException() {
            super("ERR_INEXISTING_CONFIGURATION");
        }
    }

    /**
    * @class IncorrectCredentialsException
    * @brief Wrong password or name.
    * By Alex Rodriguez.
    */
    public static class IncorrectCredentialsException extends Exception {
        public IncorrectCredentialsException() {
            super("ERR_INCORRECT_CREDENTIALS");
        }
    }

    /**
    * @class NotCreatorException
    * @brief The user that tries to perform an action on a object is not the creator of it.
    * By Alex Rodriguez.
    */
    public static class NotCreatorException extends Exception {
        public NotCreatorException() {
            super("ERR_NOT_CREATOR");
        }
    }

    /**
    * @class BotUsedException
    * @brief A bot cannot be modified or deleted if it is already part of a game.
    * By Alex Rodriguez.
    */
    public static class BotUsedException extends Exception {
        public BotUsedException() {
            super("ERR_BOT_USED");
        }
    }

    /**
    * @class InvalidDifficultyException
    * @brief The entered difficulty is null, empty, blank, less than 0 or greater than 10.
    * By Alex Rodriguez.
    */
    public static class InvalidDifficultyException extends Exception {
        public InvalidDifficultyException() {
            super("ERR_INVALID_DIFFICULTY");
        }
    }

    /**
    * @class ExistingConfigurationException
    * @brief There is already a configuration with the same name and creator ID in the system.
    * By Alex Rodriguez.
    */
    public static class ExistingConfigurationException extends Exception {
        public ExistingConfigurationException() {
            super("ERR_EXISTING_CONFIGURATION");
        }
    }

    /**
    * @class ConfigurationUsedException
    * @brief A configuration cannot be modified or deleted if it is already used in a game.
    * By Alex Rodriguez.
    */
    public static class ConfigurationUsedException extends Exception {
        public ConfigurationUsedException() {
            super("ERR_CONFIGURATION_USED");
        }
    }

    /**
    * @class InvalidBoardException
    * @brief The current board is in an illegal state.
    * By Alex Rodriguez.
    */
    public static class InvalidBoardException extends Exception {
        public InvalidBoardException() {
            super("ERR_INVALID_BOARD");
        }
    }

    /**
    * @class InvalidRulesException
    * @brief The entered configuration rules are all deactivated.
    * By Alex Rodriguez.
    */
    public static class InvalidRulesException extends Exception {
        public InvalidRulesException() {
            super("ERR_INVALID_RULES");
        }
    }

    /**
    * @class InvalidPositionException
    * @brief The entered position is null, empty, blank or ends up with an invalid board.
    * By Alex Rodriguez.
    */
    public static class InvalidPositionException extends Exception {
        public InvalidPositionException() {
            super("ERR_INVALID_POSITION");
        }
    }

    /**
    * @class InvalidPlayersException
    * @brief The entered players are the same, null, empty, blank or both bots have the same difficulty.
    * By Alex Rodriguez.
    */
    public static class InvalidPlayersException extends Exception {
        public InvalidPlayersException() {
            super("ERR_INVALID_PLAYERS");
        }
    }

    /**
    * @class InvalidConfigurationException
    * @brief The entered configuration is null, empty or blank.
    * By Alex Rodriguez.
    */
    public static class InvalidConfigurationException extends Exception {
        public InvalidConfigurationException() {
            super("ERR_INVALID_CONFIGURATION");
        }
    }

    /**
    * @class NotPlayerException
    * @brief The player that wants to perform an action is not part of the game.
    * By Alex Rodriguez.
    */
    public static class NotPlayerException extends Exception {
        public NotPlayerException() {
            super("ERR_NOT_PLAYER");
        }
    }

    /**
    * @class NotPlayerPieceException
    * @brief The player that wants to perform an action tries to use an opponent piece.
    * By Alex Rodriguez.
    */
    public static class NotPlayerPieceException extends Exception {
        public NotPlayerPieceException() {
            super("ERR_NOT_PLAYER_PIECE");
        }
    }

    /**
    * @class NotPlayerTurnException
    * @brief It is not the turn of the player that wants to perform an action.
    * By Alex Rodriguez.
    */
    public static class NotPlayerTurnException extends Exception {
        public NotPlayerTurnException() {
            super("ERR_NOT_PLAYER_TURN");
        }
    }

    /**
    * @class FinishedGameException
    * @brief The game is already finished.
    * By Alex Rodriguez.
    */
    public static class FinishedGameException extends Exception {
        public FinishedGameException() {
            super("ERR_FINISHED_GAME");
        }
    }

    /**
    * @class NotStartedGameException
    * @brief The game has not yet started.
    * By Alex Rodriguez.
    */
    public static class NotStartedGameException extends Exception {
        public NotStartedGameException() {
            super("ERR_NOT_STARTED_GAME");
        }
    }
}
