package by.htp.bookmanager.dao;

import java.util.List;

import by.htp.bookmanager.entity.AbstractItem;
import by.htp.bookmanager.entity.Book;

public interface BookDao {

	void addBook(Book book);

	List<AbstractItem> selectBookById(int id);

	List<AbstractItem> selectAllBooks();

	List<AbstractItem> selectExpiredBooksByUserNumber(int user_number);

}
