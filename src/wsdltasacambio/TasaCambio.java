/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
Autor:  Adoni Sanchez

*/
package wsdltasacambio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Administrador
 */
public class TasaCambio {
    
public double getTasa() {
    String data= "";
    double tasa = 0;
   try{
       
       //<?xml version="1.0" encoding="utf-8"?>
//<soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema"
//xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">  
//<soap:Body>    
//<TipoCambioFechaInicial xmlns="http://www.banguat.gob.gt/variables/ws/">     
//<fechainit>1/06/2020</fechainit>    
//</TipoCambioFechaInicial>  
//   <TipoCambioDia xmlns="http://www.banguat.gob.gt/variables/ws/" />
//</soap:Body></soap:Envelope>


//<?xml version="1.0" encoding="utf-8"?>
//<soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
//  <soap:Body>
//    <TipoCambioDia xmlns="http://www.banguat.gob.gt/variables/ws/" />
//  </soap:Body>
//</soap:Envelope>


        
        String rawData = 
                "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
            "  <soap:Body>\n" +
            "    <TipoCambioDia xmlns=\"http://www.banguat.gob.gt/variables/ws/\" />\n" +
            "  </soap:Body>\n" +
            "</soap:Envelope>";

                
        String type = "text/xml;charset=UTF-8;text/html, application/xhtml+xml, */*"; 
   
        URL u = new URL("http://www.banguat.gob.gt/variables/ws/TipoCambio.asmx");
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
      
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", type);
        //conn.setRequestProperty("SOAPAction",  "\"http://www.banguat.gob.gt/variables/ws/TipoCambioFechaInicial\"");
       conn.setRequestProperty("SOAPAction",  "\"http://www.banguat.gob.gt/variables/ws/TipoCambioDia\"");
       
        conn.setRequestProperty( "Content-Length","" + rawData.getBytes().length);
        byte[] outputInBytes = rawData.getBytes("UTF-8");
        OutputStream os = conn.getOutputStream();
        os.write(outputInBytes);
        os.close();


BufferedReader br = null;
if (conn.getResponseCode() == 200) {
    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String strCurrentLine;
        while ((strCurrentLine = br.readLine()) != null) {
               //System.out.println(strCurrentLine);
               data += strCurrentLine;
        }
} else {
    br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
    String strCurrentLine;
        while ((strCurrentLine = br.readLine()) != null) {
               //System.out.println(strCurrentLine);
              data = "";
        }
}  

if(!"".equals(data)){
  //  System.out.println(data);
parseXML px = new parseXML();
 tasa =   px.getTasa(data);
    }
  
   }catch(Exception ex){
       ex.printStackTrace();
   }
  
   return tasa;
}

   
   
   
}
