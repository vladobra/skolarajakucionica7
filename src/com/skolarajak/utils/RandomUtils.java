package com.skolarajak.utils;

public class RandomUtils {
	private static final int SLOVO_A = 65;
	private static final int SLOVO_Z = 90;
	
	public static String slucajnoSlovo() {
		char c = (char) slucajanBrojUintervalu(SLOVO_A, SLOVO_Z);
		return String.valueOf(c);
	}

	public static int slucajanBrojUintervalu(int min, int max) {
		return (int) (Math.random() * (max - min) + min);
	}
}
