package othello;

import othello.players.Player;

import java.util.LinkedList;
import java.util.List;

public class State {

	public static List<State> previousSituations = new LinkedList<>();

	private final Player[][] board;
	private final Player player1;
	private final Player player2;
	private Player currentPlayer;
	private int n1;
	private int n2;

	public State(Player[][] board, Player p1, Player p2) {
		this.board = board;
		this.player1 = p1;
		this.player2 = p2;
		currentPlayer = p1;
		this.n1 = 2;
		this.n2 = 2;
	}
	
	public boolean isOver() {
		return n1 == 0 || n2 == 0 || (getMove(player1).isEmpty() && getMove(player2).isEmpty());
	}

	public LinkedList<Pair<Point, Point>> getMove(Player player) {
		// Pair<Depart, Arrivee>
		LinkedList<Pair<Point, Point>> moves = new LinkedList<>();
		// Parcours du plateau de jeu
		for (int y = 0; y < this.board.length; y++) {
			for (int x = 0; x < this.board[y].length; x++) {
				if (this.board[y][x] == player) {
					// Recherche autour du pion du joueur courant
					for (int deltaY = -1; deltaY < 2; deltaY++) {
						for (int deltaX = -1; deltaX < 2; deltaX++) {
							// La position du pion trouvée est exclue
								// Si une place libre est trouvée elle est ajoutée à la liste des coups
								try {
									Point current = new Point(y, x);
									if (this.board[y+deltaY][x+deltaX] == null) {
										moves.add(new Pair<>(current, new Point(y + deltaY, x + deltaX)));
									}
									Point other = new Point(y + 2 * deltaY, x + 2 * deltaX);
									if(this.board[other.getY()][other.getX()] == null && current.isJump(other,this.board))
										moves.add(new Pair<>(current, other));
								} catch(ArrayIndexOutOfBoundsException ignored) {}
						}
					}
				}
			}
		}
		return moves;
	}
	
	public int getScore(Player player) {
		return player == player1 ? (n1/(n1+n2)) : (n2/(n1+n2));
	}
	public int getN1(){
		return this.n1;
	}
	public int getN2(){
		return this.n2;
	}

	public Player getWinner() {
		if(this.n1 > this.n2)
			return player1;
		else if(this.n2 > this.n1)
			return player2;
		return null;
	}

	public State play(Pair<Point,Point> move) {
		State copy = this.copy();
		boolean isJump = move.getLeft().isJump(move.getRight(), copy.board);
		copy.board[move.getRight().getY()][move.getRight().getX()] = copy.currentPlayer;
		if (isJump) {
			copy.board[move.getLeft().getY()][move.getLeft().getX()] = null;
			copy.board[(move.getLeft().getY() + move.getRight().getY()) / 2][(move.getLeft().getX() + move.getRight().getX()) / 2] = copy.currentPlayer;
		} else {
			for (int i = -1; i < 2; i++) {
				for (int z = -1; z < 2; z++) {
					try {
						if(copy.board[move.getRight().getY() + i][move.getRight().getX() + z] != null)
							copy.board[move.getRight().getY() + i][move.getRight().getX() + z] = copy.currentPlayer;
					} catch (IndexOutOfBoundsException ignored) {}
				}
			}
		}

		int ni = 0, nj = 0;
		for (Player[] players : copy.board) {
			for (Player player : players) {
				if (player == copy.player1) {
					ni++;
				}
				else if (player == copy.player2) {
					nj++;
				}
			}
		}
		copy.n1 = ni;
		copy.n2 = nj;
		copy.switchPlayer();
		return copy;
	}
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player player) {
		this.currentPlayer = player;
	}

	public State copy () {
		State copy = new State(new Player[7][7], this.player1, this.player2);
		for (int i = 0; i < this.board.length; i++) {
			System.arraycopy(this.board[i], 0, copy.board[i], 0, this.board[i].length);
		}
		copy.setCurrentPlayer(this.getCurrentPlayer());
		copy.n1 = n1;
		copy.n2 = n2;

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
		for (Player[] players : board) {
			for (int x = 0; x < board.length; x++) {
				if (players[x] == player1)
					str.append("O");
				else if (players[x] == player2)
					str.append("X");
				else
					str.append(".");
				str.append(" ");
			}
			str.append("\r\n");
		}
		return str.toString();
	}
	@Override
	public boolean equals(Object state) {
		boolean bool;
		bool = ( state instanceof State);
		bool = bool && (this.getCurrentPlayer().equals(((State) state).getCurrentPlayer()));
		bool = bool && (this.player1.equals(((State) state).player1)) && (this.player2.equals(((State) state).player2));
		bool = bool && (this.n1 == ((State)state).n1)&& (this.n2 == ((State)state).n2);
		for (int i = 0; i < this.board.length; i++) {
			for (int y = 0; y < this.board.length; y++){
				bool = bool && (this.board[i][y] == ((State)state).board[i][y]);
			}
		}
		return bool;
	}
}
