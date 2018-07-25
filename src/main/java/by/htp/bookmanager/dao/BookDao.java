package by.htp.bookmanager.dao;

import java.util.List;

import by.htp.bookmanager.entity.Book;

public interface BookDao {

	void addBook(Book book);

	void update(Book book);

	void delete(int id);

	List<Book> selectAllFromBooks(String sql_statement, int parametr);

}
