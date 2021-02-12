package othello;

import java.util.ArrayList;

import othello.players.AbstractPlayer;

public class State {

	private AbstractPlayer[][] board;
	private AbstractPlayer player1;
	private AbstractPlayer player2;
	private AbstractPlayer currentPlayer;
	private int n1;
	private int n2;

	public State(AbstractPlayer[][] board, AbstractPlayer p1, AbstractPlayer p2, int n1, int n2) {
		this.board = board;
		this.player1 = p1;
		this.player2 = p2;
		currentPlayer = p1;
		this.n1 = n1;
		this.n2 = n2;
	}
	
	public State(AbstractPlayer[][] board, AbstractPlayer p1, AbstractPlayer p2) {
		this(board, p1, p2, 2, 2);
	}
	
	public boolean isOver() {
		if(n1 == 0 || n2 == 0)
			return true;
		return getMove(player1).isEmpty() && getMove(player2).isEmpty();
	}
	
	public ArrayList<Pair<Point, Point>> getMove(AbstractPlayer player) {
		// Pair<Depart, Arrivee>
		ArrayList<Pair<Point, Point>> moves = new ArrayList<>();
		// Parcours du plateau de jeu
		for (int y = 0; y < this.board.length; y++) {
			for (int x = 0; x < this.board[y].length; x++) {
				if (this.board[y][x] == player) {
					// Recherche autour du pion du joueur courant
					for (int deltaY = -1; deltaY < 2; deltaY++) {
						for (int deltaX = -1; deltaX < 2; deltaX++) {
							// La position du pion trouv� est exclue
							if (deltaY != 0 && deltaX != 0) {
								// Si une place libre est trouv�e elle est ajout�e à la liste des coups
								try {
									if (this.board[y+deltaY][x+deltaX] == null) {
										moves.add(new Pair<Point, Point>(new Point(y, x), new Point(y+deltaY, x+deltaX)));
									} else {
										if(this.board[y+2*deltaY][x+2*deltaX] == null)
											moves.add(new Pair<Point, Point>(new Point(y, x), new Point(y+2*deltaY, x+2*deltaX)));
									}
								} catch(ArrayIndexOutOfBoundsException ignored) {}
							}
						}
					}
				}
			}
		}
		return moves;
	}
	
	public int getScore(AbstractPlayer player) {
		return player == player1 ? n1/(n1+n2) : n2/(n2+n1);
	}

	public AbstractPlayer getWinner() {
		int scoreP1 = getScore(player1), scoreP2 = getScore(player2);
		if(scoreP1 > scoreP2)
			return player1;
		else if(scoreP2 > scoreP1)
			return player2;
		return null;
	}

	public State play(Pair<Point,Point> pair) {
		State copy = this.copy();
		copy.board[pair.getLeft().getX()][pair.getLeft().getY()] = copy.getCurrentPlayer();
		int increment = 0;
		for(int i = -1; i<2;i++){
			for(int z = -1;z<2;z++){
				try {
					if(copy.board[pair.getLeft().getX() + i][pair.getLeft().getY() + z] != copy.getCurrentPlayer()){
						increment++;
						copy.board[pair.getLeft().getX() + i][pair.getLeft().getY() + z] = copy.getCurrentPlayer();
					}
				} catch (IndexOutOfBoundsException ignored) {}
			}
		}
		if (currentPlayer == player1)
			copy.n1 += increment;
		else
			copy.n2 += increment;
		
		copy.switchPlayer();
		return copy;
	}
	public AbstractPlayer getCurrentPlayer() {
		return currentPlayer;
	}
	
	public void setCurrentPlayer(AbstractPlayer currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public State copy () {
		State copy = new State(this.board, this.player1, this.player2,this.n1,this.n2);
		for (int i=0; i<this.board.length;i++) {
			for (int j=0; j<this.board.length; j++) {
				copy.board[i][j] = this.board[i][j];
			}
		}
		return copy;
	}
	
	public void switchPlayer() {
		setCurrentPlayer(getCurrentPlayer() == this.player1 ? player2 : player1);
	}
	
	/**
	 * TODO: display the current state of the board
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board.length; x++) {
				if(board[y][x] == player1)
					str.append("O");
				else if(board[y][x] == player2)
					str.append("X");
				else
					str.append(".");
				str.append(" ");
			}
			str.append("\r\n");
		}
		return str.toString();
	}
	
}
