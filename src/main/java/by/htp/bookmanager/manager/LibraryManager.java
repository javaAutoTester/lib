package by.htp.bookmanager.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import by.htp.bookmanager.connection.DBConnection;
import by.htp.bookmanager.service.Service;
import by.htp.bookmanager.users.AbstractUser;
import by.htp.bookmanager.users.Librarian;
import by.htp.bookmanager.users.NotUser;
import by.htp.bookmanager.users.User;
import by.htp.bookmanager.scanner.ScannerSingleton;

public class LibraryManager {

	private static Scanner scanner = ScannerSingleton.getScanner();

	public static void run() {
		AbstractUser user = authentification();
		interraction(user);
	}

	private static AbstractUser authentification() {
		AbstractUser user = new NotUser();
		int login;
		String pass;
		System.out.println("Enter your number --->");
		login = scanner.nextInt();
		System.out.println("Enter your password --->");
		pass = scanner.next();
		user = checkUserCredentials(login, pass);
		return user;
	}

	private static AbstractUser checkUserCredentials(int login, String pass) {
		final String SQL_SELECT_USER = "SELECT * FROM Users WHERE number = ? AND pass = ?";
		DBConnection dbConn = new DBConnection();
		Connection conn = dbConn.connect();
		AbstractUser user = new NotUser();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL_SELECT_USER);
			ps.setInt(1, login);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				if (rs.getString("role").equals("U")) {
					user = new User();
					user.setNumber(rs.getInt("number"));
					user.setPass("****");
					user.setRole(rs.getString("role"));
				} else if (rs.getString("role").equals("L")) {
					user = new Librarian();
					user.setNumber(rs.getInt("number"));
					user.setPass("****");
					user.setRole(rs.getString("role"));
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbConn.closeConnection(conn);
		}
		return user;
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
			System.out
					.println("MAIN MENU\nEnter:\n 1-add new book\n 2 -add new reader\n 3 - give the book out \n 4 - get the book back\n  0- exit the library");
			choice = scanner.nextInt();
			if (choice == 0) {
				System.out.println("I HOPE TO SEE YOU SOON!");
				scanner.close();
				break;
			} else if (choice == 1) {
				Service.addNewBookToLibrary();
			} else if (choice == 2) {
				System.out.println("User added "+Service.addNewUser());
			}

		}
	}

}
