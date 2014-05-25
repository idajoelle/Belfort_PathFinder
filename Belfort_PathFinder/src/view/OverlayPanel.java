package view;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JPanel;

@SuppressWarnings({ "serial", "unused" })
public class OverlayPanel extends JPanel {
	
	public OverlayPanel() {
		
		setBackground(new Color(51, 204, 102));
		setPreferredSize(new Dimension(400, 200));
		setOpaque(false);
	}

	public void paint(Graphics gOrig) {
		super.paint(gOrig);
		Graphics2D g = (Graphics2D) gOrig.create();
		try {
			paintOverlay(g);
		} finally {
			g.dispose();
		}
	}
  
	// overlay background settings
	private void drawBackground(Graphics2D g, int width, int height) {
		Color color1 = Color.black;
		Color color2 = new Color(0x30, 0x30, 0x30);
		color1 = new Color(0xc0, 0xc0, 0xc0);
		color2 = new Color(0xe0, 0xe0, 0xe0);
		Composite oldComposite = g.getComposite();
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
				0.75f));
		g.setPaint(new GradientPaint(0, 0, color1, 0, height, color2));
		g.fillRoundRect(0, 0, width, height, 4, 4);
		g.setComposite(oldComposite);
	}

	// overlay contents settings
	private void paintOverlay(Graphics2D g) {
		drawBackground(g, getWidth(), getHeight());
		g.setColor(Color.black);
		drawString(g, 0, "Zoom");
		drawString(g, 1, "MapSize");
		drawString(g, 2, "MapPosition");
	}

	// overlay view settings
	private void drawString(Graphics2D g, int row, String key) {
		int y = 16 + row * 16;
		g.drawString(key, 20, y);
	}

}
