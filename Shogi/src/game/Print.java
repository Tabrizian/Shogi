package game;

import java.util.ArrayList;

public class Print {
	public static final int BLACK = 30;
	public static final int RED = 31;
	public static final int GREEN = 32;
	public static final int YELLOW = 33;
	public static final int BLUE = 34;
	public static final int MAGNETA = 35;
	public static final int CYAN = 36;
	public static final int WHITE = 37;

	public static void printSpecial(Position pos, int color,Table table) {
		System.out.printf("%c[%d;%df", 0x1B, 9 + 1 - pos.getY(),
				pos.getX() * 2 + 1);
		System.out.printf("%c[%dm%s", 0x1B, 31, table.getTableCell(pos));
		System.out.printf("%c[%dm", 0x1B, 37);
	}

	public static void printSpecial(ArrayList<Position> poses, int color,Table table) {
		
		for (int i = 0; i < poses.size(); i++) {
			System.out.printf("%c[%d;%df", 0x1B, 9 + 1
					- poses.get(i).getY(), poses.get(i).getX() * 2 + 1);
			
			System.out.printf("%c[%dm%s", 0x1B, color, table.getTableCell(poses.get(i)));
		}
		
		System.out.printf("%c[%dm", 0x1B, WHITE);
	}

	public static void printAtPosition(Position pos) {
		System.out.printf("%c[%d;%df", 0x1B, 9 + 1 - pos.getY(),
				pos.getX() * 2 + 1);
	}

	public static void printMessege(String s) {
		System.out.printf("%c[%d;%df", 0x1B, 11, 0);
		System.out.println(s);
	}

	public static void printSimply(String x) {
		System.out.print(x);
	}

	public static void printLine(String x) {
		System.out.println(x);
	}
	
	public static void clearScreen(){
		System.out.printf("%c[2J", 0x1B);
	}
}
