package vista;

import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;









public interface InterfazVista {
	
	static final String AGREGAR="btnAgregar";
	static final String ELIMINAR="btnEliminar";
	static final String NUEVACARPETA="btnNuevaCarpeta";
	static final String SALIR="btnSalir";
	
void arranca();
	

public JTextField getTxtField_carpeta();
public JTextField getTextField_url();
public JList<Object> getLista() ;
public JTree getArbol();
public DefaultMutableTreeNode getRaiz();
public JEditorPane getEditorPane() ;

	

}
