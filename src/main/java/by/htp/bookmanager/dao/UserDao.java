package by.htp.bookmanager.dao;

import by.htp.bookmanager.users.AbstractUser;
import by.htp.bookmanager.users.User;

public interface UserDao {

	int addUser(User user);

	AbstractUser checkUserCredentials(int login, String pass);
}
