package com.skolarajak.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import com.skolarajak.model.Vlasnik;
import com.skolarajak.model.Vozilo;

public class PrikazUtils {
	private static String FILE_ROOT = "c:/tmp/izvestaj.txt";
	private static String SEPARATOR = ";";
	
	public static void izlistajVozila(List<Vozilo> vozila) {
		vozila.forEach(PrikazUtils::printVozilo);
	}
	
	public static void izlistajVlasnici(List<Vlasnik> vlasnici) {
		vlasnici.forEach(PrikazUtils::printVlasnik);
	}
	
	public static void izlistajVlasnikeUDatoteku(List<Vlasnik> vlasnici) throws IOException {
		//
		//Get the file reference
		Path path = Paths.get(FILE_ROOT);
		 
		//Use try-with-resource to get auto-closeable writer instance
		try (BufferedWriter writer = Files.newBufferedWriter(path)) 
		{
			for(Vlasnik v : vlasnici) {
				 writer.write(v.toString());
				 writer.newLine();
			}
		}
	}
	
	public static void izlistajVozilaUDatoteku(List<Vozilo> vozila) throws IOException {
		//
		//Get the file reference
		Path path = Paths.get(FILE_ROOT);
		 
		//Use try-with-resource to get auto-closeable writer instance
		try (BufferedWriter writer = Files.newBufferedWriter(path /* ,StandardOpenOption.APPEND */)) 
		{
			for(Vozilo v : vozila) {
				 writer.write(v.toString());
				 writer.newLine();
			}
		}
	}
	
	public static void izlistajVozilaIzDatoteke() throws IOException {
		//Get the file reference
				Path path = Paths.get(FILE_ROOT);
				String thisLine = null;
				//Use try-with-resource to get auto-closeable writer instance
				try (BufferedReader reader = Files.newBufferedReader(path)) 
				{
					 while ((thisLine = reader.readLine()) != null) {
				            System.out.println("*******" + thisLine);
				         }  
				}
	}
	
	public static void printVozilo(Vozilo vozilo) {
		System.out.println(vozilo.toString());
	}
	
	public static void printVlasnik(Vlasnik vlasnik) {
		System.out.println(vlasnik.toString());
	}
}
