public class Worker implements Runnable {

    Board board;

    public Worker(Board board){
        this.board = board;
    }

    @Override
    public void run() {
        board.solve();      
    }

    public void printSolutions(){
        System.out.println("Found Solutions:");
        board.getSolutionList().forEach(System.out::println); 
    }
    
}
