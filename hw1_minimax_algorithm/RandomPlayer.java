import java.util.Random;

/**
 * This class implements a tic-tac-toe player that always places a piece on a
 * random blank position. You do not need to modify this class.
 * 
 * @author Harchris
 */
public class RandomPlayer implements PlayerInterface {
	// the field that holds the random number generator of this class
	private final Random _random;

	/**
	 * The default constructor that just initializes the random number
	 * generator.
	 */
	public RandomPlayer() {
		_random = new Random();
	}

	/*
	 * This method returns the position of the move made by this player on the
	 * given gameState parameter.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see PlayerInterface#getMove(TicTacToeState)
	 */
	@Override
	public int getMove(TicTacToeState gameState) {
		int position;
		System.out.print(gameState.getCurrentPlayer());
		// find a random blank position
		do {
			position = _random.nextInt(9);
		} while (!gameState.isValidMove(position, gameState.getCurrentPlayer()));
		return position;
	}
}
