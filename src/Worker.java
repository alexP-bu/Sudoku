/*
 * Worker class that solves a sudoku board
 */
public class Worker implements Runnable {

    private int id;
    private Request req;

    /*
     * simple constructor for a worker
     */
    public Worker(int id, Request req){
        this.id = id;
        this.req = req;
    }

    /*
     * solve board
     */
    @Override
    public void run() {
        req.getBoard().solve();      
    }

    /*
     * print solutions worker has found
     */
    public void printSolutions(){
        System.out.println("Found solutions at worker " + id + ": ");
        req.getBoard().getSolutionList().forEach(System.out::println); 
    }
    
}
