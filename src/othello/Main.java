package othello;

import java.util.ArrayList;

import othello.players.AbstractPlayer;
import othello.players.RandomPlayer;

public class Main {

	
	public static void main(String[] args) {
		AbstractPlayer p1 = new RandomPlayer();
		AbstractPlayer p2 = new RandomPlayer();
		AbstractPlayer[][] board = initialize(p1, p2);
		State game = new State(board, p1, p2);
		System.out.println("joueur 1: " + p1);
		System.out.println("joueur 2: " + p2);
		while(!game.isOver()) {
			AbstractPlayer player = game.getCurrentPlayer();
			ArrayList<Pair<Point, Point>> moves = game.getMove(player);
			System.out.println(game.toString());
			game = game.play(player.play(moves));
		}
		System.out.println("C'est " + game.getWinner() + " qui a gagné");
	}
	
	public static AbstractPlayer[][] initialize(AbstractPlayer p1, AbstractPlayer p2){
		AbstractPlayer[][] board = new AbstractPlayer[7][7];
		board[0][0] = p2;
		board[0][6] = p1;
		board[6][0] = p1;
		board[6][6] = p2;
		return board;
	}
	
}
