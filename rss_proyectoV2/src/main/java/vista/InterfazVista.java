package vista;


import java.util.Vector;









import javax.swing.DefaultListModel;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;


import controlador.Controlador;

public interface InterfazVista {
	//esta es la interfaz 

	void setControlador(Controlador c);
	void arranca();
	
	
	//arbol
	JTree getArbol();
	
	DefaultMutableTreeNode getRaiz();
	
	Vector<DefaultMutableTreeNode> getArrayNodos();
	Vector<DefaultMutableTreeNode> getArrayFuentes();
	
	String getTextURLFuente();
	String getTextCarpeta();
	

	boolean buscarCarpeta(String nombre);
	DefaultMutableTreeNode buscarNodo(int indice);
	
	
	//lista
	JList<String >getLista();
	DefaultListModel<String> getListaModel();
	 
	
	//editor
	JEditorPane getEditor();
	static final String AGREGAR="btnAgregar";
	static final String ELIMINAR="btnEliminar";
	static final String NUEVA="btnNuevaCarpeta";
	
	

}
