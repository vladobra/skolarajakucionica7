package com.skolarajak.servisi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	private static final double PRAG_RASPODELE_AKTIVNIH_VOZILA = 0.4;
	private static final int SLOVO_A = 65;
	private static final int SLOVO_Z = 90;
	/**
	 * Vrati test vozila
	 * @return List<Vozilo> test vozila
	 */
    public List<Vozilo> generisi() {
    	List<Vozilo> vozila = new ArrayList<Vozilo>();
    	for(int i = 0; i < Konstante.UKUPAN_BROJ_VOZILA_U_SISTEMU; i++) {
    		int godinaProizvodnje = dodeliGodinuProizvodnje();
    		Vozilo vozilo = new Vozilo(godinaProizvodnje);
    		vozilo.setAktivno(Math.random() > PRAG_RASPODELE_AKTIVNIH_VOZILA);
    		vozilo.setRegistarskiBroj("Reg-"+i+slucajnoSlovo()+slucajnoSlovo());
    		vozila.add(vozilo);
    	}
		return vozila;
    }
    
    public List<Vozilo> euro3Vozila(List<Vozilo> vozila) {
    	/*
    	List<Vozilo> euro3Vozila = new ArrayList<Vozilo>();
    	for (Vozilo vozilo : vozila) {
    		if (vozilo.getGodisteProizvodnje() >= Konstante.EURO_3_GODISTE) {
    			euro3Vozila.add(vozilo);
    		}
    	}
    	*/
    	
    	List<Vozilo> euro3Vozila = vozila.stream()
    			                   .filter(v -> v.getGodisteProizvodnje() >= 2010)
    			                   .collect(Collectors.toList());
    	
    	return euro3Vozila; // vrati euro 3 vozila godiste >= 2000
    }
    
    public List<Vozilo> aktivnaVozila(List<Vozilo> vozila) {
    	return null;
    }
    
    private int dodeliGodinuProizvodnje() {
    	// godiste <-- {Godiste random 1960 - 2019}
    	int godina  = slucajanBrojUintervalu(Konstante.MIN_VOZILO_GODISTE, Konstante.MAX_VOZILO_GODISTE);
    	return godina;
    }
    
    private String slucajnoSlovo() {
    	char c = (char)  slucajanBrojUintervalu(SLOVO_A, SLOVO_Z);
    	return String.valueOf(c);
    }
    
    private int slucajanBrojUintervalu(int min, int max) {
    	return (int)(Math.random() * (max - min) + min);
    }
}
