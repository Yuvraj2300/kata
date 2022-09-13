package com.problems.crackcode.kata.apis.backtracking;

import org.junit.jupiter.api.Test;

import com.problems.crackcode.kata.exceptions.KataException;

public class BackTrackingApi {


	private int[][] ratMaze(int[][] maze) {
		int n = maze.length;
		int[][] soln = new int[n][n];

		int x = 0;
		int y = 0;

		if (_mazeIterator(maze, n, soln, x, y)) {
			return soln;
		} else {
			throw new KataException("No Solution exists");
		}
	}



	private boolean _mazeIterator(int[][] maze, int n, int[][] soln, int xPos, int yPos) {
		//what if the start was itslef the end......
		if (xPos == n - 1 && yPos == n - 1 && maze[xPos][yPos] == 1) {
			soln[xPos][yPos] = 1;
			return true;
		}

		//check to see if the given position is a valid cordinate or not
		//check to see if the position in question is even valid or not
		if (xPos >= 0 && xPos < n && yPos >= 0 && yPos < n && maze[xPos][yPos] == 1) {
			//already traversed
			if (soln[xPos][yPos] == 1) {
				return false;
			}

			//mark traversed in the soln
			soln[xPos][yPos] = 1;

			//recurse on x+1 and return true;
			if (_mazeIterator(maze, n, soln, xPos + 1, yPos)) {
				return true;
			}

			//recurse y+1 and return true
			if (_mazeIterator(maze, n, soln, xPos, yPos + 1)) {
				return true;
			}

			//if nothing is true then backtrack
			soln[xPos][yPos] = 0;
			return false;
		}
		return false;
	}



	@Test
	void testRatMaze() throws Exception {
		// @formatter:off
		int[][] maze 	=	{
								{ 1, 0, 0, 0 },
				                { 1, 1, 0, 1 },
				                { 0, 1, 0, 0 },
				                { 1, 1, 1, 1 } 
							};
		// @formatter:on

		int[][] soln = ratMaze(maze);

		int i = 0;
		while (i < soln.length) {
			for (int j = 0; j < soln[0].length; j++) {
				System.out.print(soln[i][j]);
			}
			System.out.println();
			i++;
		}
	}

}
