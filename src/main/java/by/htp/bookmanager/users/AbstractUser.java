package by.htp.bookmanager.users;

public abstract class AbstractUser {
	protected int number;
	protected String pass;
	protected String role;
	protected int emplID;

	public AbstractUser(int number, String pass, String role, int emplID) {
		super();
		this.number = number;
		this.pass = pass;
		this.role = role;
		this.emplID = emplID;
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getEmplID() {
		return emplID;
	}

	public void setEmplID(int emplID) {
		this.emplID = emplID;
	}

	@Override
	public String toString() {
		return "AbstractUser [number=" + number + ", pass=" + "*****" + ", role=" + role + ", emplID=" + emplID + "]";
	}

}
