/**
 * @file MediumDifficultyDriver.java
 * @author Alex Rodriguez
 * @brief MediumDifficulty class specification.
 */
package test.driver;

import java.util.ArrayList;

import domain.Board;
import domain.Board.PieceType;
import domain.MediumDifficulty;
import repository.FixtureRepository;
import util.Pair;

/**
 * @class MediumDifficultyDriver
 * @brief Implements the different options for the MediumDifficulty driver application.
 * By Alex Rodriguez
 */
public class MediumDifficultyDriver extends Driver {
    /* ATTRIBUTES */

    public MediumDifficulty currentMediumDifficulty;

    public Board currentBoard;
    public String nameCurrentBoard;

    public FixtureRepository fixtureRepository;

    /* CONSTRUCTORS */

    public MediumDifficultyDriver() {
        this.currentMediumDifficulty = null;
        this.fixtureRepository = new FixtureRepository();
    }

    /* METHODS */

    public void start() {
        while (true) {
            this.mainMenu();
        }
    }

    private void mainMenu() {
        String title = null;
        if (this.currentMediumDifficulty != null)
            title = String.format("Current maximum depth: %s\n", this.currentMediumDifficulty.getMaxDepth());
        if (this.currentBoard != null)
            title += String.format("Current Board: %s\n", this.nameCurrentBoard);

        switch (Driver.menu(title, "MediumDifficulty (Minimax with alpha-beta pruning) Driver",
                new Pair<String, String>("1", "Create MediumDifficulty"),
                new Pair<String, String>("2", "Get difficulty"),
                new Pair<String, String>("3", "Get canEatHorizontally"),
                new Pair<String, String>("4", "Get canEatVertically"),
                new Pair<String, String>("5", "Get canEatDiagonally"),
                new Pair<String, String>("6", "Get pieceType"),
                new Pair<String, String>("7", "Get maxDepth"),
                new Pair<String, String>("8", "Set maxDepth"),
                new Pair<String, String>("9", "Load Board"),
                new Pair<String, String>("10", "Print Current Board"),
                new Pair<String, String>("11", "Get next best position"))) {
        case "1":
            this.create();
            break;
        case "2":
            this.getDifficulty();
            break;
        case "3":
            this.getCanEatHorizontally();
            break;
        case "4":
            this.getCanEatVertically();
            break;
        case "5":
            this.getCanEatDiagonally();
            break;
        case "6":
            this.getPieceType();
            break;
        case "7":
            this.getMaxDepth();
            break;
        case "8":
            this.setMaxDepth();
            break;
        case "9":
            this.loadBoard();
            break;
        case "10":
            this.printCurrentBoard();
            break;
        case "11":
            this.getNextBestPosition();
            break;
        }
        Driver.pause();
    }

    private void create() {
        System.out.println(
                "Take into account that the default maximum depth is the double of the entered difficulty.\nMinimax with alpha-beta pruning with higher depths requires more time to execute. A value of 3 for the difficulty is reasonable.\n");

        Integer difficulty = Driver.inputInt("Difficulty (positive)?");
        Boolean canEatHorizontally = Driver.inputBool("Can eat horizontally?");
        Boolean canEatVertically = Driver.inputBool("Can eat vertically?");
        Boolean canEatDiagonally = Driver.inputBool("Can eat diagonally?");
        PieceType pieceType = null;

        switch (Driver.menu(null, "Select Bot pieces",
                new Pair<String, String>("1", "PLAYER 1 pieces"),
                new Pair<String, String>("2", "PLAYER 2 pieces"))) {
        case "1":
            pieceType = PieceType.PLAYER1;
            break;
        case "2":
            pieceType = PieceType.PLAYER2;
            break;
        }

        this.currentMediumDifficulty = new MediumDifficulty(difficulty, canEatHorizontally, canEatVertically,
                canEatDiagonally, pieceType);

        System.out.println(String.format("MediumDifficulty with a maximum depth of %s created successfully!",
                this.currentMediumDifficulty.getMaxDepth()));
    }

    private void getDifficulty() {
        if (this.currentMediumDifficulty == null) {
            System.out.println("No current MediumDifficulty!");
            return;
        }

        System.out.println(
                String.format("MediumDifficulty's difficulty is: %s", this.currentMediumDifficulty.getDifficulty()));
    }

    private void getCanEatHorizontally() {
        if (this.currentMediumDifficulty == null) {
            System.out.println("No current MediumDifficulty!");
            return;
        }

        System.out.println(String.format("MediumDifficulty's canEatHorizontally is: %s",
                this.currentMediumDifficulty.getCanEatHorizontally()));
    }

    private void getCanEatVertically() {
        if (this.currentMediumDifficulty == null) {
            System.out.println("No current MediumDifficulty!");
            return;
        }

        System.out.println(String.format("MediumDifficulty's canEatVertically is: %s",
                this.currentMediumDifficulty.getCanEatVertically()));
    }

    private void getCanEatDiagonally() {
        if (this.currentMediumDifficulty == null) {
            System.out.println("No current MediumDifficulty!");
            return;
        }

        System.out.println(String.format("MediumDifficulty's canEatDiagonally is: %s",
                this.currentMediumDifficulty.getCanEatDiagonally()));
    }

    private void getPieceType() {
        if (this.currentMediumDifficulty == null) {
            System.out.println("No current MediumDifficulty!");
            return;
        }

        System.out.println(
                String.format("MediumDifficulty's pieceType is: %s", this.currentMediumDifficulty.getPieceType()));
    }

    private void getMaxDepth() {
        if (this.currentMediumDifficulty == null) {
            System.out.println("No current MediumDifficulty!");
            return;
        }

        System.out.println(
                String.format("MediumDifficulty's maxDepth is: %s", this.currentMediumDifficulty.getMaxDepth()));
    }

    private void setMaxDepth() {
        if (this.currentMediumDifficulty == null) {
            System.out.println("No current MediumDifficulty!");
            return;
        }

        System.out.println(
                "Take into account that minimax with alpha-beta pruning with higher depths requires more time to execute. A value of 7 is reasonable.\n");

        this.currentMediumDifficulty.setMaxDepth(Driver.inputInt("Maximum depth (positive)?"));
        System.out.println("MediumDifficulty's maxDepth changed successfully!");
    }

    private void loadBoard() {
        if (this.currentMediumDifficulty == null) {
            System.out.println("No current MediumDifficulty!");
            return;
        }

        Pair<String, Board> selectedBoard = this.listBoardFixtures();

        this.nameCurrentBoard = selectedBoard.first;
        this.currentBoard = selectedBoard.second;

        System.out.println(String.format("Board %s loaded successfully!\n", this.nameCurrentBoard));
        this.printBoard(this.currentBoard);
    }

    private void printCurrentBoard() {
        if (this.currentMediumDifficulty == null) {
            System.out.println("No current MediumDifficulty!");
            return;
        }

        if (this.currentBoard == null) {
            System.out.println("No current Board!");
            return;
        }

        System.out.println(String.format("Board %s printed successfully!\n", this.nameCurrentBoard));
        this.printBoard(this.currentBoard);
    }

    private void getNextBestPosition() {
        if (this.currentMediumDifficulty == null) {
            System.out.println("No current MediumDifficulty!");
            return;
        }

        if (this.currentBoard == null) {
            System.out.println("No current Board!");
            return;
        }

        System.out.println("Take into account that the state of the current Board won't be globally modified.\n");

        this.printBoard(this.currentBoard);

        long startTime = System.currentTimeMillis();
        Pair<Integer, Integer> nextBestPosition = this.currentMediumDifficulty.place(this.currentBoard.getBoard());
        long durationTime = System.currentTimeMillis() - startTime;

        Board tempBoard = new Board(this.currentBoard.getBoard());

        if (nextBestPosition != null) {
            tempBoard.placePiece(nextBestPosition, this.currentMediumDifficulty.getPieceType(),
                    this.currentMediumDifficulty.getCanEatHorizontally(),
                    this.currentMediumDifficulty.getCanEatVertically(),
                    this.currentMediumDifficulty.getCanEatDiagonally());
            System.out.println(
                    String.format("The best position calculated in %s ms is %s\n", durationTime, nextBestPosition));
            System.out.println("The addition of the piece would look like this:\n");
            this.printBoard(tempBoard);
        } else {
            System.out.println("There isn't any possible position left to place a piece on.");
        }
    }

    private Pair<String, Board> listBoardFixtures() {
        Integer selectedBoard = -1;
        ArrayList<String> listBoards = this.fixtureRepository.listFiles();

        while (selectedBoard < 0 || selectedBoard >= listBoards.size()) {
            Driver.clear();
            System.out.println("==== Available Boards ====\n");

            for (Integer i = 0; i < listBoards.size(); ++i)
                System.out.println(String.format("[%d]\t%s", i, listBoards.get(i)));
            System.out.println("");

            selectedBoard = Driver.inputInt("What Board would you like to load?");
        }

        Driver.clear();

        return new Pair<String, Board>(listBoards.get(selectedBoard),
                new Board(this.fixtureRepository.boardFileToJSON(listBoards.get(selectedBoard))));
    }

    private void printBoard(Board board) {
        ArrayList<String> boardCodified = this.transcribeToCharacters(board);
        System.out.println("       0  1  2  3  4  5  6  7");
        System.out.println("    -------------------------");

        for (Integer i = 0; i < 8; ++i) {
            String row = boardCodified.get(i);
            System.out.println("  " + i + " |  " + row.charAt(0) + "  " + row.charAt(1) + "  " + row.charAt(2) + "  "
                    + row.charAt(3) + "  " + row.charAt(4) + "  " + row.charAt(5) + "  " + row.charAt(6) + "  "
                    + row.charAt(7) + "  ");
        }
        System.out.println("\n");
    }

    private ArrayList<String> transcribeToCharacters(Board board) {
        ArrayList<String> boardCodified = new ArrayList<String>(8);
        String operational = "";
        PieceType[][] current = board.getBoard();

        for (int i = 0; i < 8; ++i) {
            operational = "";
            for (int j = 0; j < 8; ++j) {
                if (current[i][j] == PieceType.PLAYER1)
                    operational = operational + "B";
                if (current[i][j] == PieceType.PLAYER2)
                    operational = operational + "N";
                if (current[i][j] == null)
                    operational = operational + "?";

            }
            boardCodified.add(operational);
        }

        return boardCodified;
    }

}
