package co.edu.upb.ejerciciointerfaces;

import java.util.ArrayList;

public class PrincipalPersonas {
	public static void main(String[] args) {
		
		ArrayList<Persona> personas = new ArrayList<Persona>();
		
		Estudiante estudiante = new Estudiante("Sofía", "venezolana", 456, 22);
		Docente profesor = new Docente("Ali", "colombiano", 678, 40);
		
		personas.add(estudiante);
		personas.add(profesor);
		
		for(int i=0; i<personas.size(); i++) {
			System.out.println(personas.get(i).getNombres());
		}
		
		profesor.ensenar();
		estudiante.aprender();
		estudiante.hacerTarea();
		profesor.subirNotas();
	}
}
