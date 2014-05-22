package model;

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
	
	public PathFinder(String xmlFileName){
	sos = new SetOfStreet();
		recupXml(xmlFileName);
		
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
	
	
	public static void main(String[] args) throws ParseException{
		PathFinder path = new PathFinder("C:/Users/ida/Documents/GitHub/Belfort_PathFinder/Belfort_PathFinder/baseline/region_belfort_streets.xml");
		for (int i=0;i< path.getSos().getNodes().size();i++){
			System.out.println(path.getSos().getStreets().get(0).getNode().get(i).getNum());
		}
	}

}
