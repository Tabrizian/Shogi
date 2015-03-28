package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {
	boolean pressed = false;
	Position pos;
	Table table;
	boolean pressedEnter = false;

	public MyKeyListener(Position pos,Table table) {
		this.pos = pos;
		this.table = table;
	}

	public void keyPressed(KeyEvent arg0) {
		System.out.printf("%c[%d;%df", 0x1B, 9 + 1 - pos.getY(),
				pos.getX() * 2 + 1);
		System.out.print(table.getTableCell(pos));
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
