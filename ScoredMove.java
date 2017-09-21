public class ScoredMove {
	
	public Move move;
	public int score;

	public ScoredMove() {
		score = 0;
	}

	public ScoredMove(int score) {
		this.score = score;
	}

	public ScoredMove(int score, Move move) {
		this.score = score;
		this.move = move;
	}
}