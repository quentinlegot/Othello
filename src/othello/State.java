package othello;

import java.util.ArrayList;

public class State {

	private int[][] board;
	private int player1;
	private int player2;
	private int currentPlayer;

	public State(int[][] board, int p1, int p2) {
		this.board = board;
		this.player1 = p1;
		this.player2 = p2;
		currentPlayer = p1;
	}
	
	public boolean isOver() {
		return false;
	}
	
	public ArrayList<int[][]> getMove(String player) {
		return null;
	}
	
	public int getScore(String player) {
		return 0;
	}
	
	public State play(int x, int y) {
		State copie = this.copie();
		copie.board[x][y] = copie.getCurrentPlayer();
		copie.switchPlayer();
		return copie;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}
	
	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public State copie () {
		State copie = new State (this.board, this.player1, this.player2);
		for (int i=0; i<this.board.length;i++) {
			for (int j=0; j<this.board.length; j++) {
				copie.board[i][j] = this.board[i][j];
			}
		}
		return copie;
	}
	
	public void switchPlayer () {
		if (getCurrentPlayer()==this.player1) {
			setCurrentPlayer(player2);
		}
		else {
			setCurrentPlayer(player1);
		}
	}
	
}
