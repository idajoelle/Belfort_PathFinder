package model;

import java.util.ArrayList;


public class Street {
	
	private String nom;
	private int sens;
	ArrayList<Node> points;

	
	//TODO Constructor
	public Street(String nom, int sens, ArrayList<Node> points) {
		
		this.nom = nom;
		this.sens = sens;
		this.points = points;
		
	}
	
   //TODO Return the list of all points of street
	public ArrayList<Node> getNode() {	
	
	return points;	
	}
	
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getSens() {
		return sens;
	}

	public void setSens(int sens) {
		this.sens = sens;
	}

	//Return position of a point
	public Node getPosition(int pos){
		
		return points.get(pos);
	}
	
	//Return number of nodes on a street
	public int getNumber(){
		
		return points.size();
	}

		
		

}
