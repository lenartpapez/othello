import java.util.Scanner;

public class Game {

	Board board;
	UI ui;
	GameEngine ge;
	Scanner sc;

	public Game() {
		this.board = new Board();
		this.ge = new GameEngine(board);
		this.ui = new UI();
	}

	public void gameStart() {
		sc = new Scanner(System.in);
		ui.updateGUI(board);
		board.displayBoard();
		int x = -1;
		int y = -1;
		char currentPlayer = 'X';
		while(!ge.gameOver(board)) {
			if(ge.getMovesForPlayer(board, currentPlayer).size() == 0) {
				System.out.println("No possible moves for " + (currentPlayer == 'X' ? "black!" : "white!"));
				if(currentPlayer == 'X') {
					currentPlayer = 'O';
				} else {
					currentPlayer = 'X';
				}
			}

			if(currentPlayer == 'X') {
				System.out.println("It's " + (currentPlayer == 'X' ? "black" : "white") + " player's turn.");
				System.out.print("Enter row: ");
				if(sc.hasNext()) {
					x = Integer.parseInt(sc.next());
				}

				System.out.print("Enter column: ");
				if(sc.hasNext()) {
					y = Integer.parseInt(sc.next());
				}

				if(ge.validMove(board, x, y, currentPlayer)) {
					ge.makeMove(board, x, y, currentPlayer);
					currentPlayer = 'O';
					board.displayBoard();
					ui.updateGUI(board);
				} else {
					System.out.println("Invalid move. Please try again.");			
				}	
			} else {
				try {
					Thread.sleep(2000);
				} catch(Exception e) {
					
				}
				ScoredMove sm = ge.miniMax(board, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 'O');
				ge.makeMove(board, sm.move.x, sm.move.y, currentPlayer);
				board.displayBoard();
				ui.updateGUI(board);
				currentPlayer = 'X';
			}			
		}

		System.out.printf("Game over! Score:\nBlack %d - %d White\n\n", ge.getScoreForPlayer(board, 'X'), ge.getScoreForPlayer(board, 'O'));
		for(int i = 5; i > 0; i--) {
			System.out.printf("Closing in %d seconds...\n", i);
			try {
				Thread.sleep(1000);
			} catch(Exception e) {

			}
		}
		sc.close();
		System.exit(0);
	}
}