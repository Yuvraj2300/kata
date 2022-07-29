package com.structures.kata;

public class SortedStack {
	InfiniteStack<Integer> mainStack;
	InfiniteStack<Integer> tempStack;

	public SortedStack() {
		mainStack = new InfiniteStack<Integer>(Integer.class);
		tempStack = new InfiniteStack<Integer>(Integer.class);
	}

	public void push(Integer data) {
		if (null != mainStack.peek() && data < mainStack.peek()) {
			mainStack.push(data);
		} else {
			while (!mainStack.isEmpty()) {
				tempStack.push(mainStack.pop());
			}

			mainStack.push(data);

			while (!tempStack.isEmpty()) {
				mainStack.push(tempStack.pop());
			}
		}
	}


	public Integer pop() {
		return mainStack.pop();
	}


	@Override
	public String toString() {
		return mainStack.toString();
	}
}
