package com.skolarajak;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.skolarajak.exceptions.dao.ResultNotFoundException;
import com.skolarajak.model.Vlasnik;
import com.skolarajak.model.Vozilo;
import com.skolarajak.servisi.AdministriranjeVozila;
import com.skolarajak.utils.PrikazUtils;

/**
 * Glavna aplikacija za administraciju vozila i vlasnika.
 * 
 * @author vladobra
 *
 */
public class AppConsole {
	static final AdministriranjeVozila administracijaVozila = new AdministriranjeVozila();
	
	public static void main(String[] args) throws ResultNotFoundException, IOException {
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
	        	case "3" : opcija3(); break;
	        	case "4" : opcija4(); break;
	        	case "5" : opcija5(); break;
	        	case "6" : opcija6(); break;
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
		PrikazUtils.izlistajVozila(vozila);
	}
	
	private static void opcija1() throws ResultNotFoundException {
		System.out.println("=====IZLISTAJ EURO3 VOZILA======");
		List<Vozilo> euro3Vozila = administracijaVozila.euro3Vozila();
		System.out.println(euro3Vozila.size());
		PrikazUtils.izlistajVozila(euro3Vozila);
	}
	
    private static void opcija2 () throws ResultNotFoundException {
    	System.out.println("=====IZLISTAJ AKTIVNA VOZILA======");
    	List<Vozilo> aktivnaVozila = administracijaVozila.aktivnaVozila();
		System.out.println(aktivnaVozila.size());
		PrikazUtils.izlistajVozila(aktivnaVozila);
	}
    
    private static void opcija3() throws ResultNotFoundException {
		List<Vlasnik> vlasnici = administracijaVozila.dajSveVlasnike();
		System.out.println("=====IZLISTAJ VLASNIKE======");
		System.out.println("Ukupno vlasnika: " + vlasnici.size());
		PrikazUtils.izlistajVlasnici(vlasnici);
	}
    
    private static void opcija4() throws ResultNotFoundException, IOException {
		List<Vlasnik> vlasnici = administracijaVozila.dajSveVlasnikeAktivnihVozila();
		System.out.println("=====IZLISTAJ VLASNIKE SVIH AKTIVNIH VOZILA======");
		System.out.println("Ukupno vlasnika aktivnih vozila: " + vlasnici.size());
		PrikazUtils.izlistajVlasnici(vlasnici);
		PrikazUtils.izlistajVlasnikeUDatoteku(vlasnici);
	}
    
    private static void opcija5() throws ResultNotFoundException, IOException {
		List<Vozilo> vozila = administracijaVozila.dajSvaVozilaCijeImeVlasnikaSadrziSlovoA();
		System.out.println("=====IZLISTAJ VOZILA  CIJE IME VLASNIKA SADRZI SLOVO A======");
		System.out.println("Ukupno vozila: " + vozila.size());
		PrikazUtils.izlistajVozila(vozila);
		PrikazUtils.izlistajVozilaUDatoteku(vozila);
		PrikazUtils.izlistajVozilaIzDatoteke();
	} 
    
    private static void opcija6() throws ResultNotFoundException, IOException {
		administracijaVozila.obrisiSve();
		System.out.println("=====SVE JE OBRISANO!!!!======");
	} 
	
	private static void prikaziOpcije( ) {
		System.out.println("-------------------------------------");
		System.out.println("0 -> Izlistaj vozila");
		System.out.println("1 -> Izlistaj euro3 vozila");
		System.out.println("2 -> Izlistaj aktivna vozila");
		System.out.println("3 -> Izlistaj vlasnike");
		System.out.println("4 -> Izlistaj vlasnike svih aktivnih vozila");
		System.out.println("5 -> Izlistaj vozila svih vlasnika cije ime sadrzi slovo A");
		System.out.println("6 -> Obrisi sve");
		System.out.println("kraj -> Izaz iz aplikacije");
		System.out.println("-------------------------------------");
	}
	
}
