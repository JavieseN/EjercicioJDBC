package es.eoi.jdbc.service;

import java.util.List;

import es.eoi.jdbc.entity.Alumno;
import es.eoi.jdbc.repository.AlumnoRepository;
import es.eoi.jdbc.repository.AlumnoRepositoryImpl;

public class AlumnoServiceImpl implements AlumnoService
{
	AlumnoRepository myRepository;;
	
	public AlumnoServiceImpl()
	{
		this.myRepository = new AlumnoRepositoryImpl();
	}

	public Alumno findByDni(String dni)
	{
		return this.myRepository.findByDni(dni);
	}

	public List<Alumno> findAll()
	{
		return this.myRepository.findAll();
	}

	public boolean create(Alumno nuevoAlumno)
	{
		return this.myRepository.create(nuevoAlumno);
	}

	public boolean delete(String dni)
	{
		return this.myRepository.delete(dni);
	}

	public boolean update(String dni, String nombre, String apellidos,Integer edad)
	{
		return this.myRepository.update(dni, nombre, apellidos,edad);
	}

}
