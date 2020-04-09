package com.skolarajak.dao;

import java.util.List;

import com.skolarajak.exceptions.dao.ResultNotFoundException;
import com.skolarajak.model.Vlasnik;
/**
 * Vlasnik DAO interface
 * 
 * @author vladobra
 *
 */
public interface VlasnikDAO {
	/**
	 * Kreiranje vlasnika
	 * @param vlasnik Vlasnik
	 * @return Kreiran vlasnik
	 */
	Vlasnik create(Vlasnik vlasnik);
	Vlasnik read(String brojVozackeDozvole) throws ResultNotFoundException;
	Vlasnik update(Vlasnik vlasnik);
	void delete(String brojVozackeDozvole);
	List<Vlasnik> getAll() throws ResultNotFoundException;
	long count() throws ResultNotFoundException;
	
	List<Vlasnik> getAllVlasniciAktivnihVozila() throws ResultNotFoundException;
}
