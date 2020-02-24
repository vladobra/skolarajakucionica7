package com.skolarajak.dao;

import java.util.HashMap;

import com.skolarajak.model.Vozilo;
import com.skolarajak.utils.RandomUtils;

public class VoziloInMemoryDAOImpl implements VoziloInMemoryDAO {
	private static final HashMap<String,Vozilo> registrovanaVozila = new HashMap<String, Vozilo>();
	@Override
	public Vozilo create(Vozilo vozilo) {
		String registarskiBroj = kreirajRegistarskiBroj();
		vozilo.setRegistarskiBroj(registarskiBroj);
		registrovanaVozila.put(vozilo.getRegistarskiBroj(), vozilo);
		return vozilo;
	}

	@Override
	public Vozilo read(String registarskiBroj) {
		// TODO Auto-generated method stub
		return registrovanaVozila.get(registarskiBroj);
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

	private String kreirajRegistarskiBroj() {    	
    	String registarskiBroj = "";
    	
    	while(1==1) {
            registarskiBroj = "Reg-"+RandomUtils.slucajnoSlovo()+RandomUtils.slucajnoSlovo();
    		if (!VoziloInMemoryDAOImpl.registrovanaVozila.containsKey(registarskiBroj)) {
    			VoziloInMemoryDAOImpl.registrovanaVozila.put(registarskiBroj, null);
    			break;
    		} else {
    			System.out.println("*********** DUPLICAT **************" + registarskiBroj);
    		}
    	}
    	return registarskiBroj;
    }

	@Override
	public long count() {
		return VoziloInMemoryDAOImpl.registrovanaVozila.keySet().size();
	}
}
