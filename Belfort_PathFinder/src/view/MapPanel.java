package view;


import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class MapPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage map;
	private int mapWidth;
	private int mapHeight;
	
	public MapPanel(String mapPath, int defaultSize){
	
	
		init();
			
	}
	
	public void init(){
		setMapWidth(map.getWidth(null));
		setMapHeight(map.getHeight(null));
	    setBackground(new Color(0xc0, 0xc0, 0xc0));
	    setOpaque(true);
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
	
}
