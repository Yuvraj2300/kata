package com.problems.crackcode.kata.apis;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
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
		Stack<Character> parenthesisStack = new Stack<Character>();
		Set<Character> openParens = new HashSet<>(List.of('(', '[', '{'));
		Set<Character> closeParens = new HashSet<>(List.of(')', ']', '}'));

		for (int i = 0; i < s.length(); i++) {
			char charAt = s.charAt(i);
			if (openParens.contains(charAt)) {
				parenthesisStack.push(charAt);
			}

			if (closeParens.contains(charAt)) {
				if (parenthesisStack.isEmpty()) {
					goodPairs = false;
					break;
				} else {
					Character currParen = parenthesisStack.pop();

					if (charAt == ')' && (currParen == '{' || currParen == '[')) {
						goodPairs = false;
					}
					if (charAt == '}' && (currParen == '(' || currParen == '[')) {
						goodPairs = false;
					}
					if (charAt == ']' && (currParen == '{' || currParen == '(')) {
						goodPairs = false;
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

	class ParenPair {
		int goodPairs;
		int badPairs;

		public ParenPair(int goodPairs, int badPairs) {
			super();
			this.goodPairs = goodPairs;
			this.badPairs = badPairs;
		}

		@Override
		public String toString() {
			return "ParenPair [goodPairs=" + goodPairs + ", badPairs=" + badPairs + "]";
		}


	}

	public ParenPair getNumOfGoodPair(String s) {
		int goodPair = 0;
		int badPair = 0;

		Stack<Character> parenthesisStack = new Stack<Character>();
		Set<Character> openParens = new HashSet<>(List.of('(', '[', '{'));
		Set<Character> closeParens = new HashSet<>(List.of(')', ']', '}'));

		for (int i = 0; i < s.length(); i++) {
			char charAt = s.charAt(i);
			if (openParens.contains(charAt)) {
				parenthesisStack.push(charAt);
			}

			if (closeParens.contains(charAt)) {
				if (parenthesisStack.isEmpty()) {
					break;
				} else {
					Character currParen = parenthesisStack.pop();

					if (charAt == ')' && (currParen == '{' || currParen == '[')) {
						badPair++;
						continue;
					}
					if (charAt == '}' && (currParen == '(' || currParen == '[')) {
						badPair++;
						continue;
					}
					if (charAt == ']' && (currParen == '{' || currParen == '(')) {
						badPair++;
						continue;
					}
				}
				goodPair++;
			}

		}

		return new ParenPair(goodPair, badPair);
	}


	@Test
	void testNumOfPairs() throws Exception {
		ParenPair pairCounts = getNumOfGoodPair("{{([])}}");
		System.out.println(pairCounts);
	}

	//

	@Test
	void testNumOfPairs_1() throws Exception {
		ParenPair pairCounts = getNumOfGoodPair("{({])}");
		System.out.println(pairCounts);
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
				if (buildingToCheck.getHeight() <= buildings.get(i).getHeight()) {
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
				if (buildingToCheck.getHeight() <= buildings.get(i).getHeight()) {
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


	@Test
	void testLargestRectangle_4() throws Exception {
		List<Integer> l = new ArrayList<>();
		l.add(11);
		l.add(11);
		l.add(10);
		l.add(10);
		l.add(10);
		long area = largestRectangle(l);
		System.out.println(area);
		assertEquals(50, area);
	}


	class PlantsRow {
		Stack<FertilizerAmount> fertAmountStack = new Stack<>();

		public void push(FertilizerAmount fertAmount) {
			this.fertAmountStack.push(fertAmount);
		}

		public FertilizerAmount pop() {
			return this.fertAmountStack.pop();
		}

		@Override
		public String toString() {
			return "PlantsRow [fertAmountStack=" + fertAmountStack + "]";
		}

	}


	class FertilizerAmount {
		int data;

		public FertilizerAmount(int data) {
			super();
			this.data = data;
		}

		@Override
		public String toString() {
			return "FertilizerAmount [data=" + data + "]";
		}

	}

	public int poisonousPlants(List<Integer> p) {
		PlantsRow plantsRow = new PlantsRow();

		for (int i = p.size() - 1; i >= 0; i--) {
			plantsRow.push(new FertilizerAmount(p.get(i)));
		}

		return _processAPlantRow(plantsRow);
	}


	private int _processAPlantRow(PlantsRow plantsRow) {
		int rowsCreated = 0;
		Queue<PlantsRow> processQueue = new LinkedList<>();
		processQueue.add(plantsRow);
		while (!processQueue.isEmpty()) {
			PlantsRow poll = processQueue.poll();

			PlantsRow newRow = new PlantsRow();

			while (!poll.fertAmountStack.isEmpty()) {
				FertilizerAmount stackPop = poll.fertAmountStack.pop();
				FertilizerAmount peek = null;
				try {
					peek = poll.fertAmountStack.peek();
				} catch (Exception ex) {
					peek = null;
				}

				if (stackPop == null) {
					break;
				} else if (null == peek && null != stackPop) {
					newRow.push(stackPop);
				} else if (stackPop.data < peek.data) {
					newRow.fertAmountStack.push(stackPop);
				}
				//				} else {
				//					break;
				//				}
			}

			if (newRow.fertAmountStack.size() > 1) {
				System.out.println("New Row created : " + newRow);
				rowsCreated++;
				processQueue.add(newRow);
			}
		}
		return rowsCreated;
	}


	@Test
	void testPoisonousPlants() throws Exception {
		List<Integer> plants = new ArrayList<Integer>();
		plants.add(3);
		plants.add(6);
		plants.add(2);
		plants.add(7);
		plants.add(5);

		int hops = poisonousPlants(plants);
		assertEquals(2, hops);
	}


	@Test
	void testPoisonousPlants1() throws Exception {
		List<Integer> plants = new ArrayList<Integer>();
		plants.add(14);
		plants.add(55);
		plants.add(2);
		plants.add(7);

		int hops = poisonousPlants(plants);
		assertEquals(2, hops);
	}


	@Test
	void testPoisonousPlants2() throws Exception {
		List<Integer> plants = new ArrayList<Integer>();
		plants.add(3);
		plants.add(2);
		plants.add(5);
		plants.add(4);

		int hops = poisonousPlants(plants);
		assertEquals(2, hops);
	}



	public String convertToPostFix(String inFix) {
		Set<Character> setOfOperators = new HashSet<>();
		setOfOperators.addAll(List.of('+', '-', '*', '/', '%'));

		Stack<Character> operators = new Stack<Character>();

		inFix += ")";
		//		operators.push('(');

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < inFix.length(); i++) {
			char charAt = inFix.charAt(i);
			if (setOfOperators.contains(charAt)) {
				if (!operators.isEmpty() && operators.peek() != '(') {
					while (!operators.isEmpty() && _getPriority(operators.peek()) >= _getPriority(charAt)) {
						sb.append(operators.pop());
					}
				}
				operators.push(charAt);
			} else if (charAt == ')') {
				while (!operators.isEmpty() && operators.peek() != '(') {
					sb.append(operators.pop());
				}
			} else if (charAt == '(') {
				operators.push('(');
			} else {
				sb.append(charAt);
			}
			//			else if (charAt == '(') {
			//				//?
			//			}

		}


		//clear backlog
		if (!operators.isEmpty()) {
			while (!operators.isEmpty()) {
				Character pop = operators.pop();

				if ('(' != pop) {
					sb.append(pop);
				}
			}
		}

		return sb.toString();
	}




	private int _getPriority(char c) {
		if (c == '/' || c == '*' || c == '%') {
			return 1;
		}
		//for + - 
		return 0;
	}


	@Test
	void testConvertToPostFix() throws Exception {
		String postFix = convertToPostFix("A+B");
		assertEquals("AB+", postFix);
	}


	@Test
	void testConvertToPostFix_1() throws Exception {
		String postFix = convertToPostFix("A+B*C");
		assertEquals("ABC*+", postFix);
	}

	@Test
	void testConvertToPostFix_2() throws Exception {
		String postFix = convertToPostFix("(A+B)*C");
		assertEquals("AB+C*", postFix);
	}


	@Test
	void testConvertToPostFix_3() throws Exception {
		String postFix = convertToPostFix("A-(B/C+(D%E*F)/G)*H");
		assertEquals("ABC/DE%F*G/+H*-", postFix);
	}


	@Test
	void testConvertToPostFix_4() throws Exception {
		String postFix = convertToPostFix("9-((3*4)+8)/4");
		assertEquals("934*8+4/-", postFix);
	}


	class Expression {
		int a;
		int b;

		public Expression(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}

		@Override
		public String toString() {
			return "Expression [a=" + a + ", b=" + b + "]";
		}


	}


	private int evaluatePostFix(String postFix) {
		BiFunction<Expression, Character, Integer> calcuate = (expression, operator) -> {
			switch (operator) {
			case '+':
				return expression.b + expression.a;
			case '-':
				return expression.b - expression.a;
			case '*':
				return expression.b * expression.a;
			case '/':
				return expression.b / expression.a;
			case '%':
				return expression.b % expression.a;
			default:
				return 0;
			}

		};

		Stack<Integer> operands = new Stack<>();

		Set<Character> setOfOperators = new HashSet<>();
		setOfOperators.addAll(List.of('+', '-', '*', '/', '%'));

		for (int i = 0; i < postFix.length(); i++) {
			char charAt = postFix.charAt(i);
			if (!setOfOperators.contains(charAt)) {
				operands.push(Character.getNumericValue(charAt));
			} else {
				if (!operands.isEmpty()) {
					int a = operands.pop();
					int b = operands.pop();
					Expression expression = new Expression(a, b);

					int midSum = calcuate.apply(expression, charAt);
					operands.push(midSum);
				}
			}
		}

		return operands.pop();
	}



	@Test
	void testEvaluatingPostFix() throws Exception {
		String postFix = convertToPostFix("9-((3*4)+8)/4");
		int calc = evaluatePostFix(postFix);
		assertEquals(4, calc);
	}



	public void towerOfHanoi(Stack<Integer> src, Stack<Integer> dest, Stack<Integer> spare) {
		int n = src.size();

		towerOfHanoiHelper(n, src, dest, spare);
		System.out.println(src);
		System.out.println(spare);
		System.out.println(dest);
	}

	/**
	 * @author yuvraj1.sharma
	 *
	 *         if stuff can be done on n-1 then it can also be done on n
	 */
	private void towerOfHanoiHelper(int n, Stack<Integer> src, Stack<Integer> dest, Stack<Integer> spare) {
		if (n == 1) {
			spare.push(src.pop());
			dest.push(spare.pop());
			return;
		}


		//n-1 move from A to B using C as spare
		towerOfHanoiHelper(n - 1, src, spare, dest);
		//Move the nth from A to C using B as spare (Base case)
		towerOfHanoiHelper(1, src, dest, spare);
		//Move n-1 From B to C using A as spare
		towerOfHanoiHelper(n - 1, spare, dest, src);
	}


	@Test
	void testTowerOfHanoi() throws Exception {
		Stack<Integer> src = new Stack<Integer>();
		src.push(15);
		src.push(12);
		src.push(11);
		src.push(10);
		src.push(5);
		src.push(3);
		src.push(1);

		Stack<Integer> spare = new Stack<Integer>();
		Stack<Integer> dest = new Stack<Integer>();


		towerOfHanoi(src, spare, dest);
	}



	private Map<Integer, Integer> getTheNGE(int[] a) {
		Map<Integer, Integer> res = new TreeMap<Integer, Integer>();
		Stack<Integer> stack = new Stack<>();

		int j = a.length - 1;

		while (j >= 0) {
			if (j == a.length - 1) {
				res.put(a[j], -1);
				stack.push(a[j]);
			} else if (!stack.isEmpty() && stack.peek() < a[j]) {
				stack.pop();
			}

			if (!stack.isEmpty() && stack.peek() > a[j]) {
				Integer peek = stack.peek();
				res.put(a[j], peek);
				stack.push(a[j]);
			} else {
				res.put(a[j], -1);
			}
			j--;
		}

		return res;
	}



	@Test
	void testGetTheNGE() {
		int[] a = { 4, 5, 2, 25 };
		Map<Integer, Integer> mapOfNge = getTheNGE(a);
		System.out.println(mapOfNge);
	}


	@Test
	void testGetTheNGE_1() {
		int[] a = { 13, 7, 6, 12 };
		Map<Integer, Integer> mapOfNge = getTheNGE(a);
		System.out.println(mapOfNge);
	}

	class PairingOfValAndFreq {
		int value;
		int freq;

		public PairingOfValAndFreq(int value, int freq) {
			super();
			this.value = value;
			this.freq = freq;
		}

		@Override
		public String toString() {
			return "ValnFreq [value=" + value + ", \nfreq=" + freq + "]";
		}


	}

	private List<Integer> getTheNGF(int[] a) {
		List<Integer> res = new ArrayList<Integer>();
		Map<Integer, Integer> mapOfValsAndOcurrences = new HashMap<Integer, Integer>();
		Stack<PairingOfValAndFreq> stack = new Stack<PairingOfValAndFreq>();

		for (int i = 0; i < a.length; i++) {
			mapOfValsAndOcurrences.put(a[i], mapOfValsAndOcurrences.getOrDefault(a[i], 0) + 1);
		}

		int j = a.length - 1;
		while (j >= 0) {
			if (j == a.length - 1) {
				stack.push(new PairingOfValAndFreq(a[j], mapOfValsAndOcurrences.get(a[j])));
				res.add(-1);
			} else if (!stack.isEmpty() && stack.peek().freq > mapOfValsAndOcurrences.get(a[j])) {
				res.add(stack.peek().value);
				stack.push(new PairingOfValAndFreq(a[j], mapOfValsAndOcurrences.get(a[j])));
			} else {
				while (!stack.isEmpty() && !(stack.peek().freq >= mapOfValsAndOcurrences.get(a[j]))) {
					stack.pop();
				}

				if (!stack.isEmpty() && stack.peek().freq >= mapOfValsAndOcurrences.get(a[j])) {
					res.add(stack.peek().value);
				} else {
					res.add(-1);
					stack.push(new PairingOfValAndFreq(a[j], mapOfValsAndOcurrences.get(a[j])));
				}
			}

			j--;
		}

		Collections.reverse(res);
		return res;
	}



	@Test
	void testNextGreaterFrequency() throws Exception {
		int[] a = { 1, 1, 1, 2, 2, 2, 2, 11, 3, 3 };
		List<Integer> listOfNgf = getTheNGF(a);
		System.out.println(listOfNgf);
	}



	@Test
	void testNextGreaterFrequency_1() throws Exception {
		int[] a = { 1, 1, 2, 3, 4, 2, 1 };
		List<Integer> listOfNgf = getTheNGF(a);
		System.out.println(listOfNgf);
	}



	private int findTheCeleb(int numberOfPeople, int[][] relMatrix) {
		Stack<Integer> contestantStack = new Stack<Integer>();

		for (int i = 0; i < numberOfPeople; i++) {
			contestantStack.push(i);
		}

		while (contestantStack.size() > 1) {
			int contestant1 = contestantStack.pop();
			int contestant2 = contestantStack.pop();

			if (_isKnowing(contestant1, contestant2, relMatrix)) {
				//cont2 is the winner;
				contestantStack.push(contestant2);
			} else {
				//cont1 is the winner;
				contestantStack.push(contestant1);
			}

		}

		int winningCandidate = -1;
		if (!contestantStack.isEmpty() && contestantStack.size() == 1) {
			winningCandidate = contestantStack.pop();
			for (int i = 0; i < numberOfPeople; i++) {
				if (i != winningCandidate) {
					if (!_isKnowing(i, winningCandidate, relMatrix))
						return -1;
				}
			}
		}

		return winningCandidate;
	}

	private boolean _isKnowing(int a, int b, int[][] relMatrix) {
		return relMatrix[a][b] == 1 ? true : false;
	}



	@Test
	void testFindTheCelebrity() throws Exception {
		// @formatter:off
		int[][] relMatrix = { 
								{ 0, 0, 1, 0 }, 
								{ 0, 0, 1, 0 },
								{ 0, 0, 0, 0 },
								{ 0, 0, 1, 0 }
							};
		// @formatter:on

		int numberOfPeople = 4;

		int idOfCeleb = findTheCeleb(numberOfPeople, relMatrix);
		assertEquals(2, idOfCeleb);
	}


	@Test
	void testFindTheCelebrity_1() throws Exception {
		// @formatter:off
		int[][] relMatrix = { 
								{ 0, 0, 1, 0 }, 
								{ 0, 0, 1, 0 },
								{ 0, 0, 0, 0 },
								{ 0, 0, 1, 0 }
							};
		// @formatter:on

		int numberOfPeople = 4;

		int idOfCeleb = findTheCeleb(numberOfPeople, relMatrix);
		assertEquals(2, idOfCeleb);
	}



	private int getMaxAreaInTheHistograms(int[] a) {
		//the idea is to use the concept of hashing (storing indices of the array 
		//in a datastructures and on the basis of the business rules excute calculations
		// storing the index so that we can calculate the area of the contigous bar
		int maxArea = 0;
		int i = 0;

		Stack<Integer> stack = new Stack<Integer>();

		while (i < a.length) {

			if (stack.isEmpty() || a[i] > a[stack.peek()]) {
				//store the index
				stack.push(i);
				//iterate forward only if next element is bigger , i.e contiguous
				i++;
			} else {
				//now calculate the area since the current value at i 
				//in the array is smaller and the previous val is not contiguous anymore
				int currHeight = a[stack.pop()];
				int currBrdth = 0;

				if (stack.isEmpty()) {
					currBrdth = 1;
				} else {
					// 0,___peek()____i____n-1,n
					currBrdth = i - stack.peek() - 1;
				}

				int currArea = currHeight * currBrdth;

				if (currArea > maxArea) {
					maxArea = currArea;
				}

			}

		}

		//stack might still have some indices to look at
		while (!stack.isEmpty()) {
			int currHeight = a[stack.pop()];
			int currBrdth = 0;

			if (stack.isEmpty()) {
				currBrdth = 1;
			} else {
				// 0,___peek()____i____n-1,n
				currBrdth = i - stack.peek() - 1;
			}

			int currArea = currHeight * currBrdth;

			if (currArea > maxArea) {
				maxArea = currArea;
			}
		}


		return maxArea;
	}



	@Test
	void testGetMaxAreaInTheHistograms() throws Exception {
		int[] a = { 6, 2, 5, 4, 5, 1, 6 };
		int maxArea = getMaxAreaInTheHistograms(a);
		assertEquals(12, maxArea);
	}


	@Test
	void testGetMaxAreaInTheHistograms_1() throws Exception {
		int[] a = { 1, 6, 8, 1 };
		int maxArea = getMaxAreaInTheHistograms(a);
		assertEquals(12, maxArea);
	}



	private String reverseString(String string) {
		Stack<Character> stack = new Stack<Character>();
		StringBuilder sb = new StringBuilder();

		int i = 0;
		while (i < string.length()) {
			stack.push(string.charAt(i));
			i++;
		}


		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}

		return sb.toString();
	}



	@Test
	void testReverseAString() throws Exception {
		String reversedString = reverseString("hello");
		assertEquals("olleh", reversedString);
	}



	private List<Integer> findMaximaFromMinimas(int[] a) {
		BiFunction<Integer, Integer, Integer> getMaximaFromCurrentIndexAndSize = (i, size) -> {
			
			
			return 0;
		};



		Map<Integer, Integer> mapOfWindowAndMaxima = new TreeMap<Integer, Integer>();

		int i = 0;
		while (i < a.length) {

		}
		return null;
	}



	@Test
	void testFindMaximaFromMinimas() {
		int[] a = { 10, 20, 30, 50, 10, 70, 30 };
		List<Integer> res = findMaximaFromMinimas(a);
	}


}
