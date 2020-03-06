package com.skolarajak.dao;

import java.util.Collection;
import java.util.List;

import com.skolarajak.exceptions.dao.ResultNotFoundException;
import com.skolarajak.model.Vozilo;

public interface VoziloDAO {
	Vozilo create(Vozilo vozilo);
	Vozilo read(String registarskiBroj) throws ResultNotFoundException;
	Vozilo update(Vozilo vozilo);
	void delete(String registarskiBroj);
	List<Vozilo> getAll() throws ResultNotFoundException;
	long count();
	
	List<Vozilo> getEuro3Vozila();
	List<Vozilo> getAktivnaVozila();
	
	List<Vozilo> getAllVozilaCijeImeVlasnikaSadrziSlovoA();
}
