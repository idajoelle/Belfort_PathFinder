package view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class MapLayout implements LayoutManager {
	private static final int PREFERRED_WIDTH = 400;
	private static final int PREFERRED_HEIGHT = 200;
	private OverlayPanel overlayPanel = new OverlayPanel();
	private ControlPanel controlPanel = new ControlPanel();

	public void addLayoutComponent(String name, Component comp) {
	}

	public void removeLayoutComponent(Component comp) {
	}

	public Dimension minimumLayoutSize(Container parent) {
		return new Dimension(1, 1);
	}

	public Dimension preferredLayoutSize(Container parent) {
		return new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT);
	}

	public void layoutContainer(Container parent) {
		int width = parent.getWidth();
		{
			Dimension psize = overlayPanel.getPreferredSize();
			overlayPanel.setBounds(width - psize.width - 20, 20, psize.width,
					psize.height);
		}
		{
			Dimension psize = controlPanel.getPreferredSize();
			controlPanel.setBounds(20, 20, psize.width, psize.height);
		}
	}

	public ControlPanel getControlPanel() {
		return controlPanel;
	}

	public void setControlPanel(ControlPanel controlPanel) {
		this.controlPanel = controlPanel;
	}
}