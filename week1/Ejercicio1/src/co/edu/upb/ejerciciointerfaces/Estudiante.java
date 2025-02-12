package co.edu.upb.ejerciciointerfaces;

public class Estudiante implements Persona {
	
	private String nombres;
	private String nacionalidad;
	private int id;
	private int edad;
	
	Estudiante(String nombres, String nacionalidad, int id, int edad){
		this.edad = edad;
		this.nombres = nombres;
		this.nacionalidad = nacionalidad;
		this.id = id;
	}

	@Override
	public String getNombres() {
		return this.nombres;
	}

	@Override
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	@Override
	public String getNacionalidad() {
		return this.nacionalidad;
	}

	@Override
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int getEdad() {
		return this.edad;
	}

	@Override
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public void aprender() {
		System.out.println("La estudiante " + this.nombres + " con ID " + this.id + " está aprendiendo!");
	}
	
	public void hacerTarea () {
		System.out.println("La estudiante " + this.nombres + " con ID " + this.id + " está haciendo la tarea!");
	}

}
