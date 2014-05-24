/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import Logica.ListarLogica;

/**
 *
 * @author sebastian
 */
@WebService(serviceName = "WasWS")
public class WasWS {

    ListarLogica lg = new ListarLogica();
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "list")
    public String list() {
        String [] arch = lg.devolverArchivos();
        String json = "{\"list \": { \"files\":[";
        for(int i = 0; i<arch.length-1; i++ )
            json += "{\"filename\": \""+ arch[i].substring(arch[i].indexOf("/")+1)+"\", \"url\": \""+ arch[i]+"\"},";
        
        json += "{\"filename\": \""+ arch[arch.length-1]+"\", \"url\": \"url\"}]}}";
        return json;
    }
    
        @WebMethod(operationName = "listTxt")
    public String listTxt() {
        String [] arch = lg.devolverArchivos();
        String json = "";
        for(int i = 0; i<arch.length; i++ )
            json += arch[i]+ "&";
        
        //json += arch[arch.length-1];
        return json;
    }
    
        @WebMethod(operationName = "find")
    public String find(@WebParam(name = "pattern") String pattern) {
        String [] arch = lg.buscarArchivos(pattern);
        String json = "{\"find \": { \"files\":[";
        if(arch.length > 0)
        {
            for(int i = 0; i<arch.length-1; i++ )
            json += "{\"filename\": \""+ arch[i].substring(arch[i].indexOf("/")+1)+"\", \"url\": \""+ arch[i]+"\"},";

            json += "{\"filename\": \""+ arch[arch.length-1]+"\", \"url\": \"url\"}]}}";
        }
        else
        {
            json += "{\"filename\": \"no se encontro nada\", \"url\": \"\"}]}}";
        }
        return json;
    }
}
