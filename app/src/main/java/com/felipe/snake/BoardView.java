package com.felipe.snake;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.felipe.snake.game.Snake;

@SuppressLint("DrawAllocation")
public class BoardView extends View {
	private static final int heightNum = 40;
	private static final int widthNum = 20;

	private Snake snake;

	private Paint paint;
	private Handler customHandler;

	private long premium = 0L;

	private int width;
	private int height;
	
	Direction direction;
//	private Node[][] board;
	Collection<Node> board = new LinkedHashSet<Node>();
	boolean boardCreated = false;

	public BoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		customHandler = new Handler();
		paint = new Paint();
		direction = Direction.RIGHT;
//		board = new Node[widthNum][heightNum];
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		initGame(canvas);
		printSnake(canvas);
		printPremium(canvas);
		postDeleyed();
	}

	private void postDeleyed() {
		customHandler.postDelayed(updateTimerThread, 1000 / 10);
	}

	private void initGame(Canvas canvas) {
		if (!boardCreated) {
			initBoard(canvas);
			initSnake(widthNum, heightNum);
			boardCreated = true;
		}
	}

	private void printPremium(Canvas canvas) {
		paint.setAntiAlias(true);
		paint.setTextSize(20);
		paint.setColor(Color.RED);
		canvas.drawText(Long.toString(premium++), 15, 15, paint);
	}

	private void printSnake(Canvas canvas) {
		Log.e("Snake", "IN�CIO - size");
		Log.e("Snake", Integer.toString(snake.getBody().size()));
		
//		for (Node node : snake.getBody()) {
//			Log.w("Snake", node.toString());
//		}
		
		try {
			paint.setColor(Color.GREEN);
			paint.setStrokeWidth(10);
			for (Node node : snake.getBody()) {
				
//				Node nodeBoard = board[node.getRow()][node.getColumn()];
//				if (nodeBoard != null) {
//					Rect rect = nodeBoard.getRect();
//					canvas.drawRect(rect, paint);
//				}
				
				if (board.contains(node)) {
					Iterator<Node> iterator = board.iterator();
					while (iterator.hasNext()) {
						Node next = iterator.next();
						if (node.equals(next)) {
							canvas.drawRect(next.getRect(), paint);
						}
					}
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("erro:", e.getMessage());
		}
	}

	private void initBoard(Canvas canvas) {
		
		int endWidth = (canvas.getWidth());
		int endHeight = (canvas.getHeight());

		width = (canvas.getWidth() / widthNum);
		height = (canvas.getHeight() / heightNum);

		int leftX = 5;
		int topY = 5;
		int rightX = width;
		int bottomY = height;

		boolean conditionX = true;
		boolean conditionY = true;

		int x = 0;
		int y = 0;
		while (conditionY) {

			while (conditionX) {
				Rect rect = new Rect(leftX, topY, rightX, bottomY);
				Node node = new Node(x, y, rect);
//				board[x][y] = node;
				
				board.add(node);
				
				leftX = leftX + width + 1;
				rightX = rightX + width + 1;
				conditionX = (leftX < (endWidth - width));
				x++;
			}
			x = 0;
			y++;

			leftX = 5;
			rightX = width;
			conditionX = true;

			topY = topY + height + 1;
			bottomY = bottomY + height + 1;

			conditionY = (topY < (endHeight - height));
		}
	}

	private void initSnake(int x, int y) {
		snake = new Snake(x, y);
		LinkedList<Node> body = new LinkedList<Node>();
		
		for (int i = 0; i < 17; i++) {
			body.add(new Node(0, i, null));
		}
		snake.setBody(body);
	}

	private Runnable updateTimerThread = new Runnable() {
		public void run() {
			int rand = (int) (Math.random() * 30); 
			
			switch (rand) {
				case 1:
					direction = Direction.RIGHT;
					break;
				case 2:
					direction = Direction.LEFT;
					break;
				case 3:
					direction = Direction.UP;
					break;
				case 4:
					direction = Direction.DOWN;
					break;
				default:
					break;
			}
			snake.move(direction);
			invalidate();
		}
	};

}
