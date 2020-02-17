package com.skolarajak;

import java.util.Date;
import java.util.List;
import com.skolarajak.model.Vozilo;
import com.skolarajak.servisi.AdministriranjeVozila;

/**
 * Glavna aplikacija za administraciju vozila i vlasnika.
 * 
 * @author vladobra
 *
 */
public class App {
	public static void main(String[] args) {
		Date datum = new Date();
		System.out.println("Pocetak rada aplikacije: " + datum.toString());
		
		// generisi vozila
		AdministriranjeVozila administracijaVozila = new AdministriranjeVozila();
		List<Vozilo> vozila = administracijaVozila.generisi();
		
		System.out.println(vozila.size());
		izlistajVozila(vozila);
		
		System.out.println("-------------------------------------");
		// izdvoji euro3 vozila
		
		List<Vozilo> euro3Vozila = administracijaVozila.euro3Vozila(vozila);
		System.out.println(euro3Vozila.size());
		izlistajVozila(euro3Vozila);
		
		System.out.println("-------------------------------------");
		// izdvoji aktivna vozila
		
		List<Vozilo> aktivnaVozila = administracijaVozila.aktivnaVozila(vozila);
		System.out.println(aktivnaVozila.size());
		izlistajVozila(aktivnaVozila);
	}
	
	private static void izlistajVozila(List<Vozilo> vozila) {
		/*for (Vozilo vozilo : vozila) {
			printVozilo(vozilo);
		}*/
		vozila.forEach(App::printVozilo);
	}
	
	private static void printVozilo(Vozilo vozilo) {
		System.out.println("Godiste: " + vozilo.getGodisteProizvodnje() + " Aktivno: " + vozilo.isAktivno()
		+ " Registarski broj: " + vozilo.getRegistarskiBroj());
	}
}
