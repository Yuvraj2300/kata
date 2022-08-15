package com.problems.crackcode.kata.sandbox;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class Sandbox {
	//25, 10, 2, 1

	//125 	= 25*5
	//120	= 25*4 10*2 = 120
	//49 	= 25*1,10*2. 2*2
	//56 	= 25*2, 2*3


	public Map<Integer, Integer> getDenomitions(int[] denominations, int val) {

		//		125/25= x;
		//		125%25= 0;
		//		120%25!=0
		//		120/25=4{
		//		
		//		}
		//				
		Map<Integer, Integer> toReturn = new HashMap<Integer, Integer>();
		//25,10,2,1

		int valToCheck = val;//125   //120

		for (int i = 0; i < denominations.length; i++) {
			int remainder = valToCheck % denominations[i]; //125%25 = 0   120%25 = 20		100%10 = 0		
			int quotient = valToCheck / denominations[i];// 125/25=5		120/25 = 4    100/10=
			System.out.println(denominations[i] + "x" + quotient);
			valToCheck = remainder;

			//			if (remainder != 0) {
			//				toReturn.put(denominations[i], quotient); //25,4 
			//				valToCheck = remainder; // 100
			//			} else {
			//				toReturn.put(denominations[i], quotient);// 25,5
			//				valToCheck = remainder;
			//				//				if (valToCheck == 0) {
			//				//					break;
			//				//				}
			//			}
		}

		return toReturn;
	}


	@Test
	void tstGetDenominations() throws Exception {

		int[] arr = { 25, 10, 2, 1 };
		Map<Integer, Integer> output = getDenomitions(arr, 125);
		assertNotNull(output);
		System.out.println(output);
		//		assertEquals(null, null);
	}


	@Test
	void tstGetDenominations_1() throws Exception {

		int[] arr = { 25, 10, 2, 1 };
		Map<Integer, Integer> output = getDenomitions(arr, 120);
		System.out.println(output);
	}



	@Test
	void tstGetDenominations_2() throws Exception {

		int[] arr = { 25, 10, 2, 1 };
		Map<Integer, Integer> output = getDenomitions(arr, 49);
		System.out.println(output);
	}


	@Test
	void tstGetDenominations_3() throws Exception {

		int[] arr = { 25, 10, 2, 1 };
		Map<Integer, Integer> output = getDenomitions(arr, 56);
		System.out.println(output);
	}


	@Test
	void tstGetDenominations_4() throws Exception {

		int[] arr = { 25, 10, 2, 1 };
		Map<Integer, Integer> output = getDenomitions(arr, 25);
		System.out.println(output);
	}

	@Test
	void tstGetDenominations_5() throws Exception {

		int[] arr = { 25, 10, 2, 1 };
		Map<Integer, Integer> output = getDenomitions(arr, 1);
		System.out.println(output);
	}
}
