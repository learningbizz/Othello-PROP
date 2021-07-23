/**
 * @file Repository.java
 * @author Alex Rodriguez
 * @brief Repository class specification.
 */
package repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * @class Repository
 * @brief Implements various CRUD operations to work with the local file system JSON databases and TXT fixtures.
 * By Alex Rodriguez.
 */
public class Repository {
    /**
    * @brief Different types for the accessed repository.
    */
    public enum RepositoryType {
        CONFIGURATION, GAME, PLAYER, RANKING, FIXTURE
    }

    /* CONSTANTS */

    /**
    * @brief Relative root path of the local JSON databases.
    */
    private static final String databasesPath = "./res/databases/";

    /**
    * @brief Relative root path of the local TXT fixtures.
    */
    private static final String fixturesPath = "./res/fixtures/";

    /* ATTRIBUTES */

    /**
    * @brief Relative path of the accessed repository.
    */
    protected String path;

    /**
    * @brief Type of the accessed repository.
    */
    protected RepositoryType type;

    /* CONSTRUCTORS */

    /**
     * @brief Create a Repository instance.
     * @pre The accessed repository JSON or TXT files exists.
     * @post A Repository instance of the given type is created and binded with the correct path.
     * @param repositoryType type of the accessed repository.
     */
    protected Repository(RepositoryType repositoryType) {
        String realPath = "";
        try {
            realPath = Paths.get(Repository.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath())
                    .getParent().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.type = repositoryType;
        switch (repositoryType) {
            case CONFIGURATION:
                this.path = Paths.get(realPath, Repository.databasesPath, "configurations.json").toString();
                break;
            case GAME:
                this.path = Paths.get(realPath, Repository.databasesPath, "games.json").toString();
                break;
            case PLAYER:
                this.path = Paths.get(realPath, Repository.databasesPath, "players.json").toString();
                break;
            case RANKING:
                this.path = Paths.get(realPath, Repository.databasesPath, "rankings.json").toString();
                break;
            case FIXTURE:
                this.path = Paths.get(realPath, Repository.fixturesPath).toString();
                break;
            default:
                this.path = null;
        }
    }

    /* METHODS */

    /**
     * @brief Obtain all entries of the database.
     * For JSON repositories.
     * @pre The accessed repository JSON or TXT files exists.
     * @post A JSONObject representing the accessed database is returned.
     * @return JSONObject that represents the accessed database.
     */
    protected JSONObject list() {
        JSONObject database = new JSONObject();

        try {
            File file = new File(this.path);
            InputStream reader = new FileInputStream(file);
            JSONTokener tokener = new JSONTokener(reader);
            database = new JSONObject(tokener);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return database;
    }

    /**
     * @brief Obtain an entry of the database by key.
     * For JSON repositories.
     * @pre The accessed repository JSON or TXT files exists.
     * @post A JSONObject representing the key entry of the accessed database is returned or null if it does not exist.
     * @param key Key of the entry in the accessed database.
     * @return JSONObject that represents the key entry of the accessed database or null if it does not exist.
     */
    protected JSONObject get(String key) {
        JSONObject database = this.list();
        return database.optJSONObject(key);
    }

    /**
     * @brief Create an entry in the database by key or update it if it does exist.
     * For JSON repositories.
     * @pre The accessed repository JSON or TXT files exists.
     * @post The key entry is created in the accessed database or it is updated if it already exists. A JSONObject representing the accessed database is returned.
     * @param key Key of the entry in the accessed database.
     * @param value Value to be inserted in the accessed database by the key.
     * @return JSONObject that represents the accessed database.
     */
    protected JSONObject createOrUpdate(String key, JSONObject value) {
        JSONObject database = this.list();
        database.put(key, value);

        try {
            File file = new File(this.path);
            FileWriter writer = new FileWriter(file);
            database.write(writer, 2, 0);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return database;
    }

    /**
     * @brief Remove an entry in the database by key if it does exist.
     * For JSON repositories.
     * @pre The accessed repository JSON or TXT files exists.
     * @post The key entry is removed in the accessed database if it does exist. A JSONObject representing the accessed database is returned.
     * @param key Key of the entry in the accessed database.
     * @return JSONObject that represents the accessed database.
     */
    protected JSONObject remove(String key) {
        JSONObject database = this.list();
        database.remove(key);

        try {
            File file = new File(this.path);
            FileWriter writer = new FileWriter(file);
            database.write(writer, 2, 0);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return database;
    }
}
