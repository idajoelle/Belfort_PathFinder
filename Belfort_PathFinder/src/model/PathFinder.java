package model;

public class PathFinder {
	private SetOfStreet sos;
	private String xmlFileName;
	private String imgName;
	
	public PathFinder(String xmlFileName){
		sos = new SetOfStreet();
		recupXml(xmlFileName);
		
	}
	public void recupXml(String xmlFileName){
		this.xmlFileName = xmlFileName;
		
	}
	
	
	
	

}
