package model;

public class Node {
	
	private int num;
	float x;
	float y;
	
	
	
	public Node(int num) {
		
		this.num = num;
	}
	
	
	public Node(int num, float x, float y) {
		
		this.num = num;
		this.x = x;
		this.y = y;
	}
	
	public void setNode(Node n){
		this.x = n.x;
		this.y = n.y;
	}


	public Node(float x, float y) {
	
		this.x = x;
		this.y = y;
	}


	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}	

		
	}
