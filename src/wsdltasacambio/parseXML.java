/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
Autor:  Adoni Sanchez
*/

package wsdltasacambio;


import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 *
 * @author Administrador
 */
public class parseXML {

    public double getTasa(String xml) throws ParserConfigurationException, SAXException, IOException{
        double tasa =0;
        
DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
InputSource is = new InputSource();
is.setCharacterStream(new StringReader(xml));

Document doc = db.parse(is);
        
        Element e = doc.getDocumentElement();
        NodeList nodeList = doc.getElementsByTagName("VarDolar");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                NodeList nodelist = element.getElementsByTagName("referencia");
                Element element1 = (Element) nodelist.item(0);
                NodeList fstNm = element1.getChildNodes();
               // System.out.print("TasaCambio : " + (fstNm.item(0)).getNodeValue());
                tasa = Double.parseDouble(fstNm.item(0).getNodeValue());
               }
        }
        return tasa;
    }
    
}
