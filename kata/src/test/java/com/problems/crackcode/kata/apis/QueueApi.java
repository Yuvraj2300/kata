package com.problems.crackcode.kata.apis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.junit.jupiter.api.Test;


class Point {
	private int x;
	private int y;


	//Used to maintain traversal direction associated
	//Not using in equals
	private Direction dir;

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Point(int x, int y, Direction dir) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Direction getDir() {
		return dir;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}



	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Point))
			return false;
		Point other = (Point) obj;
		return x == other.x && y == other.y;
	}

	@Override
	public String toString() {
		return "Point \n\t[x=" + x + ", \n\ty=" + y + ", \n\tdir=" + dir + "]";
	}


}

enum Direction {
	X,
	Y;
}


public class QueueApi {
	//TO Go around in the grid easily


	private int minimumMoves(List<String> gridAsList, int startX, int startY, int goalX, int goalY) {
		int m = gridAsList.size();
		int n = gridAsList.get(0).length();

		Point startPoint = new Point(startX, startY);
		Point goalPoint = new Point(goalX, goalY);

		char[][] grid = new char[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				grid[i][j] = gridAsList.get(i).toCharArray()[j];
			}
		}

		Set<Point> visitedPoints = new HashSet<>();

		Queue<Point> processQueue = new LinkedList<>();
		visitedPoints.add(startPoint);
		processQueue.add(startPoint);


		Queue<Integer> step = new LinkedList<Integer>();
		step.add(0);

		while (!processQueue.isEmpty()) {
			Point pointToCheck = processQueue.remove();
			int hop = step.remove();
			if (pointToCheck.equals(goalPoint)) {
				System.out.println("Goal was found : " + pointToCheck);
				return hop;
			}

			//get adjacent values
			List<Point> adjacentPoints = _getAdjacentPointsToExplore(pointToCheck, visitedPoints, grid);
			processQueue.addAll(adjacentPoints);

			for (int i = 0; i < adjacentPoints.size(); i++) {
				step.add(hop + 1);
			}
		}





		return 0;
	}



	private List<Point> _getAdjacentPointsToExplore(Point pointToCheck, Set<Point> visitedPoints, char[][] grid) {
		int x = pointToCheck.getX();
		int y = pointToCheck.getY();
		//	Direction dir = pointToCheck.getDir();

		List<Point> listOfAdjacentPoints = new ArrayList<>();

		for (int i = x + 1; i < grid.length; i++) {
			if (grid[i][y] == 'x') {
				break;
			}

			Point toadd = new Point(i, y, Direction.X);

			if (!visitedPoints.contains(toadd)) {
				visitedPoints.add(toadd);
				listOfAdjacentPoints.add(toadd);
			}

		}

		for (int i = x - 1; i >= 0; i--) {
			if (grid[i][y] == 'x') {
				break;
			}

			Point toadd = new Point(i, y, Direction.X);

			if (!visitedPoints.contains(toadd)) {
				visitedPoints.add(toadd);
				listOfAdjacentPoints.add(toadd);
			}
		}


		for (int i = y + 1; i < grid[0].length; i++) {
			if (grid[x][i] == 'x') {
				break;
			}

			Point toadd = new Point(x, i, Direction.Y);

			if (!visitedPoints.contains(toadd)) {
				visitedPoints.add(toadd);
				listOfAdjacentPoints.add(toadd);
			}
		}


		for (int i = y - 1; i >= 0; i--) {
			if (grid[x][i] == 'x') {
				break;
			}

			Point toadd = new Point(x, i, Direction.Y);

			if (!visitedPoints.contains(toadd)) {
				visitedPoints.add(toadd);
				listOfAdjacentPoints.add(toadd);
			}
		}


		return listOfAdjacentPoints;

	}



	@Test
	void testMinMoves() throws Exception {
		List<String> grid = new ArrayList<>();
		grid.add("...");
		grid.add(".x.");
		grid.add("...");
		//m=3x3

		int startX = 0;
		int startY = 0;

		int goalX = 1;
		int goalY = 2;

		int minimumMoves = minimumMoves(grid, startX, startY, goalX, goalY);

		assertEquals(2, minimumMoves);
	}



	@Test
	void testMinMoves_1() throws Exception {
		List<String> grid = new ArrayList<>();
		grid.add(".x.");
		grid.add(".x.");
		grid.add("...");
		//m=3x3

		int startX = 0;
		int startY = 0;

		int goalX = 0;
		int goalY = 2;

		int minimumMoves = minimumMoves(grid, startX, startY, goalX, goalY);

		assertEquals(3, minimumMoves);
	}


	@Test
	void testMinMoves_3() throws Exception {
		List<String> grid = new ArrayList<>();
		grid.add("...");
		grid.add(".x.");
		grid.add(".x.");
		//m=3x3

		int startX = 2;
		int startY = 0;

		int goalX = 2;
		int goalY = 2;

		int minimumMoves = minimumMoves(grid, startX, startY, goalX, goalY);

		assertEquals(3, minimumMoves);
	}



	/**
	 * @author yuvraj1.sharma
	 *
	 * 
	 *         You are given queries. Each query consists of a single number . You
	 *         can perform any of the operations on in each move:
	 * 
	 *         1: If we take 2 integers and where a*b == N, then we can change,
	 *         n=Maths.max(a,b)
	 * 
	 *         2: Decrease the value of N by 1
	 * 
	 *         Determine the minimum number of moves required to reduce the value of
	 *         to N to 0.
	 * @throws Exception
	 * 
	 * 
	 */
	public int downToZero(int n) throws Exception {

		Queue<Operation> processQueue = new LinkedList<>();
		int stepCount = 0;

		Operation operation = _startQueue(n);
		if (0 == operation.getN()) {
			return stepCount;
		}


		processQueue.add(operation);

		while (!processQueue.isEmpty()) {
			Operation operationFromQueue = processQueue.remove();

			if (0 == operationFromQueue.getN()) {
				break;
			} else {
				stepCount++;
			}
			// @formatter:off
			int executeOperation = operationFromQueue.operationType.executeOperation(
					operationFromQueue.getOperationType(),
					operationFromQueue.getA(),
					operationFromQueue.getB(),
					operationFromQueue.getN());
			// @formatter:on

			processQueue.add(_startQueue(executeOperation));

		}

		return stepCount;
	}



	private Operation _startQueue(int n) {

		if (0 == n) {
			return new Operation(0);
		}

		//check for operation 1
		Set<Integer> visitedDivisor = new HashSet<>();
		Operation operation = _getOperationFromOp1(n, visitedDivisor);

		if (null == operation) {
			operation = new Operation(OpType.MINUS_OP, n, 0, 0);
		}
		return operation;
	}



	private Operation _getOperationFromOp1(int n, Set<Integer> visitedDivisor) {
		Operation op = null;
		if (0 == n) {
			op = new Operation(OpType.ZERO_NUM);
		} else {
			for (int i = 2; i * i <= n; i++) {

				if (n % i == 0) {
					int divisor = i;
					int quotient = n / i;

					if (!visitedDivisor.contains(divisor)) {
						op = new Operation(OpType.MAX_FAC_OP, n, divisor, quotient);
						visitedDivisor.add(i);
					}
				}
			}

		}
		return op;
	}



	class Operation {
		private OpType operationType;


		private int n;
		private int a;
		private int b;


		public Operation(OpType operationType) {
			super();
			this.operationType = operationType;
		}

		public Operation(OpType operationType, int n, int a, int b) {
			super();
			this.operationType = operationType;
			this.n = n;
			this.a = a;
			this.b = b;
		}

		public Operation(int n) {
			super();
			this.n = n;
		}

		public OpType getOperationType() {
			return operationType;
		}

		public void setOperationType(OpType operationType) {
			this.operationType = operationType;
		}

		public int getN() {
			return n;
		}

		public void setN(int n) {
			this.n = n;
		}

		public int getA() {
			return a;
		}

		public void setA(int a) {
			this.a = a;
		}

		public int getB() {
			return b;
		}

		public void setB(int b) {
			this.b = b;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + Objects.hash(a, b, n);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!(obj instanceof Operation))
				return false;
			Operation other = (Operation) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			return a == other.a && b == other.b && n == other.n;
		}

		private QueueApi getEnclosingInstance() {
			return QueueApi.this;
		}

		@Override
		public String toString() {
			// @formatter:off
			return "Operation [operationType=" + operationType + 
					", \n\tn=" + n +
					", \n\ta=" + a +
					", \n\tb=" + b +
					"]";
			// @formatter:on
		}



	}



	enum OpType {

		ZERO_NUM(
				0),

		MAX_FAC_OP(
				1),
		MINUS_OP(
				2);

		private int val;


		private final BiFunction<Integer, Integer, Integer> op1 = (a, b) -> {
			return Math.max(a, b);
		};


		private final Function<Integer, Integer> op2 = (i) -> {
			return i - 1;
		};



		private OpType(int val) {
			this.val = val;
		}

		public int getVal() {
			return val;
		}

		public void setVal(int val) {
			this.val = val;
		}


		public int executeOperation(OpType opType, int x, int y, int n) throws Exception {
			if (OpType.MAX_FAC_OP == opType) {
				return exectuteOp1(x, y, op1);
			} else if (OpType.MINUS_OP == opType) {
				return executeOp2(n, op2);
			} else if (OpType.ZERO_NUM == opType) {
				return 0;
			} else {
				System.out.println("Undefined Operation was given");
				throw new Exception("Undefined Operation was given");
			}
		}

		private int exectuteOp1(int a, int b, BiFunction<Integer, Integer, Integer> fxn) {
			return fxn.apply(a, b);
		}


		private int executeOp2(int a, Function<Integer, Integer> fxn) {
			return fxn.apply(a);
		}

	}



	@Test
	void testDownToZero() throws Exception {
		try {
			List<Integer> listOfInts = new ArrayList<Integer>();
			List<Integer> listOfOps = new ArrayList<Integer>();
			listOfInts.add(3);
			listOfInts.add(4);

			for (int i : listOfInts) {
				int op;
				op = downToZero(i);
				listOfOps.add(op);

			}

			assertFalse(listOfOps.isEmpty());
			System.out.println(listOfOps);
			assertEquals(3, listOfOps.get(0));
			assertEquals(3, listOfOps.get(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Test
	void testDownToZero_1() throws Exception {
		try {
			List<Integer> listOfInts = new ArrayList<Integer>();
			List<Integer> listOfOps = new ArrayList<Integer>();
			listOfInts.add(12);

			for (int i : listOfInts) {
				int op;
				op = downToZero(i);
				listOfOps.add(op);

			}

			assertFalse(listOfOps.isEmpty());
			System.out.println(listOfOps);
			assertEquals(4, listOfOps.get(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Test
	void testDownToZero_2() throws Exception {
		try {
			List<Integer> listOfInts = new ArrayList<Integer>();
			List<Integer> listOfOps = new ArrayList<Integer>();
			listOfInts.add(76);

			for (int i : listOfInts) {
				int op;
				op = downToZero(i);
				listOfOps.add(op);

			}

			assertFalse(listOfOps.isEmpty());
			System.out.println(listOfOps);
			assertEquals(7, listOfOps.get(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	class Truck {
		private int fuel = 0;

		public Truck() {
			super();
		}




		public Truck(int fuel) {
			super();
			this.fuel = fuel;
		}




		@Override
		public String toString() {
			return "Truck [fuel=" + fuel + "]";
		}


		public int getFuel() {
			return fuel;
		}


		public void setFuel(int fuel) {
			this.fuel = fuel;
		}



	}


	class GasStation {
		private int fuelToSell;
		private int distanceToNextStation;

		private GasStation next;



		public GasStation(int fuelToSell, int distanceToNextStation, GasStation next) {
			super();
			this.fuelToSell = fuelToSell;
			this.distanceToNextStation = distanceToNextStation;
			this.next = next;
		}



		public GasStation(int fuelToSell, int distanceToNextStation) {
			super();
			this.fuelToSell = fuelToSell;
			this.distanceToNextStation = distanceToNextStation;
		}



		public GasStation() {
			super();
		}



		public GasStation getNext() {
			return next;
		}



		public void setNext(GasStation next) {
			this.next = next;
		}



		public int getFuelToSell() {
			return fuelToSell;
		}



		public void setFuelToSell(int fuelToSell) {
			this.fuelToSell = fuelToSell;
		}



		public int getDistanceToNextStation() {
			return distanceToNextStation;
		}



		public void setDistanceToNextStation(int distanceToNextStation) {
			this.distanceToNextStation = distanceToNextStation;
		}



		@Override
		public String toString() {
			return "GasStation [fuelToSell=" + fuelToSell + ", \ndistanceToNextStation=" + distanceToNextStation + "\n]";
		}



		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + Objects.hash(distanceToNextStation, fuelToSell);
			return result;
		}



		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!(obj instanceof GasStation))
				return false;
			GasStation other = (GasStation) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			return distanceToNextStation == other.distanceToNextStation && fuelToSell == other.fuelToSell;
		}



		private QueueApi getEnclosingInstance() {
			return QueueApi.this;
		}



	}



	public int truckTour(List<GasStation> gasStations) {
		int startingPoint = 0;
		boolean startingPointFound = false;

		for (int i = 0; i < gasStations.size(); i++) {
			GasStation startingStation = gasStations.get(i);
			Truck truck = new Truck();
			Set<GasStation> visitedGasStations = new HashSet<>();
			Queue<GasStation> processQueue = new LinkedList<GasStation>();

			GasStation currentGasStation = gasStations.get(i);
			processQueue.add(currentGasStation);
			visitedGasStations.add(currentGasStation);

			while (!processQueue.isEmpty()) {
				GasStation inProcessStation = processQueue.remove();
				truck.setFuel(truck.getFuel() + inProcessStation.getFuelToSell());

				if (truck.getFuel() < inProcessStation.getDistanceToNextStation()) {
					break;
				}

				if (null != inProcessStation.getNext() && !visitedGasStations.contains(inProcessStation.getNext())) {
					visitedGasStations.add(inProcessStation.getNext());
					processQueue.add(inProcessStation.getNext());
				} else if (visitedGasStations.contains(startingStation)) {
					System.out.println("Valid Starting station  : -->\n" + startingStation);
					startingPointFound = true;
					break;
				}
			}

			if (startingPointFound) {
				startingPoint = i;
				break;
			}


		}
		return startingPoint;
	}



	@Test
	void testTruckTour() throws Exception {
		List<GasStation> listOfGasStations = new ArrayList<>();

		GasStation st1 = new GasStation(1, 5);
		GasStation st2 = new GasStation(10, 3);
		GasStation st3 = new GasStation(3, 4);

		st1.setNext(st2);
		st2.setNext(st3);
		st3.setNext(st1);

		listOfGasStations.add(st1);
		listOfGasStations.add(st2);
		listOfGasStations.add(st3);

		int startingPoint = truckTour(listOfGasStations);

		assertEquals(1, startingPoint);
	}



	@Test
	void testTruckTour_1() throws Exception {
		List<GasStation> listOfGasStations = new ArrayList<>();

		GasStation st1 = new GasStation(20, 5);
		GasStation st2 = new GasStation(10, 3);
		GasStation st3 = new GasStation(3, 4);

		st1.setNext(st2);
		st2.setNext(st3);
		st3.setNext(st1);

		listOfGasStations.add(st1);
		listOfGasStations.add(st2);
		listOfGasStations.add(st3);

		int startingPoint = truckTour(listOfGasStations);

		assertEquals(0, startingPoint);
	}


}
