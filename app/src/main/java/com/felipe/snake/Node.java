package com.felipe.snake;

import android.graphics.Rect;

/**
 * @author Felipe
 *
 */
public class Node {

	private int row;
	private int column;
	private Rect rect;
	
//	private Node right

	public Node(int row, int column, Rect rect) {
		this.row = row;
		this.column = column;
		this.rect = rect;
	}

	public Rect getRect() {
		return rect;
	}

	public void setRect(Rect rect) {
		this.rect = rect;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return row + " - " + column; 
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Node toReturn = new Node(this.row, this.column, this.rect);
		return toReturn;
	}
}
