package com.skolarajak.dao;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.skolarajak.exceptions.dao.ResultNotFoundException;
import com.skolarajak.model.Vlasnik;
import com.skolarajak.utils.RandomUtils;

public class VlasnikFileSystemDAO implements VlasnikDAO {
	private static String EXTENZIJA = ".xml";
	private static String FILE_ROOT = "c:/tmp/";

	@Override
	public Vlasnik create(Vlasnik vlasnik) {
		// TODO Auto-generated method stub
		vlasnik.setBrojVozackeDozvole(String.valueOf(System.currentTimeMillis()));
		vlasnik.setIme(RandomUtils.slucajnoSlovo() + RandomUtils.slucajnoSlovo() + RandomUtils.slucajnoSlovo());
		vlasnik.setPrezime(RandomUtils.slucajnoSlovo() + RandomUtils.slucajnoSlovo() + RandomUtils.slucajnoSlovo());
		
		XMLEncoder encoder = null;
		try {
			encoder = new XMLEncoder(new BufferedOutputStream(
					new FileOutputStream(FILE_ROOT + vlasnik.getBrojVozackeDozvole() + EXTENZIJA)));
		} catch (FileNotFoundException fileNotFound) {
			System.out.println("ERROR: While Creating or Opening the File dvd.xml");
		}
		encoder.writeObject(vlasnik);
		encoder.close();
		return vlasnik;
	}

	@Override
	public Vlasnik read(String brojVozackeDozvole) throws ResultNotFoundException {
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(getFileName(brojVozackeDozvole))));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File vlasnik not found");
		}
		Vlasnik vlasnik = (Vlasnik)decoder.readObject();
		
		return vlasnik;
	}

	@Override
	public Vlasnik update(Vlasnik vlasnik) {
		XMLEncoder encoder = null;
		try {
			encoder = new XMLEncoder(new BufferedOutputStream(
					new FileOutputStream(getFileName(vlasnik.getBrojVozackeDozvole()))));
		} catch (FileNotFoundException fileNotFound) {
			System.out.println("ERROR: While Creating or Opening the File dvd.xml");
		}
		encoder.writeObject(vlasnik);
		encoder.close();
		return vlasnik;
	}

	@Override
	public void delete(String brojVozackeDozvole) {
		File file = new File(getFileName(brojVozackeDozvole));
        file.delete();
	}

	@Override
	public List<Vlasnik> getAll() throws ResultNotFoundException {
		List<Vlasnik> vlasnici = new ArrayList<Vlasnik>();

		File[] files = new File(FILE_ROOT).listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null. 

		for (File file : files) {
		    if (file.isFile()) {
		    	String fileName = file.getName(); // 112121212121212121.xml
		    	String brojVozackeDozvole = fileName.substring(0, fileName.lastIndexOf("."));
		        vlasnici.add(this.read(brojVozackeDozvole));
		    }
		}
		
		return vlasnici;
	}

	@Override
	public long count() throws ResultNotFoundException {
		// TODO Auto-generated method stub
		return this.getAll().size();
	}

	@Override
	public List<Vlasnik> getAllVlasniciAktivnihVozila() throws ResultNotFoundException {
		return getAll()
				.stream().filter(v -> v.getVozilo().isAktivno()).collect(Collectors.toList());
	}
	
	private String getFileName(String brojVozackeDozvole) {
		return FILE_ROOT + brojVozackeDozvole + EXTENZIJA;
	}

}
