package com.structures.kata;

public class MinTrackStack {
	InfiniteStack<Integer> mainStack;
	InfiniteStack<Integer> minStack;

	public MinTrackStack() {
		mainStack = new InfiniteStack<>(Integer.class);
		minStack = new InfiniteStack<>(Integer.class);
	}

	public Integer peek() {
		return mainStack.peek();
	}


	public void push(Integer data) {
		if (!minStack.isEmpty() && minStack.peek() > data) {
			minStack.push(data);
		} else if (minStack.isEmpty()) {
			minStack.push(data);
		}

		mainStack.push(data);
	}


	public Integer pop() {
		try {
			if (!minStack.isEmpty() && this.peek() == minStack.peek()) {
				minStack.pop();
			}
			return mainStack.pop();
		} catch (Exception e) {
			System.out.println("There was an error with popping elements off the stack." + e.getMessage());
			return 0;
		}

	}


	public Integer min() {
		if (!minStack.isEmpty()) {
			return minStack.peek();
		} else {
			return 0;
		}
	}
}
