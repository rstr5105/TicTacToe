package com.badwater.Helpers;


/**
 * Created by irinix on 8/3/14.
 */
public class Tuple<X, Y> {
	public final X x;
	public final Y y;

	public Tuple(X x, Y y) {
		this.x = x;
		this.y = y;
	}

	public X getFirst() {
		return x;
	}

	public Y getSecond() {
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( x == null ) ? 0 : x.hashCode () );
		result = prime * result + ( ( y == null ) ? 0 : y.hashCode () );
		return result;
	}

	@Override
	public boolean equals(Object other) {
		if ( other == null ) {
			return false;
		}
		if ( other == this ) {
			return true;
		}
		if ( !( other instanceof Tuple ) ) {
			return false;
		}
		Tuple<X, Y> other_ = (Tuple<X, Y>) other;
		return other_.x == this.x && other_.y == this.y;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}