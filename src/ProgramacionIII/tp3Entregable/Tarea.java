package ProgramacionIII.tp3Entregable;

public class Tarea implements Comparable<Tarea>{
	private Integer id;
	private String nombre;
	private String descripcion;
	private Integer duracion;
	
	public Tarea(int id, int duracion) {
		this.id = id;
		this.nombre = null;
		this.descripcion = null;
		this.duracion = duracion;
	}

	public Integer getId() {
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	@Override
	public String toString() {
		return this.id.toString();
	}

	@Override
	public int compareTo(Tarea t) {
		return new Integer(id).compareTo(t.getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarea other = (Tarea) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
