package test.driver;

import util.Pair;
import java.util.ArrayList;

import org.json.JSONObject;

import domain.Board;
import domain.Board.PieceType;

import repository.FixtureRepository;

public class BoardDriver {
    public Board currentBoard;
    public String nameCurrentBoard;

    /**
     * PLAYER1 = B de blancas
     * PLAYER2 = N de negras
     */

    /* CONSTRUCTORS*/
    public BoardDriver() {}
    public void start()
    {   
        String title = new String();
        while(true)
        {
            title = (this.currentBoard != null ? String.format("\tcurrent Board %s selected\n", this.nameCurrentBoard): "\tNo current Board is selected\n");
            switch (Driver.menu(title, "Board Driver Menu",
                new Pair<String, String>("0", "Create a Default Board"),
                new Pair<String, String>("1", "Create a Board based on the board of the currentBoard"),
                new Pair<String, String>("2", "Load a Board from a file"),
                new Pair<String, String>("3", "Serialize the current Board to JSON"),
                new Pair<String, String>("4", "Get the number of pieces of both players of the current Board"),
                new Pair<String, String>("5", "Get the whole current Board"),
                new Pair<String, String>("6", "Place a piece on the current Board"),
                new Pair<String, String>("7", "Remove a piece of the current Board"),
                new Pair<String, String>("8", "Place a piece on the current Board as if it was an initial board of a configuration"),
                new Pair<String, String>("9", "Check if the current Board is valid to play"),
                new Pair<String, String>("10", "Check which positions you can add a piece to in the current Board"),
                new Pair<String, String>("11", "Deserialize the current Board"),
                new Pair<String, String>("12", "Print the current Board")))
                {
                case "0":
                defaultBoard();
                break;
                case "1":
                createBoard();
                break;
                case "2":
                loadBoard();
                break;
                case "3":
                serializeBoard();
                break;
                case "4":
                getPiecesPlayers();
                break;
                case "5":
                getCurrentBoard();
                break;
                case "6":
                placePieceBoard();
                break;
                case "7":
                removePieceBoard();
                break;
                case "8":
                placePieceInitialBoard();
                break;
                case "9":
                validBoard();
                break;              
                case "10":
                addPositions();
                break;
                case "11":
                deserialize();
                break;
                case "12":
                {
                    if (this.currentBoard != null)
                    {
                        System.out.println(String.format("==== Printing Board %s ====\n", this.nameCurrentBoard));
                        System.out.println(String.format("Board %s printed below!\n", this.nameCurrentBoard));
                    }
                    printCurrentBoard();
                }                
                break;  
            }
            Driver.pause();
        }
    }

    //OPTION METHODS

    public void defaultBoard() //Option 0
    {
        Driver.clear();
        System.out.println("==== Creating a Default Board ====\n");
        this.currentBoard = new Board();
        this.nameCurrentBoard = "Default";

        System.out.println("Default Board was created succesfully, printed below:");
        System.out.println("\n");
        printCurrentBoard();
        System.out.println("\n");
        System.out.println("Pieces PLAYER1(B): " + this.currentBoard.getPiecesPlayer1());
        System.out.println("Pieces PLAYER2(N): " + this.currentBoard.getPiecesPlayer2());
        System.out.println("\n");
    }

    public void createBoard() //Option 1
    {
        Driver.clear();
        if(this.currentBoard == null) 
        {
            System.out.println("YOU MUST INITIALIZE A BOARD BEFORE SERIALIZING IT!\n\nGo back to the main menu and create a Default Board or Load a preexisting one\n");
        }
        else
        {
            PieceType[][] board = this.currentBoard.getBoard();
            System.out.println("Getting the currentBoard board attribute and creating a new Board instance based off of it.\n");
            this.currentBoard = new Board(board);
            printCurrentBoard();
            System.out.println("\n");
            System.out.println("Pieces PLAYER1(B): " + this.currentBoard.getPiecesPlayer1());
            System.out.println("Pieces PLAYER2(N): " + this.currentBoard.getPiecesPlayer2() + "\n");
            System.out.println("Created the Board class instance successfully\n");
        }
    }

    public void loadBoard() //Option 2
    {
            Driver.clear();
            FixtureRepository fixtureRepo =  new FixtureRepository();
            System.out.println("==== What Board would you like to load? ====" + "\n");
            ArrayList<String> listBoards = fixtureRepo.listFiles();
            
            for(Integer i = 0; i < listBoards.size(); ++i) 
            {
                System.out.println(String.format("[%d]  ", i) + listBoards.get(i));
            }

            System.out.println("\n");

            Boolean checkValid = false;
            Integer nameBoard = null;

            while(checkValid == false)
            {
                try {
                    nameBoard = Integer.parseInt(Driver.input("What Board would you like to load?"));
                    if(nameBoard < 0 || nameBoard >= listBoards.size() )
                        System.out.println("Incorrect Index! Try again :D");
                    else checkValid = true;
                } catch (Exception e) {
                    System.out.println("You didn't add an Integer! Try again :D");
                }
            }

            Driver.clear();
            String path = listBoards.get(nameBoard);
            JSONObject newBoard = fixtureRepo.boardFileToJSON(path);

            this.nameCurrentBoard = path;
            this.currentBoard = new Board(newBoard); 

            System.out.println(String.format("%s was loaded succesfully. \n\nIt's printed below!\n", path));
            printCurrentBoard();
            System.out.println("\n");
            System.out.println("Pieces PLAYER1(B): " + this.currentBoard.getPiecesPlayer1());
            System.out.println("Pieces PLAYER2(N): " + this.currentBoard.getPiecesPlayer2() + "\n");
    }

    public void serializeBoard() //Option 3
    {
        Driver.clear();
        if(this.currentBoard == null) 
        {
            System.out.println("YOU MUST INITIALIZE A BOARD BEFORE SERIALIZING IT!\n\nGo back to the main menu and create a Default Board or Load a preexisting one\n");
        }
        else
        {
            System.out.println(String.format("==== Serializing Board %s ====\n", this.nameCurrentBoard));
            printCurrentBoard();
            System.out.println("\n" + this.nameCurrentBoard + " Board was serialized correctly into JSON format.\n");
            System.out.println(this.currentBoard.serialize().toString(2) + "\n");
        }  
    }

    public void getPiecesPlayers() //Option 4
    {
        Driver.clear();
        if(this.currentBoard == null) 
        {
            System.out.println("YOU MUST INITIALIZE A BOARD BEFORE GETTING ITS ATTRIBUTES!\n\nGo back to the main menu and create a Default Board or Load a preexisting one\n");
        }
        else
        {
            System.out.println(String.format("==== Getting the number of pieces of both players of the current Board %s ====\n", this.nameCurrentBoard));
            printCurrentBoard();
            System.out.println("\nPieces PLAYER1(B): " + this.currentBoard.getPiecesPlayer1());
            System.out.println("Pieces PLAYER2(N): " + this.currentBoard.getPiecesPlayer2() + "\n");
        }
    }

    public void getCurrentBoard() //Option 5
    {
        if(this.currentBoard == null) 
        {
            System.out.println("YOU MUST INITIALIZE A BOARD BEFORE PRINTING IT!\n\nGo back to the main menu and create a Default Board or Load a preexisting one\n");
        }
        else
        {
            System.out.println(String.format("==== Getting the current Board %s ====\n", this.nameCurrentBoard));
            PieceType[][] gotBoard = currentBoard.getBoard();
            System.out.println("Get Board was executed correctly and it's saved in a PieceType[][] called gotBoard. gotBoard is printed below:\n");
            currentBoard = new Board(gotBoard);
            printCurrentBoard();
        }
    }

    public void placePieceBoard() //Option 6
    {
        Driver.clear();
        if(this.currentBoard == null) 
        {
            System.out.println("YOU MUST INITIALIZE A BOARD BEFORE PLACING A PIECE!\n\nGo back to the main menu and create a Default Board or Load a preexisting one\n");
        }
        else
        {
            System.out.println(String.format("==== Placing a piece on %s Board ====\n", this.nameCurrentBoard));
            
            printCurrentBoard();
            System.out.println("\n");
            System.out.println("Write what position you would like to add a piece in the board\n");
            
            Integer row = correctNumber("row");
            Integer col = correctNumber("column");       
            Pair<Integer, Integer> pos = new Pair<Integer, Integer>(row,col);
            PieceType myPieceType = correctPieceType();
            Boolean horizontal = correctBoolean("Horizontal"), vertical = correctBoolean("Vertical"), diagonal = correctBoolean("Diagonal");

            Integer sumPieces = this.currentBoard.getPiecesPlayer1() + this.currentBoard.getPiecesPlayer2();
            this.currentBoard.placePiece(pos, myPieceType, horizontal, vertical, diagonal);
            
            if(sumPieces == (this.currentBoard.getPiecesPlayer1() + this.currentBoard.getPiecesPlayer2())) 
                System.out.println("\nNo pieces were added, as the parameters you inserted didn't give a valid position to place a piece.");
            else
                System.out.println("\nSuccesfully added a piece in position: " + pos);

            System.out.println("\n");
            printCurrentBoard();
            System.out.println("\n");
            System.out.println("Pieces PLAYER1(B): " + this.currentBoard.getPiecesPlayer1());
            System.out.println("Pieces PLAYER2(N): " + this.currentBoard.getPiecesPlayer2() + "\n");                
            
            playAgain("placePieceBoard");
        }
    }

    public void removePieceBoard() //Option 7
    {
        Driver.clear();
        if(this.currentBoard == null) 
        {
            System.out.println("YOU MUST INITIALIZE A BOARD BEFORE REMOVING A PIECE!\n\nGo back to the main menu and create a Default Board or Load a preexisting one\n");
        }
        else
        {
            System.out.println(String.format("==== Removing a piece on %s Board ====\n", this.nameCurrentBoard));
            
            printCurrentBoard();
            System.out.println("\n");
            System.out.println("Write what position you would like to remove a piece from\n");
            Integer row = correctNumber("row");
            Integer col = correctNumber("column");
            Pair<Integer,Integer> pos = new Pair<Integer,Integer>(row,col);
            
            this.currentBoard.removePiece(pos);

            System.out.println("\n");
            printCurrentBoard();
            System.out.println("\n");
            System.out.println("Pieces PLAYER1(B): " + this.currentBoard.getPiecesPlayer1());
            System.out.println("Pieces PLAYER2(N): " + this.currentBoard.getPiecesPlayer2() + "\n");

            playAgain("removePieceBoard");
        }
    }

    public void placePieceInitialBoard() //Option 8
    {
        Driver.clear();
        if(this.currentBoard == null) 
        {
            System.out.println("YOU MUST INITIALIZE A BOARD BEFORE PLACING A PIECE!\n\nGo back to the main menu and create a Default Board or Load a preexisting one\n");
        }
        else
        {
            System.out.println(String.format("==== Placing a piece on %s INITIAL Board ====\n", this.nameCurrentBoard));
            
            printCurrentBoard();
            System.out.println("Pieces PLAYER1(B): " + this.currentBoard.getPiecesPlayer1());
            System.out.println("Pieces PLAYER2(N): " + this.currentBoard.getPiecesPlayer2() + "\n");  
            System.out.println("Write what position you would like to add a piece to the board\n");

            Integer row = correctNumber("row");
            Integer col = correctNumber("column");            
            Pair<Integer, Integer> pos = new Pair<Integer, Integer>(row,col);

            PieceType myPieceType = correctPieceType();

            this.currentBoard.placePieceConfig(pos, myPieceType);
            System.out.println("\n");
            printCurrentBoard();
            System.out.println("\n");
            System.out.println("Pieces PLAYER1(B): " + this.currentBoard.getPiecesPlayer1());
            System.out.println("Pieces PLAYER2(N): " + this.currentBoard.getPiecesPlayer2() + "\n");   
        
            playAgain("placePieceInitialBoard");
        }
    }

    public void validBoard() //Option 9
    {
        Driver.clear();
        if(this.currentBoard == null) 
        {
            System.out.println("YOU MUST INITIALIZE A BOARD BEFORE CHECKING IF ITS VALID!\n\nGo back to the main menu and create a Default Board or Load a preexisting one\n");
        }
        else
        {
            System.out.println(String.format("==== Checking if %s Board is valid ====\n", this.nameCurrentBoard));
            printCurrentBoard();
            System.out.println("\n");

            try {
                Boolean horizontal = correctBoolean("Horizontal"), vertical = correctBoolean("Vertical"), diagonal = correctBoolean("Diagonal");

                this.currentBoard.isValid(horizontal, vertical, diagonal);

                System.out.println("\nThe board is valid with the configuration you've inserted!\n");
                ArrayList<Pair<Integer,Integer>> validPosPlayer1 = this.currentBoard.validPositions(PieceType.PLAYER1, horizontal, vertical, diagonal);
                ArrayList<Pair<Integer,Integer>> validPosPlayer2 = this.currentBoard.validPositions(PieceType.PLAYER1, horizontal, vertical, diagonal);

                if(!validPosPlayer1.isEmpty()) System.out.println("For example, PLAYER1(B) can add a piece in " + validPosPlayer1.get(0) + "\n");
                else if(!validPosPlayer2.isEmpty()) System.out.println("For example, PLAYER2(B) can add a piece in " + validPosPlayer2.get(0) + "\n"); 

            } catch (Exception e) {
                System.out.println("\nThe board is invalid with the configuration you've inserted, meaning neither of both players can add a piece.\n");
            }
        }
    }

    public void addPositions() //Option 10
    {
        Driver.clear();
        if(this.currentBoard == null) 
        {
            System.out.println("YOU MUST INITIALIZE A BOARD BEFORE SEEING THE VALID POSITIONS!\n\nGo back to the main menu and create a Default Board or Load a preexisting one\n");
        }
        else
        {
            System.out.println(String.format("==== Getting all available positions of a PLAYER in %s Board ====\n", this.nameCurrentBoard));
            printCurrentBoard();
            System.out.println("\n");

            Boolean checkValues = false;
            Boolean both = false;
            PieceType myPieceType = null;

            while(!checkValues)
            {
                try {
                    String typePiece = new String(Driver.input("Write which color piece you want to see its valid positions (B or N or BN)"));
                    if(typePiece.equals("B"))
                    {
                        myPieceType = PieceType.PLAYER1; 
                        checkValues = true;
                    }
                    else if (typePiece.equals("N"))
                    {
                        myPieceType = PieceType.PLAYER2; 
                        checkValues = true;
                    }
                    else if (typePiece.equals("BN"))
                    {
                        myPieceType = PieceType.PLAYER2; 
                        checkValues = true;
                        both = true;
                    }                    
                    else {
                        System.out.println("\nERROR! Piece is neither B nor N nor BN\n");
                    }
                } catch (Exception e) {
                    System.out.println("\nERROR! Piece is neither B nor N nor BN\n");
                }
            }

            Boolean horizontal = correctBoolean("Horizontal"), vertical = correctBoolean("Vertical"), diagonal = correctBoolean("Diagonal");

            ArrayList<Pair<Integer,Integer>> validPosPlayer1 = this.currentBoard.validPositions(PieceType.PLAYER1, horizontal, vertical, diagonal);
            ArrayList<Pair<Integer,Integer>> validPosPlayer2 = this.currentBoard.validPositions(PieceType.PLAYER2, horizontal, vertical, diagonal);
            
            if(both || myPieceType == PieceType.PLAYER1)
            {
                for(int i = 0; i < validPosPlayer1.size(); ++i)
                {
                    if(i == 0) System.out.println("\nValid positions for PLAYER1(B):");
                    System.out.println("Position: " + validPosPlayer1.get(i));
                }
            }

            System.out.println("\n");

            if(both || myPieceType == PieceType.PLAYER2)
            {
                for(int i = 0; i < validPosPlayer2.size(); ++i)
                {
                    if(i == 0) System.out.println("Valid positions for PLAYER2(N):");
                    System.out.println("Position: " + validPosPlayer2.get(i));
                }
            }
            System.out.println("\n");
        }  
    }

    public void deserialize() //Option 11
    {
        Driver.clear();
        if(this.currentBoard == null) 
        {
            System.out.println("YOU MUST INITIALIZE A BOARD BEFORE DESERIALIZING IT!\n\nGo back to the main menu and create a Default Board or Load a preexisting one\n");
        }
        else
        {
            System.out.println(String.format("==== Deserializing Board %s ====\n", this.nameCurrentBoard));

            System.out.println(this.currentBoard.serialize().toString(2) + "\n");
            this.currentBoard = new Board(this.currentBoard.serialize());

            System.out.println("The currentBoard has been deserialized from the JSON format successfully, as we can see below:\n");
            printCurrentBoard();
            System.out.println("\n");
            System.out.println("Pieces PLAYER1(B): " + this.currentBoard.getPiecesPlayer1());
            System.out.println("Pieces PLAYER2(N): " + this.currentBoard.getPiecesPlayer2() + "\n");        }
    }

    public void printCurrentBoard() //Option 12
    {
        if(this.currentBoard == null) 
        {
            System.out.println("YOU MUST INITIALIZE A BOARD BEFORE PRINTING IT!\n\nGo back to the main menu and create a Default Board or Load a preexisting one\n");
        }
        else
        {
            
            ArrayList<String> boardCodified = transcribeToCharacters();
            System.out.println("       0  1  2  3  4  5  6  7");
            System.out.println("    -------------------------");

        for(Integer i = 0; i < 8; ++i)
        {
            String row = boardCodified.get(i);
            System.out.println("  " + i + " |  " + row.charAt(0) + "  " + row.charAt(1) + "  " + row.charAt(2) + "  " + row.charAt(3) +
                                "  " + row.charAt(4) + "  " + row.charAt(5) + "  " + row.charAt(6) + "  " + row.charAt(7) + "  ");
        }

            System.out.println("\n");
        } 
    }

    //PRIVATE

    private ArrayList<String> transcribeToCharacters()
    
    {
        ArrayList<String> boardCodified = new ArrayList<String>(8);
        String operational = "";
        PieceType[][] current = this.currentBoard.getBoard();

        if (current != null)
        {
            for(int i = 0; i < 8; ++i)
            {
                operational = "";
                for(int j = 0; j < 8; ++j)
                {
                    if(current[i][j] == PieceType.PLAYER1) operational = operational + "B";
                    if(current[i][j] == PieceType.PLAYER2) operational = operational + "N";
                    if(current[i][j] == null) operational = operational + "?";

                }
                boardCodified.add(operational);
            }
        }

        return boardCodified;
    }

    private Integer correctNumber(String rowOrColumn)
    {
        Boolean checkValues = false;
        Integer returnNumber = -1;

        while(!checkValues)
        {
                try {
                    Integer input = Integer.parseInt(Driver.input(String.format("Write the %s number", rowOrColumn)));
                    if(input < 0 || input > 7) System.out.println(String.format("\nERROR! %s is not between 0 and 7\n", rowOrColumn));
                    else
                    {
                        checkValues = true;
                        returnNumber = input;
                    }  
                } catch (Exception e) {
                    System.out.println(String.format("\nERROR! %s is not between 0 and 7\n", rowOrColumn));
                }
        }
        return returnNumber;
    }

    private PieceType correctPieceType()
    {
        Boolean checkValues = false;
        PieceType myPieceType = null;

        while(!checkValues)
        {
            try {
                char typePiece = new String(Driver.input("Write which color plays (B or N)")).charAt(0);
                if(typePiece == 'B')
                {
                    myPieceType = PieceType.PLAYER1; 
                    checkValues = true;
                }
                else if (typePiece == 'N')
                {
                    myPieceType = PieceType.PLAYER2; 
                    checkValues = true;
                }
                else {
                    System.out.println("\nERROR! Piece is not B or N\n");
                }
            } catch (Exception e) {
                System.out.println("\nERROR! Piece is not B or N\n");
            }   
        }
        return myPieceType;
    }

    private void playAgain(String method)
    {
        Boolean checkValues = false;
        String methodDescription = null;

        if(method.equals("placePieceInitialBoard") || method.equals("placePieceBoard"))
            methodDescription = "add";
        else if(method.equals("removePieceBoard"))
            methodDescription = "remove";

        while(!checkValues)
        {
            try {
                char cont = new String(Driver.input(String.format("Do you want to %s another piece? Write y or n", methodDescription))).charAt(0);
                if(cont == 'y')
                {
                    checkValues = true;
                    if(method.equals("placePieceInitialBoard"))
                        placePieceInitialBoard();
                    else if(method.equals("placePieceBoard"))
                        placePieceBoard();
                    else if(method.equals("removePieceBoard"))
                        removePieceBoard();
                }
                else if(cont == 'n')
                {
                    checkValues = true;
                    return;
                }
                else System.out.println("\nERROR! You didn't write y or n. Try again! :D\n");
            } catch (Exception e) {
                System.out.println("You didn't write y or n! Try again :D");
            }    
        }
    }

    private Boolean correctBoolean(String eatingMethod)
    {
        Boolean checkValues = false;
        Boolean eating = null;

        while(!checkValues)
        {
            try {
                char input = new String(Driver.input(String.format("Can we eat in %s? Write y or n", eatingMethod))).charAt(0);
                if(input == 'y')
                {
                    eating = true;
                    checkValues = true;
                }
                else if(input == 'n')
                {
                    eating = false; 
                    checkValues = true;
                }
                else System.out.println(String.format("\nERROR! %s eating was neither affirmative nor negative\n", eatingMethod));   
            } catch (Exception e) {
                System.out.println(String.format("\nERROR! %s eating was neither affirmative nor negative\n", eatingMethod));
            }   
        }
        return eating;
    }
}
