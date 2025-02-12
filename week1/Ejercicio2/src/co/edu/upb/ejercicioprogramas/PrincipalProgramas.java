package co.edu.upb.ejercicioprogramas;

import java.util.ArrayList;
import java.util.Arrays;

public class PrincipalProgramas {

	public static void main(String[] args) {
		
		ArrayList<String> materiasDerecho = new ArrayList<String>(Arrays.asList("Constitución", "Jurídica"));
		ArrayList<String> materiasIngMec = new ArrayList<String>(Arrays.asList("Motor", "Física", "Mecánica de fluidos"));
		
		Derecho derechoProg = new Derecho("Carlos Andrés Peña Moreno", 1000, materiasDerecho, 20);
		IngenieriaMecanica ingMecanicaProg = new IngenieriaMecanica("Gabriel Fernando García Sánchez", 980, materiasIngMec, 25);
		ArrayList<InterfazProgramaAcademico> programasAcademicos = new ArrayList<InterfazProgramaAcademico>();
		programasAcademicos.add(derechoProg);
		programasAcademicos.add(ingMecanicaProg);
		
		for (int i = 0; i<programasAcademicos.size(); i++) {
			System.out.println(programasAcademicos.get(i).getNombreDirector());
		}
		
		derechoProg.delegarConsultoria();
		derechoProg.ensenarConstitucion1();
		ingMecanicaProg.designarLaboratorios();
		ingMecanicaProg.ensenarMotor1();	
	}
}
