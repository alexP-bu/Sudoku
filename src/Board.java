import java.util.ArrayList;
import java.util.List;

public class Board {

    private int[][] sboard;
    private List<String> solutionList;

    public Board(){
        sboard = new int[9][9];
        solutionList = new ArrayList<>();
    }

    public void generateSampleBoard(){
        sboard[0][0] = 9;
        sboard[0][7] = 3;
        sboard[0][8] = 7;

        sboard[1][2] = 4;
        sboard[1][3] = 7;

        sboard[2][4] = 3;
        sboard[2][5] = 8;
        sboard[2][6] = 9;
        sboard[2][8] = 5;

        sboard[3][0] = 4;
        sboard[3][1] = 2;
        sboard[3][6] = 8;

        sboard[4][0] = 1;
        sboard[4][1] = 7;
        sboard[4][3] = 9;
        sboard[4][5] = 3;
        sboard[4][6] = 4;
        sboard[4][7] = 5;
        sboard[4][8] = 6;

        sboard[5][0] = 6;
        sboard[5][1] = 9;
        sboard[5][3] = 8;
        sboard[5][4] = 4;
        sboard[5][5] = 1;
        sboard[5][7] = 2;
        sboard[5][8] = 3;

        sboard[6][2] = 9;
        sboard[6][4] = 7;
        sboard[6][5] = 4;
        sboard[6][7] = 6;

        sboard[7][0] = 3;
        sboard[7][6] = 5;
        
        sboard[8][0] = 8;
        sboard[8][2] = 6;
        sboard[8][3] = 3;
        sboard[8][5] = 2;
        System.out.println("Board generated:");
        System.out.println(this.toString());
    }

    public void solve(){
       for(int r = 0; r < sboard.length; r++){
        for(int c = 0; c < sboard[0].length; c++){
            if(sboard[r][c] == 0){
                for(int value = 0; value < 10; value++){
                    if (isValid(r,c,value)){
                        sboard[r][c] = value;
                        solve();
                        sboard[r][c] = 0;
                    }
                }
                return;
            }
        }
       }
       solutionList.add(toString(sboard));
    }

    private boolean isValid(int r, int c, int value) {
        //check cols
        for(int i = 0; i < sboard.length; i++){
            if(sboard[r][i] == value){
                return false;
            }
        }
        //check rows
        for(int i = 0; i < sboard[0].length; i++){
            if(sboard[i][c] == value){
                return false;
            }
        }
        //check box - solution from leetcode sudoku checker
        int roffset = Math.floorDiv(r, 3) * 3;
        int coffset = Math.floorDiv(c, 3) * 3;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(sboard[roffset+i][coffset+j] == value){
                    return false;
                }
            }
        }  
        return true;
    }

    public String toString(){
        String out = "\n";
        for(int i = 0; i < sboard.length; i++){
            if(i % 3 == 0){
                out = hr(out);
            }
            for(int j = 0; j < sboard.length; j++){
                if(j == 0){
                    out += " | ";
                }
                out += sboard[i][j];
                if(j % 3 == 2){
                    out += " | ";
                }
            }
            if(i != sboard.length - 1){
                out += "\n";
            }
        }
        out += "\n";
        return out = hr(out);
    }

    public String toString(int[][] board){
        String out = "\n";
        for(int i = 0; i < board.length; i++){
            if(i % 3 == 0){
                out = hr(out);
            }
            for(int j = 0; j < board.length; j++){
                if(j == 0){
                    out += " | ";
                }
                out += board[i][j];
                if(j % 3 == 2){
                    out += " | ";
                }
            }
            if(i != board.length - 1){
                out += "\n";
            }
        }
        out += "\n";
        return out = hr(out);
    }

    public int[][] getBoard(){
        return sboard;
    }

    private String hr(String str){
        str += "_";
        for(int k = 0; k < sboard.length + 1; k++){
            str += " _";
        }
        str += "\n";
        str += "\n";
        return str;
    }

    public List<String> getSolutionList(){
        return this.solutionList;
    }
}
