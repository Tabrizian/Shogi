package game;

public class Table {
	private char[][] table;

	public Table() {
		table = new char[9][9];
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[0].length; j++) {
				table[i][j] = '-';
			}
		}
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

}
