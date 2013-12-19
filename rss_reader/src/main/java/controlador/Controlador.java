package controlador;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import modelo.Modelo;
import vista.InterfazVista;



public class Controlador implements ActionListener, MouseListener {
	
	private Modelo modelo;
	private InterfazVista vista;
	
	

	public Controlador(Modelo modelo, InterfazVista vista) {
		super();
		this.modelo = modelo;
		this.vista = vista;
	}
	
	//getters
	public InterfazVista getVista() {
		return vista;
	}
	
	public Modelo getModelo() {
		return modelo;
	}
	
	public void actionPerformed(ActionEvent evnt) {
		ActionEvent src = (ActionEvent) evnt.getSource();
		
		if (src.getActionCommand().equals((InterfazVista.AGREGAR))) {
			System.out.println("agregar");
		}else if(src.getActionCommand().equals(InterfazVista.NUEVACARPETA)){
			System.out.println("nueva carpeta");
		}else if(src.getActionCommand().equals(InterfazVista.ELIMINAR)){
			System.out.println("eliminar fuente");
		}else{
			System.out.println("nada que hacer");
		}
		
	}

	public void mouseClicked(MouseEvent evnt) {
		
		
	}


	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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


	

}
