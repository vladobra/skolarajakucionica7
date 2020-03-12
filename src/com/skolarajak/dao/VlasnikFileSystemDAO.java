package com.skolarajak.dao;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Vlasnik> getAllVlasniciAktivnihVozila() throws ResultNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String getFileName(String brojVozackeDozvole) {
		return FILE_ROOT + brojVozackeDozvole + EXTENZIJA;
	}

}
