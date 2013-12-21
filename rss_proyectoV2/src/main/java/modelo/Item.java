package modelo;

public class Item {
	 private String titulo;
	    private String descripcion;
	    private String autor;
	    private String enlace;
	    private String fecha_publicacion;
		private boolean esVisto;

	    public Item(){}

	    public void setTitulo( String value )
	    {
	        this.titulo =  Valor( value , "Sin titulo");
	    }

	    public String getTitulo()
	    {
	        return this.titulo;
	    }

	    public void setDescripcion( String value )
	    {
	        this.descripcion = Valor( value , "No tiene");
	    }
	    
	    public String getDescripcion()
	    {
	        return this.descripcion;
	    }
	    
	 

	    public void setAutor( String value )
	    {
	        this.autor =  Valor( value , "Anonimo");
	    }

	    public String getAutor()
	    {
	        return this.autor;
	    }

	    public void setEnlace( String value )
	    {
	        this.enlace = Valor( value , "Sin enlace");
	    }

	    public String getEnlace()
	    {
	        return this.enlace;
	    }

	    public void setFecha_Publicacion( Object value )
	    {
	        this.fecha_publicacion =   Valor( value , "Sin Fecha de publicación");
	    }

	    public String getFecha_Publicacion()
	    {
	        return this.fecha_publicacion;
	    }

	    private String Valor(Object value , String Default)
	    {
	        return (value!=null) ? (value.toString().length()>0 ? value.toString() : Default ) : Default ;        
	    }

		public boolean isEsVisto() {
			return esVisto;
		}

		public void setEsVisto(boolean esVisto) {
			this.esVisto = esVisto;
		}

}
