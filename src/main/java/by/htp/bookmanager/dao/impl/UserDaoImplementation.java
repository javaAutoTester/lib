package by.htp.bookmanager.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import by.htp.bookmanager.connection.DBConnection;
import by.htp.bookmanager.dao.UserDao;
import by.htp.bookmanager.users.User;

public class UserDaoImplementation implements UserDao {

	private DBConnection dbConn = new DBConnection();

	@Override
	public int addUser(User user) {
		String sql_add_user = "INSERT INTO lib.Users(`pass`,`role`,`id_employes`)VALUES(?,?,?);";
		String select_user_number = "SELECT Users.number FROM lib.Users WHERE Users.id_employes = ?";
		int result = 0;
		Connection conn = dbConn.connect();
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
			dbConn.closeConnection(conn);
		}
		return result;

	}

}
