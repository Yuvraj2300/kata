package com.problems.crackcode.kata.apis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Supplier;

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


	class Building {
		private int height;
		private int position;

		public Building(int height) {
			super();
			this.height = height;
		}


		public Building(int height, int position) {
			super();
			this.height = height;
			this.position = position;
		}


		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}


		public int getPosition() {
			return position;
		}


		public void setPosition(int position) {
			this.position = position;
		}


		@Override
		public String toString() {
			return "Building [height=" + height + ", position=" + position + "]";
		}
	}

	class Area {
		int ht;
		int brdth;


		public Area(int ht, int brdth) {
			super();
			this.ht = ht;
			this.brdth = brdth;
		}


		public int getHt() {
			return ht;
		}


		public void setHt(int ht) {
			this.ht = ht;
		}




		public int getBrdth() {
			return brdth;
		}




		public void setBrdth(int brdth) {
			this.brdth = brdth;
		}


		@Override
		public String toString() {
			return "Area [ht=" + ht + " x brdth=" + brdth + "]";
		}
	}




	/**
	 * @author yuvraj1.sharma
	 *
	 *         https://www.hackerrank.com/challenges/largest-rectangle/problem?isFullScreen=true
	 */
	public long largestRectangle(List<Integer> h) {
		Map<Area, Integer> mapOfAreaAndCalc = new LinkedHashMap<>();



		BiFunction<List<Building>, Building, Integer> getBreadthForward = (buildings, buildingToCheck) -> {
			int brdth = 1;
			for (int i = buildingToCheck.getPosition() + 1; i < buildings.size(); i++) {
				if (buildingToCheck.getHeight() < buildings.get(i).getHeight()) {
					brdth++;
				} else {
					break;
				}
			}

			return brdth;
		};



		BiFunction<List<Building>, Building, Integer> getBreadthBackwards = (buildings, buildingToCheck) -> {
			int brdth = 0;
			for (int i = buildingToCheck.getPosition() - 1; i >= 0; i--) {
				if (buildingToCheck.getHeight() < buildings.get(i).getHeight()) {
					brdth++;
				} else {
					break;
				}
			}

			return brdth;
		};


		List<Building> listOfBuildings = new LinkedList<>();

		//		for (int i = h.size() - 1; i >= 0; i--) {
		//			listOfBuildings.add(new Building(h.get(i), i));
		//		}

		for (int i = 0; i < h.size(); i++) {
			listOfBuildings.add(new Building(h.get(i), i));
		}


		for (int i = 0; i < listOfBuildings.size(); i++) {
			Building currentBuilding = listOfBuildings.get(i);

			Integer applicableBreathForward = getBreadthForward.apply(listOfBuildings, currentBuilding);
			Integer applicableBreathBackward = getBreadthBackwards.apply(listOfBuildings, currentBuilding);

			Area areaObj = new Area(currentBuilding.getHeight(), applicableBreathForward + applicableBreathBackward);
			mapOfAreaAndCalc.put(areaObj, areaObj.getBrdth() * areaObj.getHt());
		}

		System.out.println(mapOfAreaAndCalc);
		// @formatter:off
		Optional<java.util.Map.Entry<Area, Integer>> max = mapOfAreaAndCalc.entrySet()
			.stream()
			.max(Comparator.comparing(Map.Entry::getValue));
 
		// @formatter:on

		return max.get().getValue();
	}

	@Test
	void testLargestRectangle() throws Exception {
		List<Integer> l = new ArrayList<>();
		l.add(3);
		l.add(2);
		l.add(3);
		long area = largestRectangle(l);
		assertEquals(6, area);
	}

	@Test
	void testLargestRectangle_1() throws Exception {
		List<Integer> l = new ArrayList<>();
		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);
		l.add(5);
		long area = largestRectangle(l);
		assertEquals(9, area);
	}




	@Test
	void testLargestRectangle_2() throws Exception {
		List<Integer> l = new ArrayList<>();
		l.add(8979);
		l.add(4570);
		l.add(6436);
		l.add(5083);
		l.add(7780);
		l.add(3269);
		l.add(5400);
		l.add(7579);
		l.add(2324);
		l.add(2116);
		long area = largestRectangle(l);
		System.out.println(area);
		assertEquals(26152, area);
	}


	@Test
	void testLargestRectangle_3() throws Exception {
		List<Integer> l = new ArrayList<>();
		l.add(2);
		l.add(6);
		l.add(9);
		l.add(18);
		l.add(3);
		l.add(4);
		l.add(16);
		long area = largestRectangle(l);
		System.out.println(area);
		assertEquals(18, area);
	}
}
