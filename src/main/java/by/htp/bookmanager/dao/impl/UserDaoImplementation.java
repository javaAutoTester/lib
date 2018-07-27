package by.htp.bookmanager.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import by.htp.bookmanager.dao.UserDao;
import by.htp.bookmanager.users.AbstractUser;
import by.htp.bookmanager.users.Librarian;
import by.htp.bookmanager.users.NotUser;
import by.htp.bookmanager.users.User;

public class UserDaoImplementation extends AbstractDaoImplementation implements UserDao {

	@Override
	public int addUser(User user) {
		String sql_add_user = "INSERT INTO lib.Users(`pass`,`role`,`id_employes`)VALUES(?,?,?);";
		String select_user_number = "SELECT Users.number FROM lib.Users WHERE Users.id_employes = ?";
		int result = 0;
		Connection conn = connect();
		try {
			PreparedStatement ps = conn.prepareStatement(sql_add_user);
			ps.setString(1, user.getPass());
			ps.setString(2, user.getRole());
			ps.setInt(3, user.getEmplID());
			result = ps.executeUpdate();
			if (result != 0) {
				PreparedStatement ps2 = conn.prepareStatement(select_user_number);
				ps2.setInt(1, user.getEmplID());
				ResultSet rs = ps2.executeQuery();
				while (rs.next()) {
					result = rs.getInt("number");
				}
			}

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(
					"Can not add this user. Maybe this employe has been added as a user yet. Check the initial data.");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return result;

	}

	@Override
	public AbstractUser checkUserCredentials(int login, String pass) {
		final String SQL_SELECT_USER = "SELECT * FROM Users WHERE number = ? AND pass = ?";
		Connection conn = connect();
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
			closeConnection(conn);
		}
		return user;
	}

}
