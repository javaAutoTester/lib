package by.htp.bookmanager.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import by.htp.bookmanager.dao.UserBookDao;
import by.htp.bookmanager.entity.AbstractItem;
import by.htp.bookmanager.entity.ExpiredBooks;

public class UserBookDaoImplementation extends AbstractDaoImplementation implements UserBookDao {

	@Override
	public void giveBook(int user_number, int book_number) {
		String sql_give_book = "INSERT INTO lib.Book_usage_info (`number_users`,`id_books`,`takeout_date`) VALUES(? ,? ,current_date)";
		String sql_check_book_avaliable = "select * from lib.Book_usage_info where id_books= ? AND book_returned='N' ";
		String sql_check_book_expired = "select * from lib.Book_usage_info where Book_usage_info.number_users= ?  AND (to_days(CURRENT_DATE())-to_days(Book_usage_info.takeout_date))>'30';";
		String sql_check_book_quantity = "select * from lib.Book_usage_info where Book_usage_info.number_users= ? GROUP BY Book_usage_info.number_users HAVING COUNT(number_users)>='3'";
		String[] check = { "Ok", "Ok", "Ok" };
		int check_index = 1;
		Connection conn = connect();
		try {
			PreparedStatement ps1 = conn.prepareStatement(sql_check_book_avaliable);
			ps1.setInt(1, book_number);
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {
				check[0] = "BookNotAvaliable";
				check_index = 0;

			}
			PreparedStatement ps2 = conn.prepareStatement(sql_check_book_expired);
			ps2.setInt(1, user_number);
			ResultSet rs2 = ps2.executeQuery();
			while (rs2.next()) {
				check[1] = "ExpiredBooks";
				check_index = 0;
			}
			PreparedStatement ps3 = conn.prepareStatement(sql_check_book_quantity);
			ps3.setInt(1, user_number);
			ResultSet rs3 = ps3.executeQuery();
			while (rs3.next()) {
				check[2] = "BookNumberLimit";
				check_index = 0;
			}

			if (check_index == 0) {
				System.out.println("Can not give book: " + Arrays.toString(check));
			}
			if (check_index == 1) {
				PreparedStatement ps = conn.prepareStatement(sql_give_book);
				ps.setInt(1, user_number);
				ps.setInt(2, book_number);
				ps.executeUpdate();
			}

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Can not execute your quary. Check the initial data (User number, Book number)");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}

	}

	@Override
	public void getBook(int user_number, int book_number) {
		String sql_get_book_back = "UPDATE lib.Book_usage_info SET book_returned = 'Y' where number_users= ? AND id_books= ?";
		Connection conn = connect();
		int result = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql_get_book_back);
			ps.setInt(1, user_number);
			ps.setInt(2, book_number);
			result = ps.executeUpdate();
			if (result == 0) {
				System.out.println("Can not execute your quary. Check the initial data (User number, Book number)");
			}
		} catch (SQLException e) {
			System.out.println("Can not execute your quary. Check the initial data (User number, Book number)");

		} finally {
			closeConnection(conn);
		}

	}

	@Override
	public List<AbstractItem> expiredBooks() {
		String sql_expired_books_control = "select Book_usage_info.takeout_date, Books.title, (to_days(CURRENT_DATE())-to_days(Book_usage_info.takeout_date)) as expired_over, L.name, L.surname, L.phone_number from lib.Book_usage_info\n"
				+ "join lib.Books on Book_usage_info.id_books = Books.id\n"
				+ "join (select Employes.*, Users.number from lib.Employes join lib.Users on Employes.id=Users.id_employes) L on Book_usage_info.number_users = L.number \n"
				+ "where (to_days(CURRENT_DATE())-to_days(Book_usage_info.takeout_date))>'30' and Book_usage_info.book_returned='N';";
		Connection conn = connect();
		ExpiredBooks expBooks = null;
		Calendar takeout_date = null;
		List<AbstractItem> listExpBooks = new ArrayList<>();
		try {
			PreparedStatement ps1 = conn.prepareStatement(sql_expired_books_control);
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {
				expBooks = new ExpiredBooks();
				takeout_date = new GregorianCalendar();

				takeout_date.setTime(rs1.getDate("takeout_date"));
				expBooks.setTakeOutDate(takeout_date);

				expBooks.setTitle(rs1.getString("title"));
				expBooks.setExpiredDays(rs1.getInt("expired_over"));
				expBooks.setEmployeName(rs1.getString("name"));
				expBooks.setEmployeSurname(rs1.getString("surname"));
				expBooks.setEmployePhone(rs1.getString("phone_number"));
				listExpBooks.add(expBooks);
			}

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Can not execute your quary. Check the initial data (User number, Book number)");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}

		return listExpBooks;
	}

}
