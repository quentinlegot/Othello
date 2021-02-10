package othello;

import java.awt.Point;
import java.util.ArrayList;

public class State {

	private int[][] board;
	private int player1;
	private int player2;
	private int currentPlayer;
	private int n1;
	private int n2;

	public State(int[][] board, int p1, int p2, int n1,int n2) {
		this.board = board;
		this.player1 = p1;
		this.player2 = p2;
		currentPlayer = p1;
		this.n1 = n1+2;
		this.n2 = n2+2;
	}
	
	public boolean isOver() {
		return false;
	}
	
	public ArrayList<Point> getMove(int player) {
	ArrayList<Point> moves = new ArrayList<Point>();
		
		// Clonage
		// Parcours du plateau de jeu
		for (int i=0; i<this.board.length;i++) {
			for (int j=0; j<this.board.length; j++) {
				if (this.board[i][j] == this.currentPlayer) {
					// Recherche autour du pion du joueur courant
					for (int k=-1; k<2;k++) {
						for (int l=-1; l<2; l++) {
							// La position du pion trouvé est exclue
							if (k!=0 || l!=0) {
								// Si une place libre est trouvée elle est ajoutée à la liste de coups
								if ( ((i+k >= 0) && (i+k < 7 )) && ((j+l >= 0) && (j+l < 7 )) && (this.board[i+k][j+l]==0)) {
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
		if (currentPlayer == 1)
			return n1/(n1+n2);
		else
			return n2/(n2+n1);
	}
	
	public State play(int x, int y) {
		State copie = this.copie();
		copie.board[x][y] = copie.getCurrentPlayer();
		int increment = 0;
		for(int i = -1; i<2;i++){
			for(int z = -1;z<2;z++){
				try {
					copie.board[x+i][y+z] = copie.getCurrentPlayer();
					increment+=1;			
				} catch (Exception e) {

				}
			}
		}
		if (currentPlayer == 1){
			copie.n1 += increment;
		}else{
			copie.n2 += increment;
		}
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
		State copie = new State (this.board, this.player1, this.player2,this.n1,this.n2);
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
