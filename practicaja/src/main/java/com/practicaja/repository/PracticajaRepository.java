package com.practicaja.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.practicaja.common.Exception;
import com.practicaja.services.PracticajaServices;

@Component("PracticajaRepository")
public class PracticajaRepository implements PracticajaServices{

	@Autowired
	@Qualifier("jdbcMysql")
	private JdbcTemplate jdbcTemplate;
	private Connection conexion;
	private Statement stm;
	private ResultSet rs;
	private PreparedStatement pstmt;
	@Override
	public String saluda() throws Exception {
		// TODO Auto-generated method stub
		String nombre = "";
		try {
			conexion = this.jdbcTemplate.getDataSource().getConnection();
			//DESCOMENTAR ESE SIEMPRE PARA INSERCIONES Y ACTUALIZACIONES 
			//conexion.setAutoCommit(false);
			stm = conexion.createStatement();
			rs = stm.executeQuery("select nombreUsuario from Usuarios where id = 1");
			if(rs.next())
			{
				nombre = rs.getString(1);
			}
			else
			{
				throw new Exception("2000", "No hay registros");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new Exception("2001", e.getMessage());
		}
		
		return nombre;
	}
}
