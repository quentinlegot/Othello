package othello;

import java.awt.Point;
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
	
	public ArrayList<Point> getMove(int player) {
		ArrayList<Point> moves = null;
		
		// Clonage
		// Parcours du plateau de jeu
		for (int i=0; i<this.board.length;i++) {
			for (int j=0; j<this.board.length; j++) {
				if (this.board[i][j] == this.currentPlayer) {
					// Recherche autour du pion du joueur courant
					System.out.println("recherche");
					for (int k=-1; k<2;k++) {
						for (int l=-1; l<2; l++) {
							// La position du pion trouvé est exclue
							if (k!=0 || l!=0) {
								// Si une place libre est trouvée elle est ajouté à la liste de coups
								System.out.println("close");
								if ( (this.board[i+k][j+l]==0) && (i+k >= 0) && (i+k < 7 ) && (j+l >= 0) && (j+l < 7 ) ) {
									System.out.println("jadd");
									moves.add(new Point(i+k, j+l));
								}
							}
						}
					}
				}
			}
		}
		
		// Saut
		
		
		return moves;
	}
	
	public int getScore(int player) {
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
