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
import by.htp.bookmanager.users.User;

public class Service {

	private static Scanner scanner = ScannerSingleton.getScanner();

	public static AbstractUser authentification() {
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
			List<AbstractItem> listBook = bookDao.selectBookById(book_id);
			printListOfBooks(listBook);
		}
	}

	public static void seeAllBooks() {
		BookDao bookDao = new BookDaoImplementation();
		List<AbstractItem> listBook = bookDao.selectAllBooks();
		printListOfBooks(listBook);
	}

	public static void seeAllExpiredBooks(AbstractUser user) {
		int userNumber = user.getNumber();
		BookDao bookDao = new BookDaoImplementation();
		List<AbstractItem> listBook = bookDao.selectExpiredBooksByUserNumber(userNumber);
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

	public static void raitingBooksList() {
		UserBookDao userBookDao = new UserBookDaoImplementation();
		List<AbstractItem> ratingList = userBookDao.rateBooks();
		printListOfBooks(ratingList);
	}

	public static void userSatatInfo() {
		int month;
		int year;
		System.out.println("Enter MONTH --->");
		month = scanner.nextInt();
		System.out.println("Enter YEAR --->");
		year = scanner.nextInt();
		UserBookDao userBookDao = new UserBookDaoImplementation();
		List<AbstractItem> statList = userBookDao.userStat(month, year);
		printListOfBooks(statList);
	}

}
