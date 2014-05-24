/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author sebastian
 */
public class ListarLogica {
    
   XmlParser pars = new XmlParser();
   String [] dirs = pars.leerDirs();
   
   void actualizarXml()
   {
       dirs = pars.leerDirs();
   }
    
   public String [] devolverArchivos()
   {
       actualizarXml();
       String [] archivos = null;// = {"a", "b", "c"};
       
       File directorio = new File(dirs[0]);
            String[] listaDirectorio = directorio.list();
            if (listaDirectorio == null)
                  System.out.println("No hay ficheros en el directorio especificado");
                else {
                archivos = new String[listaDirectorio.length];
                  for (int x=0;x<listaDirectorio.length;x++)
                  {
                    System.out.println(listaDirectorio[x]);
                    archivos[x] = dirs[1]+ "/" + listaDirectorio[x];
                  }
                }
       
       return archivos;
   }
   
   public String [] convertirArray(ArrayList<String> resu)
   {
         String [] resultados = new String[resu.size()];
         for(int i = 0; i <resu.size();i++)
             resultados[i] = resu.get(i);
         
         return resultados;
         
   }
   
   public String [] buscarArchivos (String sentencia){
       actualizarXml();
       ArrayList<String> resultados = new ArrayList<String>();
       File raiz = new File(dirs[0]);
   
  if(!raiz.isDirectory()){
    throw new IllegalArgumentException("Archivo raiz no es una carpeta");
  }
  File[] archivos = raiz.listFiles();
  for(int i=0; i<archivos.length; i++){
    File archivo = archivos[i];
    //si es directorio comenzamos la busqueda en ese directorio

    //solo compara contra el nombre del archivo
    if(archivo.getName().contains(sentencia)){
      resultados.add(dirs[1]+ "/" + archivo.getName());
    }
  }               
    return convertirArray(resultados);
}
}
