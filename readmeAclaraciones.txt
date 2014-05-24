nombre ws

el ws se llama WasWS y se encuentra en el proyecto wasServer, el contexto es sgarci18WasServer listar y buscar son metodos del ws
url: http://localhost:8080/sgarci18WasServer/WasWS?WSDL

cambios para correr

- como yo no use nada raro para parsear el json (cosa que probablemente me limito el uso de los arreglos) no hay que importar nada,
eso si, a pesar de que todo se lee por archivos de configuracion, el path de estos archivos xml si debe estar quemado, pues cuando
se despliega en glassfish se pierde la ruta, esto tiene dos soluciones
	-poner la ruta estatica en cada pc en los 2 parsers xml (clase XmlParser de cada proyecto) en la linea 27
	-dejar el solo nombre del archivo de esta forma
	Document doc = docBuilder.parse (new File("sharedirs.xml"));
	como seria si el archivo estuviera al lado del build pero poniendo ambos archivos
		en disco:/<carpeta de instalacion de glasssfish>\glassfish\domains\domain1\config que es donde busca los archivos cuando ya estan
		desplegados
	nota: ambos archivos estan en el respectivo proyecto junto al build
		
sharedirs.xml contiene la carpeta del saw donde estan los archivos y la ruta que se husa remotamente para llegar a ellos
urls.xml contiene el ws de cada saw, en este caso solo 1

corriendo

- la aplicacion web es el index del siw
http://localhost:8080/SIW/index.html

-el boton listar dispara dicha accion

-el buscar dicha accion, si el patron es "", equivale a listar

-se actuliza sin necesidad de nada, si borran o agregan archivos, la siguiente busqueda o listado refleja los cambios, solo 
es cuetion de dar a tag volver

- esos links url son de un archivo en el saw, eso es lo que tenia para la descarga, pero como por alguna razon nunca pude parsear 
el array del json no pude utilizarlo apropiadamente