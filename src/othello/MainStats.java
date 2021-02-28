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


	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		Player p1 = new NegamaxPlayer(1);
		Player p2 = new NegamaxPlayer(1);
		Player[][] board = initializeBoard(p1, p2);

		State game = new State(board, p1, p2);
		System.out.println("joueur 1: " + p1);
		System.out.println("joueur 2: " + p2);
		int tour = 1; // Pour le rapport
		PrintWriter writer = new PrintWriter("statsj1.txt");
		PrintWriter writer2 = new PrintWriter("statsj2.txt");
		
		while(!game.isOver()) {
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
	
	public static Player[] extractArgs(String[] args) {
		Player p1;
		Player p2;
		int depthP1 = 4;
		int depthP2 = 4;
		boolean useAlphaBeta = true;
		try {
			if(args.length >= 3) { // les paramètres > 3 sont ignorés
				depthP1 = Integer.parseInt(args[0]);
				depthP2 = Integer.parseInt(args[1]);
				useAlphaBeta = Boolean.parseBoolean(args[2]);
			}
		} catch (NumberFormatException e) {
			System.out.println("Les arguments de lancement ne pas corrects (pas des nombres entiers)");
			System.out.println("Utilisation des paramètres par défaut ( 4 4 true )");
		} finally {
			if(useAlphaBeta) {
				p1 = new AlphaBetaPlayer(depthP1);
				p2 = new AlphaBetaPlayer(depthP2);
			} else {
				p1 = new NegamaxPlayer(depthP1);
				p2 = new NegamaxPlayer(depthP2);
			}
		}
		return new Player[]{p1, p2};
	}
	
	public static Player[][] initializeBoard(Player p1, Player p2){
		Player[][] board = new Player[7][7];
		board[0][0] = p2;
		board[0][6] = p1;
		board[6][0] = p1;
		board[6][6] = p2;
		return board;
	}

	public static void endGame(State game, Player p1, Player p2) {
		System.out.println(game.toString());
		System.out.println(game.getN1()+"   "+ game.getN2());
		System.out.println(game.getWinner() + " a gagné la partie");
		System.out.println("Score joueur 1 -> " + game.getScore(p1));
		System.out.println("Score joueur 2 -> "+ game.getScore(p2));
	}
	
}
