package view;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Composite;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel {

	protected static final int MOVE_STEP = 32;

	private JButton makeButton(Action action) {
		JButton b = new JButton(action);
		b.setFocusable(false);
		b.setText(null);
		b.setContentAreaFilled(false);
		b.setBorder(BorderFactory.createEmptyBorder());
		BufferedImage image = (BufferedImage) ((ImageIcon) b.getIcon())
				.getImage();
		BufferedImage hl = new BufferedImage(image.getWidth(),
				image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) hl.getGraphics();
		g.drawImage(image, 0, 0, null);
		drawRollover(g, hl.getWidth(), hl.getHeight());
		hl.flush();
		b.setRolloverIcon(new ImageIcon(hl));
		return b;
	}

	public ControlPanel() {
		setOpaque(false);
		setBackground(Color.black);
		setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
		setLayout(new BorderLayout());

		Action zoomInAction = new AbstractAction() {
			{
				String text = "Zoom In";
				putValue(Action.NAME, text);
				putValue(Action.SHORT_DESCRIPTION, text);
				putValue(
						Action.SMALL_ICON,
						new ImageIcon(flip(
								makePlus(new Color(0xc0, 0xc0, 0xc0)), false,
								false)));
			}

			public void actionPerformed(ActionEvent e) {
				// zoomInAnimated(new Point(MapPanel.this.getWidth() / 2,
				// MapPanel.this.getHeight() / 2));
			}
		};
		Action zoomOutAction = new AbstractAction() {
			{
				String text = "Zoom Out";
				putValue(Action.NAME, text);
				putValue(Action.SHORT_DESCRIPTION, text);
				putValue(
						Action.SMALL_ICON,
						new ImageIcon(flip(
								makeMinus(new Color(0xc0, 0xc0, 0xc0)), false,
								false)));
			}

			public void actionPerformed(ActionEvent e) {
				// zoomOutAnimated(new Point(MapPanel.this.getWidth() / 2,
				// MapPanel.this.getHeight() / 2));
				
			}
		};

		Action upAction = new AbstractAction() {
			{
				String text = "Up";
				putValue(Action.NAME, text);
				putValue(Action.SHORT_DESCRIPTION, text);
				putValue(
						Action.SMALL_ICON,
						new ImageIcon(flip(makeYArrow(new Color(0xc0, 0xc0,
								0xc0)), false, false)));
			}

			public void actionPerformed(ActionEvent e) {
				// translateMapPosition(0, -MOVE_STEP);
				// MapPanel.this.repaint();
			}
		};
		Action downAction = new AbstractAction() {
			{
				String text = "Down";
				putValue(Action.NAME, text);
				putValue(Action.SHORT_DESCRIPTION, text);
				putValue(
						Action.SMALL_ICON,
						new ImageIcon(flip(makeYArrow(new Color(0xc0, 0xc0,
								0xc0)), false, true)));
			}

			public void actionPerformed(ActionEvent e) {
				// translateMapPosition(0, +MOVE_STEP);
				// MapPanel.this.repaint();
			}
		};
		Action leftAction = new AbstractAction() {
			{
				String text = "Left";
				putValue(Action.NAME, text);
				putValue(Action.SHORT_DESCRIPTION, text);
				putValue(
						Action.SMALL_ICON,
						new ImageIcon(flip(makeXArrow(new Color(0xc0, 0xc0,
								0xc0)), false, false)));
			}

			public void actionPerformed(ActionEvent e) {
				// translateMapPosition(-MOVE_STEP, 0);
				// MapPanel.this.repaint();
			}
		};
		Action rightAction = new AbstractAction() {
			{
				String text = "Right";
				putValue(Action.NAME, text);
				putValue(Action.SHORT_DESCRIPTION, text);
				putValue(
						Action.SMALL_ICON,
						new ImageIcon(flip(makeXArrow(new Color(0xc0, 0xc0,
								0xc0)), true, false)));
			}

			public void actionPerformed(ActionEvent e) {
				// translateMapPosition(+MOVE_STEP, 0);
				// MapPanel.this.repaint();
			}
		};
		JPanel moves = new JPanel(new BorderLayout());
		moves.setOpaque(false);
		JPanel zooms = new JPanel(new BorderLayout(0, 0));
		zooms.setOpaque(false);
		zooms.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 0));
		moves.add(makeButton(upAction), BorderLayout.NORTH);
		moves.add(makeButton(leftAction), BorderLayout.WEST);
		moves.add(makeButton(downAction), BorderLayout.SOUTH);
		moves.add(makeButton(rightAction), BorderLayout.EAST);
		zooms.add(makeButton(zoomInAction), BorderLayout.NORTH);
		zooms.add(makeButton(zoomOutAction), BorderLayout.SOUTH);
		add(moves, BorderLayout.NORTH);
		add(zooms, BorderLayout.SOUTH);
	}
	
	
    //
	public void paint(Graphics gOrig) {
		Graphics2D g = (Graphics2D) gOrig.create();
		try {
			int w = getWidth(), h = getHeight();
			drawBackground(g, w, h);
		} finally {
			g.dispose();
		}
		super.paint(gOrig);
	}

	//
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

	//
	private void drawRollover(Graphics2D g, int width, int height) {
		Color color1 = Color.white;
		Color color2 = new Color(0xc0, 0xc0, 0xc0);
		Composite oldComposite = g.getComposite();
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
				0.25f));
		g.setPaint(new GradientPaint(0, 0, color1, width, height, color2));
		g.fillRoundRect(0, 0, width, height, 4, 4);
		g.setComposite(oldComposite);
	}

	//
	private BufferedImage flip(BufferedImage image, boolean horizontal,
			boolean vertical) {
		int width = image.getWidth(), height = image.getHeight();
		if (horizontal) {
			for (int y = 0; y < height; ++y) {
				for (int x = 0; x < width / 2; ++x) {
					int tmp = image.getRGB(x, y);
					image.setRGB(x, y, image.getRGB(width - 1 - x, y));
					image.setRGB(width - 1 - x, y, tmp);
				}
			}
		}
		if (vertical) {
			for (int x = 0; x < width; ++x) {
				for (int y = 0; y < height / 2; ++y) {
					int tmp = image.getRGB(x, y);
					image.setRGB(x, y, image.getRGB(x, height - 1 - y));
					image.setRGB(x, height - 1 - y, tmp);
				}
			}
		}
		return image;
	}

	//
	private BufferedImage makeIcon(Color background) {
		final int WIDTH = 16, HEIGHT = 16;
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		for (int y = 0; y < HEIGHT; ++y)
			for (int x = 0; x < WIDTH; ++x)
				image.setRGB(x, y, 0);
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(background);
		g2d.fillOval(0, 0, WIDTH - 1, HEIGHT - 1);

		double hx = 4;
		double hy = 4;
		for (int y = 0; y < HEIGHT; ++y) {
			for (int x = 0; x < WIDTH; ++x) {
				double dx = x - hx;
				double dy = y - hy;
				double dist = Math.sqrt(dx * dx + dy * dy);
				if (dist > WIDTH) {
					dist = WIDTH;
				}
				int color = image.getRGB(x, y);
				int a = (color >>> 24) & 0xff;
				int r = (color >>> 16) & 0xff;
				int g = (color >>> 8) & 0xff;
				int b = (color >>> 0) & 0xff;
				double coef = 0.7 - 0.7 * dist / WIDTH;
				image.setRGB(x, y, (a << 24)
						| ((int) (r + coef * (255 - r)) << 16)
						| ((int) (g + coef * (255 - g)) << 8)
						| (int) (b + coef * (255 - b)));
			}
		}
		g2d.setColor(Color.gray);
		g2d.drawOval(0, 0, WIDTH - 1, HEIGHT - 1);
		return image;
	}

	private BufferedImage makeXArrow(Color background) {
		BufferedImage image = makeIcon(background);
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.fillPolygon(new int[] { 10, 4, 10 }, new int[] { 5, 8, 11 }, 3);
		image.flush();
		return image;

	}

	private BufferedImage makeYArrow(Color background) {
		BufferedImage image = makeIcon(background);
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.fillPolygon(new int[] { 5, 8, 11 }, new int[] { 10, 4, 10 }, 3);
		image.flush();
		return image;
	}

	private BufferedImage makePlus(Color background) {
		BufferedImage image = makeIcon(background);
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.fillRect(4, 7, 8, 2);
		g.fillRect(7, 4, 2, 8);
		image.flush();
		return image;
	}

	private BufferedImage makeMinus(Color background) {
		BufferedImage image = makeIcon(background);
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.fillRect(4, 7, 8, 2);
		image.flush();
		return image;
	}

}