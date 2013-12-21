package modelo;




import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;









import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import conexion.Conexion;
import controlador.Controlador;





public class Modelo {
	
	
	Conexion cn;
	Controlador c;
	ArrayList<Fuente> fuentes;
	
	public Modelo() {
		try{
		cn = Conexion.getConexion();
		fuentes = cn.getFuentes();
		}catch(Exception e){
			System.out.println("no se ha conectado a la base o no se han cargado las fuentes desde la base");
		}
	}

	public Conexion getCn() {
		return cn;
	}

	

	public ArrayList<Fuente> getFuentes() {
		return fuentes;
	}

	public void setFuentes(ArrayList<Fuente> fuentes) {
		this.fuentes = fuentes;
	}
	
	@SuppressWarnings("unchecked")
	public Map<Integer, Item> rssReader(String uri) throws IllegalArgumentException, FeedException, IOException{
		URL url;
		XmlReader xmlReader = null;
		 List<SyndEntry> entradas = new ArrayList<SyndEntry>();
		 
		 Map mapa = new HashMap();
		try {
			url = new URL(uri);
			xmlReader = new XmlReader(url);
			SyndFeed fuente = new SyndFeedInput().build(xmlReader);
			entradas = fuente.getEntries();
			
			
			 Iterator<SyndEntry> it = entradas.iterator();
			 int indice =0;
		        while (it.hasNext()) {
		            SyndEntry entrada = it.next();
		            Item itm = new Item();
		           
		            itm.setTitulo( entrada.getTitle() );
		            itm.setDescripcion( entrada.getDescription().getValue() );
		            itm.setFecha_Publicacion( entrada.getPublishedDate() );
		            itm.setAutor( entrada.getAuthor() );
		            itm.setEnlace( entrada.getLink() );
		            mapa.put( indice , itm );
		            indice++;
		        }
		        
		        return mapa;

		} finally {
			if (xmlReader != null)
				xmlReader.close();
		}
	}
	
	
	 
	
}
