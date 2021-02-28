package othello;

import othello.players.Player;

import java.util.LinkedList;
import java.util.List;

public class State {

	/**
	 * Contains previous situations of the {@link State#board}, if the game return in a situation which have already
	 * been played, the game ends.
	 * We only keep the 10 previous situations due to performances issues
	 */
	public static List<String> previousSituations = new LinkedList<>();

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
		return n1 == 0 || n2 == 0 || (getMove(player1).isEmpty() && getMove(player2).isEmpty())
				|| previousSituations.contains(toString());
	}

	/**
	 * The method check every possible movement which can do {@code player}'s pawns and add the movement in a list when
	 * there is no-one on the {@link State#board board}. the left side of the {@link Pair tuple} contains the position
	 * where the pawn currently is and the right side the position where it can go by cloning itself or jumping over an
	 * other pawn
	 * @param player the player whose possible movements will be checked
	 * @return a {@link LinkedList list} containing every movement which can do {@code player} in this current situation
	 */
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
	
	public float getScore(Player player) {
		return player == player1 ? ((float) n1/(n1+n2)) : ((float) n2/(n1+n2));
	}

	public int getN1() {
		return this.n1;
	}

	public int getN2() {
		return this.n2;
	}

	public Player getWinner() {
		if(this.n1 > this.n2)
			return player1;
		else if(this.n2 > this.n1)
			return player2;
		return null;
	}

	/**
	 * The method create a copy of itself and modify this copy depending on the {@code move} parameter, it'll clone or jump
	 * a pawn from the left side of {@code move} to the right side, switch current player and recalculate players' score
	 * @param move a {@link Pair tuple} containing 2 elements,
	 *                the left side contains the starting point (where is the point)
	 *                and the right side contains the point where it'll clone or jump
	 * @return a modified copy of the current situation
	 */
	public State play(Pair<Point,Point> move) {
		if(previousSituations.size() == 15) // on ne garde que les 10 dernieres situations par soucis de perfs
			previousSituations.remove(0);
		State copy = this.copy();
		boolean isJump = move.getLeft().isJump(move.getRight(), copy.board);
		copy.board[move.getRight().getY()][move.getRight().getX()] = copy.currentPlayer;
		if (isJump) {
			copy.board[move.getLeft().getY()][move.getLeft().getX()] = null;
		}
		for (int i = -1; i < 2; i++) {
			for (int z = -1; z < 2; z++) {
				try {
					if(copy.board[move.getRight().getY() + i][move.getRight().getX() + z] != null)
						copy.board[move.getRight().getY() + i][move.getRight().getX() + z] = copy.currentPlayer;
				} catch (IndexOutOfBoundsException ignored) {}
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
