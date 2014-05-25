package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

import javax.swing.BorderFactory;

import model.Run;
import controller.MapController;

public class MapPanel extends ScrollablePanel {

	protected double smoothScale = 1.0D;
	private static final long serialVersionUID = 1L;
	private Image map;
	private float zoom_percentage;
	private int mapWidth;
	private int mapHeight;
	private AffineTransform xaffine;
	private float zoom = 1f;
	private Point mapPosition = new Point(0, 0);
	private Run run;
	private int smoothOffset = 0;
	private Point smoothPosition, smoothPivot;
	private MapController mouseListener = new MapController(run);

	public MapPanel(String mapPath, int size) {
		super(size);
		map = Toolkit.getDefaultToolkit().getImage(mapPath);
		setBackground(new Color(254, 253, 254));
		init();

	}

	public void init() {
		setMapWidth(map.getWidth(null));
		setMapHeight(map.getHeight(null));
		setZoomZoom(Run.ZOOM_INITIAL);
		setOpaque(true);
	}

	// initial zoom on launch
	public void setZoomZoom(float zoom) {
		zoom_percentage = zoom;
		super.setMaxUnitIncrement(zoom);
		xaffine = new AffineTransform();
		xaffine.scale(zoom_percentage, zoom_percentage);
		int zoomWidth = (int) (mapWidth * zoom_percentage);
		int zoomHeight = (int) (mapHeight * zoom_percentage);
		setSize(new Dimension(400, 200));
		setBorder(BorderFactory.createEmptyBorder(zoomHeight, zoomWidth, 0, 0));
	}

	/**
	 * public String DistanceUnitConverter(double px, float zoom) { String unite
	 * = "m"; double m = (double)(px * (double) getSCALE_SIZE() *
	 * (double)((double)1 / (double)zoom)); if (m > 1000) { m /= 1000; unite =
	 * "km"; } m = ((double) Math.round(m * 100)) / 100; return new String(m +
	 * " " + unite); }
	 */

	//map listener
	public void mapPanelListener(MapController listener) {
		addMouseMotionListener(listener);
		addMouseListener(listener);
		addMouseWheelListener(listener);
	}

	//zoom-
	public void zoomIn(Point pivot) {
		if (getZoom() >= Run.ZOOM_MAX)
			return;
		Point mapPosition = getMapPosition();
		int dx = pivot.x;
		int dy = pivot.y;
		run.defineZoom((float) Run.ZOOM_MINUS);
		setMapPosition(mapPosition.x * 2 + dx, mapPosition.y * 2 + dy);
		repaint();
	}

	//zoom+
	public void zoomOut(Point pivot) {
		if (getZoom() <= Run.ZOOM_MIN)
			return;
		Point mapPosition = getMapPosition();
		int dx = pivot.x;
		int dy = pivot.y;
		run.defineZoom((float) Run.ZOOM_PLUS);
		setMapPosition((mapPosition.x - dx) / 2, (mapPosition.y - dy) / 2);
		repaint();
	}
    //animation rectangle on zoom
	/**
	 * private void drawScaledRect(Graphics2D g, int cx, int cy, double f,
	 * double scale) { AffineTransform oldTransform = g.getTransform();
	 * g.translate(cx, cy); g.scale(scale, scale); g.translate(-cx, -cy); int c
	 * = 0x80 + (int) Math.floor(f * 0x60); if (c < 0) c = 0; else if (c > 255)
	 * c = 255; Color color = new Color(c, c, c); g.setColor(color);
	 * g.drawRect(cx - 40, cy - 30, 80, 60); g.setTransform(oldTransform); }
	 */

	//paint method surcharge
	synchronized public void paint(Graphics g) {
		super.paint(g);
		//super.paintChildren(g);
		Graphics2D gg = (Graphics2D) g;
		gg.setColor(getBackground());
		gg.drawImage(map, xaffine, null);
		gg.setColor(Color.black);
		gg.dispose();
	}
	public Point getCursorPosition() {
		return new Point(mapPosition.x + mouseListener.getMouseCoords().x,
				mapPosition.y + mouseListener.getMouseCoords().y);
	}

	public void setCenterPosition(Point p) {
		setMapPosition(p.x - getWidth() / 2, p.y - getHeight() / 2);
	}

	public Point getMapPosition() {
		return new Point(mapPosition.x, mapPosition.y);
	}

	public void setMapPosition(Point mapPosition) {
		setMapPosition(mapPosition.x, mapPosition.y);
	}

	public void setMapPosition(int x, int y) {
		if (mapPosition.x == x && mapPosition.y == y)
			return;
		Point oldMapPosition = getMapPosition();
		mapPosition.x = x;
		mapPosition.y = y;
		firePropertyChange("mapPosition", oldMapPosition, getMapPosition());
	}

	public Image getMap() {
		return map;
	}

	public void setMap(Image map) {
		this.map = map;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
	}

	public float getZoom_percentage() {
		return zoom_percentage;
	}

	public void setZoom_percentage(float zoom_percentage) {
		this.zoom_percentage = zoom_percentage;
	}

	public AffineTransform getXaffine() {
		return xaffine;
	}

	public void setXaffine(AffineTransform xaffine) {
		this.xaffine = xaffine;
	}

	
	public float getZoom() {
		return zoom;
	}

	public void setZoom(float zoom) {
		this.zoom = zoom;
	}

	public Point getSmoothPivot() {
		return smoothPivot;
	}

	public Point setSmoothPivot(Point smoothPivot) {
		this.smoothPivot = smoothPivot;
		return smoothPivot;
	}

	public Point getSmoothPosition() {
		return smoothPosition;
	}

	public void setSmoothPosition(Point smoothPosition) {
		this.smoothPosition = smoothPosition;
	}

	public int getSmoothOffset() {
		return smoothOffset;
	}

	public void setSmoothOffset(int smoothOffset) {
		this.smoothOffset = smoothOffset;
	}

}
