package othello.players;

import java.util.ArrayList;

import othello.Pair;
import othello.Point;

public abstract class AbstractPlayer {

	public abstract Pair<Point, Point> play(ArrayList<Pair<Point, Point>> moves);
	
}
