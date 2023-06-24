package finalProject;

import java.util.Arrays;
import java.util.Random;

public class SentientAI extends ConnectFourPlayer {
	
	public SentientAI() {
		
	}
	
	public int getMove(int[][] board, int player) {
		int [] moves = ConnectFourBoard.getMoves(board);
		// this array is parallel to moves
		double [] wins = new double[moves.length];
		
		for (int i = 0; i < moves.length; i++) {
			// updates the board
			int[][] newBoard = ConnectFourBoard.forecastMove(board, moves[i], player);
			//controls wait time and difficulty
			for (int j = 0; j < 1000; j++) {
				// declares a random game object and plays it
				ConnectFourBoardModified newBoardState = new ConnectFourBoardModified(newBoard);
				int winner = newBoardState.play(new RandomPlayerModified(), new RandomPlayerModified(), player);
				// adds the result to the win count
				if (winner == player) {
					wins[i]++;
				} else if (winner == -1) {
					wins[i] += .5;
				}
			}
		}
		// searches the win list for the best average move
		System.out.println("Favorable Results From 1000: " + Arrays.toString(wins));
		double max = 0;
		int maxIndex = 0;
		for (int i = 0; i < wins.length; i++) {
			if (wins[i] > max) {
				max = wins[i];
				maxIndex = i;
			}
		}
		return moves[maxIndex];
	}
}




class ConnectFourBoardModified
{
    private int [][] board;  //0 = open, 1 = player 1, 2 == player 2
    
    public static final int WIN_LENGTH = 4;
    
    public ConnectFourBoardModified(int[][] board){
        this.board = board;
    }
    
    public int play(ConnectFourPlayer one, ConnectFourPlayer two, int currentPlayer){
    	//pick off where you started
        //loop until game ends
        while (hasWinner(this.board) == 0){
        	// if it is a tie, restart from scratch
        	
        	// failed recursive method
        	/*if (getMoves(this.board).length == 0) {
        		play(one, two, currentPlayer);
        		return -1;
        	}*/
        	
        	if (getMoves(this.board).length == 0) {
        		return -1;
        	}
        	
            //current player gets asked for a move
            int move;
            if (currentPlayer == 1){
                move = one.getMove(this.board, 1);
            }
            else {
                move = two.getMove(this.board, 2);
            }
            //update the board
            board = forecastMove(board, move, currentPlayer);
            if (board == null){
                System.out.println("Player " + currentPlayer + " has made an invalid move");
                return currentPlayer = ((currentPlayer % 2) + 1);
            }
            currentPlayer = ((currentPlayer % 2) + 1); 
        //there is a winner
        }
        return hasWinner(this.board);
    }
    
    //returns an array containing all of the valid moves for a player
    public static int [] getMoves(int [][] board){
        int [] temp = new int[board.length];
        int count = 0;
        for (int i = 0; i < board.length; i++){
            if (board[i][0] == 0){
                temp[count] = i;
                count++;
            }
        }
        int [] ret = new int[count];
        for (int i = 0; i < ret.length; i++){
            ret[i] = temp[i];
        }
        return ret;
    }
    
    //returns a new board that has been updated with the given move
    //or null if the move is invalid
    public static int [][] forecastMove(int [][] board, int move, int currentPlayer){
        int [][] next = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                next[i][j] = board[i][j];
            }
        }
        
        if (move < 0 || move >= board.length){
            return null;
        }
        
        for (int i = next[0].length - 1; i >= 0; i--){
            if (next[move][i] == 0){
                next[move][i] = currentPlayer;
                return next;
            }
        }
        
        return null;
    }
    
    //returns 0 if there is no current winner 
    //1 if player 1 wins or 2 if player two wins
    public static int hasWinner(int [][] board){
    	//added part
    	if (board == null) {
    		return -1;
    	}
    	
        //check if player 1 wins
        int player = 1;
        //look for vert
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length - WIN_LENGTH + 1; j++){
                boolean won = true;
                for (int k = 0; k < WIN_LENGTH; k++){
                    won = won && board[i][j + k] == player;
                }
                if (won) {
                    return player;
                }
            }
        }
        //look for hori
        for (int j = 0; j < board[0].length; j++){
            for (int i = 0; i < board.length - WIN_LENGTH + 1; i++){
                boolean won = true;
                for (int k = 0; k < WIN_LENGTH; k++){
                    won = won && board[i + k][j] == player;
                }
                if (won) {
                    return player;
                }
            }
        }
        //look for pos diag
        for (int i = 0; i < board.length - WIN_LENGTH + 1; i++){
            for (int j = 0; j < board[0].length - WIN_LENGTH + 1; j++){
                boolean won = true;
                for (int k = 0; k < WIN_LENGTH; k++){
                    won = won && board[i + k][j + k] == player;
                }
                if (won) {
                    return player;
                }
            }
        }
        //look for neg diag
        for (int i = 0; i < board.length - WIN_LENGTH + 1; i++){
            for (int j = WIN_LENGTH - 1; j < board[0].length; j++){
                boolean won = true;
                for (int k = 0; k < WIN_LENGTH; k++){
                    won = won && board[i + k][j - k] == player;
                }
                if (won) {
                    return player;
                }
            }
        }
        //check if player 2 wins
        player = 2;
        //look for vert
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length - WIN_LENGTH + 1; j++){
                boolean won = true;
                for (int k = 0; k < WIN_LENGTH; k++){
                    won = won && board[i][j + k] == player;
                }
                if (won) {
                    return player;
                }
            }
        }
        //look for hori
        for (int j = 0; j < board[0].length; j++){
            for (int i = 0; i < board.length - WIN_LENGTH + 1; i++){
                boolean won = true;
                for (int k = 0; k < WIN_LENGTH; k++){
                    won = won && board[i + k][j] == player;
                }
                if (won) {
                    return player;
                }
            }
        }
        //look for pos diag
        for (int i = 0; i < board.length - WIN_LENGTH + 1; i++){
            for (int j = 0; j < board[0].length - WIN_LENGTH + 1; j++){
                boolean won = true;
                for (int k = 0; k < WIN_LENGTH; k++){
                    won = won && board[i + k][j + k] == player;
                }
                if (won) {
                    return player;
                }
            }
        }
        //look for neg diag
        for (int i = 0; i < board.length - WIN_LENGTH + 1; i++){
            for (int j = WIN_LENGTH - 1; j < board[0].length; j++){
                boolean won = true;
                for (int k = 0; k < WIN_LENGTH; k++){
                    won = won && board[i + k][j - k] == player;
                }
                if (won) {
                    return player;
                }
            }
        }
        
        return 0;
    }

}


class RandomPlayerModified extends ConnectFourPlayer 
{
    
    private Random r;
    
    public RandomPlayerModified(){
        r = new Random();
    }
    
    //returns a value between 0 and board.length
    //which corresponds to the column you want to put your piece in
    public int getMove(int [][] board, int player){
    	
        int [] moves = ConnectFourBoardModified.getMoves(board);
        int m = (int) (moves.length * r.nextDouble());
        return moves[m];
    }
}