package com.bjagud;

/**
 * This heap-like data structure will allow us to sort input
 * as we read it in. Sorting will require no additional memory, 
 * but accessing the median instantly will not be possible. 
 * 
 * This implementation uses an array to represent the heap, this
 * felt the most straight-forward, since we are working with 
 * integers, and not complex data types. 
 * 
 * To heapify the array we use sink rather than swim, the reason for
 * this is that we only need to iterate over half the array, since 
 * we know that all root elements of the heap will be sorted as we 
 * iterate the parents. 
 * 
 * @author bjarni
 */
public class StatsHeap {

	private int size;
	private int currentMin = 0;
	
	/**
	 Array representation of a heap. Index 0 will be left unused, 
	 this is a standard that makes heap operations more comfortable. 
	 At any time we know that the element at index K has children 
	 located at indices 2K and 2K + 1. */
	private int heapArray[];
	
	
	StatsHeap(int size) {
		this.size = size + 1;
		this.heapArray = new int[this.size];
	}
	
	/**
	 Insert the given element into the heap */
	public void insert(int num) {
		
		currentMin++;
		heapArray[currentMin] = num;
		swim(currentMin);
	}
	
	/**
	 Get the max element without removing it from the heap */
	public int peakMax() {
		return heapArray[1];
	}
	
	/**
	 The node at the given index "swims up" until it's 
	 located in the correct place in the heap with relation
	 to its parent. */
	private void swim(int node) {
				
		while(node > 1) {		
			
			int parent = node/2;
			if(!(heapArray[parent] < heapArray[node])) {
				//Parent is larger, swim no more
				break;
			}
			
			//Just keep swimming
			exch(node, parent);
			node = parent;
		}
	}
	
	/**
	 Exchanges the elements at indices a and b */
	private void exch(int a, int b) {		
		int temp = heapArray[a];
		heapArray[a] = heapArray[b];
		heapArray[b] = temp;
	}
	
	// -- Work in progress below this line -- 
	
	/**
	 Pop the max element of the heap, returns max element */
	public int delMax() {
		return 0;
	}	
	
	
	/** 
	 Converts a normal array into a heap */
	private void heapify() {		
		for(int index = size/2; index >= 1; index--) {
			sink(index);
		}
	}
	
	
	/**
	 The node at the given index, is "sunk" (i.e. moved lower
	 down in the heap) until it is in the correct place relative
	 to its children. */
	private void sink(int node) {
		
		while(node * 2 < size) {
			
			int child = node * 2;			
			if(child < size && heapArray[child] < heapArray[child + 1]) {
				//Child has a larger sibling
				child++;
			}
			
			if(!(heapArray[node] < heapArray[child])) {
				//Node is in correct place relative to children
				break;
			}
			
			//Parent node sinks down
			exch(node, child);
			node = child;
		}
	}
}