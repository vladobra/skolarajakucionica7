package com.skolarajak;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.skolarajak.exceptions.dao.ResultNotFoundException;
import com.skolarajak.model.Vozilo;
import com.skolarajak.servisi.AdministriranjeVozila;

/**
 * Glavna aplikacija za administraciju vozila i vlasnika.
 * 
 * @author vladobra
 *
 */
public class AppConsole {
	static final AdministriranjeVozila administracijaVozila = new AdministriranjeVozila();
	
	public static void main(String[] args) throws ResultNotFoundException {
		Date datum = new Date();
		System.out.println("Pocetak rada aplikacije: " + datum.toString());
		
		// generisi vozila
		
		administracijaVozila.generisi();
		
		System.out.println("-----------------Glavna programska petlja--------------------");
		
		Scanner in = new Scanner(System.in);
		while (1==1) {
			prikaziOpcije();
			
	        String s = in.nextLine();
	        System.out.println("You entered string "+s);
	        System.out.println(">:");
	        switch (s) {
	            case "0" : opcija0(); break;
	        	case "1" : opcija1(); break;
	        	case "2" : opcija2(); break;
	        }
	        if ("kraj".equals(s)) {
	        	System.out.println("KRAJ RADA, HVALA!!");
	        	break;
	        }
		}
	}
	
	private static void opcija0() throws ResultNotFoundException {
		List<Vozilo> vozila = administracijaVozila.dajSvaVozila();
		System.out.println("=====IZLISTAJ VOZILA======");
		System.out.println("Ukupno vozila: " + vozila.size());
		izlistajVozila(vozila);
	}
	
	private static void opcija1() {
		System.out.println("=====IZLISTAJ EURO3 VOZILA======");
		List<Vozilo> euro3Vozila = administracijaVozila.euro3Vozila();
		System.out.println(euro3Vozila.size());
		izlistajVozila(euro3Vozila);
	}
	
    private static void opcija2 () {
    	System.out.println("=====IZLISTAJ AKTIVNA VOZILA======");
    	List<Vozilo> aktivnaVozila = administracijaVozila.aktivnaVozila();
		System.out.println(aktivnaVozila.size());
		izlistajVozila(aktivnaVozila);
	}
	
	private static void prikaziOpcije( ) {
		System.out.println("-------------------------------------");
		System.out.println("0 -> Izlistaj vozila");
		System.out.println("1 -> Izlistaj euro3 vozila");
		System.out.println("2 -> Izlistaj aktivna vozila");
		System.out.println("kraj -> Izaz iz aplikacije");
		System.out.println("-------------------------------------");
	}
	
	private static void izlistajVozila(List<Vozilo> vozila) {
		vozila.forEach(AppConsole::printVozilo);
	}
	
	private static void printVozilo(Vozilo vozilo) {
		System.out.println("Godiste: " + vozilo.getGodisteProizvodnje() + " Aktivno: " + vozilo.isAktivno()
		+ " Registarski broj: " + vozilo.getRegistarskiBroj());
	}
}
