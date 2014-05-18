package view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class MapPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image map;
	private int mapWidth;
	private int mapHeight;
	private AffineTransform xaffine;
	private Dimension mapSize = new Dimension(0, 0);
	private Point mapPosition = new Point(0, 0);
    private int zoom;
    //
    private boolean useAnimations = true;
    private MapAnimation animation;
    
    //constants
    private static final int TILE_SIZE = 256;
    
	
	public MapPanel(String mapPath){
		
	 map = Toolkit.getDefaultToolkit().getImage(mapPath);
	 init();		
	}
	
	public void init(){
		setMapWidth(map.getWidth(null));
		setMapHeight(map.getHeight(null));
	    setBackground(new Color(60, 60, 60));
	    setOpaque(true);
	}
	
	public void paintComponent(Graphics g){
	super.paintComponent(g);
	g.drawImage(map, 0, 0, getWidth(), getHeight(), this);
	}
	
 
	public Image getMap(){
		return map;
	}
	
	public void setMap(Image map){
		this.map=map;
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

	public AffineTransform getXaffine() {
		return xaffine;
	}

	public void setXaffine(AffineTransform xaffine) {
		this.xaffine = xaffine;
	}

	public Dimension getMapSize() {
		return mapSize;
	}

	public void setMapSize(Dimension mapSize) {
		this.mapSize = mapSize;
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

	    public void translateMapPosition(int tx, int ty) {
	        setMapPosition(mapPosition.x + tx, mapPosition.y + ty);
	    }

	public int getZoom() {
		return zoom;
	}

	public void setZoom(int zoom) {
		  if (zoom == this.zoom)
	            return;
	        int oldZoom = this.zoom;
	       // this.zoom = Math.min(map.getMaxZoom(), zoom);
	       // mapSize.width = getXMax();
	      //  mapSize.height = getYMax();
	        firePropertyChange("zoom", oldZoom, zoom);
	}

	public boolean isUseAnimations() {
		return useAnimations;
	}

	public void setUseAnimations(boolean useAnimations) {
		this.useAnimations = useAnimations;
	}

	public MapAnimation getAnimation() {
		return animation;
	}

	public void setAnimation(MapAnimation animation) {
		this.animation = animation;
	}

	public static int getTileSize() {
		return TILE_SIZE;
	}
	
}
