package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class MyKeyListener implements KeyListener {
	boolean pressed = false;
	Position pos;
	ArrayList<Position> positions;
	Table table;
	boolean pressedEnter = false;

	public MyKeyListener(Position pos, Table table) {
		this.pos = pos;
		this.table = table;
	}

	public MyKeyListener(Position pos, Table table, ArrayList<Position> poses) {
		this.pos = pos;
		this.table = table;
		positions = new ArrayList<>(poses);
	}

	public void keyPressed(KeyEvent arg0) {

		if (positions != null && positions.size() > 0
				&& positions.contains(pos))
			Print.printSpecial(pos,Print.RED,table);
		else {
			Print.printAtPosition(pos);
			Print.printSimply(table.getTableCell(pos).toString());
		}
		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_UP:
			if (!pressed)
				pos.increseY();
			pressed = true;
			break;
		case KeyEvent.VK_DOWN:
			if (!pressed)
				pos.decreseY();
			pressed = true;
			break;
		case KeyEvent.VK_RIGHT:
			if (!pressed)
				pos.increseX();
			pressed = true;
			break;
		case KeyEvent.VK_LEFT:
			if (!pressed)
				pos.decreseX();
			pressed = true;
			break;
		case KeyEvent.VK_ENTER:
			pressedEnter = true;
			break;
		default:
			break;
		}
		pos.setX(pos.getX() % 9);
		pos.setY(pos.getY() % 9);
		if (pos.getX() < 0)
			pos.setX(9 + pos.getX());
		if (pos.getY() < 0)
			pos.setY(9 + pos.getY());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		pressed = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	public boolean isPressedEnter() {
		return pressedEnter;
	}

}
