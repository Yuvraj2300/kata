package com.structures.kata;

public class QueueWithStacks {
	InfiniteStack<Integer> mainStack;
	InfiniteStack<Integer> secondStack;

	public QueueWithStacks() {
		mainStack = new InfiniteStack<Integer>(Integer.class);
		secondStack = new InfiniteStack<Integer>(Integer.class);
	}

	//1,2
	public void push(Integer data) {
		mainStack.push(data);
	}


	public Integer pop() {
		_processSecondStackInsertion();
		return secondStack.pop();

	}



	public Integer peek() {
		_processSecondStackInsertion();
		return secondStack.peek();
	}



	private void _processSecondStackInsertion() {
		if (secondStack.isEmpty()) {
			while (!mainStack.isEmpty()) {
				Integer popMainStack = mainStack.pop();
				secondStack.push(popMainStack);
			}
		}
	}


}
