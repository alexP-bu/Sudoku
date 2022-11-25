import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class App {

    public static void main(String[] args) throws Exception {
        File boardsFile = new File("boards.txt");
   
        if(!boardsFile.exists()){
            defaultRun();
        }else{
            //read boards from file
            RequestQueue requestQueue = new RequestQueue();
            readBoards(requestQueue, boardsFile);
            //create work queue
            
        }
    }
    
    private static void readBoards(List<Board> boardList, File boardsFile){
        //read boards from file
        int lineNumber = 0;
        int row = 0;
        String line;
        Board currBoard = new Board();
        try (BufferedReader br = new BufferedReader(new FileReader(boardsFile))){
            while((line = br.readLine()) != null){
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
        boardList.forEach(System.out::println);
    }
    

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

    private static void defaultRun() {
        System.out.println("Example run:");
    
        Board board = new Board();
        board.generateSampleBoard();
        System.out.println();


        Worker worker = new Worker(board);
        Thread thread = new Thread(worker);
        thread.start();
        try{
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        worker.printSolutions();

        System.out.println("Now... input your own!");
        System.out.println("Save a file called boards.txt with your board as a matrix");
        System.out.println("Ex:");
        System.out.println("[10...9]");
        System.out.println("[23...6]");
        System.out.println("etc for 9 rows - any rows that are incomplete will be filled with 0s");
        System.out.println("Then, rerun this program!");
    }
}