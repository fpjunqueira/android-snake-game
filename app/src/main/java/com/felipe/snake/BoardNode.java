package com.felipe.snake;

import android.graphics.Rect;

/**
 * @author Felipe
 *
 */
public class BoardNode {

	private int row;
	private int column;
	private Rect rect;

	public BoardNode(int row, int column, Rect rect) {
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
		result = prime * result + ((rect == null) ? 0 : rect.hashCode());
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
		BoardNode other = (BoardNode) obj;
		if (column != other.column)
			return false;
		if (rect == null) {
			if (other.rect != null)
				return false;
		} else if (!rect.equals(other.rect))
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return row + " - " + column; 
	}
}
