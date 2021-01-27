package othello;

public class State {
	private int[][] plateau;

	private String[][] board;
	private String player1;
	private String player2;
	private String currentPlayer;

	public State(String[][] board, String p1, String p2) {
		this.board = board;
		this.player1 = p1;
		this.player2 = p2;
		currentPlayer = p1;
	}
	
	public boolean isOver() {
		return false;
	}
	
	public void getMove(String player) {
		
	}
	
	public int getScore(String player) {
		return 0;
	}
	
	public void play(int move) {
		
	}

	public String getCurrentPlayer() {
		return currentPlayer;
	}
	
}
