import java.util.Scanner;

public class Game {

	Board board;
	UI ui;
	GameEngine ge;

	public Game() {
		board = new Board();
		ge = new GameEngine(board);
		ui = new UI();
	}

	public void gameStart() {
		board.resetBoard();
		ui.updateGUI(board);
		board.displayBoard();
		int x = -1;
		int y = -1;
		char currentPlayer = 'X';
		Scanner sc = new Scanner(System.in);
		while(!ge.gameOver()) {

			if(ge.getMovesForPlayer(currentPlayer).size() == 0) {
				System.out.println("No possible moves for " + (currentPlayer == 'X' ? "black!" : "white!"));
				if(currentPlayer == 'X') {
					currentPlayer = 'O';
				} else {
					currentPlayer = 'X';
				}
			}

			System.out.println("It's " + (currentPlayer == 'X' ? "black" : "white") + " player's turn.");
			System.out.print("Enter row: ");
			if(sc.hasNext()) {
				x = Integer.parseInt(sc.next());
			}

			System.out.print("Enter column: ");
			if(sc.hasNext()) {
				y = Integer.parseInt(sc.next());
			}

			if(ge.validMove(x, y, currentPlayer) || (x == -1)) {
				if(x != -1)
					ge.makeMove(x, y, currentPlayer);
				if(currentPlayer == 'X') {
					currentPlayer = 'O';
				} else {
					currentPlayer = 'X';
				}
				board.displayBoard();
				ui.updateGUI(board);
			} else {
				System.out.println("Invalid move. Please try again.");			
			}
		}

		System.out.printf("Game over! Score:\nBlack %d - %d White\n", ge.getScoreForPlayer('X'), ge.getScoreForPlayer('O'));
		System.out.print("New game? (y/n): ");
		if(sc.hasNext() && sc.next() == "y") {
			gameStart();
		} else {
			System.exit(0);
		}
	}
}