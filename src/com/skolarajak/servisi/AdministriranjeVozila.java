package com.skolarajak.servisi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
				vozila.add(vozilo);
				zadnjeVozilo = voziloDAO.create(vozilo);
			}

			System.out.println("UKUPNO reg brojeva: " + voziloDAO.count());
			System.out.println("Godina proizvodnje poslednjeg registrovanog: " + zadnjeVozilo.getRegistarskiBroj()
					+ " : " + voziloDAO.read(zadnjeVozilo.getRegistarskiBroj()).isAktivno());

			zadnjeVozilo.setAktivno(!zadnjeVozilo.isAktivno());
			zadnjeVozilo = voziloDAO.update(zadnjeVozilo);
			System.out.println("Godina proizvodnje poslednjeg registrovanog: " + zadnjeVozilo.getRegistarskiBroj()
					+ " : " + voziloDAO.read(zadnjeVozilo.getRegistarskiBroj()).isAktivno());
			voziloDAO.delete(zadnjeVozilo.getRegistarskiBroj());

			zadnjeVozilo = voziloDAO.read(zadnjeVozilo.getRegistarskiBroj());
		} catch (ResultNotFoundException e) {
			System.out.println(e.getMessage()); 
			System.out.println("OBRISANO"); 
		}
		/*
		 * if (zadnjeVozilo!=null) {
		 * System.out.println("Godina proizvodnje poslednjeg registrovanog: " +
		 * zadnjeVozilo.getRegistarskiBroj() + " : " + zadnjeVozilo.isAktivno()); } else
		 * { System.out.println("OBRISANO"); }
		 */

		return vozila;
	}

	public List<Vozilo> euro3Vozila(List<Vozilo> vozila) {
		List<Vozilo> euro3Vozila = vozila.stream().filter(v -> v.getGodisteProizvodnje() >= 2010)
				.collect(Collectors.toList());

		return euro3Vozila; // vrati euro 3 vozila godiste >= 2000
	}

	public List<Vozilo> aktivnaVozila(List<Vozilo> vozila) {
		List<Vozilo> aktivnaVozila = vozila.stream().filter(v -> v.isAktivno()).collect(Collectors.toList());

		return aktivnaVozila;
	}

	private int dodeliGodinuProizvodnje() {
		// godiste <-- {Godiste random 1960 - 2019}
		int godina = RandomUtils.slucajanBrojUintervalu(Konstante.MIN_VOZILO_GODISTE, Konstante.MAX_VOZILO_GODISTE);
		return godina;
	}

}
