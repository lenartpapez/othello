import java.util.Scanner;

public class Main {

	public static void main(String[]args) {
		Board board = new Board();
		GameEngine ge = new GameEngine(board);
		UI ui = new UI();
		board.displayBoard();
		int x = -1;
		int y = -1;
		char currentPlayer = 'X';
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Enter row: ");
			if(sc.hasNext()) {
				x = Integer.parseInt(sc.next());
			}

			System.out.println("Enter column: ");
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
	}
}