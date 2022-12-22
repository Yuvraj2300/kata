package com.structures.kata.sandbox;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public class Sandbox {

	class Pair {
		int x;
		int y;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + Objects.hash(x, y);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!(obj instanceof Pair))
				return false;
			Pair other = (Pair) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			return x == other.x && y == other.y || y == other.x && x == other.y;
		}

		private Sandbox getEnclosingInstance() {
			return Sandbox.this;
		}
	}




	//	array : -1,3,2,5,-4,3,1
	//	int Key =2
	//	
	//	You need to find out the unique pairs which difference is key
	//	O/P 1,3.   2,4.   3,5


	// Key+a[i] = x
	// 1,1,2,3,3,4,5
	//1,3
	//2+2 = 4
	//3+2 =5

	//[1,3]
	//[3,1]
	Set<Pair> getPairsForKey(int key, int[] a) {
		Arrays.sort(a);
		int i = 0;
		while (i < a.length) {

			//binSrch(key,low,high,a);
		}


		return null;
	}


	// max sum of the subarray
	//	array : -1,3,-2,5,-4,3,1

	//max
	//tempMax
	//i

	//-1 
	//3
	//


	int getMaxSumSubArray(int[] a) {
		int i = 0;
		int maxSum = Integer.MIN_VALUE;

		int currSum = 0;

		while (i < a.length) {
			currSum += a[i];
			
			if(currSum<0) {
				currSum	=0;
			}
			
			if (currSum > maxSum) {
				maxSum = currSum;
			}
		}

		return maxSum;
	}



}
