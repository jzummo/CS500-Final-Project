package finalProject;

import java.util.Random;

/**
 * A simple AI that makes a random move each turn
 * Also includes an example of how to create a game and play ot
 */

public class RandomPlayer extends ConnectFourPlayer 
{
    public static void go(){
        ConnectFourBoard cf = new ConnectFourBoard(9, 7);
        ConnectFourPlayer p1 = new RandomPlayer();
        ConnectFourPlayer p2 = new HumanPlayer();
        
        boolean showGraphics = true;
        boolean showText = false;
        cf.play(p1, p2, showGraphics, showText);
    }
    
    private Random r;
    
    public RandomPlayer(){
        r = new Random();
    }
    
    //returns a value between 0 and board.length
    //which corresponds to the column you want to put your piece in
    public int getMove(int [][] board, int player){
        int [] moves = ConnectFourBoard.getMoves(board);
        int m = (int) (moves.length * r.nextDouble());
        return moves[m];
    }
}
