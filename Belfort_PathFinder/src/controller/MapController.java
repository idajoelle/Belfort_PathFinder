package controller;


import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.event.MouseInputListener;

import model.Run;

public class MapController implements MouseInputListener, MouseWheelListener {
	
private Run run;
	
	private final float WHEEL_RATE = (float)-0.06;
	private final int WHEEL_TICKS = 3;
	private int nbTicks = 0;
	private int prevX = 0;
	private int prevY = 0;
	private Point mouseCoords;
	
	public MapController(Run run) {
		setMouseCoords(new Point());
		this.run = run;
	}
  
  //Methods required by the MouseMotionListener interface
  public void mouseMoved(MouseEvent e) {
  	//app.updateCoord(e.getX(),e.getY());
  }
  public void mouseDragged(MouseEvent e) {
	  run.translateMap(prevX-e.getX(), prevY-e.getY());
  }
  
  //Methods required by the MouseListener interface
   public void mousePressed(MouseEvent e) {

  	 if (e.getButton() == 1) {
  		 run.modifyCursorView(Cursor.MOVE_CURSOR);
	    	 prevX = e.getX();
	    	 prevY = e.getY();
  	 }
   }
   public void mouseReleased(MouseEvent e) {
	   run.modifyCursorView(Cursor.HAND_CURSOR);
   }
   public void mouseEntered(MouseEvent e) {
	   run.modifyCursorView(Cursor.HAND_CURSOR);
   }
   public void mouseExited(MouseEvent e) {
	   run.modifyCursorView(Cursor.DEFAULT_CURSOR);
   }
   public void mouseClicked(MouseEvent e) {
  	 //right click
  	 if (e.getButton() == 3) {
  	
  	 }
   }

   //mouse wheel management
   public void mouseWheelMoved(MouseWheelEvent e) {
  	 if (++nbTicks >= WHEEL_TICKS) {
  		 nbTicks = 0;
  		 run.defineZoom(e.getWheelRotation()* WHEEL_RATE);
  	 }
   }

public Run getRun() {
	return run;
}

public void setRun(Run run) {
	this.run = run;
}

public Point getMouseCoords() {
	return mouseCoords;
}

public void setMouseCoords(Point mouseCoords) {
	this.mouseCoords = mouseCoords;
}
}