
public class Request {

    private final long TIMEOUT = 3000;
    private long creationTimestamp;
    private long completionTimestamp;
    private Board board;
    boolean completed;

    /*
     * default constructor creates a finished request (bc it's empty!)
     */
    public Request(){
        this.creationTimestamp = System.currentTimeMillis();
        completed = true; 
    }

    /*
     * construct request with a board
     */
    public Request(Board board){
        this.creationTimestamp = System.currentTimeMillis();
        this.board = board;
        this.completed = false;
    }

    public long getTimeStamp(){
        return creationTimestamp;
    }

    public long getCompletionTimestamp(){
        return completionTimestamp;
    }

    public void setCompletionTimestamp(){
        this.completionTimestamp = System.currentTimeMillis();
    }

    public void setCompleted(boolean completed) {
      this.completed = completed;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isCompleted() {
        return completed;
    }

    public long getTIMEOUT() {
      return TIMEOUT;
    }
}
