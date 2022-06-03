/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toannh.utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author HuuToan
 */
public class XMLHelper implements Serializable{
    public static Document parseDOMFromFile (String xmlFile) throws ParserConfigurationException, SAXException, IOException{
       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
       DocumentBuilder builder = factory.newDocumentBuilder();
       Document document = builder.parse(xmlFile);
       return document;
    }
    public static XPath getXPath(){
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();   
        return xpath;
    }
    public static void transformDOMtoFile(Node node, String xmlFile) throws TransformerConfigurationException, TransformerException{
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        Source src = new DOMSource(node);
        Result result = new StreamResult(xmlFile);       
        transformer.transform(src, result);
        
    }
    
    public static Element createElement(String elementName, String bodyText, Map<String, String> attributes, Document document){
        if(document == null){
            return null;
        }//document has existed
        if(elementName == null){
            return null;
        }
        if(elementName.trim().isEmpty()){
            return null;
        }
        //element has name
        Element result = document.createElement(elementName);
        //add body if it has
        if(bodyText != null){
            result.setTextContent(bodyText);
        }//element has body
        //add attributes if they existed
        if(attributes != null){
            for(String key : attributes.keySet()){
                result.setAttribute(key, attributes.get(key));
            }//end for traverse attribute
        }//end atatibutes has existed
        
        return result;
    }
}
