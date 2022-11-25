
public class Request {

    private final long CREATION_TIMESTAMP;
    private Board board;
    boolean completed;

    public Request(){
        CREATION_TIMESTAMP = System.currentTimeMillis(); 
    }

    public Request(Request req) {
        
    }

    public Board getBoard() {
        return board;
    }

}
