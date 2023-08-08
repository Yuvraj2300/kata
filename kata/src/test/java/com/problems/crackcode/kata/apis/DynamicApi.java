package com.problems.crackcode.kata.apis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class DynamicApi {


	//coins problem
	@Test
	@DisplayName("Get Min Num Of Coins for Given Amount")
	void getMinNumOfCoinsForGivenAmount() {
		int minNumOfCoins = getMinNumOfCoins(6, new int[] { 4, 3, 1 });
		Assertions.assertEquals(2, minNumOfCoins);
	}


	@Test
	@DisplayName("Get Min Num Of Coins for Given Amount")
	void getMinNumOfCoinsForGivenAmount1() {
		int minNumOfCoins = getMinNumOfCoins(18, new int[] { 5, 2, 1 });
		Assertions.assertEquals(5, minNumOfCoins);
	}

	class MinObj {
		int sum = Integer.MAX_VALUE;
	}


	int getMinNumOfCoins(int amount, int[] coins) {
		int C = amount;
		MinObj minObj = new MinObj();
		int[][] dp = new int[coins.length + 1][amount + 1];
		//		Arrays.fill(dp, -1);


		for (int i = 0; i < coins.length; i++) {
			if (dp[i][amount] == 0) {
				_helper(C, coins[i], 1, coins, minObj);
				dp[i][amount] = minObj.sum;
			} else {
				if (dp[i][amount] < minObj.sum) {
					minObj.sum = dp[i][amount];
				}
			}
		}

		return minObj.sum;
	}

	private void _helper(int c, int v, int count, int[] coins, MinObj minObj) {
		if (v > c) {
			return;
		} else if (v == c) {
			if (count < minObj.sum) {
				minObj.sum = count;
			}
		} else {
			for (int coin : coins) {
				_helper(c, v + coin, count + 1, coins, minObj);
			}
		}
	}


	@Test
	@DisplayName("Test Knapsack Interative")
	void testKnapsackInterative() {

		knapsackIterative(new int[] { 1, 2, 3 }, new int[] { 6, 10, 12 }, 20);
	}




	public static int knapsackIterative(int[] values, int[] weights, int capacity) {
		int n = values.length;
		int[][] dp = new int[n + 1][capacity + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= capacity; j++) {
				if (weights[i - 1] > j) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i - 1][j - weights[i - 1]]);
				}
			}
		}

		return dp[n][capacity];
	}

}
