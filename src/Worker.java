import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/*
 * 
 * Worker class which implements the runnable interface;
 * Designed to recieve and process a request
 * 
 */

public class Worker implements Runnable {

    int id;
    Request req;

    public Worker(int id, Request req){
        this.id = id;
        this.req = req;
    }

    @Override
    public void run() {
        req.getBoard().solve();      
    }

    public void printSolutions(){
        try(BufferedOutputStream out = new BufferedOutputStream(new OutputStream())){
            out.write("Found solution at worker " + id + ": ".getBytes(StandardCharsets.UTF_8));
        }
        System.out.println("Found Solution at worker " + id + ": ");
        req.getBoard().getSolutionList().forEach(System.out::println); 
    }
    
}
