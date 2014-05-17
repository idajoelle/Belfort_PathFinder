package view;


import java.awt.Color;
import java.awt.Image;
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
	
	public MapPanel(String mapPath, int defaultSize){
		
	 map = Toolkit.getDefaultToolkit().getImage(mapPath);
	 init();
			
	}
	
	public void init(){
		setMapWidth(map.getWidth(null));
		setMapHeight(map.getHeight(null));
	    setBackground(new Color(0xc0, 0xc0, 0xc0));
	    setOpaque(true);
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
	
}
