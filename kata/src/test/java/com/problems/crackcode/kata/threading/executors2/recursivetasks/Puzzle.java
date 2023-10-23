package com.problems.crackcode.kata.threading.executors2.recursivetasks;

import java.util.Set;

public interface Puzzle<P, M> {
    P getInitPosition();

    Set<M> getLegalMoves(P pos);

    boolean isGoal(P pos);

    P applyMove(P pos, M move);
}

