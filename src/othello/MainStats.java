package othello;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import othello.players.AlphaBetaPlayer;
import othello.players.NegamaxPlayer;
import othello.players.Player;

public class MainStats {


	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		Player p1 = new NegamaxPlayer(2);
		Player p2 = new NegamaxPlayer(2);
		Player[][] board = initialize(p1, p2);
		State game = new State(board, p1, p2);
		System.out.println("joueur 1: " + p1);
		System.out.println("joueur 2: " + p2);
		int tour = 1; // Pour le rapport
		PrintWriter writer = new PrintWriter("statsj1.txt", "UTF-8");
		PrintWriter writer2 = new PrintWriter("statsj2.txt", "UTF-8");
		
		while(!game.isOver()) {
			Player player = game.getCurrentPlayer();
			System.out.println(game.toString());
			game = game.play(player.play(game));
			if(tour%2 == 0) {
				writer2.println(player.getComplexity());
			}
			else {
				writer.println(player.getComplexity());
			}
			tour++;
		}
		writer.close();
		writer2.close();
		System.out.println(game.toString());
		System.out.println(game.getN1()+"   "+ game.getN2());
		System.out.println(game.getWinner() + " a gagné la partie");
		System.out.println("Score joueur 1 -> " + game.getN1());
		System.out.println("Score joueur 2 -> "+ game.getN2());

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
