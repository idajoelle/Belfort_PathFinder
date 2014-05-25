package model;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.ImageIcon;

import view.GUI;
import view.MapPanel;
import view.MapView;
import controller.MapController;

public class Run {

	public final static float ZOOM_MAX = (float) 1;
	public final static float ZOOM_MIN = (float) 0.1;
	public final static float ZOOM_INITIAL = (float) 0.5;
	public final static float ZOOM_PLUS = (float) 0.1;
	public final static float ZOOM_MINUS = (float) -0.1;
	public final static float INITIAL_ZOOM_RETURN = -2;
	

	private final Point LAMBERT_TOP_LEFT = new Point(897990, 2324046);
	private final Point LAMBERT_BOTTOM_RIGHT = new Point(971518, 2272510);
	private final Point PIXELS_BOTTOM_RIGHT = new Point(9807, 6867);
	private final String SYSTEME_UNITE = "Lambert II";

	private float percentageZoom = ZOOM_INITIAL;
	private float oldZoom = ZOOM_INITIAL;

	private final int SCALE_SIZE = 30;

	private Point centerPoint = new Point(-1, -1);

	private GUI guigui;
	private MapPanel map;
	private MapView mapView;
	private String mapPath;

	private MapController mapListener;

	public Run() {
		mapPath = "baseline/region_belfort_routes_fleuves_habitats.gif";
		new ImageIcon(mapPath);
		guigui = new GUI(mapPath);
		guigui.setJMenuBar(guigui.createMenuBar());
		guigui.setVisible(true);
		printMap();

		mapListener = new MapController(this);
		allListener();

	}

	// all listener instantiate
	private void allListener() {
		guigui.getBelfortMap().getMapPanel().mapPanelListener(mapListener);
	}

	// map display
	private void printMap() {
		guigui.getBelfortMap().revalidate();
		refocusView(centerPoint);
	}

	// zoom definition
	private void setZoom() {
		if (percentageZoom > ZOOM_MAX)
			percentageZoom = ZOOM_MAX;
		if (percentageZoom < ZOOM_MIN)
			percentageZoom = ZOOM_MIN;
		if (oldZoom != percentageZoom) {

			guigui.getBelfortMap().getMapPanel().setZoomZoom(percentageZoom);
			guigui.getBelfortMap().getMapPanel()
					.setMaxUnitIncrement(percentageZoom);
			printMap();
		}
	}

	// refocus view on center
	public void refocusView(Point pt) {
		centerPoint = pt;
		Dimension dim = guigui.getBelfortMap().getViewport().getSize();
		int newX = (int) ((float) pt.getX() * percentageZoom)
				- (int) ((float) dim.getWidth() / (float) 2);
		int newY = (int) ((float) pt.getY() * percentageZoom)
				- (int) ((float) dim.getHeight() / (float) 2);
		newX = guigui.getBelfortMap().getXX(newX);
		newY = guigui.getBelfortMap().getYY(newY);
		guigui.getBelfortMap().getViewport()
				.setViewPosition(new java.awt.Point(newX, newY));
	}

	// zoom change
	public void defineZoom(float mode) {
		oldZoom = percentageZoom;
		if (mode == INITIAL_ZOOM_RETURN) {
			percentageZoom = ZOOM_INITIAL;
		} else {
			percentageZoom += mode;
		}
		setZoom();
	}

	// translate map
	public void translateMap(int tx, int ty) {
		guigui.getBelfortMap().translateMapPanel(tx, ty);
		updateCenter();
	}

	// update map center on translate
	public void updateCenter() {
		Dimension dim = guigui.getBelfortMap().getViewport().getSize();
		Point conner = new Point(guigui.getBelfortMap().getViewport()
				.getViewPosition());
		centerPoint = new Point(
				(int) ((conner.getX() / percentageZoom) + dim.getWidth()
						/ (2 * percentageZoom)),
				(int) ((conner.getY() / percentageZoom) + dim.getHeight()
						/ (2 * percentageZoom)));
	}

	public MapView getMapView() {
		return mapView;
	}

	public void modifyCursorView(int cursor) {
		guigui.getBelfortMap().getMapPanel().setCursor(new Cursor(cursor));
	}

	public void setMapView(MapView mapView) {
	}

	public MapPanel getMap() {
		return map;
	}

	public void setMap(MapPanel map) {
		this.map = map;
	}

	public GUI getGuigui() {
		return guigui;
	}

	public void setGuigui(GUI guigui) {
		this.guigui = guigui;
	}

	/**
	 * public Point getMapPosition() { return new Point(mapPosition.x,
	 * mapPosition.y); }
	 * 
	 * public void setMapPosition(Point mapPosition) {
	 * setMapPosition(mapPosition.x, mapPosition.y); }
	 */
	public int getSCALE_SIZE() {
		return SCALE_SIZE;
	}

	public Point getLAMBERT_TOP_LEFT() {
		return LAMBERT_TOP_LEFT;
	}

	public Point getLAMBERT_BOTTOM_RIGHT() {
		return LAMBERT_BOTTOM_RIGHT;
	}

	public Point getPIXELS_BOTTOM_RIGHT() {
		return PIXELS_BOTTOM_RIGHT;
	}

	public String getSYSTEME_UNITE() {
		return SYSTEME_UNITE;
	}

	public Point getCenterPoint() {
		return centerPoint;
	}

	public void setCenterPoint(Point centerPoint) {
		this.centerPoint = centerPoint;
	}

	public MapController getMapListener() {
		return mapListener;
	}

	public void setMapListener(MapController mapListener) {
		this.mapListener = mapListener;
	}

}
