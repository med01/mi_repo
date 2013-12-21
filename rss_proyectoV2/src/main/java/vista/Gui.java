package vista;







import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTree;

import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import modelo.Modelo;
import controlador.Controlador;

import java.util.Vector;
import java.awt.Font;

import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;






@SuppressWarnings("serial")
public class Gui  implements InterfazVista{

	private JTree arbol;
	private DefaultTreeModel  m;
	
	private JList<String> lista;
	private DefaultListModel<String> mdlist;
	
	private JLabel urlLabel;
	private JTextField url_txt, carpeta_txt;
	private JButton btnAgregar, btnEliminar, btnNuevaCarpeta;
	private JPanel panelGestion, treePanel, entradasPanel,articulosPanel;
	private JEditorPane fuentes, articulos;
	private JScrollPane jspe,jsptree , jspa;

	private DefaultMutableTreeNode raiz;
	
	private Vector<DefaultMutableTreeNode> vec_carpetas = new Vector<DefaultMutableTreeNode>();
	private Vector<DefaultMutableTreeNode> vec_fuentes = new Vector<DefaultMutableTreeNode>();
	private JLabel lblNewLabel_1;
	private JSeparator separator_1;
	
	JFrame ventana;
	Modelo modelo = new Modelo();
	Controlador controlador = new Controlador(this, modelo);
	
	
	 

	public Gui(){
		
		ventana = new JFrame("Lector rss");
		ventana.setIconImage(Toolkit.getDefaultToolkit().getImage("..\\rss_proyectoV2\\src\\main\\resources\\imagenes\\feed.png"));
		
		ventana.setSize(1008, 689);
		ventana.getContentPane().setLayout(null);
		
		//------------------------------panel del Arbol--------------------------------------------
		
		treePanel = new JPanel();
		treePanel.setBounds(10, 50, 210, 411);
		treePanel.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, null));
		
		//crear la carpeta raiz :categorias
		raiz=new DefaultMutableTreeNode("Categorias");
		vec_carpetas.add(raiz);
		controlador.cargarFuentes();	
		ventana.getContentPane().add(treePanel);
		treePanel.setLayout(null);
		arbol=new JTree(raiz);
		jsptree = new JScrollPane(arbol);
		jsptree.setBounds(2, 2, 206, 407);
		treePanel.add(jsptree);
		
		//---------------------------panel de JList--------------------------------------------------
		JPanel entradasPanel = new JPanel();
		entradasPanel.setBounds(230, 54, 752, 135);
		ventana.getContentPane().add(entradasPanel);
		entradasPanel.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, null));
		ventana.getContentPane().add(entradasPanel);
		 mdlist = new DefaultListModel<String>();
		   entradasPanel.setLayout(null);
		  

		  
		   lista =new JList<String>();
		   lista.setModel(mdlist);
		   
		   jspe = new JScrollPane(lista);
		   jspe.setBounds(2, 2, 748, 131);
		   entradasPanel.add(jspe);
		
		 //--------------------------------------------------panel de Articulos--------------------------------------
		articulosPanel = new JPanel();
		articulosPanel = new JPanel();
		articulosPanel.setBounds(230, 198, 752, 263);
		articulosPanel.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, null));
		ventana.getContentPane().add(articulosPanel);
		articulosPanel.setLayout(null);
		
		articulos = new JEditorPane("text/html", "");
		articulos.setEditable(false);
		jspa = new JScrollPane(articulos);
		jspa.setBounds(2, 2, 748, 259);
		articulosPanel.add(jspa);
		
		//--------------------------------------Separador--------------------------------------------------------------
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.RED);
		separator.setBounds(10, 475, 972, 12);
		ventana.getContentPane().add(separator);
		
		
		
		
		panelGestion = new JPanel();
		panelGestion.setBounds(10, 485, 972, 64);
		panelGestion.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, null));
		panelGestion.setLayout(null);
		
		
		
		
		//-----------------------panel de abajo y sus componentes--------------------------------
		urlLabel = new JLabel("Fuente: ");
		urlLabel.setBounds(10, 26, 50, 15);
		urlLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelGestion.add(urlLabel);
		url_txt  = new JTextField(40);
		url_txt.setBounds(97, 24, 326, 20);
		panelGestion.add(url_txt);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(604, 18, 0, 0);
		lblNewLabel_2.setIcon(new ImageIcon("..\\rss_proyectoV2\\src\\main\\resources\\imagenes\\37.png"));
		panelGestion.add(lblNewLabel_2);
		
		
		btnAgregar=new JButton("agregar");
		btnAgregar.setBounds(505, 23, 71, 23);
		btnAgregar.setActionCommand(AGREGAR);
				panelGestion.add(btnAgregar);
		
		btnEliminar=new JButton("eliminar");
		btnEliminar.setBounds(626, 23, 69, 23);
		btnEliminar.setActionCommand(ELIMINAR);
		panelGestion.add(btnEliminar);
		ventana.getContentPane().add(panelGestion);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 573, 972, 50);
		ventana.getContentPane().add(panel);
		panel.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, null));
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Carpeta:");
		lblNewLabel.setBounds(10, 12, 54, 15);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblNewLabel);
		
		carpeta_txt = new JTextField();
		carpeta_txt.setBounds(99, 10, 86, 20);
		panel.add(carpeta_txt);
		carpeta_txt.setColumns(10);
		
				
		btnNuevaCarpeta = new JButton("nueva carpeta");
		btnNuevaCarpeta.setBounds(502, 9, 103, 23);
		panel.add(btnNuevaCarpeta);
		btnNuevaCarpeta.setActionCommand(NUEVA);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(360, 12, 18, 28);
		lblNewLabel_1.setIcon(new ImageIcon(Gui.class.getResource("/javax/swing/plaf/metal/icons/ocean/newFolder.gif")));
		panel.add(lblNewLabel_1);
		
		separator_1 = new JSeparator();
		separator_1.setBackground(Color.RED);
		separator_1.setBounds(10, 560, 972, 2);
		ventana.getContentPane().add(separator_1);
		
	}
	
	
	
	
	public void setControlador(Controlador c) {
		btnAgregar.addActionListener(c);
		btnEliminar.addActionListener(c);
		btnNuevaCarpeta.addActionListener(c);
		arbol.addMouseListener(c);
		lista.addMouseListener(c);
		articulos.addMouseListener(c);
		
	}

	
	public void arranca() {
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(true);
		ventana.setVisible(true);	
	}


	public String getTextURLFuente() {
		return url_txt.getText();
	}
	
	

	
	//buscar un nodo segun el indice
	public boolean buscarCarpeta(String nombre) {
		
		for(DefaultMutableTreeNode c: vec_carpetas){
			System.out.println(c.getUserObject().toString()+ "\n");
			if(c.getUserObject().toString().toLowerCase().equals(nombre.toLowerCase())){
				return true;
			}
		}
		return false;
	}
	
	public Vector<DefaultMutableTreeNode> getArrayNodos() {
		return vec_carpetas;
	}


	public DefaultMutableTreeNode getRaiz() {
		return raiz;
	}

	public JTree getArbol() {
		return arbol;
	}

	public String getTextCarpeta() {
		return carpeta_txt.getText();
	}



	public Vector<DefaultMutableTreeNode> getArrayFuentes() {
		return vec_fuentes;
	}



	public DefaultMutableTreeNode buscarNodo(int indice) {
		return vec_carpetas.get(indice);
	}



	public JList<String> getLista() {
		return lista;
	}



	public DefaultListModel<String> getListaModel() {
		return mdlist;
	}



	public JEditorPane getEditor() {
		return articulos;
	}
}
		
		
		
		
	
		
		
		


