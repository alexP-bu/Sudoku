import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QueueManager {
    
    private static final int MAX_WORKERS = 10;
    private int totalWorkersRan;
    private Queue<Request> queue;
    private List<Thread> threads;
    
    /*
     * simple constructor for a queue manager
     */
    public QueueManager(){
        this.queue = new LinkedList<>();
        totalWorkersRan = 0;
    }
    
    /*
     * queue manager recieves request
     * @param req
     */
    public void recieveRequest(Request req){
        queue.add(req);
        if(!queue.isEmpty()){
            this.serveNext();
        }
    }

    /*
     * queue manager serves next request and any other requests in queue
     */
    private void serveNext(){
        //if a job arrives, start it
        while(!queue.isEmpty()){
            //make sure there are only 10 threads working at once
            if(threads.size() < QueueManager.MAX_WORKERS){
                Request req = queue.poll();
                if(req != null){
                    Worker worker = new Worker(totalWorkersRan, req);
                    Thread thread = new Thread(worker);
                    threads.add(thread);        
                }
            }
        }
    }

    /*
     * queue manager ensures all threads complete
     */
    public void waitForAllJobs(){
        threads.stream().parallel().forEach(thread -> {
            try{
                thread.join();
            }catch(InterruptedException e){
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        });
    }
}
