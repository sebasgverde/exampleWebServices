
package web;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException; 

public class XmlParser{

    private int numeroUrls;
    public String [] urls;
    
    public String [] leerUrls(){
    try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                            
            Document doc = docBuilder.parse (new File("urls.xml"));
            

            // normalize text representation
            doc.getDocumentElement ().normalize ();
            
            NodeList listaUrls = doc.getElementsByTagName("saw");
            numeroUrls = listaUrls.getLength();
            urls = new String[numeroUrls];
            System.out.println("Total no of urls : " + numeroUrls);

            for(int s=0; s<listaUrls.getLength() ; s++){
                NodeList nodes = listaUrls.item(s).getChildNodes();
                Node node = (Node) nodes.item(0);
                System.out.println(node.getNodeValue());
                urls[s] = node.getNodeValue();
            }//end of for loop with s var


        }catch (SAXParseException err) {
        System.out.println ("** Parsing error" + ", line " 
             + err.getLineNumber () + ", uri " + err.getSystemId ());
        System.out.println(" " + err.getMessage ());

        }catch (SAXException e) {
        Exception x = e.getException ();
        ((x == null) ? e : x).printStackTrace ();

        }catch (Throwable t) {
        t.printStackTrace ();
        }
        //System.exit (0);
    return urls;
    }//end of main

}