package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.GUI;

public class ScrollablePanelController implements MouseListener{

	private GUI gui;
	
	public ScrollablePanelController(GUI gui){
		this.setGui(gui);
	}
	
	public void mouseClicked(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public GUI getGui() {
		return gui;
	}

	public void setGui(GUI gui) {
		this.gui = gui;
	}
	
}
