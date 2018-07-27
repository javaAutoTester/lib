package by.htp.bookmanager.dao;

import java.util.List;

import by.htp.bookmanager.entity.AbstractItem;
import by.htp.bookmanager.entity.ExpiredBooks;

public interface UserBookDao {

	void giveBook(int user_number, int book_number);

	void getBook(int user_number, int book_number);

	List<AbstractItem> expiredBooks();

}
