package othello.players;

import othello.Pair;
import othello.Point;
import othello.State;

public class NegamaxPlayer extends Player {

    public NegamaxPlayer(int id) {
        super(id);
    }

    @Override
    public Pair<Point, Point> play(State game) {
        return game.getMove(this).get(negamax(game, 10, this.id));
    }

    private int negamax(State game, int depth, int id) {
        if(depth == 0 || game.isOver()) {
            return id;
        }
        int value = Integer.MIN_VALUE;
        Player player = game.getPlayerById(id);
        for(Pair<Point, Point> move : game.getMove(player)) {
            game = game.play(move);
            game.setCurrentPlayer(player);
            value = Math.max(value, -negamax(game, depth - 1, -id));
        }
        return value;
    }

}
