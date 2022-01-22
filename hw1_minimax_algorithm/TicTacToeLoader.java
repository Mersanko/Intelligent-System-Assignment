/**
 * This class starts the graphical user interface of the game. This class is
 * required in order to access the player .class files defined outside the
 * HW1.jar package. You do not need to modify this class.
 * 
 * @author Harchris
 */
public final class TicTacToeLoader {
	/**
	 * This method launches the graphical user interface of the game if the
	 * correct number of parameters are given. Two parameters (command-line
	 * arguments) should be passed that represent the names of classes of the
	 * two players.
	 * 
	 * @param args
	 *            The command-line arguments passed during the invocation of
	 *            this program.
	 * @throws Exception
	 *             The exception thrown when any of the parameters are
	 *             incorrect.
	 */
	public static void main(String[] args) throws Exception {
		if (args.length == 2) {
			// run the graphical user interface of the game
			TicTacToeGame.runApplication((PlayerInterface) Class.forName(
					args[0]).newInstance(), (PlayerInterface) Class.forName(
					args[1]).newInstance());
		} else {
			// display help message
			System.out
					.println("Usage: java -cp HW1.jar;. TicTacToeLoader [PlayerXClassName] [PlayerOClassName]");
			System.out
					.println("Example: java -cp HW1.jar;. TicTacToeLoader RandomPlayer RandomPlayer");
		}
	}
}
