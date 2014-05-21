package model;

import java.util.ArrayList;

public class SetOfStreet {
	
	private ArrayList<Node> points;
	private ArrayList<Street> streets;
	
	
	public SetOfStreet() {
	}

	public SetOfStreet(ArrayList<Node> points, ArrayList<Street> streets) {
		this.points = points;
		this.streets = streets;
	}
	
	// TODO add a new point
	public void addNode(Node node){
		points.add(node);
	}
	
	/*public void addNodes(int num,Node node){
		//points.add(num,x,y);
		points.add(num, node);
	}
	*/
	//TODO add a new street
	public void addStreet(Street street){
		streets.add(street);
	}
	
	public ArrayList<Street> getStreets(){
		
		return streets;
		
	}
	
	public ArrayList<Node> getNodes(){
		
		return points;
		
	}	
}
