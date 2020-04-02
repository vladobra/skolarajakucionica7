package com.skolarajak.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skolarajak.exceptions.dao.ResultNotFoundException;
import com.skolarajak.model.Vlasnik;
import com.skolarajak.model.Vozilo;
import com.skolarajak.utils.DBUtils;

public class VoziloDBDAOImpl implements VoziloDAO {
	
	public VoziloDBDAOImpl() throws ClassNotFoundException {
		 Class.forName(DBUtils.myDriver);
	}

	@Override
	public Vozilo create(Vozilo vozilo) {
		try {
			Connection conn = getConnection();

			// the mysql insert statement
			String query = "insert into vozilo (regbroj, godisteProizvodnje, status, vlasnikId)"
					+ " values (?, ?, ?, ?)";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, vozilo.getRegistarskiBroj());
			preparedStmt.setInt(2,vozilo.getGodisteProizvodnje());
			preparedStmt.setBoolean(3, vozilo.isAktivno());
			preparedStmt.setString(4, vozilo.getVlasnik().getBrojVozackeDozvole());

			// execute the preparedstatement
			preparedStmt.execute();
            preparedStmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		return vozilo;
	}

	@Override
	public Vozilo read(String registarskiBroj) throws ResultNotFoundException {
		Vlasnik vlasnik = new Vlasnik();
		Vozilo vozilo =  new Vozilo();
		try {
			Connection conn = getConnection();

			// the mysql insert statement
			String query = "select * from vlasnik, vozilo where regbroj=?"
					+ " and vlasnik.brojVozackeDozvole=vozilo.vlasnikId";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = conn.prepareStatement(query);
		
			preparedStmt.setString(1, registarskiBroj);

			// execute the preparedstatement
			
			ResultSet rs = preparedStmt.executeQuery();
			
		    while ( rs.next() )
		    {
		      vlasnik.setBrojVozackeDozvole(rs.getString("brojVozackeDozvole") );
		      vlasnik.setIme(rs.getString("ime") );
		      vlasnik.setPrezime(rs.getString("prezime") );
		      
		      vozilo.setRegistarskiBroj(registarskiBroj);
		      vozilo.setGodisteProizvodnje(rs.getInt("godisteProizvodnje"));
		      vozilo.setAktivno(rs.getBoolean("status"));
		      vozilo.setVlasnik(vlasnik);
		      
		      vlasnik.setVozilo(vozilo);
		    }
		    
		    rs.close();
			preparedStmt.close();
			conn.close();
		} catch (Throwable t) {
			System.err.println("Got an exception!");
			System.err.println(t.getMessage());
		}
		return vozilo;
	}

	@Override
	public Vozilo update(Vozilo vozilo) {
		try {
			Connection conn = getConnection();

			// the mysql insert statement
			String query = "update vozilo set regbroj=?, godisteProizvodnje=?, status=?, vlasnikId=?"
					+ " where regbroj = ?";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, vozilo.getRegistarskiBroj());
			preparedStmt.setInt(2, vozilo.getGodisteProizvodnje());
			preparedStmt.setBoolean(3, vozilo.isAktivno());
			preparedStmt.setString(4, vozilo.getVlasnik().getBrojVozackeDozvole());
			preparedStmt.setString(5, vozilo.getRegistarskiBroj());

			// execute the preparedstatement
			preparedStmt.executeUpdate();
			
            preparedStmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		return vozilo;
	}

	@Override
	public void delete(String registarskiBroj) {
		try {
			Connection conn = getConnection();

			// the mysql insert statement
			String query = "delete from vozilo where regbroj = ?";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, registarskiBroj);

			// execute the preparedstatement
			preparedStmt.execute();
			
            preparedStmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}

	}

	@Override
	public List<Vozilo> getAll() throws ResultNotFoundException {
		List<Vozilo> vozila = new ArrayList<Vozilo>();

		try {
			Connection conn = getConnection();

			// the mysql insert statement
			String query = "select * from vlasnik, vozilo"
					+ " WHERE vlasnik.brojVozackeDozvole=vozilo.vlasnikId";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = conn.prepareStatement(query);

			// execute the preparedstatement

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				Vlasnik vlasnik = new Vlasnik();
				Vozilo vozilo = new Vozilo();
				vlasnik.setBrojVozackeDozvole(rs.getString("brojVozackeDozvole"));
				vlasnik.setIme(rs.getString("ime"));
				vlasnik.setPrezime(rs.getString("prezime"));

				vozilo.setRegistarskiBroj(rs.getString("regbroj"));
				vozilo.setGodisteProizvodnje(rs.getInt("godisteProizvodnje"));
				vozilo.setAktivno(rs.getBoolean("status"));
				vozilo.setVlasnik(vlasnik);
				vlasnik.setVozilo(vozilo);
				
				vozila.add(vozilo);
			}

			rs.close();
			preparedStmt.close();
			conn.close();
		} catch (Throwable t) {
			System.err.println("Got an exception!");
			System.err.println(t.getMessage());
		}
		return vozila;
	}

	@Override
	public long count() throws ResultNotFoundException {
		
		long count = 0;
		try {
			Connection conn = getConnection();
			// the mysql insert statement
			String query = "select count(*) as broj from vozilo";

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
	public List<Vozilo> getEuro3Vozila() throws ResultNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vozilo> getAktivnaVozila() throws ResultNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vozilo> getAllVozilaCijeImeVlasnikaSadrziSlovoA() throws ResultNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
private Connection getConnection() throws ClassNotFoundException, SQLException {
		
		return DriverManager.getConnection(DBUtils.myUrl, "root", "root");
	}

}
