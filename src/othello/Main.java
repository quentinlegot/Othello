package othello;

import java.util.ArrayList;

import othello.players.Player;
import othello.players.RandomPlayer;

public class Main {

	
	public static void main(String[] args) {
		Player p1 = new RandomPlayer();
		Player p2 = new RandomPlayer();
		Player[][] board = initialize(p1, p2);
		State game = new State(board, p1, p2);
		System.out.println("joueur 1: " + p1);
		System.out.println("joueur 2: " + p2);
		while(!game.isOver()) {
			Player player = game.getCurrentPlayer();
			ArrayList<Pair<Point, Point>> moves = game.getMove(player);
			System.out.println(game.toString());
			game = game.play(player.play(moves, game, player));
		}
		System.out.println("C'est " + game.getWinner() + " qui a gagné");
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
