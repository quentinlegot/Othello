package othello;

import othello.players.NegamaxPlayer;
import othello.players.Player;

public class Main {


	public static void main(String[] args) {
		Player p1 = new NegamaxPlayer(4);
		Player p2 = new NegamaxPlayer(4);
		Player[][] board = initialize(p1, p2);
		State game = new State(board, p1, p2);
		System.out.println("joueur 1: " + p1);
		System.out.println("joueur 2: " + p2);
		while(!game.isOver()) {
			Player player = game.getCurrentPlayer();
			System.out.println(game.toString());
			game = game.play(player.play(game));
		}
		System.out.println(game.toString());
		System.out.println(game.getWinner() + " a gagné la partie");
		System.out.println(game.getScore(p1));
		System.out.println(game.getScore(p2));
	}
	
	public static Player[][] initialize(Player p1, Player p2){
		Player[][] board = new Player[7][7];
		board[0][0] = p2;
		board[0][6] = p1;
		board[6][0] = p1;
		board[6][6] = p2;
		return board;
	}
	
}
