/**
 * @file FixtureRepository.java
 * @author Alex Rodriguez
 * @brief FixtureRepository class specification.
 */
package repository;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;

/**
 * @class FixtureRepository
 * @brief Implements various CRUD operations to work with the Fixture repository.
 * By Alex Rodriguez.
 */
public class FixtureRepository extends Repository {
    /* CONSTRUCTORS */

    /**
     * @brief Create a FixtureRepository instance.
     * @pre The Fixture repository TXT files exists.
     * @post A FixtureRepository instance is created.
     */
    public FixtureRepository() {
        super(RepositoryType.FIXTURE);
    }

    /* METHODS */

    /**
     * @brief List all the files of the local TXT fixtures directory.
     * @pre The Fixture repository TXT files exists.
     * @post An ArrayList containing the names of the local TXT fixtures directory is returned.
     * @return ArrayList of the names of the local TXT fixtures directory.
     */
    public ArrayList<String> listFiles() {
        try {
            ArrayList<String> list = new ArrayList<String>(Files.walk(Paths.get(this.path)).filter(Files::isRegularFile)
                    .map(file -> file.toString()).collect(Collectors.toList()));
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<String>();
    }

    /**
     * @brief Read all lines of a file identified by a path.
     * @pre The Fixture repository TXT files exists.
     * @post A List containing the lines of the file identified by the path is returned.
     * @param path Path of the file to be read.
     * @return List of the lines of the file identified by the path.
     */
    private List<String> getLines(String path) {
        List<String> lines = new ArrayList<String>();

        try {
            lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lines;
    }

    /**
     * @brief Read a Board from a TXT file identified by the path and convert it to its JSON representation. 
     * @pre The Fixture repository TXT files exists.
     * @post A JSONObject representing the Board contained in the file identified by the path is returned.
     * @param path Path of the file containing the Board to be read.
     * @return JSONObject that represents the Board contained in the file identified by the path.
     */
    public JSONObject boardFileToJSON(String path) {
        JSONObject board = new JSONObject();

        List<String> lines = this.getLines(path);

        for (int i = 0; i < 8; i++) {
            String row = "";
            if (lines.size() > i)
                row = lines.get(i);
            board.put("row" + i, (row.replaceAll("[^BN\\?,.]+", "") + "????????").substring(0, 8));
        }

        return board;
    }
}
