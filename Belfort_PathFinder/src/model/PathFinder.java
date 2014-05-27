package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;




//import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;




//import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class PathFinder {
	
	private SetOfStreet sos;
	private String xmlFileName;
	private String imgName;
	
	private static PathFinder pathfinder;
	//Declaration of constants
	private final Node LAMBERT_HAUT_GAUCHE = new Node(897990, 2324046);
	private final Node LAMBERT_BAS_DROITE = new Node(971518, 2272510);
	private final Node PIXELS_BAS_DROITE = new Node(9807, 6867);
	//private final String SYSTEME_UNITE = "Lambert II";
	//private final int MARGE_REDIMENSIONNEMENT_AUTO = 50;

	public PathFinder(String xmlFileName){
		sos = new SetOfStreet();
		recupXml(xmlFileName);
		
	}
	
	private PathFinder(){
		sos = new SetOfStreet();
	}
	
	public static PathFinder getInstance(){
		if (pathfinder==null){
			pathfinder= new PathFinder();		
		}
		return pathfinder;
	}
	
	//TODO return SetOfStreet
	public SetOfStreet getSos() {
		return sos;
	}

	//TODO return xml File name
	public String getXmlFileName() {
		return xmlFileName;
	}

	//TODO return image name
	public String getImgName() {
		return imgName;
	}
	
	
	//conversion 
	
	public Node getLambertCoords(Node nodePixel) {
		int etendue_x = (int) (LAMBERT_BAS_DROITE.getX() - LAMBERT_HAUT_GAUCHE.getX());
		int etendue_y = (int) (LAMBERT_BAS_DROITE.getY() - LAMBERT_HAUT_GAUCHE.getY());
		double lambert_zero_x = nodePixel.getX() * etendue_x / PIXELS_BAS_DROITE.getX();
		double lambert_zero_y = nodePixel.getY() * etendue_y / PIXELS_BAS_DROITE.getY();
		int x = (int) (LAMBERT_HAUT_GAUCHE.getX() + lambert_zero_x);
		int y = (int) (LAMBERT_HAUT_GAUCHE.getY() + lambert_zero_y);
		return new Node(x, y);
	}
	

	//
	
	public ArrayList<Node> getConversionNodes(ArrayList<Node> n){
		
		for(int l=0;l<n.size();l++){
			n.get(l).setNode(getLambertCoords(n.get(l)));
		}
		return new ArrayList<Node>(n);
	}
	
	public void drawNodes(Graphics g, ArrayList<Node> n, int size){
		for(int l=0; l<n.size();l++){
			g.setColor(Color.BLACK);
			g.drawOval((int)n.get(l).getX() - size/2, (int)n.get(l).getY() - size/2, size, size);
			g.fillOval((int)n.get(l).getX()  - size/2, (int)n.get(l).getY()  - size/2, size, size);
			}
		
	}
	
	// TODO Parse File
	public void recupXml(String xmlFileName){
		this.xmlFileName = xmlFileName;
		
	try{
			// Creating a factory documents
			DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
			
			// create a document builder
			DocumentBuilder constructeur = fabrique.newDocumentBuilder();
			
			// reading the contents of an XML file with DOM
			File xml = new File(xmlFileName);
			Document document = constructeur.parse(xml);
			
			//Reading and data Processing
					Element	Racine = document.getDocumentElement();
						
						// Reading of source image
						imgName = Racine.getAttributes().getNamedItem("src").getNodeValue();
						
						// Reading of the points
					
						for (int i = 0; i <Racine.getElementsByTagName("points").getLength(); i++) {
							
							for (int j = 0; j < Racine.getElementsByTagName("points").item(i).getChildNodes().getLength(); j++) {
								if (Racine.getElementsByTagName("points").item(i).getChildNodes().item(j).getNodeName() == "point") {
									int num = Integer.decode(Racine.getElementsByTagName("points").item(i).getChildNodes().item(j).getAttributes().getNamedItem("num").getNodeValue());
									float x = (float) Double.parseDouble(Racine.getElementsByTagName("points").item(i).getChildNodes().item(j).getAttributes().getNamedItem("x").getNodeValue());
									float y = (float) Double.parseDouble(Racine.getElementsByTagName("points").item(i).getChildNodes().item(j).getAttributes().getNamedItem("y").getNodeValue());
									//sos.put();
								sos.addNode(new Node(num,x,y));
								//sos.add(new Node(num,x,y));
									
								}
							}
						}
						
						//Reading streets
						NodeList baliseStreets;
						NodeList balisePt;
						for (int i = 0; i <Racine.getElementsByTagName("rues").getLength(); i++) {
							baliseStreets = Racine.getElementsByTagName("rues").item(i).getChildNodes();
							for (int j = 0; j < baliseStreets.getLength(); j++) {	
								if (baliseStreets.item(j).getNodeName() == "rue") {
									balisePt = baliseStreets.item(j).getChildNodes();
									ArrayList<Node> tempNode = new ArrayList<Node>();
									for(int k = 0;k < balisePt.getLength();k++){
										if (balisePt.item(k).getNodeName()=="pt"){
											tempNode.add(new Node(Integer.decode(balisePt.item(k).getAttributes().getNamedItem("num").getNodeValue())));
										}
									 
									}
									sos.addStreet(new Street(baliseStreets.item(j).getAttributes().getNamedItem("nom").getNodeValue(),
											Integer.parseInt(baliseStreets.item(j).getAttributes().getNamedItem("sens").getNodeValue()), tempNode));
									
//									sos.addStreet(new Street(baliseStreets.item(j).getAttributes().getNamedItem("nom").getNodeValue(),
//											Integer.parseInt(baliseStreets.item(j).getAttributes().getNamedItem("nom").getNodeValue()), retourPoints(){
//									for(int k = 0;k < balisePt.getLength();k++){
//										return new Node(balisePt.item(k).getAttributes().getNamedItem("num").getNodeValue());
//									}
//									});
								}
							}
						}
			
		}
		catch(ParserConfigurationException p) { System.out.println("Erreur de configuration du parseur DOM lors de l'appel fabrique.newDocumentBuilder();"); }
		catch(SAXException s) { System.out.println("Erreur lors du parsing du document lors de l'appel construteur.parse(xml)"); }
		catch(IOException i) { System.out.println("Erreur d'entr�e/sortie lors de l'appel construteur.parse(xml)"); }
	}
	
	
/*	public static void main(String[] args) throws ParseException{
		PathFinder path = new PathFinder("C:/Users/ida/Documents/GitHub/Belfort_PathFinder/Belfort_PathFinder/baseline/region_belfort_streets.xml");
		for (int i=0;i< path.getSos().getNodes().size();i++){
			System.out.println(path.getSos().getStreets().get(0).getNode().get(i).getNum());
		}
	}*/

}
