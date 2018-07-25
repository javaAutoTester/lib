package by.htp.bookmanager.service;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import by.htp.bookmanager.dao.BookDao;
import by.htp.bookmanager.dao.impl.BookDaoImplementation;
import by.htp.bookmanager.entity.Book;
import by.htp.bookmanager.scanner.ScannerSingleton;
import by.htp.bookmanager.users.AbstractUser;

public class Service {
	private static final String SQL_SELECT_BOOK_BY_ID = "SELECT * FROM Books WHERE id = ?";
	private static final String SQL_SELECT_ALL_BOOK = "SELECT * FROM Books";
	private static final String SQL_EXPIRED_BOOKS = "SELECT * FROM Books JOIN Book_usage_info ON Books.id = Book_usage_info.id_books WHERE Book_usage_info.number_users = ?";

	private static Scanner scanner = ScannerSingleton.getScanner();

	public static void seeBookById() {
		BookDao bookDao = new BookDaoImplementation();
		int book_id = 0;
		while (true) {
			System.out.println("Enter:\nID - to see book info\n 0 - to get back to MAIN MENU\n");
			book_id = scanner.nextInt();
			if (book_id == 0) {
				break;
			}
			List<Book> listBook = bookDao.selectAllFromBooks(SQL_SELECT_BOOK_BY_ID, book_id);
			printListOfBooks(listBook);
		}
	}

	public static void seeAllBooks() {
		BookDao bookDao = new BookDaoImplementation();
		List<Book> listBook = bookDao.selectAllFromBooks(SQL_SELECT_ALL_BOOK, -1);
		printListOfBooks(listBook);
	}

	public static void seeAllExpiredBooks(AbstractUser user) {
		int userNumber = user.getNumber();
		BookDao bookDao = new BookDaoImplementation();
		List<Book> listBook = bookDao.selectAllFromBooks(SQL_EXPIRED_BOOKS, userNumber);
		if (listBook.size() == 0) {
			System.out.println("You have expired books!");
			printListOfBooks(listBook);
		}
	}

	private static void printListOfBooks(List<Book> listBook) {
		if (listBook.size() == 0) {
			System.out.println("THERE ARE NO BOOKS MATCHED THE QUERY");
		} else {
			for (Iterator<Book> iterator = listBook.iterator(); iterator.hasNext();) {
				Book book = (Book) iterator.next();
				System.out.println(book);
			}
		}

	}
}
