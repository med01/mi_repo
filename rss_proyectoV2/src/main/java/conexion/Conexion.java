package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.tree.TreePath;

import vista.InterfazVista;
import controlador.Controlador;
import modelo.Fuente;



public class Conexion {
	
	static final Conexion cn = new Conexion();
	Connection conexion ;
	Controlador controlador;

	public static Conexion getConexion() {
		return cn;
	}
	
	private   Conexion()
    {
		 
      
        try
        {
        	
            Class.forName("com.mysql.jdbc.Driver");
            String servidor = "jdbc:mysql://localhost/rss";
            String usuarioDB="root";
            String passwordDB="";
             conexion =  DriverManager.getConnection(servidor,usuarioDB,passwordDB);
             
             System.out.println("conectado");
             
        }
        catch(ClassNotFoundException ex)
        {
        	JOptionPane.showMessageDialog(null, "Error ..."+ ex);
            
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,  "Error en la Conexión con la base datos ");
            
        }
		
    }
	
	public ArrayList<Fuente> getFuentes(){
		 ArrayList<Fuente> listaFuentes = new ArrayList<Fuente>();
		
		try{
			 
			 Statement st = conexion.createStatement();
			 ResultSet rs = st.executeQuery("select * from rss.fuentes");
			 Fuente f = null;
			 	while(rs.next()){
			 		f = new Fuente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			 		listaFuentes.add(f);
			 	}
			 	return listaFuentes;
		 }catch(SQLException e){
			 System.out.println("Error de conexion...");
		 }
		
		return null;
	}
	
	
	
//	public int anyadeFuente(Fuente fuente){
//			TreePath trp = controlador.getVista().getArbol().getSelectionPath();
//			System.out.println(trp.getLastPathComponent().toString());
//		try {
//			PreparedStatement consulta = conexion.prepareStatement("INSERT INTO rss.fuentes(`Nombre`,`URI`,`carpeta`) VALUES(?,?,?);");
//			consulta.setString(2, fuente.getNombre());
//			consulta.setString(3, fuente.getUri());
//			consulta.setString(4, fuente.setCarpeta(trp.toString()));
//			return consulta.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return -1;
//	}

}
