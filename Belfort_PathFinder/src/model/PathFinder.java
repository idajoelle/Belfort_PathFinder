package model;

import java.io.File;



import java.io.IOException;



//import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;



//import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
public class PathFinder {
	private SetOfStreet sos;
	private String xmlFileName;
	private String imgName;
	
	public PathFinder(String xmlFileName){
	sos = new SetOfStreet();
		recupXml(xmlFileName);
		
	}
	
	public SetOfStreet getSos() {
		return sos;
	}


	public String getXmlFileName() {
		return xmlFileName;
	}

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
					
						for (int i = 0; i <Racine.getElementsByTagName("sos").getLength(); i++) {
							
							for (int j = 0; j < Racine.getElementsByTagName("sos").getLength(); j++) {
								if (Racine.getElementsByTagName("sos").item(i).getChildNodes().item(j).getNodeName() == "point") {
									int num = Integer.decode(Racine.getElementsByTagName("sos").item(i).getChildNodes().item(j).getAttributes().getNamedItem("num").getNodeValue());
									float x = (float) Double.parseDouble(Racine.getElementsByTagName("sos").item(i).getChildNodes().item(j).getAttributes().getNamedItem("x").getNodeValue());
									float y = (float) Double.parseDouble(Racine.getElementsByTagName("sos").item(i).getChildNodes().item(j).getAttributes().getNamedItem("y").getNodeValue());
									//sos.put();
								sos.addNode(new Node(num,x,y));
								//sos.add(new Node(num,x,y));
									
								}
							}
						}
			
		}
		catch(ParserConfigurationException p) { System.out.println("Erreur de configuration du parseur DOM lors de l'appel fabrique.newDocumentBuilder();"); }
		catch(SAXException s) { System.out.println("Erreur lors du parsing du document lors de l'appel construteur.parse(xml)"); }
		catch(IOException i) { System.out.println("Erreur d'entr�e/sortie lors de l'appel construteur.parse(xml)"); }
	}
	
	
	
	

}
