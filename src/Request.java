
public class Request {

    private final long CREATION_TIMESTAMP;
    private Board board;
    boolean completed;

    public Request(){
        CREATION_TIMESTAMP = System.currentTimeMillis(); 
    }

    public Request(Board board){
        CREATION_TIMESTAMP = System.currentTimeMillis();
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }
}
