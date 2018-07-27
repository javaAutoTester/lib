package by.htp.bookmanager.service;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import by.htp.bookmanager.dao.BookDao;
import by.htp.bookmanager.dao.UserBookDao;
import by.htp.bookmanager.dao.UserDao;
import by.htp.bookmanager.dao.impl.BookDaoImplementation;
import by.htp.bookmanager.dao.impl.UserBookDaoImplementation;
import by.htp.bookmanager.dao.impl.UserDaoImplementation;
import by.htp.bookmanager.entity.AbstractItem;
import by.htp.bookmanager.entity.Book;
import by.htp.bookmanager.scanner.ScannerSingleton;
import by.htp.bookmanager.users.AbstractUser;
import by.htp.bookmanager.users.NotUser;
import by.htp.bookmanager.users.User;

public class Service {
	private static final String SQL_SELECT_BOOK_BY_ID = "SELECT * FROM Books WHERE id = ?";
	private static final String SQL_SELECT_ALL_BOOK = "SELECT * FROM Books";
	private static final String SQL_EXPIRED_BOOKS = "SELECT * FROM Books JOIN Book_usage_info ON Books.id = Book_usage_info.id_books WHERE Book_usage_info.number_users = ? AND (to_days(CURRENT_DATE())-to_days(Book_usage_info.takeout_date))>'30'";

	private static Scanner scanner = ScannerSingleton.getScanner();

	public static AbstractUser authentification() {
		AbstractUser user = new NotUser();
		int login;
		String pass;
		System.out.println("Enter your number --->");
		login = scanner.nextInt();
		System.out.println("Enter your password --->");
		pass = scanner.next();
		UserDao userDao = new UserDaoImplementation();

		return userDao.checkUserCredentials(login, pass);
	}

	public static void seeBookById() {
		BookDao bookDao = new BookDaoImplementation();
		int book_id = 0;
		while (true) {
			System.out.println("Enter:\nID - to see book info\n 0 - to get back to MAIN MENU\n");
			book_id = scanner.nextInt();
			if (book_id == 0) {
				break;
			}
			List<AbstractItem> listBook = bookDao.selectAllFromBooks(SQL_SELECT_BOOK_BY_ID, book_id);
			printListOfBooks(listBook);
		}
	}

	public static void seeAllBooks() {
		BookDao bookDao = new BookDaoImplementation();
		List<AbstractItem> listBook = bookDao.selectAllFromBooks(SQL_SELECT_ALL_BOOK, -1);
		printListOfBooks(listBook);
	}

	public static void seeAllExpiredBooks(AbstractUser user) {
		int userNumber = user.getNumber();
		BookDao bookDao = new BookDaoImplementation();
		List<AbstractItem> listBook = bookDao.selectAllFromBooks(SQL_EXPIRED_BOOKS, userNumber);
		if (listBook.size() != 0) {
			System.out.println("You have expired books!");
			printListOfBooks(listBook);
		}
	}

	private static void printListOfBooks(List<AbstractItem> listBook) {
		if (listBook.size() == 0) {
			System.out.println("THERE ARE NO BOOKS MATCHED THE QUERY");
		} else {
			for (Iterator<AbstractItem> iterator = listBook.iterator(); iterator.hasNext();) {
				AbstractItem item = (AbstractItem) iterator.next();
				System.out.println(item);
			}
		}

	}

	public static void addNewBookToLibrary() {
		System.out.println("Enter   BOOK_title|AUTHOR_name|AUTHOR_surname   separated by '|' ");
		StringBuilder sb = new StringBuilder();
		int a = 0;
		while (a < 2) {
			sb.append(scanner.nextLine());
			a++;
		}
		String str = sb.toString();
		String[] strArr = str.split("\\|");
		Book book = new Book();
		book.setTitle(strArr[0].trim());
		book.setAuthorName(strArr[1].trim());
		book.setAuthorSurname(strArr[2].trim());
		BookDao bookDao = new BookDaoImplementation();
		bookDao.addBook(book);
	}

	public static int addNewUser() {
		System.out.println("Enter   USER_pass|USER_role|EMPLOYE_id   separated by '|' ");
		StringBuilder sb = new StringBuilder();
		int a = 0;
		while (a < 2) {
			sb.append(scanner.nextLine());
			a++;
		}
		String str = sb.toString();
		String[] strArr = str.split("\\|");
		User user = new User();
		user.setPass(strArr[0].trim());
		user.setRole(strArr[1].trim());
		user.setEmplID(new Integer(strArr[2].trim()));
		UserDao userDao = new UserDaoImplementation();

		return userDao.addUser(user);
	}

	public static void giveBookOut() {
		int book_number;
		int user_number;
		System.out.println("Enter USER number --->");
		user_number = scanner.nextInt();
		System.out.println("Enter BOOK number --->");
		book_number = scanner.nextInt();
		UserBookDao userBookDao = new UserBookDaoImplementation();
		userBookDao.giveBook(user_number, book_number);
	};

	public static void getBookBack() {
		int book_number;
		int user_number;
		System.out.println("Enter USER number --->");
		user_number = scanner.nextInt();
		System.out.println("Enter BOOK number --->");
		book_number = scanner.nextInt();
		UserBookDao userBookDao = new UserBookDaoImplementation();
		userBookDao.getBook(user_number, book_number);
	};

	public static void expiredBooksControl() {
		UserBookDao userBookDao = new UserBookDaoImplementation();
		List<AbstractItem> expBooks = userBookDao.expiredBooks();
		printListOfBooks(expBooks);
	}

}
