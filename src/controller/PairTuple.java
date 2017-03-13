package controller;


public class PairTuple<T1,T2> {
	public T1 left;
	public T2 right;
	
	public PairTuple(T1 left, T2 right) {
		this.left = left;
		this.right = right;
	}
}
