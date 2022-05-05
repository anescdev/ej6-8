package edu.manu.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class TestXmlDom {
	//TODO: definir la lista de personas
	private LinkedList<Persona> personas = new LinkedList<>();

	//TODO: definir el mapa que se construye a partir del XML
	private HashMap<Integer, String> mapaPersona = new HashMap<>();
	public static void main(String[] args) {
		TestXmlDom txd = new TestXmlDom();
		try {
			// 1º Creamos una nueva instancia de un fábrica de constructores
			// de documentos.DocumentBuilderFactory
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// 2º A partir de la instancia anterior, fabricamos un 
			// constructor de documentos, que procesará el XML.
			DocumentBuilder db = dbf.newDocumentBuilder();
			// 3º Procesamos el documento (almacenado en un archivo) 
			// y lo convertimos en un árbol DOM.			
			Document documento=db.parse("personas.xml");	

			System.out.println(documento.getXmlVersion());
			System.out.println(documento.getXmlEncoding());
			System.out.println(documento.getNodeName());

			Element root = documento.getDocumentElement();
			System.out.println(root.getNodeName());

			//Buscar un nodo concreto
			NodeList listaNodos=documento.
					getElementsByTagName("persona");
			txd.showPersonProperties(listaNodos);

			Element persona;
			if (listaNodos.getLength()>0){
				persona=(Element)listaNodos.item(0);
				NodeList nodoPersona = persona.getElementsByTagName("age");
				if(nodoPersona.getLength()==1) {
					Element firstName = (Element)nodoPersona.item(0);
					System.out.println(firstName.getChildNodes().
							item(0));

				}
			}


			//obtener lista de hijos
			listaNodos=documento.
					getDocumentElement().getChildNodes();
			



		} catch (SAXException | IOException | ParserConfigurationException e) {

			e.printStackTrace();
		}

	}

	/**
	 * Método que muestra las propiedades( o etiquetas hija) del NodeList
	 * @param listaNodos
	 */
	private void showPersonProperties(NodeList listaNodos) {
		//For para recorrer todos los nodos persona
		for (int i=0; i<listaNodos.getLength();i++) {
			//Guardamos el elemento actual.
			Node nodo=listaNodos.item(i);
			//Si es de tipo nodo, recorrerá dentro de persona todas sus etiquetas hijas,
			//Si Es de texto, imprime el texto
			switch (nodo.getNodeType()){
			case Node.ELEMENT_NODE:
				//Casteamos el elemento actual de la lista de nodos a Element.
				Element elemento = (Element) nodo;
				//Imprimimos Etiqueta: junto la etiqueta padre de cada nodo
				System.out.println("Etiqueta:" +
						elemento.getTagName());
				//Bucle para recorrer los diferentes hijos de elemento.
				for (int j=0; j<elemento.getChildNodes().getLength(); j++) {
					//Creamos otro elemento pero esta vez almacenará el elemento actual de las
					//propiedades.
					Element ele = (Element) elemento.getChildNodes().item(j);
					//Imprimimos el nombre de la etiqueta junto a su contenido
					System.out.println(" - " + ele.getTagName() + ": " + ele.getTextContent());
				}
				break;
			case Node.TEXT_NODE:
				Text texto = (Text) nodo;
				System.out.println("Texto:" + 
						texto.getWholeText());
				break;
			}      
		}
	}
	//TODO recorrer cada persona, mostrar sus propiedades, crear y almacenar
	//las personas en una lista ordenada por apellidos y nombre, y también
	//almacenar todas las personas en un mapa compuesto por: el identificador
	//como clave y el correo como valor
	private HashMap<Integer,String> showProperties(Element elemento) {
		elemento.getElementsByTagName("id");
		elemento.getElementsByTagName("email");
		return null;		
	}
}

