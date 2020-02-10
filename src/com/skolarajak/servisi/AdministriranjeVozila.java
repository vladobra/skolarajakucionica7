package com.skolarajak.servisi;

import java.util.ArrayList;
import java.util.List;
import com.skolarajak.model.Vozilo;
import com.skolarajak.utils.Konstante;
/**
 * Servis za administrativne operacije sa vozilima
 * 
 * @author vladobra
 *
 */
public class AdministriranjeVozila {
	private static final boolean STATUS = true;
	
	/**
	 * Vrati test vozila
	 * @return List<Vozilo> test vozila
	 */
    public List<Vozilo> generisi() {
    	List<Vozilo> vozila = new ArrayList<Vozilo>();
    	for(int i = 0; i < Konstante.UKUPAN_BROJ_VOZILA_U_SISTEMU; i++) {
    		// godiste <-- {Godiste random 1960 - 2019}
    		Vozilo vozilo = new Vozilo(i);
    		vozilo.setAktivno(STATUS);
    		vozilo.setRegistarskiBroj("Reg-"+i);
    		vozila.add(vozilo);
    	}
		return vozila;
    }
    
    public List<Vozilo> euro3Vozila(List<Vozilo> vozila) {
    	
    	 return null;// vrari euro 3 vozila
    }
}
