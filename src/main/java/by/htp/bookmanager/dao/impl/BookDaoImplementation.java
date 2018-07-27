package by.htp.bookmanager.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import by.htp.bookmanager.dao.BookDao;
import by.htp.bookmanager.entity.AbstractItem;
import by.htp.bookmanager.entity.Book;

public class BookDaoImplementation extends AbstractDaoImplementation implements BookDao {

	@Override
	public List<AbstractItem> selectBookById(int id) {
		String sql_select_book_by_id = "SELECT * FROM Books WHERE id = ?";
		return selectFromBooks(sql_select_book_by_id, id);
	}

	@Override
	public List<AbstractItem> selectAllBooks() {
		String sql_select_all_books = "SELECT * FROM Books";
		return selectFromBooks(sql_select_all_books, -1);
	}

	@Override
	public List<AbstractItem> selectExpiredBooksByUserNumber(int user_number) {
		String sql_expired_books_by_user_number = "SELECT * FROM Books JOIN Book_usage_info ON Books.id = Book_usage_info.id_books "
				+ "WHERE Book_usage_info.number_users = ? "
				+ "AND (to_days(CURRENT_DATE())-to_days(Book_usage_info.takeout_date))>'30' AND Book_usage_info.book_returned = 'N';";
		return selectFromBooks(sql_expired_books_by_user_number, user_number);
	}

	private List<AbstractItem> selectFromBooks(String sql_statement, int parametr) {

		Connection conn = connect();
		Book book = null;
		List<AbstractItem> listBook = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql_statement);
			if (parametr > 0) {
				ps.setInt(1, parametr);
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				book = new Book();
				book.setId(rs.getInt("id"));
				book.setTitle(rs.getString("title"));
				book.setAuthorName(rs.getString("author_name"));
				book.setAuthorSurname(rs.getString("author_surname"));
				listBook.add(book);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return listBook;
	}

	@Override
	public void addBook(Book book) {
		String sql_add_book = "INSERT INTO lib.Books (`title`,`author_name`,`author_surname`) VALUES (?, ?, ?)";
		Connection conn = connect();
		try {
			PreparedStatement ps = conn.prepareStatement(sql_add_book);
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthorName());
			ps.setString(3, book.getAuthorSurname());
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
	}

}
