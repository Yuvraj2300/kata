package com.problems.crackcode.kata.apis.backtracking;

import org.junit.jupiter.api.Test;

public class BackTrackingApi {


	private int[][] ratMaze(int[][] maze) {
		int n = maze.length;
		int[][] soln = new int[n][n];

		int x = 0;
		int y = 0;

		if (_mazeIterator(maze, n, soln, x, y)) {
			return soln;
		} else {

		}



		return soln;
	}



	private boolean _mazeIterator(int[][] maze, int n, int[][] soln, int x, int y) {

		if (x >= 0 && x < n && y >= 0 && y < n && maze[x][y] == 1) {
			if (x == n - 1 && y == n - 1 && maze[x][y] == 1) {
				soln[x][y] = 1;
				return true;
			}

			if (soln[x][y] == 1) {
				return false;
			}

			soln[x][y] = 1;


			if (_mazeIterator(maze, n, soln, x + 1, y))
				return true;

			if (_mazeIterator(maze, n, soln, x, y + 1))
				return true;


			soln[x][y] = 0;
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


	private int[][] solveNQueen(int[][] board, int numOfQueens) {

		//	while all queens are not placed
		//		check if placing the queen on the current row/column is ok
		//					to check, check if any other queen lies in the  :
		//					same row
		//					same column
		//					diagonal lower
		//					diagonal upper
		//							if all the cases are safe place the queen on the initial location
		//				repeat till all queens place on the board


		int x = 0;
		int y = 0;

		if (_boardInterator(board, x, y)) {
			return board;
		} else {
			return null;
		}

	}



	private boolean _boardInterator(int[][] board, int x, int y) {
		int i;
		int j;
		if (x >= 0 && x < board.length && y >= 0 && y < board.length) {
			//right
			for (i = y; i < board[x].length; i++) {
				if (board[x][i] == 1) {
					return false;
				}
			}

			//		left
			for (i = y; i >= 0; i--) {
				if (board[x][i] == 1) {
					return false;
				}
			}

			//up
			for (i = x; i >= 0; i--) {
				if (board[i][y] == 1) {
					return false;
				}
			}

			//down
			for (i = x; i < board.length; i++) {
				if (board[i][y] == 1) {
					return false;
				}
			}


			//lower diagonal left
			for (i = x, j = y; j >= 0; i++, j--) {
				if (board[x][y] == 1) {
					return false;
				}
			}

			//upper diagonal right
			for (i = x, j = y; j < board.length; i--, j++) {
				if (board[x][y] == 1) {
					return false;
				}
			}


			//lower diagonal right
			for (i = x, j = y; j < board.length; i++, j++) {
				if (board[x][y] == 1) {
					return false;
				}
			}

			//upper diagonal left
			for (i = x, j = y; i >= 0; i--, j--) {
				if (board[x][y] == 1) {
					return false;
				}
			}

			board[x][y] = 1;

			if (_boardInterator(board, x + 1, y)) {
				return true;
			}

			if (_boardInterator(board, x, y + 1)) {
				return true;
			}

			board[x][y] = 0;
			return false;
		}
		return false;
	}




	@Test
	void testNQueenProblem() throws Exception {
		// @formatter:off
		int[][] board 	=	{
								{ 0, 0, 0, 0 },
				                { 0, 0, 0, 0 },
				                { 0, 0, 0, 0 },
				                { 0, 0, 0, 0 } 
							};
		// @formatter:on

		board = solveNQueen(board, 4);

		int i = 0;
		while (i < board.length) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
			i++;
		}

	}



}
