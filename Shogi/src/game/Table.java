package game;

import game.gui.BeatenPicecesPanel;
import game.gui.BoardPanel;
import game.gui.MessagePanel;
import game.pieces.None;
import game.pieces.Pawn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;

public class Table extends JFrame {
	private Piece[][] table;
	private BoardPanel board;
	private MessagePanel formPanel;
	private BeatenPicecesPanel player1Komadi;
	private BeatenPicecesPanel player2Komadi;
	private Game game;
	private MessagePanel turnViewer;
	public static final Color ORIGINAL_COLOR = (new JButton()).getBackground();
	public static final Border ORIGINAL_BORDER = (new JButton()).getBorder();
	private LayoutManager borderLayout;

	public Table(Game game) {
		super("SHOGI");
		this.game = game;
		formPanel = new MessagePanel();
		table = new Piece[9][9];
		borderLayout = new BorderLayout();
		board = new BoardPanel();
		player1Komadi = new BeatenPicecesPanel();
		player2Komadi = new BeatenPicecesPanel();
		turnViewer = new MessagePanel();
		add(turnViewer, BorderLayout.NORTH);
		board.initialize(this);
		add(board, BorderLayout.CENTER);
		add(formPanel, BorderLayout.SOUTH);
		add(player1Komadi, BorderLayout.EAST);
		add(player2Komadi, BorderLayout.WEST);
	}

	public void setTableCell(Position pos, Piece piece) {
		table[pos.getY()][pos.getX()] = piece;
		piece.setPos(pos);
		board.setButtonText(pos);

	}

	public boolean setTableKomadiCell(Position pos, Piece piece) {
		if (game.getPlayer1().getKomadi().contains(piece)) {
			if (pos.getY() <= 5 && checkKomadiConditions(piece, pos)) {
				setTableCell(pos, piece);
				return true;
			}
		} else if (game.getPlayer2().getKomadi().contains(piece)) {
			if (pos.getY() >= 3 && checkKomadiConditions(piece, pos)) {
				setTableCell(pos, piece);
				return true;
			}
		} else {
			setTableCell(pos, piece);
			return true;
		}
		return false;
	}

	public boolean checkKomadiConditions(Piece piece, Position pos) {
		if (piece instanceof Pawn) {
			for (int i = pos.getY() + 1; i != pos.getY(); i++, i = i % 9) {
				Piece currentPiece = getTableCell(new Position(pos.getX(), i));
				if (currentPiece instanceof Pawn) {
					if (game.getTurn() % 2 == 0) {
						if (!currentPiece.isUpgraded()
								&& game.getPlayer1().getPieces()
										.contains(currentPiece))
							return false;
					} else {

						if (!currentPiece.isUpgraded()
								&& game.getPlayer2().getPieces()
										.contains(currentPiece))
							return false;
					}
				}
			}
			if (game.getTurn() % 2 == 0) {
				game.getPlayer1().getPieces().add(piece);
				game.getPlayer1().getKomadi().remove(piece);
			} else {
				game.getPlayer2().getPieces().add(piece);
				game.getPlayer2().getKomadi().remove(piece);
			}
			setTableCell(pos, piece);
			if (game.getPlayer1().getPlayerId() == 1) {
				if (isKingMate(game.getPlayer1(), game)) {
					setTableCell(pos, new None(this, pos));
					game.getPlayer1().getPieces().remove(piece);
					game.getPlayer1().getKomadi().add(piece);
					return false;
				}

			} else {
				if (isKingMate(game.getPlayer2(), game)) {
					setTableCell(pos, new None(this, pos));
					game.getPlayer2().getPieces().remove(piece);
					game.getPlayer2().getKomadi().add(piece);
					return false;
				}

			}

		}
		if (game.getTurn() % 2 == 0) {
			game.getPlayer1().getPieces().remove(piece);
			game.getPlayer1().getKomadi().add(piece);
		} else {
			game.getPlayer2().getPieces().remove(piece);
			game.getPlayer2().getKomadi().add(piece);
		}
		return true;
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
			boolean upgraded = temp.isUpgraded();
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
				player.getKomadi().get(player.getKomadi().size() - 1)
						.downGrade();
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
				boolean upgraded = getTableCell(pos).isUpgraded();
				boolean moved[] = swapTableCells(pos, piece.getPos(), game,
						player);
				if (moved[0]) {
					undoMove(pos1, pos2, game, player, moved[1]);
					getTableCell(pos).setUpgraded(upgraded);
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
		setSize(660, 540);
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

	public void colorTable(Game game) {
		ArrayList<Piece> piecesPlayer1 = game.getPlayer1().getPieces();
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table.length; j++) {
				if (piecesPlayer1.contains(getTableCell(new Position(j, i)))) {
					board.setBorder(new Position(j, i));
				} else {
					board.getButtons()[i][j].setBorder(ORIGINAL_BORDER);
				}
			}
		}
	}

	public void update() {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[0].length; j++) {
				getBoard().setButtonText(new Position(j, i));
				if (game.getTurn() % 2 == 0)
					turnViewer.showMessage("Turn Player 1");
				else
					turnViewer.showMessage("Turn Player 2");
				if (isKingCheck(game.getPlayer1(), game)
						|| isKingCheck(game.getPlayer1(), game))
					formPanel.showMessage("You're CHECK!");
				else
					formPanel.showMessage("");
			}
		}
	}

	public MessagePanel getTurnViewer() {
		return turnViewer;
	}

}
