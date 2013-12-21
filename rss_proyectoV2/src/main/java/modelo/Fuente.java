package modelo;

public class Fuente {
	
	private int id;
	private String nombre;
	private String uri;
	private String carpeta;
	
	

	public Fuente(int id, String nombre, String uri,  String carpeta) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.uri = uri;
		this.carpeta= carpeta;
	}


	


	
	




	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public String getCarpeta() {
		return carpeta;
	}





	public void setCarpeta(String carpeta) {
		this.carpeta = carpeta;
	}





	@Override
	public String toString() {
		return nombre;
	}
	

}
