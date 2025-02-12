package co.edu.upb.ejercicioprogramas;

import java.util.ArrayList;

public class IngenieriaMecanica implements InterfazProgramaAcademico {

	private String nombreDirector;
	private int cantidadEstudiantes;
	private ArrayList<String> materias;
	private int cantidadProfesores;
	
	public IngenieriaMecanica(String nombreDirector, int cantidadEstudiantes, ArrayList<String> materias, int cantidadProfesores) {
		this.nombreDirector = nombreDirector;
		this.cantidadEstudiantes = cantidadEstudiantes;
		this.materias = materias;
		this.cantidadProfesores = cantidadProfesores;
	}

	@Override
	public String getNombreDirector() {
		return this.nombreDirector;
	}

	@Override
	public void setNombreDirector(String nombreDirector) {
		this.nombreDirector = nombreDirector;
	}

	@Override
	public int getCantidadEstudiantes() {
		return this.cantidadEstudiantes;
	}

	@Override
	public void setCantidadEstudiantes(int cantidadEstudiantes) {
		this.cantidadEstudiantes = cantidadEstudiantes;
	}

	@Override
	public ArrayList<String> getMaterias() {
		return this.materias;
	}

	@Override
	public void setMaterias(ArrayList<String> materias) {
		this.materias = materias;
	}

	@Override
	public int getCantidadProfesores() {
		return this.cantidadProfesores;
	}

	@Override
	public void setCantidadProfesores(int cantidadProfesores) {
		this.cantidadProfesores = cantidadProfesores;
	}
	
	public void designarLaboratorios() {
		
		String materiasString = "";
		
		for(int i = 0; i<this.materias.size(); i++) {
			materiasString += materias.get(i)+" - ";
		}
		
		System.out.println("Se están designando los laboratorios para que los " + this.cantidadEstudiantes + " puedan ver las materias " + materiasString);
	}
	
	public void ensenarMotor1() {
		System.out.println("Se está enseñando Motor I a " + this.cantidadEstudiantes/5 + " por parte de todos los "+ this.cantidadProfesores + " profesores!");
	}

}
