import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class App {

    private QueueManager queueManager;

    public static void main(String[] args) {
        File boardsFile = new File("boards.txt");
        //initialize app
        App app = new App();
        app.queueManager = new QueueManager();
        //if the boards file doesn't exist, do the default run
        if(!boardsFile.exists() || boardsFile.length() == 0){
            app.defaultRun();
        }else{
            //if file exists and isn't empty, read boards from file
            List<Board> importedBoards = app.readBoards(boardsFile);
            //run the queue manager with the board list
            importedBoards.stream()
                          .parallel()
                          .forEach(board -> app.queueManager.recieveRequest(new Request(board)));
            System.out.println("All boards imported from file. Starting solver...");
            //wait for all jobs to complete
            app.queueManager.waitForAllJobs();
            System.out.println("All solvable boards complete.");
        }
        System.out.println("Exiting...");
    }
    
    /*
     * read boards from designated file
     */
    private List<Board> readBoards(File boardsFile){
        ArrayList<Board> boardList = new ArrayList<>();
        //read boards from file
        int lineNumber = 0;
        int row = 0;
        String line;
        Board currBoard = new Board();
        try (BufferedReader br = new BufferedReader(new FileReader(boardsFile))){
            while((line = br.readLine()) != null){
                //if the line is null, skip it
                if(line.equals("")){
                    continue;
                }
                //check to make sure it's formatted properly
                if(line.charAt(0) != '['){
                    System.out.println("invalid row in boards.txt; missing [ at row: " + lineNumber);
                    break;
                }
                //read each row of the matrix
                currBoard = importLine(line, currBoard, row);
                //reset board if row is < 9
                if(row == 8){
                    boardList.add(currBoard);
                    currBoard = new Board();
                    row = 0;
                }else{
                    row++;
                }
                lineNumber++;
            }
            if(row != 0){
                while(row < 9){
                    currBoard = importLine("[000000000]", currBoard, row);
                    row++;
                }
                boardList.add(currBoard);
                currBoard = new Board();
            }  
            } catch (Exception e){
                e.printStackTrace();
            }
        System.out.println("Boards imported from file:");
        boardList.forEach(System.out::println);
        System.out.println("\n");
        return boardList;
    }
    
    /*
     * import a line from boards.txt
     */
    private static Board importLine(String line, Board board, int row){
        int col = 0;
        for(int i = 1; i < line.length(); i++){
            if(line.charAt(i) == ']'){
                break;
            }
            if(line.charAt(i) != ' '){
                board.getBoard()[row][col] = Character.getNumericValue(line.charAt(i));
                col++; 
            }
        }
        while(col < 9){
            board.getBoard()[row][col] = 0;
            col++;
        }

        return board;
    }

    /*
     * default run code
     */
    private void defaultRun() {
        System.out.println("Example run:");
        //create a board
        Board board = new Board();
        board.generateSampleBoard();
        System.out.println("\n");
        //create a request and give it to the queuemanger
        Request req = new Request(board);
        queueManager.recieveRequest(req);
        //wait for all jobs to complete
        queueManager.waitForAllJobs();
        //give instructions dialog        
        instructions();
    }

    /*
     * instructions dialog
     */
    private void instructions(){
        System.out.println("Now... input your own!");
        System.out.println("Save a file called boards.txt with your custom board configurations!");
        System.out.println("HINT: read the readme to learn how to format your boards.txt file.");
        System.out.println("Then, rerun this program!");
    }
}