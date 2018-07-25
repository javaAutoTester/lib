package by.htp.bookmanager.scanner;

import java.util.Scanner;

public class ScannerSingleton {

	private static Scanner scanner;

	static {
		scanner = new Scanner(System.in);
	}

	private ScannerSingleton() {

	}

	public static Scanner getScanner() {
		return scanner;
	}

}
