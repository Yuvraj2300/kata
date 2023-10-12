package com.problems.crackcode.kata.threading.executors2.recursivetasks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SequentialPuzzleSolver<P, M> {
    Puzzle<P, M> puzzle;
    Set<P> seen = new HashSet<>();

    public SequentialPuzzleSolver(Puzzle<P, M> puzzle) {
        this.puzzle = puzzle;
    }


    public List<M> solve() {
        P initPosition = puzzle.getInitPosition();
        return search(new Node<P, M>(initPosition, null, null));
    }


    private List<M> search(Node<P, M> node) {
        if (!seen.contains(node)) {
            seen.add(node.pos);

            if (puzzle.isGoal(node.pos)) return node.asListOfMoves();

            for (M move : puzzle.getLegalMoves(node.pos)) {
                P p = puzzle.applyMove(node.pos, move);
                Node<P, M> child = new Node<>(p, move, node);
                List<M> result = search(child);
                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }


    static class Node<P, M> {
        final M move;
        final P pos;
        final Node<P, M> prev;

        public Node(P pos, M move, Node<P, M> prev) {
            this.move = move;
            this.pos = pos;
            this.prev = prev;
        }


        List<M> asListOfMoves() {
            List<M> toReturn = new ArrayList<>();
            for (Node<P, M> n = this; n != null; n = n.prev) {
                //using this overloading of the method creates a list whose first element is the starting of the list
                toReturn.add(0, n.move);
            }

            return toReturn;
        }
    }
}



