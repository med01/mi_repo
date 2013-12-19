package vista;

import java.awt.BorderLayout;





import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTree;
import javax.swing.JList;
import javax.swing.JEditorPane;
import javax.swing.AbstractListModel;
import javax.swing.ListModel;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import controlador.Controlador;




public class Vista extends JFrame implements InterfazVista
{
	private Controlador c;
	
	private JPanel contentPane, panel_norte, panel_sur;
	
	private JLabel lblCarpeta, lblFuente;
	
	private JTextField txtField_carpeta;
	private JTextField textField_url;
	
	private JButton btnAgregar, btnEliminar, btnAadir, btnSalir;
	
	private JScrollPane scrollPane_tree, scrollPane_lista;
	
	private JList<Object> lista;
	private JTree arbol;
	private JEditorPane editor;
	private JPanel panel_editor;
	private JScrollPane scrollPane;
	private DefaultMutableTreeNode raiz;
	

	public Vista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("..\\rss_reader\\src\\main\\resources\\imagenes\\feed.png"));
		setBounds(100, 100, 1091, 755);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel_norte = new JPanel();
		panel_norte.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(30, 144, 100), null));
		contentPane.add(panel_norte, BorderLayout.NORTH);
		
		scrollPane_tree = new JScrollPane();
		
		scrollPane_lista = new JScrollPane();
		GroupLayout gl_panel_norte = new GroupLayout(panel_norte);
		gl_panel_norte.setHorizontalGroup(
			gl_panel_norte.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_norte.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_tree, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_lista, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_norte.setVerticalGroup(
			gl_panel_norte.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_norte.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_norte.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane_lista, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
						.addComponent(scrollPane_tree, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		lista = new JList<Object>();
		lista.setVisibleRowCount(15);
		
		lista.setModel(new AbstractListModel() {
			String[] values = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane_lista.setViewportView(lista);
		//crear la carpeta raiz :fuentes
	    raiz = new DefaultMutableTreeNode("Fuentes");
	    arbol = new JTree(raiz);
		scrollPane_tree.setViewportView(arbol);
		panel_norte.setLayout(gl_panel_norte);
		
		panel_sur = new JPanel();
		panel_sur.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(30, 144, 100), null));
		contentPane.add(panel_sur, BorderLayout.SOUTH);
		
		btnEliminar = new JButton("eliminar");
		btnEliminar.setActionCommand(ELIMINAR);
		
		btnAgregar = new JButton("agregar");
		btnAgregar.setActionCommand(AGREGAR);
		
		btnSalir = new JButton("salir");
		btnSalir.setActionCommand(SALIR);
		
		lblFuente = new JLabel("fuente :");
		
		txtField_carpeta = new JTextField();
		txtField_carpeta.setColumns(10);
		
		textField_url = new JTextField();
		textField_url.setColumns(10);
		
		lblCarpeta = new JLabel("carpeta :");
		
	    btnAadir = new JButton("a\u00F1adir");
		GroupLayout gl_panel_sur = new GroupLayout(panel_sur);
		gl_panel_sur.setHorizontalGroup(
			gl_panel_sur.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_sur.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_panel_sur.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCarpeta)
						.addComponent(lblFuente))
					.addGap(74)
					.addGroup(gl_panel_sur.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_sur.createSequentialGroup()
							.addComponent(textField_url, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE)
							.addGap(84)
							.addComponent(btnAgregar, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
							.addGap(39)
							.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_sur.createSequentialGroup()
							.addComponent(txtField_carpeta, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(btnAadir)))
					.addGap(150))
		);
		gl_panel_sur.setVerticalGroup(
			gl_panel_sur.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_sur.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_sur.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_url, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEliminar)
						.addComponent(lblFuente)
						.addComponent(btnAgregar)
						.addComponent(btnSalir))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_sur.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtField_carpeta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCarpeta)
						.addComponent(btnAadir))
					.addGap(53))
		);
		panel_sur.setLayout(gl_panel_sur);
		
		panel_editor = new JPanel();
		panel_editor.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(30, 144, 100), null));
		contentPane.add(panel_editor, BorderLayout.CENTER);
		
		scrollPane = new JScrollPane();
		GroupLayout gl_panel_editor = new GroupLayout(panel_editor);
		gl_panel_editor.setHorizontalGroup(
			gl_panel_editor.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_editor.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1045, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_editor.setVerticalGroup(
			gl_panel_editor.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_editor.createSequentialGroup()
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		editor = new JEditorPane();
		scrollPane.setViewportView(editor);
		panel_editor.setLayout(gl_panel_editor);
	}







	public void arranca() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setVisible(true);
		
	}


	
	public JTextField getTxtField_carpeta() {
		return txtField_carpeta;
	}



	public JTextField getTextField_url() {
		return textField_url;
	}



	public JList<Object> getLista() {
		return lista;
	}



	public JTree getArbol() {
		return arbol;
	}



	public JEditorPane getEditorPane() {
		return editor;
	}



	public DefaultMutableTreeNode getRaiz() {
		return raiz;
	}



	
	
	
	
	
	
}
