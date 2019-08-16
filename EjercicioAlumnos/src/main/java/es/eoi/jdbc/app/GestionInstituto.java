package es.eoi.jdbc.app;

import java.util.List;

import es.eoi.jdbc.entity.Alumno;
import es.eoi.jdbc.service.AlumnoService;
import es.eoi.jdbc.service.AlumnoServiceImpl;

public class GestionInstituto
{
	public static void main(String[] args)
	{
		AlumnoService servicio = new AlumnoServiceImpl();
		Alumno yo = servicio.findByDni("46087920Z");
		System.out.println(yo);
		System.out.println("----------------------------------");
		List<Alumno> alumnos = servicio.findAll();
		for (Alumno alumno : alumnos)
		{
			System.out.println(alumno);
		}
		
		/*if(servicio.create(new Alumno("1256L","Kol","Ja",10)))
		{
			System.out.println("Se ha añadido");
		}
		else
		{
			System.out.println("F");
		}*/
		/*if(servicio.delete("123A"))
		{
			System.out.println("Se ha borrado correctamentes");
		}
		else
		{
			System.out.println("F");
		}*/
		/*if(servicio.update("1256L", "aaaaaa", "bbbbbbb", 16))
		{
			System.out.println("Se ha modificado");
		}
		else
		{
			System.out.println("F");
		}*/
	}

}
