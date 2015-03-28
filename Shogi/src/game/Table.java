package game;

import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Table {
	private Piece[][] table;
	private JFrame jFrame;

	public Table() {
		table = new Piece[9][9];
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[0].length; j++) {
				table[i][j] = null;
			}
		}
		jFrame = new JFrame();
	}

	public void setTableCell(Position pos, Piece type) {
		table[pos.getY()][pos.getX()] = type;
	}

	public Piece getTableCell(Position pos) {
		return table[pos.getY()][pos.getX()];
	}

	public void print() {
		for (int i = 0; i < table.length; i++) {
			for (int j = 8; j >= 0; j--) {
				if (table[i][j] == null) {
					System.out.print("-" + " ");
				} else
					System.out.print(table[i][j] + " ");
			}
			System.out.println();
		}
	}

	public Piece blinkCell(Position pos) {
		KeyListener listener = new MyKeyListener(pos, this);
		while (true) {
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
			if (getTableCell(pos) == null) {
				System.out.print('-');
			} else
				System.out.print(getTableCell(pos));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (((MyKeyListener) listener).isPressedEnter()) {
				break;
			}
		}
		jFrame.setVisible(false);
		return table[pos.getY()][pos.getX()];
	}

	public boolean isEmpty(Position pos) {
		if (getTableCell(pos) == null)
			return true;
		else
			return false;
	}

	public void printSpecial(ArrayList<Position> poses) {
		for (int i = 0; i < poses.size(); i++) {
			System.out.printf("%c[%d;%df", 0x1B, table.length + 1
					- poses.get(i).getY(), poses.get(i).getX() * 2 + 1);
			if (getTableCell(poses.get(i)) == null) {
				System.out.printf("%c[%dm%s", 0x1B, 31, "*");
			}
			else
				System.out.printf("%c[%dm%s", 0x1B, 31, getTableCell(poses.get(i)));
		}
		System.out.printf("%c[%dm", 0x1B, 37);
	}
}
