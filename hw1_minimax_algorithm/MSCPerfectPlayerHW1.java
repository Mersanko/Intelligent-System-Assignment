/**
 * Assignment No. 1
 * 
 * Due: November 30, 2021 (Tuesday) at 11:55PM
 * 
 * Complete the stubbed getMove() method commented with three successive
 * question marks (???) below. The method should compute the optimal move for
 * the current player using the basic minimax algorithm as discussed in class.
 * You can add your own methods for use in this class. To facilitate player
 * competition, you must rename this class (including the filename) by changing
 * the first three letters of the class name to your capitalized initials
 * (first, middle, last). There are sources included in this homework that
 * implement various kinds of players. You can compile and use them as players.
 * You must submit the whole homework package with your modifications/additions
 * in the electronic submission. This homework must comply with the homework
 * policy specified in the "Assignments" page.
 * 
 * To compile and/or run the sources in this homework, include the provided
 * HW1.jar file as well as the current directory containing the sources in the
 * classpath. This can be done by using the "-cp [classpaths]" option of the
 * "javac" and "java" executables. For example, assuming that the HW1.jar file
 * and the source files are in the current directory, use the following commands
 * to compile the sources and run the program.
 * 
 * To compile the source files:
 * 
 * javac -cp HW1.jar;. Filename.java
 * 
 * Example:
 * 
 * javac -cp HW1.jar;. FMLPerfectPlayerHW1.java
 * 
 * 
 * To run:
 * 
 * java -cp HW1.jar;. TicTacToeLoader [PlayerXClassName] [PlayerOClassName]
 * 
 * Example:
 * 
 * java -cp HW1.jar;. TicTacToeLoader FMLPerfectPlayerHW1 RandomPlayer
 */

// MERSAN SUMADERO CANONIGO JR. 2018-5830

public class MSCPerfectPlayerHW1 implements PlayerInterface {
	/*
	 * This method returns the position of the move made by this player on the
	 * given gameState parameter. This method always returns the optimal move of
	 * the current player on the given gameState parameter. The optimal move is
	 * computed using the basic minimax algorithm.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see PlayerInterface#getMove(TicTacToeState)
	 */
	



	//get the maximum val between two int 
	public int max(int int1, int int2) {
		if (int1 > int2) {
			return int1;
		} else {
			return int2;
		}
	}

	//get the minimum val between two int 
	public int min(int int1, int int2) {
		if (int1 < int2) {	
			return int1;
		} else {
			return int2;
		}
	}

	//this will swap the getWinner result because 0 and 1 interchange in TicTacToeState  
	public int swap_score(int score) {
		if (score == 1) {
			score = 0;
		} else if (score == 0) {
			score = 1;
		}
		return score;
	}

	//this will return the best position for playerX 
	public int[] max_value(TicTacToeState gameState,TicTacToeState gameStateClone){
		int alpha = Integer.MIN_VALUE;
		int xscore = alpha;
		int best_position=-1;
		for (int position = 0 ; position<9;++position){
			if (!gameState.isValidMove(position, gameStateClone.getCurrentPlayer())) {
				continue;
			}
			else{
				alpha = max(alpha,minimax(gameStateClone.doMove(position, gameStateClone.getCurrentPlayer()),1)[1]);
				gameStateClone = gameState;
				if (xscore != alpha) {
					xscore = alpha;
					best_position = position;
					
				}
				
			}		
		}
		return new int[] {best_position,alpha};
	}

	//this will return the best position for playerO
	public int[] min_value(TicTacToeState gameState,TicTacToeState gameStateClone){
		int beta = Integer.MAX_VALUE;
		int oscore = beta;
		int best_position=-1;
		
		for (int position = 0 ; position<9;++position){
			if (!gameState.isValidMove(position, gameState.getCurrentPlayer())) {
				continue;
			}
			else{
				beta = min(beta,minimax(gameStateClone.doMove(position, gameStateClone.getCurrentPlayer()),2)[1]);
				gameStateClone = gameState;
				if (oscore != beta) {
					oscore = beta;
					best_position = position;
					
				}
			}		
		}
		return new int[] {best_position,beta};
				
	}

	//minimax method
	public int[] minimax(TicTacToeState gameState,int player_turn) {
		int[] result; 
		if (gameState.getWinner()!=-1){
			return new int[] {0,swap_score(gameState.getWinner())};
		}
		else{
			if (player_turn==2){
				result = max_value(gameState,gameState);	
				return result;
			}
			else{
				result = min_value(gameState,gameState);
				return result;
			}
		}
		
	}
	

	
	public int getMove(TicTacToeState gameState) {
		
		// ??? - modify this method

		// dummy position, just return a random blank position
		int position;
		int score;
		position = minimax(gameState,gameState.getCurrentPlayer())[0];
		score = minimax(gameState,gameState.getCurrentPlayer())[1];
		// System.out.print(position);
		// System.out.print(' ');
		// System.out.print(score);
		return position;
	}
}