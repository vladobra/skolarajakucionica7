package com.skolarajak;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
	
	public static void main(String[] args) {
		Date datum = new Date();
		System.out.println("Pocetak rada aplikacije: " + datum.toString());
		
		// generisi vozila
		
		List<Vozilo> vozila = administracijaVozila.generisi();
		
		System.out.println("-----------------Glavna programska petlja--------------------");
		
		Scanner in = new Scanner(System.in);
		while (1==1) {
			prikaziOpcije();
			System.out.println(">:");
	        String s = in.nextLine();
	        System.out.println("You entered string "+s);
	        switch (s) {
	            case "0" : opcija0(vozila); break;
	        	case "1" : opcija1(vozila); break;
	        	case "2" : opcija2(vozila); break;
	        }
	        if ("kraj".equals(s)) {
	        	System.out.println("KRAJ RADA, HVALA!!");
	        	break;
	        }
		}
		
	}
	
	private static void opcija0(List<Vozilo> vozila) {
		System.out.println("=====IZLISTAJ VOZILA======");
		System.out.println("Ukupno vozila: " + vozila.size());
		izlistajVozila(vozila);
	}
	
	private static void opcija1(List<Vozilo> vozila) {
		System.out.println("=====IZLISTAJ EURO3 VOZILA======");
		List<Vozilo> euro3Vozila = administracijaVozila.euro3Vozila(vozila);
		System.out.println(euro3Vozila.size());
		izlistajVozila(euro3Vozila);
	}
	
    private static void opcija2 (List<Vozilo> vozila) {
    	System.out.println("=====IZLISTAJ AKTIVNA VOZILA======");
    	List<Vozilo> aktivnaVozila = administracijaVozila.aktivnaVozila(vozila);
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
		/*for (Vozilo vozilo : vozila) {
			printVozilo(vozilo);
		}*/
		vozila.forEach(AppConsole::printVozilo);
	}
	
	private static void printVozilo(Vozilo vozilo) {
		System.out.println("Godiste: " + vozilo.getGodisteProizvodnje() + " Aktivno: " + vozilo.isAktivno()
		+ " Registarski broj: " + vozilo.getRegistarskiBroj());
	}
}
