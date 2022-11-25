import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestQueue {
    
    Queue<Request> requestQueue;
    Vector<Thread> threads;
    AtomicInteger totalWorkers;
    
    public RequestQueue(Vector<Thread> threads){
        this.requestQueue = new LinkedList<>();
        this.threads = threads;
        totalWorkers.set(0);
    }
    
    public void recieveRequest(Request req){
        requestQueue.add(new Request(req));
        if(!requestQueue.isEmpty()){
            this.serveNext();
        }
    }

    private void serveNext(){
        while(!requestQueue.isEmpty()){
            if(threads.size() < 10){
                Request req = requestQueue.poll();
                if(req != null){
                    Worker worker = new Worker(totalWorkers.incrementAndGet(), req);
                    Thread thread = new Thread(worker);
                    threads.add(thread);        
                }
            }
        }
    }
}
