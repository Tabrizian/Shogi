package game.gui;

import game.Game;
import game.Piece;
import game.Player;
import game.Table;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeatenButtonsListener implements ActionListener {
	private BeatenPicecesPanel panel;
	private Piece piece;
	private Table table;
	private Player player;
	private Game game;

	public void setPlayer(Player player) {
		this.player = player;
	}

	public BeatenButtonsListener(BeatenPicecesPanel panel, Piece piece,
			Table table, Game game) {
		this.panel = panel;
		this.piece = piece;
		this.table = table;
		this.game = game;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (panel.getKomadi().get(piece).getBackground() != Color.GREEN) {
			if (game.getTurn() % 2 == 0) {
				if (game.getPlayer1().getKomadi().contains(piece)) {
					panel.getKomadi().get(piece).setBackground(Color.GREEN);
					panel.setPressed(true);
				}
			} else {
				if (game.getPlayer2().getKomadi().contains(piece)) {
					panel.getKomadi().get(piece).setBackground(Color.GREEN);
					panel.setPressed(true);
				}
			}
		}
	}
}
