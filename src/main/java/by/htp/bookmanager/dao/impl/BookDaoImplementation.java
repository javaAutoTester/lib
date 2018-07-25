package by.htp.bookmanager.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import by.htp.bookmanager.connection.DBConnection;
import by.htp.bookmanager.dao.BookDao;
import by.htp.bookmanager.entity.Book;

public class BookDaoImplementation implements BookDao {

	private DBConnection dbConn = new DBConnection();

	@Override
	public List<Book> selectAllFromBooks(String sql_statement, int parametr) {
		Connection conn = dbConn.connect();
		Book book = null;
		List<Book> listBook = new ArrayList<>();
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
			dbConn.closeConnection(conn);
		}
		return listBook;
	}

	@Override
	public void addBook(Book book) {
		String sql_add_book = "INSERT INTO lib.Books (`title`,`author_name`,`author_surname`) VALUES (?, ?, ?)";
		Connection conn = dbConn.connect();
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
			dbConn.closeConnection(conn);
		}
	}

	@Override
	public void update(Book book) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

}
