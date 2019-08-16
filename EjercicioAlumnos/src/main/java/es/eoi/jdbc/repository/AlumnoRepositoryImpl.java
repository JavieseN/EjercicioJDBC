package es.eoi.jdbc.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.eoi.jdbc.entity.Alumno;

public class AlumnoRepositoryImpl implements AlumnoRepository
{
	private java.sql.Connection openConnection()
	{
		Connection conexion = null;
		try
		{
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejerciciojdbc?serverTimezone=UTC&useSSL=false","root","1234");
		} catch (SQLException e)
		{
			System.err.println("No se ha podido acceder a la base de datos.");
		}
		return conexion;
	}

	public Alumno findByDni(String dni)
	{
		Connection conexion = openConnection();
		Alumno alumno = new Alumno();
		try
		{
			String sql = "SELECT * FROM alumno WHERE dni like ?";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, dni);
			
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next())
			{
				alumno.setDni(rs.getString("dni"));
				alumno.setNombre(rs.getString("nombre"));
				alumno.setApellidos(rs.getString("apellidos"));
				alumno.setEdad(rs.getInt("edad"));
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			conexion.close();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alumno;
	}

	public List<Alumno> findAll()
	{
		Connection conexion = openConnection();
		List<Alumno> alumnos = new ArrayList<Alumno>();
		
		String sql = "SELECT * FROM alumno";
		Statement ps;
		try
		{
			ps = conexion.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			
			while(rs.next())
			{
				alumnos.add(new Alumno(rs.getString("dni"),rs.getString("nombre"),rs.getString("apellidos"),rs.getInt("edad")));
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			conexion.close();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alumnos;
	}

	public boolean create(Alumno nuevoAlumno)
	{
		if(nuevoAlumno != null)
		{
			Connection conexion = openConnection();
			String sql = "INSERT INTO alumno(dni,nombre,apellidos,edad) VALUES (?,?,?,?)";
			PreparedStatement ps;
			
			try
			{
				ps = conexion.prepareStatement(sql);
				ps.setString(1, nuevoAlumno.getDni());
				ps.setString(2, nuevoAlumno.getNombre());
				ps.setString(3, nuevoAlumno.getApellidos());
				ps.setInt(4, nuevoAlumno.getEdad());
				if(ps.executeUpdate() == 0)
				{
					return false;
				}
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try
			{
				conexion.close();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean delete(String dni)
	{
		if(dni != null)
		{
			Connection conexion = openConnection();
			String sql = "DELETE FROM alumno WHERE dni = ?";
			PreparedStatement ps;
			try
			{
				ps = conexion.prepareStatement(sql);
				ps.setString(1, dni);
				if(ps.executeUpdate() == 0)
				{
					return false;
				}
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try
			{
				conexion.close();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean update(String dni, String nombre, String apellidos, Integer edad)
	{
		Connection conexion = openConnection();
		String sql = "UPDATE alumno SET nombre = ?, apellidos = ?, edad = ? WHERE dni = ? ";
		PreparedStatement ps;
		try
		{
			ps = conexion.prepareStatement(sql);
			ps.setString(1, nombre);
			ps.setString(2, apellidos);
			ps.setInt(3, edad);
			ps.setString(4, dni);
			if(ps.executeUpdate() == 0)
			{
				return false;
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			conexion.close();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
