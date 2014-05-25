package view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class CustomSplitPane extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int SPACER_SIZE = 4;
	private final boolean horizontal;
	private final JComponent spacer;

	private double split = 0.5;
	private int dx, dy;
	private Component componentOne, componentTwo;

	public CustomSplitPane(boolean horizontal) {
		this.spacer = new JPanel();
		this.spacer.setOpaque(false);
		this.spacer.setCursor(horizontal ? Cursor
				.getPredefinedCursor(Cursor.E_RESIZE_CURSOR) : Cursor
				.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
		this.dx = this.dy = -1;
		this.horizontal = horizontal;

		class SpacerMouseAdapter extends MouseAdapter implements
				MouseMotionListener {
			public void mouseReleased(MouseEvent e) {
				Insets insets = getInsets();
				int width = getWidth();
				int height = getHeight();
				int availw = width - insets.left - insets.right;
				int availh = height - insets.top - insets.bottom;
				if (CustomSplitPane.this.horizontal && dy != -1) {
					setSplit((double) dx / availw);
				} else if (dx != -1) {
					setSplit((double) dy / availh);
				}
				dx = dy = -1;
				spacer.setOpaque(false);
				repaint();
			}

			public void mouseDragged(MouseEvent e) {
				dx = e.getX() + spacer.getX();
				dy = e.getY() + spacer.getY();
				spacer.setOpaque(true);
				if (dx != -1 && CustomSplitPane.this.horizontal) {
					spacer.setBounds(dx, 0, SPACER_SIZE, getHeight());
				} else if (dy != -1 && !CustomSplitPane.this.horizontal) {
					spacer.setBounds(0, dy, getWidth(), SPACER_SIZE);
				}
				repaint();
			}

			public void mouseMoved(MouseEvent e) {
			}
		}
		;
		SpacerMouseAdapter mouseAdapter = new SpacerMouseAdapter();
		spacer.addMouseListener(mouseAdapter);
		spacer.addMouseMotionListener(mouseAdapter);

		setLayout(new LayoutManager() {
			public void addLayoutComponent(String name, Component comp) {
			}

			public void removeLayoutComponent(Component comp) {
			}

			public Dimension minimumLayoutSize(Container parent) {
				return new Dimension(1, 1);
			}

			public Dimension preferredLayoutSize(Container parent) {
				return new Dimension(128, 128);
			}

			public void layoutContainer(Container parent) {
				Insets insets = parent.getInsets();
				int width = parent.getWidth();
				int height = parent.getHeight();
				int availw = width - insets.left - insets.right;
				int availh = height - insets.top - insets.bottom;

				if (CustomSplitPane.this.horizontal) {
					availw -= SPACER_SIZE;
					int width1 = Math.max(0, (int) Math.floor(split * availw));
					int width2 = Math.max(0, availw - width1);
					if (componentOne.isVisible() && !componentTwo.isVisible()) {
						spacer.setBounds(0, 0, 0, 0);
						componentOne.setBounds(insets.left, insets.top, availw,
								availh);
					} else if (!componentOne.isVisible()
							&& componentTwo.isVisible()) {
						spacer.setBounds(0, 0, 0, 0);
						componentTwo.setBounds(insets.left, insets.top, availw,
								availh);
					} else {
						spacer.setBounds(insets.left + width1, insets.top,
								SPACER_SIZE, availh);
						componentOne.setBounds(insets.left, insets.top, width1,
								availh);
						componentTwo.setBounds(insets.left + width1
								+ SPACER_SIZE, insets.top, width2, availh);
					}
				} else {
					availh -= SPACER_SIZE;
					int height1 = Math.max(0, (int) Math.floor(split * availh));
					int height2 = Math.max(0, availh - height1);
					if (componentOne.isVisible() && !componentTwo.isVisible()) {
						spacer.setBounds(0, 0, 0, 0);
						componentOne.setBounds(insets.left, insets.top, availw,
								availh);
					} else if (!componentOne.isVisible()
							&& componentTwo.isVisible()) {
						spacer.setBounds(0, 0, 0, 0);
						componentTwo.setBounds(insets.left, insets.top, availw,
								availh);
					} else {
						spacer.setBounds(insets.left, insets.top + height1,
								availw, SPACER_SIZE);
						componentOne.setBounds(insets.left, insets.top, availw,
								height1);
						componentTwo.setBounds(insets.left, insets.top
								+ height1 + SPACER_SIZE, availw, height2);
					}
				}
			}
		});
		add(spacer);
	}

	public double getSplit() {
		return split;
	}

	public void setSplit(double split) {
		if (split < 0)
			split = 0;
		else if (split > 1)
			split = 1;
		this.split = split;
		invalidate();
		validate();
	}

	public void setComponentOne(Component component) {
		this.componentOne = component;
		if (componentOne != null)
			add(componentOne);
	}

	public void setComponentTwo(Component component) {
		this.componentTwo = component;
		if (componentTwo != null)
			add(componentTwo);
	}
}