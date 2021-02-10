package othello.players;

import java.util.ArrayList;
import java.util.Random;

import othello.Pair;
import othello.Point;

public class RandomPlayer extends AbstractPlayer {

	@Override
	public Pair<Point, Point> play(ArrayList<Pair<Point, Point>> moves) {
		return moves.get(new Random().nextInt(moves.size()));
	}

}
