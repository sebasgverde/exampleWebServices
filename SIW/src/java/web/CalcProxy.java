/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import cliente.WasWS;
import cliente.WasWS_Service;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author sebastian
 */
public class CalcProxy {
    String uri;// = "http://localhost:8080/sgarci18WasServer/WasWS";
    
    WasWS calc1 = null;
    cliente.WasWS_Service calc1Serv = null;
    
    public String ParsearJson(String jsonString)
    {
        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();            
                JsonObject list = jsonObject.getJsonObject("list");
               // jsonObject.getJsonArray("files").toString();
                //JsonObject arr = list.getJsonObject("files");

        return jsonObject.toString();// getString("res");        
    }    
 
    public CalcProxy(String [] urls)
    {

        uri = urls[0];
        //String uri = "http://localhost:8080/sgarci18WasServer/WasWS";
        System.out.println(urls[0]);
        try {
            calc1Serv = new WasWS_Service(new URL(uri));
            calc1 = calc1Serv.getWasWSPort();
            
        } catch (MalformedURLException ex) {  
            Logger.getLogger(CalcProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String listar() {
        return ParsearJson(calc1.list());
    }
    
    public String listarTxt() {
        return calc1.listTxt();
    }

    public String buscar(String a) {
        return ParsearJson(calc1.find(a));
    }    
}
