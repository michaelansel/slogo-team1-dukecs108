package slogo.view.subpanels;

import javax.swing.JPanel;

import slogo.model.arena.Arena;

public class drawnArena extends JPanel {
	private static final long serialVersionUID = 1L;
	private Arena myArena;

	public drawnArena(Arena a) {
		myArena=a;
	}

}
