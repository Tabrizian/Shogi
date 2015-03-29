package game;

import game.pieces.None;

import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Table {
	private Piece[][] table;
	private JFrame jFrame;
	private static final int sleepTime= 250;
	public Table() {
		table = new Piece[9][9];
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[0].length; j++) {
				table[i][j] = new None(this, new Position(j, i));
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

	public void swapTableCells(Position pos1, Position pos2) {
		Piece temp;
		temp = getTableCell(pos1);
		setTableCell(pos1, getTableCell(pos2));
		setTableCell(pos2, temp);
		getTableCell(pos1).setPos(pos1);
		getTableCell(pos2).setPos(pos2);
	}

	public void print() {
		for (int i = 8; i >= 0; i--) {
			for (int j = 0; j < table.length; j++) {
				Print.printSimply((table[i][j].toString() + " "));
			}
			Print.printLine("");
		}
	}

	public Piece blinkCell(Position pos) {
		KeyListener listener = new MyKeyListener(pos, this);
		while (true) {
			jFrame.setVisible(true);
			jFrame.requestFocus();
			jFrame.addKeyListener(listener);
			Print.printAtPosition(pos);
			Print.printSimply(" ");
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Print.printAtPosition(pos);
			Print.printSimply(getTableCell(pos).toString());
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (((MyKeyListener) listener).isPressedEnter()) {
				break;
			}
		}
		return table[pos.getY()][pos.getX()];
	}

	public Piece blinkCell(Position pos, ArrayList<Position> specialPoses) {
		KeyListener listener = new MyKeyListener(pos, this, specialPoses);
		
		while (true) {
			jFrame.setVisible(true);
			jFrame.requestFocus();
			jFrame.addKeyListener(listener);
			Print.printAtPosition(pos);
			Print.printSimply(" ");
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!specialPoses.contains(pos)) {
				Print.printAtPosition(pos);
				Print.printSimply(getTableCell(pos).toString());
			} else {
				Print.printSpecial(pos,Print.RED,this);
			}
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (((MyKeyListener) listener).isPressedEnter()) {
				break;
			}

		}
		return table[pos.getY()][pos.getX()];
	}

	public boolean isEmpty(Position pos) {
		if (getTableCell(pos) instanceof None)
			return true;
		else
			return false;
	}
}
