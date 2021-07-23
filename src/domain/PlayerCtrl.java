/** @file PlayerCtrl.java
 @brief PlayerCtrl controller specification
 */

package domain;

import java.util.UUID;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import util.Pair;

import org.json.JSONObject;

import domain.Exceptions.ExistingPlayerException;
import domain.Exceptions.NotCreatorException;
import domain.Exceptions.InvalidPasswordException;
import domain.Exceptions.InvalidNameException;
import domain.Exceptions.InvalidDifficultyException;
import domain.Exceptions.InexistingPlayerException;
import domain.Exceptions.IncorrectCredentialsException;
import domain.Exceptions.BadConfirmationException;
import domain.Exceptions.BotUsedException;
import repository.GameRepositoryCtrl;
import repository.PlayerRepositoryCtrl;

/**
 * @class PlayerCtrl
 * @brief Player class controller
 * 
 *        Done by Manuel Navid
 * 
 *        It contains the necessary functions to obtain the information that
 *        needs to send to the presentation layer.
 *
 * @see domain.Player
 */
public class PlayerCtrl {
    /* ATTRIBUTES */

    /**
     * @brief Instance of the Player Repository
     */
    private PlayerRepositoryCtrl repositoryCtrl;

    /**
     * @brief Instance of the Game Repository.
     */
    private GameRepositoryCtrl gameRepositoryCtrl;

    /* CONSTRUCTORS */

    /**
     * @brief Creator method that creates an instance of Player Control
     * @pre <em>True</em>
     * @post Creates an instance of PlayerCtrl
     */
    public PlayerCtrl() {
        this.repositoryCtrl = new PlayerRepositoryCtrl();
        this.gameRepositoryCtrl = new GameRepositoryCtrl();
    }

    /* METHODS */

    /** CREATORS */

    /**
     * @brief Creator that, given a name and a password, creates a new user in the
     *        repository.
     * @pre <em>True</em>
     * @param name Name of a User
     * @param password Password of a User
     * @param confirmation Confirmation of the entered password
     * @post saveUser is called and a saved new user is returned.
     */

    public User createUser(String name, String password, String confirmation)
            throws InvalidNameException, InvalidPasswordException, ExistingPlayerException, BadConfirmationException {
        if (!password.equals(confirmation))
            throw new BadConfirmationException();
        if (this.repositoryCtrl.getByName(name) != null)
            throw new ExistingPlayerException();
        return this.saveUser(name, hash(password), UUID.randomUUID());
    }

    /**
     * @brief Method that, given a name, a difficulty and an ID, creates a new bot
     *        in the repository.
     * @pre <em>True</em>
     * @param name Name of the Bot
     * @param difficulty Difficulty of the Bot
     * @param creatorID UUID of a Player
     * @post saveBot is called and a saved new bot is returned.
     */

    public Bot createBot(String name, Integer difficulty, UUID creatorID)
            throws InvalidNameException, InvalidDifficultyException, ExistingPlayerException {
        if (this.repositoryCtrl.getByName(name) != null)
            throw new ExistingPlayerException();
        return this.saveBot(name, difficulty, UUID.randomUUID(), creatorID);
    }

    /**
     * @brief Method that, given a name and a password, allows us to save a user in
     *        the repository.
     * @pre <em>True</em>
     * @param name Name of a User
     * @param password Password of a User
     * @param id UUID of a User
     * @post User is saved in the users' list at repository and returned.
     */

    private User saveUser(String name, String password, UUID id) throws InvalidNameException, InvalidPasswordException {
        if (name.isBlank())
            throw new InvalidNameException();
        if (password.isBlank())
            throw new InvalidPasswordException();
        User user = new User(name, password, id);
        this.repositoryCtrl.save(user.serialize());
        return user;
    }

    /**
     * @brief Method that, given a name, a difficulty and an ID, allows us to save a
     *        bot in repository.
     * @pre <em>True</em>
     * @param name Name of the Bot
     * @param difficulty Difficulty of the Bot
     * @param id UUID of the Bot
     * @param creatorID UUID of a Player
     * @post Bot is saved in the bots' list and returned.
     */

    private Bot saveBot(String name, Integer difficulty, UUID id, UUID creatorID)
            throws InvalidNameException, InvalidDifficultyException {
        if (name.isBlank())
            throw new InvalidNameException();
        if (difficulty < 1 || difficulty > 10)
            throw new InvalidDifficultyException();
        Bot bot = new Bot(name, difficulty, id, creatorID);
        this.repositoryCtrl.save(bot.serialize());
        return bot;
    }

    /**
     * @brief Method that, given a name and a password, allows us to log in the
     *        system.
     * @pre <em>True</em>
     * @param name Name of a User
     * @param password Password of a User
     * @post The user found in the repository is returned.
     */
    public User login(String name, String password) throws InvalidNameException, InvalidPasswordException,
            InexistingPlayerException, IncorrectCredentialsException {
        if (name.isBlank())
            throw new InvalidNameException();
        if (password.isBlank())
            throw new InvalidPasswordException();

        JSONObject rawUser = this.repositoryCtrl.getByName(name);
        if (rawUser == null)
            throw new InexistingPlayerException();
        if (rawUser.getString("type").equals("BOT"))
            throw new InexistingPlayerException();

        User user = new User(rawUser);
        if (user.getIsDeleted())
            throw new InexistingPlayerException();

        if (!user.getPassword().equals(hash(password)))
            throw new IncorrectCredentialsException();

        return user;
    }

    /** CONSULTANTS */

    /**
     * @brief Method that, given an ID, returns a user.
     * @pre <em>userID is not null</em>
     * @param userID UUID of a User
     * @post User is found in repository and returned.
     */

    public User getUser(UUID userID) throws InexistingPlayerException {
        JSONObject user = this.repositoryCtrl.get(userID);
        if (user == null)
            throw new InexistingPlayerException();
        if (user.getString("type").equals("BOT"))
            throw new InexistingPlayerException();
        return new User(user);
    }

    /**
     * @brief Method that, given an ID, returns a bot.
     * @pre <em>botID is not null</em>
     * @param botID UUID of the Bot
     * @post Bot is found in repository and returned.
     */

    public Bot getBot(UUID botID) throws InexistingPlayerException {
        JSONObject bot = this.repositoryCtrl.get(botID);
        if (bot == null)
            throw new InexistingPlayerException();
        if (bot.getString("type").equals("USER"))
            throw new InexistingPlayerException();
        return new Bot(bot);
    }

    /**
     * @brief Method that lists all users from repository.
     * @pre <em>True</em>
     * @post All users are listed.
     */

    public ArrayList<Pair<String, UUID>> listUsers() {
        return this.repositoryCtrl.listUsers();
    }

    /**
     * @brief Method that lists all bots from repository.
     * @pre <em>True</em>
     * @post All bots are listed.
     */

    public ArrayList<Pair<String, UUID>> listBots() {
        return this.repositoryCtrl.listBots();
    }

    /** MODIFIERS */

    /**
     * @brief Modifier that, given an ID, a name and a password, allows us to modify
     *        the user's credentials changing the name, the password or both.
     * @pre <em>True</em>
     * @param userid UUID of a User
     * @param name Name of a User
     * @param password Password of User
     * @param confirmation Confirmation of the entered password
     * @post Name, password or both are changed and modified user is returned.
     */

    public User modifyUser(UUID userID, String name, String password, String confirmation)
            throws InvalidNameException, InvalidPasswordException, InexistingPlayerException, ExistingPlayerException, BadConfirmationException {
        User original = this.getUser(userID);

        if (name != null) {
            if (name.isBlank())
                throw new InvalidNameException();
            if (!original.getName().equals(name) && this.repositoryCtrl.getByName(name) != null)
                throw new ExistingPlayerException();
            original.setName(name);
        }

        if (password != null) {
            if (password.isBlank())
                throw new InvalidPasswordException();
            if (!password.equals(confirmation))
                throw new BadConfirmationException();
            original.setPassword(hash(password));
        }

        return this.saveUser(original.getName(), original.getPassword(), original.getID());
    }

    /**
     * @brief Method that, given an ID, a name, a difficulty and an ID, allows us to
     *        modify the bot changing the name, the difficulty or both.
     * @pre <em>True</em>
     * @param name Name of the Bot
     * @param difficulty Difficulty of the Bot
     * @param botID UUID of the Bot
     * @param modifierID UUID of a Player
     * @post Bot's name, difficulty or both are modified and modified bot is
     *       returned.
     */

    public Bot modifyBot(UUID botID, String name, Integer difficulty, UUID modifierID)
            throws InvalidNameException, InvalidDifficultyException, ExistingPlayerException, InexistingPlayerException,
            BotUsedException, NotCreatorException {
        Bot original = this.getBot(botID);

        if (!original.getCreatorID().equals(modifierID))
            throw new NotCreatorException();

        if (name != null) {
            if (name.isBlank())
                throw new InvalidNameException();
            if (!original.getName().equals(name) && this.repositoryCtrl.getByName(name) != null)
                throw new ExistingPlayerException();
            original.setName(name);
        }

        if (difficulty != null) {
            if (difficulty < 1 || difficulty > 10)
                throw new InvalidDifficultyException();
            original.setDifficulty(difficulty);
        }

        if (this.gameRepositoryCtrl.existsGameByPlayerID(botID))
            throw new BotUsedException();

        return this.saveBot(original.getName(), original.getDifficulty(), original.getID(), modifierID);
    }

    /** DELETERS */

    /**
     * @brief Method that, given an ID, a name and a password, allows us to delete a
     *        user.
     * @pre <em>True</em>
     * @param userID UUID of a User
     * @param password Passowrd of a User
     * @post The user is deleted from the repository.
     */

    public void deleteUser(UUID userID, String password)
            throws IncorrectCredentialsException, InexistingPlayerException {
        User user = this.getUser(userID);

        if (!user.getPassword().equals(hash(password)))
            throw new IncorrectCredentialsException();

        this.repositoryCtrl.delete(userID);
    }

    /**
     * @brief Method that, given a name, a botID and a deleterID, allows us to
     *        delete a bot.
     * @pre <em>True</em>
     * @param botID UUID of a bot
     * @param deleterID UUID of a User
     * @post The bot is deleted from the repository.
     */

    public void deleteBot(UUID botID, UUID deleterID)
            throws NotCreatorException, InexistingPlayerException, BotUsedException {
        Bot bot = this.getBot(botID);

        if (!bot.getCreatorID().equals(deleterID))
            throw new NotCreatorException();
        if (this.gameRepositoryCtrl.existsGameByPlayerID(botID))
            throw new BotUsedException();

        this.repositoryCtrl.delete(botID);
    }

    /**
     * @brief Method that, given a password, it hashes it using SHA-256 
     * @pre <em>True</em>
     * @param text String to hash
     * @post Returns the hashed password
     */
    private String hash(String text) {
        if (text.isBlank())
            return "";
        try {
            byte[] hash = MessageDigest.getInstance("SHA-256").digest(text.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder(2 * hash.length);
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
