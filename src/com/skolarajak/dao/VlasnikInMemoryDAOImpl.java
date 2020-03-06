package com.skolarajak.dao;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.skolarajak.exceptions.dao.ResultNotFoundException;
import com.skolarajak.model.Vlasnik;
import com.skolarajak.model.Vozilo;
import com.skolarajak.utils.RandomUtils;

public class VlasnikInMemoryDAOImpl implements VlasnikDAO {

	private static final HashMap<String, Vlasnik> vlasnici = new HashMap<String, Vlasnik>();

	public Vlasnik create(Vlasnik vlasnik) {
		String brojVozackeDozvole = kreirajBrojVozackeDozvole();
		vlasnik.setBrojVozackeDozvole(brojVozackeDozvole);
		// ime i prezime ABC DEF
		vlasnik.setIme(RandomUtils.slucajnoSlovo() + RandomUtils.slucajnoSlovo() + RandomUtils.slucajnoSlovo());
		vlasnik.setPrezime(RandomUtils.slucajnoSlovo() + RandomUtils.slucajnoSlovo() + RandomUtils.slucajnoSlovo());
		vlasnici.put(vlasnik.getBrojVozackeDozvole(), vlasnik);
		return vlasnik;
	}

	public Vlasnik read(String brojVozackeDozvole) throws ResultNotFoundException {
		Vlasnik vlasnik = vlasnici.get(brojVozackeDozvole);
		if (vlasnik == null) {
			throw new ResultNotFoundException("Vlasnik nije pronadjen");
		}
		return vlasnik;
	}

	@Override
	public Vlasnik update(Vlasnik vlasnik) {
		vlasnici.put(vlasnik.getBrojVozackeDozvole(), vlasnik);
		return vlasnik;
	}

	@Override
	public void delete(String brojVozackeDozvole) {
		vlasnici.remove(brojVozackeDozvole);

	}

	@Override
	public List<Vlasnik> getAll() throws ResultNotFoundException {
	
		return VlasnikInMemoryDAOImpl.vlasnici.values().stream().collect(Collectors.toList());
	}

	@Override
	public long count() {
		return VlasnikInMemoryDAOImpl.vlasnici.keySet().size();
	}

	private String kreirajBrojVozackeDozvole() {
		String brojVozackeDozvole = "";

		while (1 == 1) {
			brojVozackeDozvole = RandomUtils.slucajnoSlovo() + RandomUtils.slucajnoSlovo();
			if (!VlasnikInMemoryDAOImpl.vlasnici.containsKey(brojVozackeDozvole)) {
				VlasnikInMemoryDAOImpl.vlasnici.put(brojVozackeDozvole, null);
				break;
			} else {
				System.out.println("*********** DUPLICAT **************" + brojVozackeDozvole);
			}
		}
		return brojVozackeDozvole;
	}

	@Override
	public List<Vlasnik> getAllVlasniciAktivnihVozila() throws ResultNotFoundException {
		return getAll()
		.stream().filter(v -> v.getVozilo().isAktivno()).collect(Collectors.toList());
	}

}
