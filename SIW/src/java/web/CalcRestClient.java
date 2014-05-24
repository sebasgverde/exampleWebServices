/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:ws2calc2 [/ws2calc2]<br>
 * USAGE:
 * <pre>
 *        CalcRestClient client = new CalcRestClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author sebastian
 */
public class CalcRestClient {
    private WebTarget webTarget;
    private Client client;
    //private static final String BASE_URI = "http://localhost:8080/AppServer2/webresources";

    public CalcRestClient(String uri) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        //webTarget = client.target(BASE_URI).path("ws2calc2");
        webTarget = client.target(uri);
    }

    public String getDatos(String op, String p1, String p2) throws ClientErrorException {
        
        System.out.println(op +p1+ p2);
        
        WebTarget resource = webTarget;
        if (op != null) {
            resource = resource.queryParam("op", op);
        }
        if (p2 != null) {
            resource = resource.queryParam("p2", p2);
        }
        if (p1 != null) {
            resource = resource.queryParam("p1", p1);
        }
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
