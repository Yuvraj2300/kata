package com.problems.crackcode.kata.apis;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

public class StackApi {

	class CylinderStack {
		private Stack<Cylinder<Integer>> stackOfCylinders;
		private int size;


		public int getSize() {
			return size;
		}

		private void setSize(int val) {
			this.size += val;
		}

		public Stack<Cylinder<Integer>> getStackOfCylinders() {
			return stackOfCylinders;
		}

		public void setStackOfCylinders(Stack<Cylinder<Integer>> stackOfCylinders) {
			this.stackOfCylinders = stackOfCylinders;
		}

		public void addAllDataToStack(List<Integer> list) {
			for (int i = list.size() - 1; i >= 0; i--) {
				Cylinder<Integer> cyl = new Cylinder<>();
				cyl.setData(list.get(i));
				stackOfCylinders.push((cyl));
				this.setSize((int) list.get(i));
			}
		}


		public void popCylinders() {
			Cylinder<Integer> pop = stackOfCylinders.pop();
			this.size -= pop.data;
		}

		@Override
		public String toString() {
			return "CylinderStack [stackOfCylinders=" + stackOfCylinders + ", size=" + size + "]";
		}


	}


	class Cylinder<T extends Number> {
		private T data;

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		@Override
		public String toString() {
			return "Cylinder [data=" + data + "]";
		}
	}

	/**
	 * @author yuvraj1.sharma
	 *
	 *
	 *         Read Here :
	 *         https://www.hackerrank.com/challenges/equal-stacks/problem?isFullScreen=true
	 *
	 */
	public int equalStacks(List<Integer> h1, List<Integer> h2, List<Integer> h3) {
		CylinderStack cylStack1 = new CylinderStack();
		Stack<Cylinder<Integer>> stck1 = new Stack<>();
		cylStack1.setStackOfCylinders(stck1);
		cylStack1.addAllDataToStack(h1);

		CylinderStack cylStack2 = new CylinderStack();
		Stack<Cylinder<Integer>> stck2 = new Stack<>();
		cylStack2.setStackOfCylinders(stck2);
		cylStack2.addAllDataToStack(h2);


		CylinderStack cylStack3 = new CylinderStack();
		Stack<Cylinder<Integer>> stck3 = new Stack<>();
		cylStack3.setStackOfCylinders(stck3);
		cylStack3.addAllDataToStack(h3);


		Supplier<? extends Number> getMinSize = () -> {
			int size1 = cylStack1.getSize();
			int size2 = cylStack2.getSize();
			int size3 = cylStack3.getSize();

			return Math.min(Math.min(size1, size2), size3);
		};

		BiConsumer<CylinderStack, Integer> getToPopNumber = (cylinderStack, minVal) -> {
			if (cylinderStack.getSize() > minVal) {
				cylinderStack.popCylinders();
			}
		};

		Integer minVal = 0;
		while ((cylStack1.getSize() != cylStack2.getSize()) || (cylStack1.getSize() != cylStack3.getSize())) {
			minVal = (Integer) getMinSize.get();
			getToPopNumber.accept(cylStack1, minVal);
			getToPopNumber.accept(cylStack2, minVal);
			getToPopNumber.accept(cylStack3, minVal);
		}
		//		System.out.println(cylStack1);
		//		System.out.println(cylStack2);
		//		System.out.println(cylStack3);

		return minVal;
	}


	@Test
	void testEqualStacks() throws Exception {
		List<Integer> vals1 = new ArrayList<>();
		vals1.add(1);
		vals1.add(2);
		vals1.add(1);
		vals1.add(1);

		List<Integer> vals2 = new ArrayList<>();
		vals2.add(1);
		vals2.add(1);
		vals2.add(2);


		List<Integer> vals3 = new ArrayList<>();
		vals3.add(1);
		vals3.add(1);

		int val = equalStacks(vals1, vals2, vals3);

		assertEquals(2, val);
	}


	@Test
	void testEqualStacks_1() throws Exception {
		List<Integer> vals1 = new ArrayList<>();
		vals1.add(3);
		vals1.add(2);
		vals1.add(1);
		vals1.add(1);
		vals1.add(1);

		List<Integer> vals2 = new ArrayList<>();
		vals2.add(4);
		vals2.add(3);
		vals2.add(2);


		List<Integer> vals3 = new ArrayList<>();
		vals3.add(1);
		vals3.add(1);
		vals3.add(4);
		vals3.add(1);

		int val = equalStacks(vals1, vals2, vals3);

		assertEquals(5, val);
	}



	enum Brackets {
		CURLY(
				'{',
				'}'),
		SMALL(
				'(',
				')'),
		SQUARE(
				'[',
				']');

		private char open;
		private char close;

		private Brackets(char open, char close) {
			this.open = open;
			this.close = close;
		}

		public char getOpenValue() {
			return this.open;
		}

		public char getCloseValue() {
			return this.close;
		}

		public static Brackets getBracket(char openClose) {
			if (openClose == CURLY.getOpenValue() || openClose == CURLY.getCloseValue()) {
				return CURLY;
			} else if (openClose == SMALL.getOpenValue() || openClose == SMALL.getCloseValue()) {
				return SMALL;
			} else if (openClose == SQUARE.getOpenValue() || openClose == SQUARE.getCloseValue()) {
				return SQUARE;
			} else {
				return null;
			}
		}
	}


	public static String isBalanced(String s) {
		if (_checkBracketPair(s)) {
			return "YES";
		} else {
			return "NO";
		}
	}


	private static boolean _checkBracketPair(String s) {
		boolean goodPairs = true;
		Stack<Character> bracketStack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			if (!goodPairs) {
				break;
			}

			char bracketInString = s.charAt(i);
			// @formatter:off
			if (bracketInString == Brackets.CURLY.getOpenValue() 
					|| bracketInString == Brackets.SQUARE.getOpenValue() 
					|| bracketInString == Brackets.SMALL.getOpenValue()) {
			// @formatter:on
				bracketStack.add(bracketInString);
			} else {
				if (bracketStack.isEmpty()) {
					goodPairs &= false;
				} else {
					Character popedBracket = bracketStack.pop();
					Brackets poppedBracketType = Brackets.getBracket(popedBracket);
					Brackets currentBracketType = Brackets.getBracket(bracketInString);

					if (null != poppedBracketType && currentBracketType == poppedBracketType) {
						goodPairs &= true;
					} else {
						goodPairs &= false;
					}
				}
			}
		}
		return goodPairs;
	}


	@Test
	void testCheckBracketPair() throws Exception {
		String balanced = isBalanced("{{([])}}");
		assertEquals("YES", balanced);
	}

	@Test
	void testCheckBracketPair_1() throws Exception {
		String balanced = isBalanced("{[(])}");
		assertEquals("NO", balanced);
	}

	@Test
	void testCheckBracketPair_2() throws Exception {
		String balanced = isBalanced("{(([])[])[]}");
		assertEquals("YES", balanced);
	}

	@Test
	void testCheckBracketPair_3() throws Exception {
		String balanced = isBalanced("{(([])[])[]]}");
		assertEquals("NO", balanced);
	}

	@Test
	void testCheckBracketPair_4() throws Exception {
		String balanced = isBalanced("{(([])[])[]}[]");
		assertEquals("YES", balanced);
	}


	@Test
	void testCheckBracketPair_5() throws Exception {
		String balanced = isBalanced("{}");
		assertEquals("YES", balanced);
	}

	@Test
	void testCheckBracketPair_6() throws Exception {
		String balanced = isBalanced("}([[{)[]))]{){}[");
		assertEquals("NO", balanced);
	}

	@Test
	void testCheckBracketPair_7() throws Exception {
		String balanced = isBalanced("{]]{()}{])");
		assertEquals("NO", balanced);
	}

	@Test
	void testCheckBracketPair_8() throws Exception {
		String balanced = isBalanced("(){}");
		assertEquals("YES", balanced);
	}

	@Test
	void testCheckBracketPair_9() throws Exception {
		String balanced = isBalanced("{}{()}{{}}");
		assertEquals("YES", balanced);
	}



	class GameStack {
		private Stack<Integer> stack = new Stack<>();

		public void pushAllDataToStack(List<Integer> list) {
			for (int i = list.size() - 1; i >= 0; i--) {
				stack.push(list.get(i));
			}
		}

		public int popStack() {
			return this.stack.pop();
		}

		public Stack<Integer> getStack() {
			return stack;
		}

		public boolean isEmpty() {
			return this.stack.isEmpty();
		}

		@Override
		public String toString() {
			return "GameStack [stack=" + stack + "]";
		}


	}


	class GameCompareOperation {
		int sumToMaintain;
		int runningSum = 0;

		int numOfIntegersRemoved = 0;



		public GameCompareOperation(int sumToMaintain) {
			super();
			this.sumToMaintain = sumToMaintain;
		}

		public int getSumToMaintain() {
			return sumToMaintain;
		}

		public void setSumToMaintain(int sumToMaintain) {
			this.sumToMaintain = sumToMaintain;
		}

		public int getRunningSum() {
			return runningSum;
		}

		public void setRunningSum(int runningSum) {
			this.runningSum = runningSum;
		}

		public int getNumOfIntegersRemoved() {
			return numOfIntegersRemoved;
		}

		public void incrementNumberOfIntegersRemoved() {
			++this.numOfIntegersRemoved;
		}

		@Override
		public String toString() {
			return "GameCompareOperation [sumToMaintain=" + sumToMaintain + ", runningSum=" + runningSum + ", numOfIntegersRemoved=" + numOfIntegersRemoved + "]";
		}



	}


	public int twoStacks(int maxSum, List<Integer> a, List<Integer> b) {
		GameStack gameStack1 = new GameStack();
		gameStack1.pushAllDataToStack(a);

		GameStack gameStack2 = new GameStack();
		gameStack2.pushAllDataToStack(b);


		Supplier<GameCompareOperation> getCompareOp = () -> {
			GameCompareOperation operation = new GameCompareOperation(maxSum);

			while (operation.getRunningSum() < operation.getSumToMaintain()) {
				Integer peek1 = gameStack1.getStack().peek();
				Integer peek2 = gameStack2.getStack().peek();
				int popVal = 0;
				if (peek1 < peek2) {
					popVal = gameStack1.popStack();
				} else {
					popVal = gameStack2.popStack();
				}
				System.out.println("Popping Value: " + popVal);
				int updatedSum = operation.getRunningSum() + popVal;
				if (updatedSum < operation.getSumToMaintain()) {
					operation.setRunningSum(updatedSum);
					operation.incrementNumberOfIntegersRemoved();
				} else {
					break;
				}
			}

			return operation;
		};



		return getCompareOp.get().numOfIntegersRemoved;
	}



	@Test
	void testTwoStacks() throws Exception {
		List<Integer> a = new ArrayList<>();
		a.add(4);
		a.add(2);
		a.add(3);
		a.add(6);
		a.add(1);

		List<Integer> b = new ArrayList<>();
		b.add(2);
		b.add(1);
		b.add(8);
		b.add(5);

		int maxSum = 10;

		int op = twoStacks(maxSum, a, b);
		System.out.println(op);
		assertEquals(4, op);
	}



	@Test
	void testTwoStacks_1() throws Exception {
		List<Integer> a = new ArrayList<>();
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);

		List<Integer> b = new ArrayList<>();
		b.add(6);
		b.add(7);
		b.add(8);
		b.add(9);

		int maxSum = 10;

		int op = twoStacks(maxSum, a, b);
		System.out.println(op);
	}

	@Test
	void testTwoStacks_2() throws Exception {
		List<Integer> a = new ArrayList<>();
		a.add(4);
		a.add(2);
		a.add(4);
		a.add(6);
		a.add(1);

		List<Integer> b = new ArrayList<>();
		b.add(2);
		b.add(1);
		b.add(8);
		b.add(5);

		int maxSum = 10;

		int op = twoStacks(maxSum, a, b);
		System.out.println(op);
	}


	@Test
	void testTwoStacks_3() throws Exception {
		List<Integer> a = new ArrayList<>();
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);

		List<Integer> b = new ArrayList<>();
		b.add(6);
		b.add(7);
		b.add(8);
		b.add(9);

		int maxSum = 12;

		int op = twoStacks(maxSum, a, b);
		System.out.println(op);
	}
}
