package othello;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import othello.players.AlphaBetaPlayer;
import othello.players.NegamaxPlayer;
import othello.players.Player;


public class MainStats {

	public static void main(String[] args) throws IOException {
		Player[] players = Main.extractArgs(args);
		Player p1 = players[0];
		Player p2 = players[1];
		Player[][] board = Main.initializeBoard(p1, p2);
		State game = new State(board, p1, p2);
		System.out.println("joueur 1: " + p1);
		System.out.println("joueur 2: " + p2);
		int tour = 1; // Pour le rapport
		PrintWriter writer = new PrintWriter("statsj1.txt");
		PrintWriter writer2 = new PrintWriter("statsj2.txt");

		while(!game.isOver()) {
			State.previousSituations.add(game.toString());
			Player player = game.getCurrentPlayer();
			System.out.println(game.toString());
			game = game.play(player.play(game));
			if(tour % 2 == 0)
				writer2.println(player.getComplexity());
			else
				writer.println(player.getComplexity());
			tour++;
		}
		writer.close();
		writer2.close();
		Main.endGame(game, p1, p2);
	}

}
