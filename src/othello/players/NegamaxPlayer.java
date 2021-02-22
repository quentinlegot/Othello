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
        Integer bestValue = null;
        Pair<Point, Point> bestMove = null;
        for(Pair<Point, Point> move : game.getMove(game.getCurrentPlayer())) {
            int v = negamax(game.play(move),10,-id);
            if(bestValue == null || v > bestValue) {
                bestValue = v;
                bestMove = move;
            }
        }
        return bestMove;
    }

    private int negamax(State game, int depth, int id) {
        if(depth == 0 || game.isOver()) {
            if(game.getWinner() == game.getCurrentPlayer())
                return game.getWinner().getId();
            else if(game.getWinner() != null)
                return game.getWinner().getId();
            return 0;
        }
        Integer value = null;
        Player player = game.getPlayerById(id);
        for(Pair<Point, Point> move : game.getMove(player)) {
            int v = negamax(game.play(move),10,-id);
            if(value == null || v>value){
                value = v;
            }
        }
        return value;
    }

}
