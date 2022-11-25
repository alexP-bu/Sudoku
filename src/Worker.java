/*
 * Worker class which serves a request
 * default timeout is 3 seconds
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
        this.req.getBoard().solve();
        long time = System.currentTimeMillis();
        while(this.req.getBoard().getSolutionList().isEmpty() || 
             (time + this.req.getTIMEOUT()) < System.currentTimeMillis()){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        this.printSolutions();     
    }

    /*
     * print solutions worker has found
     */
    public void printSolutions(){
        System.out.println("Found solutions at worker " + id + ": ");
        req.getBoard().getSolutionList().forEach(System.out::println); 
    }
    
}
