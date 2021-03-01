package othello.players;

import othello.Pair;
import othello.Point;
import othello.State;

public class AlphaBetaPlayer extends Player{

    public AlphaBetaPlayer(int depth) {
        super(depth);
    }

    /**
     * This method will find the best move to try to win the game by searching all possible movement given by
     * {@link State#getMove(Player)} in the limit of {@link Player#depth}
     * @see AlphaBetaPlayer#alphabeta(State, int, int, int)
     * @see Player#play(State)
     * @see NegamaxPlayer#play(State)
     */
    @Override
    public Pair<Point, Point> play(State game) {
        int bestValue = Integer.MIN_VALUE;
        Pair<Point, Point> bestMove = null;
        for(Pair<Point, Point> move : game.getMove(game.getCurrentPlayer())) {
            State nextState = game.play(move);
            int value = -alphabeta(nextState, this.depth,Integer.MIN_VALUE,Integer.MAX_VALUE);
            if (value > bestValue) {
                bestValue = value;
                bestMove = move;
            }
        }
        return bestMove;
    }

    private int alphabeta(State state, int depth,int alpha,int beta) {
        if(depth == 0 || state.isOver()) {
            return evaluate(state);
        }
        else{
            for (Pair<Point, Point> move : state.getMove(state.getCurrentPlayer())) {
                State nextState = state.play(move);
                complexity++;
                alpha = Math.max(alpha,-alphabeta(nextState,depth-1,-beta,-alpha));
                if(alpha >= beta)
                    return alpha;
            }
            return alpha;
        }
    }
}
