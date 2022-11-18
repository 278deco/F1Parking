package fr.f1parking.core.utils;

public class Tuple<A, B> {

	private A valueA;
	private B valueB;
	
	public Tuple(A valueA, B valueB) {
		this.valueA = valueA;
		this.valueB = valueB;
	}
	
	public A getValueA() {
		return valueA;
	}
	
	public B getValueB() {
		return valueB;
	}
 	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Tuple  && isEqual((Tuple<?, ?>)obj);
	}
	
	private boolean isEqual(Tuple<?, ?> tuple) {
		return tuple.getValueA().equals(this.getValueA()) && tuple.getValueB().equals(this.getValueB());
	}
	
	public String toString() {
		return "( "+ valueA + " : " + valueB +" )";
	}
	
}
