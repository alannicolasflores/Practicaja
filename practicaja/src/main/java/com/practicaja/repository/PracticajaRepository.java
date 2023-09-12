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

import com.practicaja.beans.Cuenta;
import com.practicaja.beans.Movimiento;
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
				throw new Exception("0000", "No hay registros");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new Exception("0001", e.getMessage());
		}
		
		return nombre;
	}
	@Override
	public Cuenta consultarSaldo(String noTarjeta, String NIP) throws Exception {
		String query = "select t.Cuenta_noCuenta, t.noTarjeta, c.saldo, b.idBanco, b.nombreBanco, u.idUsuario, u.nombre, u.aPaterno, u.aMaterno from Tarjeta t, Cuenta c, Banco b, Usuario u where t.Cuenta_noCuenta = c.noCuenta and c.Banco_idBanco = b.idBanco and u.idUsuario = c.Usuario_idUsuario and t.noTarjeta = '"+noTarjeta+"' and t.NIP = '"+NIP+"'";
		Cuenta datosCuenta = null;

		try {
			conexion = this.jdbcTemplate.getDataSource().getConnection();
			//DESCOMENTAR ESE SIEMPRE PARA INSERCIONES Y ACTUALIZACIONES 
			//conexion.setAutoCommit(false);
			stm = conexion.createStatement();
			rs = stm.executeQuery(query);
			if(rs.next())
			{
				datosCuenta = new Cuenta(
					rs.getInt(1),
					rs.getString(2),
				    rs.getDouble(3),
					rs.getInt(4),
					rs.getString(5),
					rs.getInt(6), 
					rs.getString(7),
					rs.getString(8),
					rs.getString(9));
			}
			else
			{
				throw new Exception("1000", "Los datos no son correctos");
			}
		} catch (SQLException e) {
			throw new Exception("1001", e.getMessage());
		}

		return datosCuenta;
	}
	@Override
	public double retirar(double montoRetiro, Cuenta cuenta) throws Exception {
		String query = "INSERT INTO Movimientos (tipoMovimiento, Importe, fechaMovimiento, Cuenta_noCuenta, Cuenta_Banco_idBanco,Cuenta_Usuario_idUsuario) VALUES ('Retiro', "+montoRetiro+", CURDATE(), "+cuenta.getNoCuenta()+", "+cuenta.getIdBanco()+", "+cuenta.getIdUsuario()+")";
		double saldoFinal = 0;

		try {
			conexion = this.jdbcTemplate.getDataSource().getConnection();
			//DESCOMENTAR ESE SIEMPRE PARA INSERCIONES Y ACTUALIZACIONES 
			conexion.setAutoCommit(false);
			stm = conexion.createStatement();
			System.out.println(query);
			stm.executeUpdate(query);
			saldoFinal = cuenta.getSaldo() - montoRetiro;

			query = "update Cuenta set saldo = "+saldoFinal+" where noCuenta = "+cuenta.getNoCuenta()+"";
			System.out.println(query);
			stm.executeUpdate(query);
			conexion.commit();
			
		} catch (SQLException e) {			
			throw new Exception("3000", "Ocurrió un error al retirar");
		}

		return saldoFinal;
	}
	@Override
	public Cuenta consultaCuentaDestino(String noTarjetaCuenta) throws Exception {
		String query = "SELECT c.noCuenta, t.noTarjeta, c.saldo, b.idBanco, b.nombreBanco, u.idUsuario, u.nombre, u.aPaterno, u.aMaterno FROM Cuenta c, Tarjeta t, Banco b, Usuario u WHERE c.noCuenta = t.Cuenta_noCuenta AND c.Banco_idBanco = b.idBanco AND c.Usuario_idUsuario = u.idUsuario AND c.noCuenta = "+noTarjetaCuenta+" ";
		Cuenta datosCuenta = null;

		try {
			conexion = this.jdbcTemplate.getDataSource().getConnection();
			//DESCOMENTAR ESE SIEMPRE PARA INSERCIONES Y ACTUALIZACIONES 
			//conexion.setAutoCommit(false);
			stm = conexion.createStatement();
			rs = stm.executeQuery(query);
			if(rs.next())
			{
				datosCuenta = new Cuenta(
					rs.getInt(1), 
					rs.getString(2),
					rs.getDouble(3),
					rs.getInt(4),
					rs.getString(5),
					rs.getInt(6),
					rs.getString(7),
					rs.getString(8),
					rs.getString(9)
				);
			}
			else
			{
				query = "SELECT c.noCuenta, t.noTarjeta, c.saldo, b.idBanco, b.nombreBanco, u.idUsuario, u.nombre, u.aPaterno, u.aMaterno FROM Tarjeta t, Cuenta c, Banco b, Usuario u WHERE t.Cuenta_noCuenta = c.noCuenta AND t.Cuenta_idBanco = b.idBanco AND t.Cuenta_idUsuario = u.idUsuario AND t.noTarjeta = '"+noTarjetaCuenta+"'";
				rs = stm.executeQuery(query);
				if(rs.next())
				{
					datosCuenta = new Cuenta(
						rs.getInt(1), 
						rs.getString(2),
						rs.getDouble(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9)
					);
				}
				else{
					throw new Exception("4000", "Los datos no son correctos");
				}
			}
		} catch (SQLException e) {
			throw new Exception("4001", e.getMessage());
		}

		return datosCuenta;
	}
	@Override
	public Movimiento depositar(double montoDeposito, Cuenta cuenta) throws Exception {
		String query = "INSERT INTO Movimientos (tipoMovimiento, Importe, fechaMovimiento, noCuentaDestino) VALUES ('Depósito', " + montoDeposito + ", CURDATE(), " + cuenta.getNoCuenta() + ")";
		double saldoFinal = 0;
		Movimiento movimiento = null;
		try {
			conexion = this.jdbcTemplate.getDataSource().getConnection();
			conexion.setAutoCommit(false);
			stm = conexion.createStatement();
			System.out.println(query);
			stm.executeUpdate(query);
			saldoFinal = cuenta.getSaldo() + montoDeposito;
			query = "UPDATE Cuenta SET saldo = " + saldoFinal + " WHERE noCuenta = " + cuenta.getNoCuenta() + "";
			System.out.println(query);
			stm.executeUpdate(query);
			conexion.commit();

			query = "select idMovimientos, fechaMovimiento from Movimientos where tipoMovimiento = 'Depósito' and Importe = "+montoDeposito+" and noCuentaDestino = "+cuenta.getNoCuenta()+"";
			rs = stm.executeQuery(query);
			if(rs.next())
			{
				movimiento = new Movimiento(rs.getInt(1), "Depósito", montoDeposito, rs.getString(2), cuenta.getNoCuenta());
			}
			else{
				throw new Exception("5000", "Ocurrió un error al depositar");
			}
		} catch (SQLException e) {
			//rollback
			//conexion.rollback();
			throw new Exception("5001", e.getMessage());
		}

		return movimiento;
		
	}
}
