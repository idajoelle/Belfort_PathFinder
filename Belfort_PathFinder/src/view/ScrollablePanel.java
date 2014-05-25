package view;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class ScrollablePanel extends JPanel implements Scrollable {

	private final int MIN_UNIT_INCREMENT = 5;

	private int initialMaxUnitIncrement = MIN_UNIT_INCREMENT;
	private int currentMaxUnitIncrement = MIN_UNIT_INCREMENT;
	private boolean missingPicture = false;

	public ScrollablePanel(int size) {
		super();
		initialMaxUnitIncrement = size;
		currentMaxUnitIncrement = size;
		
		setBackground(Color.white);

		// Let the user scroll by dragging to outside the window.
		setAutoscrolls(true); // enable synthetic drag events
	}

	public Dimension getPreferredSize() {
		if (missingPicture) {
			return new Dimension(320, 480);
		} else {
			return super.getPreferredSize();
		}
	}

	public Dimension getPreferredScrollableViewportSize() {
		return getPreferredSize();
	}

	public int getScrollableUnitIncrement(Rectangle visibleRect,
			int orientation, int direction) {
		// Get the current position.
		int currentPosition = 0;
		if (orientation == SwingConstants.HORIZONTAL) {
			currentPosition = visibleRect.x;
		} else {
			currentPosition = visibleRect.y;
		}

		// Return the number of pixels between currentPosition
		// and the nearest tick mark in the indicated direction.
		if (direction < 0) {
			int newPosition = currentPosition
					- (currentPosition / currentMaxUnitIncrement)
					* currentMaxUnitIncrement;
			return (newPosition == 0) ? currentMaxUnitIncrement : newPosition;
		} else {
			return ((currentPosition / currentMaxUnitIncrement) + 1)
					* currentMaxUnitIncrement - currentPosition;
		}
	}

	public int getScrollableBlockIncrement(Rectangle visibleRect,
			int orientation, int direction) {
		if (orientation == SwingConstants.HORIZONTAL) {
			return visibleRect.width - currentMaxUnitIncrement;
		} else {
			return visibleRect.height - currentMaxUnitIncrement;
		}
	}

	public boolean getScrollableTracksViewportWidth() {
		return false;
	}

	public boolean getScrollableTracksViewportHeight() {
		return false;
	}

	public void setMaxUnitIncrement(float zoom) {
		int temp = (int) (initialMaxUnitIncrement * zoom);
		currentMaxUnitIncrement = (temp > MIN_UNIT_INCREMENT) ? temp
				: MIN_UNIT_INCREMENT;
	}

}
