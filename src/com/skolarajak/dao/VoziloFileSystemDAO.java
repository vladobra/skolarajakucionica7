package com.skolarajak.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.skolarajak.exceptions.dao.ResultNotFoundException;
import com.skolarajak.model.Vlasnik;
import com.skolarajak.model.Vozilo;

public class VoziloFileSystemDAO implements VoziloDAO {
	private static String EXTENZIJA = ".xml";
	private static String FILE_ROOT = "c:/tempv/";

	@Override
	public Vozilo create(Vozilo vozilo) {
		vozilo.setRegistarskiBroj(String.valueOf(System.currentTimeMillis()));

		XMLEncoder encoder = null;
		try {
			encoder = new XMLEncoder(new BufferedOutputStream(
					new FileOutputStream(FILE_ROOT + vozilo.getRegistarskiBroj() + EXTENZIJA)));
		} catch (FileNotFoundException fileNotFound) {
			System.out.println("ERROR while creating or opening .xml file!");
		}
		encoder.writeObject(vozilo);
		encoder.close();
		return vozilo;
	}

	@Override
	public Vozilo read(String registarskiBroj) throws ResultNotFoundException {
		XMLDecoder decoder = null;
		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(getFileName(registarskiBroj))));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File vozilo  not found");
		}
		Vozilo vozilo = (Vozilo) decoder.readObject();

		return vozilo;
	}

	@Override
	public Vozilo update(Vozilo vozilo) {
		XMLEncoder encoder = null;
		try {
			encoder = new XMLEncoder(new BufferedOutputStream(
					new FileOutputStream(getFileName(vozilo.getRegistarskiBroj()))));
		} catch (FileNotFoundException fileNotFound) {
			System.out.println("ERROR while creating or opening .xml file!");
		}
		encoder.writeObject(vozilo);
		encoder.close();
		return vozilo;
	}

	@Override
	public void delete(String registarskiBroj) {
		File file = new File(getFileName(registarskiBroj));
        file.delete();

	}

	@Override
	public List<Vozilo> getAll() throws ResultNotFoundException {
		List<Vozilo> vozila = new ArrayList<Vozilo>();

		File[] files = new File(FILE_ROOT).listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null. 

		for (File file : files) {
		    if (file.isFile()) {
		    	String fileName = file.getName(); // 112121212121212121.xml
		    	String brojRegistracije = fileName.substring(0, fileName.lastIndexOf("."));
		        vozila.add(this.read(brojRegistracije));
		    }
		}
		
		return vozila;
		
	}

	@Override
	public long count() throws ResultNotFoundException {
		// TODO Auto-generated method stub
		return this.getAll().size();
	}

	@Override
	public List<Vozilo> getEuro3Vozila() throws ResultNotFoundException {
		return  this.getAll()
				.stream().filter(v -> v.getGodisteProizvodnje() >= 2010)
				.collect(Collectors.toList());
	}

	@Override
	public List<Vozilo> getAktivnaVozila() throws ResultNotFoundException {
		// TODO Auto-generated method stub
		return this.getAll()
				.stream().filter(v -> v.isAktivno()).collect(Collectors.toList());
	}

	@Override
	public List<Vozilo> getAllVozilaCijeImeVlasnikaSadrziSlovoA() throws ResultNotFoundException {
		return this.getAll()
				.stream().filter(v -> v.getVlasnik().getIme().contains("A"))
				.collect(Collectors.toList());
	}

	private String getFileName(String registarskiBroj) {
		return FILE_ROOT + registarskiBroj + EXTENZIJA;
	}

}
