package by.htp.bookmanager.entity;

public class UserStat implements AbstractItem {
	private int amount;
	private String name;
	private String surname;
	private int number;
	private int id;

	public UserStat(int amount, String name, String surname, int number, int id) {
		super();
		this.amount = amount;
		this.name = name;
		this.surname = surname;
		this.number = number;
		this.id = id;
	}

	public UserStat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + number;
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserStat other = (UserStat) obj;
		if (amount != other.amount)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number != other.number)
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserStat [Amount of Books: " + amount + ", Employe: " + name + " " + surname + ", User number: "
				+ number + ", Employe id: " + id + "]";
	}

}
