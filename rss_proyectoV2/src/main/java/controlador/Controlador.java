package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;




import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.sun.syndication.io.FeedException;

import vista.InterfazVista;
import modelo.Fuente;
import modelo.Item;
import modelo.Modelo;

public class Controlador  implements ActionListener, MouseListener{
	
	
	private InterfazVista vista;
    private Modelo modelo;
    
    private Map<Integer, Item> mapa ;
    
    public Controlador(InterfazVista vista, Modelo modelo){
    	this.vista = vista;
    	this.modelo = modelo;
    }
    

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
    
    public InterfazVista getVista() {
		return vista;
	}

    public void setVista(InterfazVista vista) {
        this.vista = vista;
    }

  
    
	public ArrayList<Fuente> getFuentes(){
		return modelo.getFuentes();
	}
	
	public void arrancaVista() {
        System.out.println("arrancando la vista");
        vista.arranca();
    }

	

//	public int borrarEntrada(String nombre) {
//		return modelo.borrarEntrada(nombre);
//		
//	}
//
	public void cargarFuentes() { //se cargan las carpetas y las fuentes desde la base de datos para visualizarlos en la vista (Gui)
	
		DefaultMutableTreeNode r = vista.getRaiz();
		ArrayList<Fuente> listaFuents = modelo.getFuentes();
		
		Set<String> hs = new HashSet<String>();
		
		for(Fuente f : listaFuents){
			hs.add(f.getCarpeta());
		}
		
		System.out.println("carpetas " +hs);
	
		for (String s : hs){
			DefaultMutableTreeNode carpeta = new DefaultMutableTreeNode();
			carpeta.setUserObject(s); //poner el nombre a las carpetas en la vista
			vista.getArrayNodos().add(carpeta);//añadir las carpetas al cargarlas de la base al vector de carpetas
			for(Fuente f : listaFuents){
				if(f.getCarpeta().equals(carpeta.toString())){
					DefaultMutableTreeNode hoja = new DefaultMutableTreeNode();
					hoja.setUserObject(f.getUri());//poner el nombre a las fuentes
					hoja.setAllowsChildren(false); //hacer que no se pueda añadir fuente a una fuente existente
					carpeta.add(hoja);				//añadir la fuente a la carpeta
				}
				
			}
			r.add(carpeta);			//añadir carpeta a la raiz
		}
		
		
		
		//DefaultTreeModel  modeloArbol = new DefaultTreeModel(r);
		
		//vista.getArbol().setModel(modeloArbol);
	}

	//
	public void actionPerformed(ActionEvent evento) {

		if(evento.getActionCommand().equals(InterfazVista.AGREGAR)){ //agregar fuente

			DefaultMutableTreeNode n = (DefaultMutableTreeNode) vista.getArbol().getLastSelectedPathComponent();
			if( n !=null  ){
				DefaultTreeModel mdl = (DefaultTreeModel) vista.getArbol().getModel();
				String url = vista.getTextURLFuente();
				Vector<DefaultMutableTreeNode> an = vista.getArrayFuentes();
				boolean existe =false;
				
				if( !url.isEmpty()){
					int i=0;
					while(!existe && i < an.size()){
						if(url.toLowerCase().equals(an.get(i).getUserObject().toString().toLowerCase()) ){
							existe=true;
						}
						i++;
					}
					if(!existe){
						
						DefaultMutableTreeNode nueva_fuente = new DefaultMutableTreeNode(vista.getTextURLFuente());
						DefaultMutableTreeNode padre = (DefaultMutableTreeNode) vista.getArbol().getLastSelectedPathComponent();
					     
					    if(padre!=null && n.getAllowsChildren()){//(padre.getLevel() ==0 | padre.getLevel()==1)){//esCarpeta(padre)){ //hay que arreglar que se añaden fuentes solo a carpetas y no las otras fuentes.
					    	
					    	vista.getArrayFuentes().addElement(nueva_fuente);
							mdl.insertNodeInto(nueva_fuente, n , mdl.getChildCount(n));
							nueva_fuente.setAllowsChildren(false);
							
					    }else{
					    	JOptionPane.showMessageDialog(null, "solo se pueden añadir fuentes en carpetas");
					    }

					}else{
						JOptionPane.showMessageDialog(null, "la fuente ya existe");
					}
				}else{
					JOptionPane.showMessageDialog(null, "introduce la url de la fuente rss ");
				}
				
			}else{
				JOptionPane.showMessageDialog(null, "selecciona la carpeta donde añadir la fuente rss");
			}
			
		}else if(evento.getActionCommand().equals(InterfazVista.ELIMINAR)){ //eliminar carpetas y fuentes
				
			
			System.out.println("eliminar");
		
		
		}else if (evento.getActionCommand().equals(InterfazVista.NUEVA)){  //agregar carpeta
				DefaultMutableTreeNode n = (DefaultMutableTreeNode) vista.getArbol().getLastSelectedPathComponent();

				if(n != null && n.getLevel()==0){
					DefaultTreeModel mdl = (DefaultTreeModel) vista.getArbol().getModel();
					String nombreCarpeta = vista.getTextCarpeta();
					boolean existe = vista.buscarCarpeta(nombreCarpeta);
					
					if( !nombreCarpeta.isEmpty()){

						if(!existe){
							
							DefaultMutableTreeNode nueva_carpeta = new DefaultMutableTreeNode(vista.getTextCarpeta());
							vista.getArrayNodos().addElement(nueva_carpeta);
							mdl.insertNodeInto(nueva_carpeta, n , mdl.getChildCount(n));
							

						}else{
							JOptionPane.showMessageDialog(null, "la carpeta ya existe");
						}
					}else{
						JOptionPane.showMessageDialog(null, "introduce el nombre de la carpeta ");
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "selecciona la raiz para poder añadir una carpeta");
				}
			
		}

	}


	public void mouseClicked(MouseEvent evento) {
		
		if(evento.getSource().getClass().equals(JTree.class)){ //evento del mouse viene del arbol
			if(mapa != null && !mapa.isEmpty()) {
				mapa.clear();
				vista.getListaModel().clear();
			}
			
			DefaultMutableTreeNode nodoFuente = (DefaultMutableTreeNode) vista.getArbol().getLastSelectedPathComponent();;
			
			if(nodoFuente != null && !nodoFuente.getAllowsChildren()){
					try {
						
						 mapa = modelo.rssReader(nodoFuente.toString());
						
						//llenar la lista grafica con los titulos de los items
				        Iterator iter = mapa.entrySet().iterator();
				        int i =1;
				        while (iter.hasNext()) {
				            Map.Entry e = (Map.Entry)iter.next();            
				            Item itm = (Item) e.getValue();
				            //modelo.getCn().getConexion().
				            vista.getListaModel().addElement(i+"- "+itm.getTitulo());
				            i++;
				        }   
						
					} catch (IllegalArgumentException e) {
						System.out.println("IllegalArgumentException -- c");
						JOptionPane.showMessageDialog(null, "la url no es una fuente rss ");
					} catch (FeedException e) {
						System.out.println("FeedException -- c");
						JOptionPane.showMessageDialog(null, "la url no es una fuente rss ");
					} catch (IOException e) {
						System.out.println("IOException -- c");
						JOptionPane.showMessageDialog(null, "la url no es una fuente rss ");
					}
			}
		
		}else if(evento.getSource().getClass().equals(JList.class)){ //evento del mouse viene de JList
			
			JList<Object> lst = (JList<Object>) evento.getSource();
			//int indice = lst.locationToIndex(evento.getPoint());
			int indice =lst.getSelectedIndex();
	        if (indice >= 0) {
	        	Iterator iter = mapa.entrySet().iterator();
		        while (iter.hasNext()) {
		            Map.Entry e =  (Entry) iter.next();
		            if( Integer.valueOf(e.getKey().toString()) == indice){
		                Item itm = (Item) e.getValue();
		                if(!itm.isEsVisto()){
		                	itm.setEsVisto(true);
		                }
		                
		                
		                vista.getEditor().setText( "<b text='#569823'>Publicado: "+itm.getFecha_Publicacion()+ "</b><br/>"+
		                							"<b text='#999999'>Por: " +itm.getAutor() +
		                							"</b><br/>"+ "<p>"
		                								+ itm.getDescripcion() +"</p>" );
		                 
		                
		            }
		        }
	        }           
	           
		}
	}


	


	public void mouseEntered(MouseEvent evento) {
		
	}


	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}//fin de la clase


	
//	 public boolean Iniciar_Fuente( String url )
//	    {
//	        try {
//	            URL feedUrl = new URL(url);
//	            SyndFeedInput input = new SyndFeedInput();
//	            feed = input.build(new XmlReader(feedUrl));
//	            return true;
//	        } catch (Exception ex) {
//	            System.out.println( ex );
//	        }
//	        return false;
//	    }
//

//public boolean Iniciar_Fuente( String url ){
//	URL url_Fuente = new URL(url);
//	XmlReader xmlReader = null;
//	
//	try {
//	
//		xmlReader = new XmlReader(url);
//		SyndFeed feeder = new SyndFeedInput().build(xmlReader);
//		//xmlReader = new XmlReader(new File(args[0]));
//		//SyndFeed feeder = new SyndFeedInput().build(xmlReader);
//		if (feeder.getAuthor() != null)
//			System.out.println("Autor " + feeder.getAuthor());
//	
//		for (Object entradaObj : feeder.getEntries()) {
//			SyndEntry entrada = (SyndEntry) entradaObj;
//			System.out.println(entrada.getTitle());
//			System.out.println("Autor: " + entrada.getAuthor());
//		}
//	} finally {
//		if (xmlReader != null)
//			xmlReader.close();
//	}
//
//}