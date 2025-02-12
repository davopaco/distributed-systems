package co.edu.upb.ejercicioprogramas;

import java.util.ArrayList;

public interface InterfazProgramaAcademico {
	String getNombreDirector();
	void setNombreDirector(String nombreDirector);
	int getCantidadEstudiantes();
	void setCantidadEstudiantes(int cantidadEstudiantes);
	ArrayList<String> getMaterias();
	void setMaterias(ArrayList<String> materias);
	int getCantidadProfesores();
	void setCantidadProfesores(int cantidadProfesores);
}
