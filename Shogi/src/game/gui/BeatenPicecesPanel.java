package game.gui;

import game.Game;
import game.Piece;
import game.Player;
import game.Table;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BeatenPicecesPanel extends JPanel {
	
	private HashMap<Piece,JButton> komadi;
	private BeatenButtonsListener listener;
	private Game game;
	private boolean pressed = false;
	public BeatenPicecesPanel() {
		komadi = new HashMap<>();
		Dimension dim = getPreferredSize();
		dim.width = 50;
		setPreferredSize(dim);
		setBorder(BorderFactory.createLineBorder(Color.white,3));
	}
	
	public void addToPanel(Piece piece,Table table,Game game){
		JButton button = new JButton(piece.toString());
		komadi.put(piece,button);
		listener = new BeatenButtonsListener(this, piece,table,game);
		button.addActionListener(listener);
		add(button);
		this.game = game;
	}
	
	public void removeFromPanel(Piece piece){
		if(komadi.containsKey(piece)){
			remove(komadi.get(piece));
			komadi.remove(piece);
			repaint();
		}
	}
	
	public void addName(Player player){
		if(player.getPlayerId() == 1){
			JLabel label = new JLabel("Player 1");
			add(label);
		}
		else{
			JLabel label = new JLabel("Player 2");
			add(label);
		}
	}
	
	public HashMap<Piece,JButton> getKomadi(){
		return komadi;
	}

	public boolean isPressed() {
		return pressed;
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}
	
	public Piece getPressedPiece(){
		Set<Piece> pieces = komadi.keySet();
		for (Iterator<Piece> iterator = pieces.iterator(); iterator.hasNext();) {
			Piece piece = (Piece) iterator.next();
			if(komadi.get(piece).getBackground() == Color.GREEN)
				return piece;
		}
		return null;
	}

	
	
}
