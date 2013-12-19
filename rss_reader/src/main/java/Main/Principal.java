package Main;

import javax.swing.SwingUtilities;







import modelo.Modelo;
import controlador.Controlador;
import vista.InterfazVista;
import vista.Vista;


public class Principal {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Modelo m = new Modelo();
					InterfazVista v = new Vista();
					Controlador c = new Controlador(m, v);
					v.arranca();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
