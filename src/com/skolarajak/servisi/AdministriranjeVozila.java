package com.skolarajak.servisi;

import java.util.ArrayList;
import java.util.List;
import com.skolarajak.dao.VoziloInMemoryDAOImpl;
import com.skolarajak.exceptions.dao.ResultNotFoundException;
import com.skolarajak.model.Vozilo;
import com.skolarajak.utils.Konstante;
import com.skolarajak.utils.RandomUtils;

/**
 * Servis za administrativne operacije sa vozilima
 * 
 * @author vladobra
 *
 */
public class AdministriranjeVozila {
	private static final boolean STATUS = true;
	private static final double PRAG_RASPODELE_AKTIVNIH_VOZILA = 0.4;

	private VoziloInMemoryDAOImpl voziloDAO;

	public AdministriranjeVozila() {
		voziloDAO = new VoziloInMemoryDAOImpl();
	}

	/**
	 * Vrati test vozila
	 * 
	 * @return List<Vozilo> test vozila
	 */
	public List<Vozilo> generisi() {
		List<Vozilo> vozila = new ArrayList<Vozilo>();
		try {
			Vozilo zadnjeVozilo = null;
			for (int i = 0; i < Konstante.UKUPAN_BROJ_VOZILA_U_SISTEMU; i++) {
				int godinaProizvodnje = dodeliGodinuProizvodnje();
				Vozilo vozilo = new Vozilo(godinaProizvodnje);
				vozilo.setAktivno(Math.random() > PRAG_RASPODELE_AKTIVNIH_VOZILA);
				zadnjeVozilo = voziloDAO.create(vozilo);
			}

			System.out.println("UKUPNO reg brojeva: " + voziloDAO.count());
			
			vozila = voziloDAO.getAll();
			
		} catch (ResultNotFoundException e) {
			System.out.println(e.getMessage()); 
			System.out.println("OBRISANO"); 
		}
		
		return vozila;
	}

	public List<Vozilo> euro3Vozila() {
		List<Vozilo> euro3Vozila = voziloDAO.getEuro3Vozila();

		return euro3Vozila; // vrati euro 3 vozila godiste >= 2000
	}

	public List<Vozilo> aktivnaVozila() {
		List<Vozilo> aktivnaVozila = voziloDAO.getAktivnaVozila();

		return aktivnaVozila;
	}
	
	public List<Vozilo> dajSvaVozila() throws ResultNotFoundException {
		return voziloDAO.getAll();
	}

	private int dodeliGodinuProizvodnje() {
		// godiste <-- {Godiste random 1960 - 2019}
		int godina = RandomUtils.slucajanBrojUintervalu(Konstante.MIN_VOZILO_GODISTE, Konstante.MAX_VOZILO_GODISTE);
		return godina;
	}

}
