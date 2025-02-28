package com.problems.crackcode.kata.apis.backtracking;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DPApi {

    class Point {
        int x, y, dist;

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist; // dist from start
        }
    }

    //rat solution with queue and not dp

    int findShortestPath(int[][] maze, int sx, int sy, int cx, int cy) {
        int rows = maze.length;
        int cols = maze[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int[] dx = {-1, 1, 0, 0};//movements  in x - up down left right
        int[] dy = {0, 0, -1, 1};//movements  in x - up down left right

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(sx, sy, 0));
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            Point current = q.poll();
            if (current.x == cx && current.y == cy) return current.dist;

            for (int i = 0; i < 4; i++) {
                int newX = current.x + dx[i];
                int newY = current.y + dy[i];

                //check if this was a valid move
                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols
                        && maze[newX][newY] == 0 && !visited[newX][newY]) {
                    q.add(new Point(newX, newY, current.dist + 1));
                    visited[newX][newY] = true;
                }
            }
        }

        return -1;
    }


    int findShortestPathUsingDP(int[][] maze, int sx, int sy, int cx, int cy) {
        int rows = maze.length, cols = maze[0].length;
        int dp[][] = new int[rows][cols];
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);

        dp[sx][sy] = 0;

        for (int i = sx; i < rows; i++) {
            for (int j = sy; j < cols; j++) {
                if (maze[i][j] == 1) continue;

                if (i > 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                }
                if (j > 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                }
            }
        }
        return dp[cx][cy] == Integer.MAX_VALUE ? -1 : dp[cx][cy];
    }

    

}
