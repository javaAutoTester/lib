package by.htp.bookmanager.manager;

import java.util.Scanner;

import by.htp.bookmanager.service.Service;
import by.htp.bookmanager.users.AbstractUser;
import by.htp.bookmanager.users.Librarian;
import by.htp.bookmanager.users.NotUser;
import by.htp.bookmanager.users.User;
import by.htp.bookmanager.scanner.ScannerSingleton;

public class LibraryManager {

	private static Scanner scanner = ScannerSingleton.getScanner();

	public static void run() {
		AbstractUser user = Service.authentification();
		interraction(user);
	}

	private static void interraction(AbstractUser user) {
		if (user instanceof NotUser) {
			System.out.println("You are not a library user. Ask your librarian for further information");
		} else if (user instanceof Librarian) {
			librarianInterraction((Librarian) user);
		} else if (user instanceof User) {
			userInterraction((User) user);
		}

	}

	private static void userInterraction(User user) {
		System.out.println("You are logged in as a User");
		Service.seeAllExpiredBooks(user);
		int choice = 0;
		while (true) {
			System.out
					.println("MAIN MENU\nEnter:\n 1-see book info by ID\n 2 -see list of books\n 0- exit the library");
			choice = scanner.nextInt();
			if (choice == 0) {
				System.out.println("I HOPE TO SEE YOU SOON!");
				scanner.close();
				break;
			} else if (choice == 1) {
				Service.seeBookById();
			} else if (choice == 2) {
				Service.seeAllBooks();
			}

		}
	}

	private static void librarianInterraction(Librarian user) {
		System.out.println("You are logged in as the Librarian");
		int choice = 0;
		while (true) {
			System.out.println(
					"MAIN MENU\nEnter:\n 1 - add new book\n 2 - add new reader\n 3 - give the book out \n 4 - get the book back\n 5 - list of expired books\n 6 - books raiting\n 7 - users statistics\n 0 - exit the library");
			choice = scanner.nextInt();
			if (choice == 0) {
				System.out.println("I HOPE TO SEE YOU SOON!");
				scanner.close();
				break;
			} else if (choice == 1) {
				Service.addNewBookToLibrary();
			} else if (choice == 2) {
				System.out.println("User added " + Service.addNewUser());
			} else if (choice == 3) {
				Service.giveBookOut();
			} else if (choice == 4) {
				Service.getBookBack();
			} else if (choice == 5) {
				Service.expiredBooksControl();
			} else if (choice == 6) {
				Service.raitingBooksList();
			}else if (choice == 7) {
				Service.userSatatInfo();
			}

		}
	}

}
