package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Table {
	private char[][] table;
	private JFrame jFrame;

	public Table() {
		table = new char[9][9];
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[0].length; j++) {
				table[i][j] = '-';
			}
		}
		jFrame = new JFrame();
	}

	public void setTableCell(Position pos, char type) {
		table[pos.getY()][pos.getX()] = type;
	}

	public char getTableCell(Position pos) {
		return table[pos.getY()][pos.getX()];
	}

	public void print() {
		for (int i = 0; i < table.length; i++) {
			for (int j = 8; j >= 0; j--) {
				System.out.print(table[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void blinkCell(Position pos) {
		KeyListener listener = new MyKeyListener(pos,this);
		while (true) {
			boolean pressed = false;
			jFrame.setVisible(true);
			jFrame.requestFocus();
			jFrame.addKeyListener(listener);
			System.out.printf("%c[%d;%df", 0x1B, table.length + 1 - pos.getY(),
					pos.getX() * 2 + 1);
			System.out.print(" ");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.printf("%c[%d;%df", 0x1B, table.length + 1 - pos.getY(),
					pos.getX() * 2 + 1);
			System.out.print(getTableCell(pos));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(((MyKeyListener)listener).isPressedEnter()){
				System.out.printf("%c[2J",0x1B);
				break;
			}
		}
	}
}
