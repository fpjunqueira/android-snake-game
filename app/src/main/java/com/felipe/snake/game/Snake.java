package com.felipe.snake.game;

import java.util.LinkedList;

import com.felipe.snake.Direction;
import com.felipe.snake.Node;

// sudo chown fj /dev/kvm
public class Snake {

	private LinkedList<Node> body;
	private int endRow;
	private int endColumn;
	private Direction direction;
	
	public Snake(int maxRows, int maxColumns) {
		this.endRow = maxRows;
		this.endColumn = maxColumns;
		this.setBody(new LinkedList<Node>());
		this.direction = Direction.RIGHT;
		
		
	}

	public void move(Direction direction) {

		if (!validadeDirection(direction)) {
			return;
		}

		Node head = getBody().getLast();

		for (int i = 0; i < body.size() - 1; i++) {
			Node nexNode = body.get(i + 1);
			Node node = body.get(i);
			node.setRow(nexNode.getRow());
			node.setColumn(nexNode.getColumn());
		}
		
		moveHead(direction, head);
		
		this.direction = direction;
	}
	
	public boolean verify(int row, int column) {
		for (Node node : this.getBody()) {
			if (node.getRow() == row && node.getColumn() == column) {
				return true;
			}
		}
		return false;
	}
	
	private void moveHead(Direction direction, Node node) {
		if (direction.equals(Direction.LEFT)) {

			if (node.getColumn() == 0) {
				node.setColumn(endColumn);
			} else {
				node.setColumn(node.getColumn() - 1);
			}

		} else if (direction.equals(Direction.RIGHT)) {

			if (node.getColumn() == endColumn) {
				node.setColumn(0);
			} else {
				node.setColumn(node.getColumn() + 1);
			}

		} else if (direction.equals(Direction.UP)) {

			if (node.getRow() == 0) {
				node.setRow(endRow - 1);
			} else {
				node.setRow(node.getRow() - 1);
			}

		} else if (direction.equals(Direction.DOWN)) {

			if (node.getRow() == endRow) {
				node.setRow(0);
			} else {
				node.setRow(node.getRow() + 1);
			}

		}
	}

	private boolean validadeDirection(Direction direction) {
		if (direction.equals(Direction.LEFT)
				&& this.direction.equals(Direction.RIGHT)) {
			return false;
		}
		if (direction.equals(Direction.RIGHT)
				&& this.direction.equals(Direction.LEFT)) {
			return false;
		}
		if (direction.equals(Direction.UP)
				&& this.direction.equals(Direction.DOWN)) {
			return false;
		}
		if (direction.equals(Direction.DOWN)
				&& this.direction.equals(Direction.LEFT)) {
			return false;
		}
		return true;
	}

	public LinkedList<Node> getBody() {
		return body;
	}

	public void setBody(LinkedList<Node> body) {
		this.body = body;
	}

}
