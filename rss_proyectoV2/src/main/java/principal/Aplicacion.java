package principal;





import javax.swing.SwingUtilities;

import vista.Gui;
import vista.InterfazVista;

import controlador.Controlador;
import modelo.Modelo;





public class Aplicacion {
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Modelo modelo = new Modelo();
					InterfazVista vista = new Gui();
					Controlador controlador = new Controlador(vista, modelo);
					vista.setControlador(controlador);
					controlador.arrancaVista();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
	


