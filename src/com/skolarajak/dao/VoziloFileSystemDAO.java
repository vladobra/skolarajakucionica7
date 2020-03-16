package com.skolarajak.dao;

import java.util.List;

import com.skolarajak.exceptions.dao.ResultNotFoundException;
import com.skolarajak.model.Vozilo;

public class VoziloFileSystemDAO implements VoziloDAO {

	@Override
	public Vozilo create(Vozilo vozilo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vozilo read(String registarskiBroj) throws ResultNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vozilo update(Vozilo vozilo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String registarskiBroj) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Vozilo> getAll() throws ResultNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Vozilo> getEuro3Vozila() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vozilo> getAktivnaVozila() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vozilo> getAllVozilaCijeImeVlasnikaSadrziSlovoA() {
		// TODO Auto-generated method stub
		return null;
	}

}
