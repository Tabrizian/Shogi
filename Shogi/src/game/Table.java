package game;

import game.gui.BeatenPicecesPanel;
import game.gui.BoardPanel;
import game.gui.FormPanel;
import game.pieces.None;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Table extends JFrame {
	private Piece[][] table;
	private BoardPanel board;
	private FormPanel formPanel;
	private BeatenPicecesPanel player1Komadi;
	private BeatenPicecesPanel player2Komadi;
	private Game game;
	public static final Color ORIGINAL_COLOR = (new JButton()).getBackground();
	LayoutManager borderLayout;

	public Table(Game game) {
		super("SHOGI");
		this.game = game;
		formPanel = new FormPanel();
		table = new Piece[9][9];
		borderLayout = new BorderLayout();
		board = new BoardPanel();
		player1Komadi = new BeatenPicecesPanel();
		player2Komadi = new BeatenPicecesPanel();

		board.initialize(this);
		add(board, BorderLayout.CENTER);
		add(formPanel, BorderLayout.WEST);
		JPanel beatenPanel = new JPanel();
		beatenPanel.setLayout(new BorderLayout());
		beatenPanel.add(player1Komadi, BorderLayout.EAST);
		beatenPanel.add(player2Komadi, BorderLayout.WEST);
		add(beatenPanel, BorderLayout.EAST);
	}

	public void setTableCell(Position pos, Piece piece) {
		table[pos.getY()][pos.getX()] = piece;
		piece.setPos(pos);
		board.setButtonText(pos);

	}

	public boolean setTableKomadiCell(Position pos, Piece piece) {
		if (game.getPlayer1().getKomadi().contains(piece)) {
			if (pos.getY() <= 5) {
				setTableCell(pos, piece);
				return true;
			}
		} else if (game.getPlayer2().getKomadi().contains(piece)) {
			if (pos.getY() >= 3) {
				setTableCell(pos, piece);
				return true;
			}
		} else {
			setTableCell(pos, piece);
			return true;
		}
		return false;
	}

	public Piece getTableCell(Position pos) {
		return table[pos.getY()][pos.getX()];
	}

	public void undoMove(Position pos1, Position pos2, Game game,
			Player player, boolean delete) {

		if (!delete) {
			Piece temp;
			temp = getTableCell(pos1);
			setTableCell(pos1, getTableCell(pos2));
			setTableCell(pos2, temp);
			getTableCell(pos1).setPos(pos1);
			getTableCell(pos2).setPos(pos2);
			board.getButton(pos1).setText(getTableCell(pos1).toString());
			board.getButton(pos2).setText(getTableCell(pos2).toString());
		} else {
			Piece deleted = player.getKomadi().get(
					player.getKomadi().size() - 1);
			player.getKomadi().remove(player.getKomadi().size() - 1);
			if (player == game.getPlayer2()) {
				game.getPlayer1().getPieces().add(deleted);
				getPlayer2Komadi().removeFromPanel(deleted);
			} else {
				game.getPlayer2().getPieces().add(deleted);
				getPlayer1Komadi().removeFromPanel(deleted);
			}
			Piece piece = getTableCell(pos1);
			setTableCell(pos1, deleted);
			setTableCell(pos2, piece);
			getTableCell(pos1).setPos(pos1);
			getTableCell(pos2).setPos(pos2);
			board.getButton(pos1).setText(getTableCell(pos1).toString());
			board.getButton(pos2).setText(getTableCell(pos2).toString());
		}
	}

	public boolean[] swapTableCells(Position pos1, Position pos2, Game game,
			Player player) {
		boolean res[] = new boolean[2];
		if (getTableCell(pos1) instanceof None) {
			Piece temp;
			temp = getTableCell(pos1);
			setTableCell(pos1, getTableCell(pos2));
			setTableCell(pos2, temp);
			getTableCell(pos1).setPos(pos1);
			getTableCell(pos2).setPos(pos2);
			board.getButton(pos1).setText(getTableCell(pos1).toString());
			board.getButton(pos2).setText(getTableCell(pos2).toString());
			if (!isKingCheck(player, game)) {
				res[0] = true;
				res[1] = false;
				return res;
			} else {
				res[0] = false;
				res[1] = false;
				undoMove(pos1, pos2, game, player, false);
				return res;
			}
		} else {
			player.getKomadi().add(getTableCell(pos1));
			if (player == game.getPlayer1()) {
				getPlayer1Komadi().addToPanel(getTableCell(pos1), this, game);

			} else {
				getPlayer2Komadi().addToPanel(getTableCell(pos1), this, game);

			}
			if (game.getPlayer1().getPieces().contains(getTableCell(pos1)))
				game.getPlayer1().getPieces().remove(getTableCell(pos1));
			if (game.getPlayer2().getPieces().contains(getTableCell(pos1)))
				game.getPlayer2().getPieces().remove(getTableCell(pos1));
			setTableCell(pos1, getTableCell(pos2));
			Piece newPiece = new None(this, pos2);
			setTableCell(pos2, newPiece);
			getTableCell(pos1).setPos(pos1);
			getTableCell(pos2).setPos(pos2);
			board.getButton(pos1).setText(getTableCell(pos1).toString());
			board.getButton(pos2).setText(getTableCell(pos2).toString());
			if (!isKingCheck(player, game)) {
				res[0] = true;
				res[1] = true;
				return res;
			} else {
				undoMove(pos1, pos2, game, player, true);

				res[0] = false;
				res[1] = true;
				return res;
			}
		}

	}

	public void showMessage(String s) {
		formPanel.getLabel().setText(s);
	}

	public boolean isKingCheck(Player player, Game game) {
		if (player == game.getPlayer2()) {
			for (int i = 0; i < game.getPlayer1().getPieces().size(); i++) {
				Piece piece = game.getPlayer1().getPieces().get(i);
				if (piece.getAllowedCells(game, game.getPlayer1()).contains(
						game.getPlayer2().getKing().getPos())) {
					return true;
				}
			}
			return false;
		} else {
			for (int i = 0; i < game.getPlayer2().getPieces().size(); i++) {
				Piece piece = game.getPlayer2().getPieces().get(i);
				if (piece.getAllowedCells(game, game.getPlayer2()).contains(
						game.getPlayer1().getKing().getPos())) {
					return true;
				}
			}
			return false;
		}
	}

	public boolean isKingMate(Player player, Game game) {
		ArrayList<Piece> pieces = player.getPieces();
		for (Piece piece : pieces) {
			ArrayList<Position> positions = piece.getAllowedCells(game, player);
			for (Position pos : positions) {
				Position pos1 = pos;
				Position pos2 = new Position(piece.getPos());
				boolean moved[] = swapTableCells(pos, piece.getPos(), game,
						player);
				if (moved[0]) {
					undoMove(pos1, pos2, game, player, moved[1]);
					return false;
				}
			}
		}
		return true;
	}

	public boolean isEmpty(Position pos) {
		if (getTableCell(pos) instanceof None)
			return true;
		else
			return false;
	}

	public void showGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setVisible(true);
	}

	public BoardPanel getBoard() {
		return board;
	}

	public BeatenPicecesPanel getPlayer1Komadi() {
		return player1Komadi;
	}

	public BeatenPicecesPanel getPlayer2Komadi() {
		return player2Komadi;
	}

	public boolean isACellSelected() {
		for (int i = 0; i < getBoard().getButtons().length; i++) {
			for (int j = 0; j < getBoard().getButtons().length; j++) {
				if (getBoard().getButtons()[i][j].getBackground() == Color.ORANGE)
					return true;
			}
		}
		return false;
	}

}
