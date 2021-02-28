public Pair<Point, Point> play(State game) {
     int bestValue = Integer.MIN_VALUE;
     Pair<Point, Point> bestMove = null;
     for(Pair<Point, Point> move : game.getMove(game.getCurrentPlayer())) {
         State nextState = game.play(move);
         int value = -negamax(nextState, this.depth);
         if (value > bestValue) {
             bestValue = value;
             bestMove = move;
        }
     }
     return bestMove;
}

private int negamax(State state, int depth) {
    if(depth == 0 || state.isOver()) {
        return evaluate(state);
    } else{
        int m = Integer.MIN_VALUE;
        for (Pair<Point, Point> move : state.getMove(state.getCurrentPlayer())) {
            State nextState = state.play(move);
            complexity++;
            m= Math.max(m,-negamax(nextState,depth-1));
        }
        return m;
    }
}
