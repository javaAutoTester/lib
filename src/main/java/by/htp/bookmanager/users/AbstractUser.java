package by.htp.bookmanager.users;

public abstract class AbstractUser {
	protected int number;
	protected String pass;
	protected String role;

	public AbstractUser(int number, String pass, String role) {
		super();
		this.number = number;
		this.pass = pass;
		this.role = role;
	}

	public AbstractUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	// public String getPass() {
	// return pass;
	// }

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "AbstractUser [number=" + number + ", pass=" + pass + ", role=" + role + "]";
	}

}
