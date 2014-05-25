package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseListener;

import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class MapView extends JScrollPane {

	private MapPanel mapPanel;

	public MapView(MapPanel isMapPanel) {
		super(isMapPanel);
		mapPanel = isMapPanel;
		setWheelScrollingEnabled(false);
		getViewport().setBackground(new Color(254, 253, 254));
	}
	public void paint(Graphics gOrig) {
		super.paint(gOrig);
		super.paintChildren(gOrig);
		Graphics2D g = (Graphics2D) gOrig.create();
		try {
			repaint();
		} finally {
			g.dispose();
		}
	}

	public MapPanel getMapPanel() {
		return mapPanel;
	}

	public void setMapPanel(MapPanel mapPanel) {
		this.mapPanel = mapPanel;
	}

	// map translate on view
	public void translateMapPanel(int x, int y) {
		Point currentPosition = getViewport().getViewPosition();
		int newX = (int) currentPosition.getX() + x;
		int newY = (int) currentPosition.getY() + y;
		newX = getXX(newX);
		newY = getYY(newY);
		getViewport().setViewPosition(new Point(newX, newY));
	}

	// map scrollable listener
	public void ScrollBarListener(MouseListener listener) {
		this.getHorizontalScrollBar().addMouseListener(listener);
		this.getVerticalScrollBar().addMouseListener(listener);
	}

	public int getXX(int X) {
		int ancreMaxX = (int) (mapPanel.getWidth() - getViewport().getSize()
				.getWidth());
		if (X > ancreMaxX)
			X = ancreMaxX;
		if (X < 0)
			X = 0;
		return X;
	}

	public int getYY(int Y) {
		int ancreMaxY = (int) (mapPanel.getHeight() - getViewport().getSize()
				.getHeight());
		if (Y > ancreMaxY)
			Y = ancreMaxY;
		if (Y < 0)
			Y = 0;
		return Y;
	}

}
