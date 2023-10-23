package com.problems.crackcode.kata.threading.executors2.recursivetasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

public class ConcurrentPuzzleSolver<P, M> {
    Puzzle<P, M> puzzle;
    ConcurrentHashMap<P, Boolean> seen = new ConcurrentHashMap<P, Boolean>();

    ValueLatch<Node<P, M>> solutionsNodeValue = new ValueLatch<>();

    ExecutorService es;

    public ConcurrentPuzzleSolver(Puzzle<P, M> puzzle, ExecutorService es) {
        this.puzzle = puzzle;
        this.es = es;
    }


    public List<M> solve(Node<P, M> node) {
        P start = puzzle.getInitPosition();
        es.execute(new SolverTask(node.pos, node.move, node.prev));
        try {
            //this blocks
            Node<P, M> solnNode = solutionsNodeValue.getValue();
            return solnNode.asListOfMoves();
        } catch (InterruptedException e) {
            return null;
        }
    }


    class SolverTask extends ConcurrentPuzzleSolver.Node<P, M> implements Runnable {
        SolverTask(P pos, M move, Node<P, M> prev) {
            super(pos, move, prev);
        }

        @Override
        public void run() {
            if (!seen.containsKey(pos)) {
                seen.putIfAbsent(pos, true);
                Set<M> legalMoves = puzzle.getLegalMoves(pos);
                for (M move : legalMoves) {
                    P posReached = puzzle.applyMove(pos, move);
                    if (puzzle.isGoal(posReached)) {
                        //this must release the countdownlatch
                        solutionsNodeValue.setValue(this);
                        return;
                    } else {
                        es.execute(new SolverTask(posReached, move, prev));
                    }
                }
            }
        }
    }

    static class Node<P, M> {
        M move;
        P pos;
        ConcurrentPuzzleSolver.Node<P, M> prev;

        public Node(P pos, M move, ConcurrentPuzzleSolver.Node<P, M> prev) {
            this.move = move;
            this.pos = pos;
            this.prev = prev;
        }

        List<M> asListOfMoves() {
            List<M> toReturn = new ArrayList<>();
            for (ConcurrentPuzzleSolver.Node<P, M> n = this; n != null; n = n.prev) {
                //using this overloading of the method creates a list whose first element is the starting of the list
                toReturn.add(0, n.move);
            }

            return toReturn;
        }
    }
}



