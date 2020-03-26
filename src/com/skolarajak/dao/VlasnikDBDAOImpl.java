package com.skolarajak.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import com.skolarajak.exceptions.dao.ResultNotFoundException;
import com.skolarajak.model.Vlasnik;
import com.skolarajak.utils.RandomUtils;

public class VlasnikDBDAOImpl implements VlasnikDAO {
	
	private final String myDriver = "com.mysql.jdbc.Driver";
	private final String myUrl = "jdbc:mysql://localhost:3306/ams";
	
	public VlasnikDBDAOImpl() throws ClassNotFoundException {
		// create a mysql database connection

	    Class.forName(myDriver);
	}

	@Override
	public Vlasnik create(Vlasnik vlasnik) {
		try {
			Connection conn = getConnection();

			// the mysql insert statement
			String query = "insert into vlasnik (brojVozackeDozvole, ime, prezime)"
					+ " values (?, ?, ?)";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, vlasnik.getBrojVozackeDozvole());
			preparedStmt.setString(2, vlasnik.getIme());
			preparedStmt.setString(3, vlasnik.getPrezime());

			// execute the preparedstatement
			preparedStmt.execute();
            preparedStmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		return vlasnik;
	};

	@Override
	public Vlasnik read(String brojVozackeDozvole) throws ResultNotFoundException {
		Vlasnik vlasnik = new Vlasnik();
		try {
			Connection conn = getConnection();

			// the mysql insert statement
			String query = "select * from vlasnik where brojVozackeDozvole=?";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, brojVozackeDozvole);

			// execute the preparedstatement
			
			ResultSet rs = preparedStmt.executeQuery();
			
		    while ( rs.next() )
		    {
		      vlasnik.setBrojVozackeDozvole(brojVozackeDozvole);
		      vlasnik.setIme(rs.getString("ime") );
		      vlasnik.setPrezime(rs.getString("prezime") );
		    }
		    
		    rs.close();
			preparedStmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		return vlasnik;
	};

	@Override
	public Vlasnik update(Vlasnik vlasnik) {
		try {
			Connection conn = getConnection();

			// the mysql insert statement
			String query = "update vlasnik set ime=?, prezime=? where brojVozackeDozvole = ?";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, vlasnik.getIme());
			preparedStmt.setString(2, vlasnik.getPrezime());
			preparedStmt.setString(3, vlasnik.getBrojVozackeDozvole());

			// execute the preparedstatement
			preparedStmt.executeUpdate();
			
            preparedStmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		return vlasnik;
	}

	@Override
	public void delete(String brojVozackeDozvole) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Vlasnik> getAll() throws ResultNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() throws ResultNotFoundException {
		long count = 0;
		try {
			Connection conn = getConnection();
			// the mysql insert statement
			String query = "select count(*) as broj from vlasnik";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			// execute the preparedstatement
			
			ResultSet rs = preparedStmt.executeQuery();
			
		    while ( rs.next() )
		    {      
		      count = rs.getLong("broj");
		    }
		    
		    rs.close();
			preparedStmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		return count;
	}

	@Override
	public List<Vlasnik> getAllVlasniciAktivnihVozila() throws ResultNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		
		return DriverManager.getConnection(myUrl, "root", "root");
	}
}
