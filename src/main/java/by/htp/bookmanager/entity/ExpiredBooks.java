package by.htp.bookmanager.entity;

import java.util.Calendar;

public class ExpiredBooks implements AbstractItem {

	private Calendar takeOutDate;
	private String title;
	private int expiredDays;
	private String employeName;
	private String employeSurname;
	private String employePhone;

	public ExpiredBooks(Calendar takeOutDate, String title, int expiredDays, String employeName, String employeSurname,
			String employePhone) {
		super();
		this.takeOutDate = takeOutDate;
		this.title = title;
		this.expiredDays = expiredDays;
		this.employeName = employeName;
		this.employeSurname = employeSurname;
		this.employePhone = employePhone;
	}

	public ExpiredBooks() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Calendar getTakeOutDate() {
		return takeOutDate;
	}

	public void setTakeOutDate(Calendar takeOutDate) {
		this.takeOutDate = takeOutDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getExpiredDays() {
		return expiredDays;
	}

	public void setExpiredDays(int expiredDays) {
		this.expiredDays = expiredDays;
	}

	public String getEmployeName() {
		return employeName;
	}

	public void setEmployeName(String employeName) {
		this.employeName = employeName;
	}

	public String getEmployeSurname() {
		return employeSurname;
	}

	public void setEmployeSurname(String employeSurname) {
		this.employeSurname = employeSurname;
	}

	public String getEmployePhone() {
		return employePhone;
	}

	public void setEmployePhone(String employePhone) {
		this.employePhone = employePhone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeName == null) ? 0 : employeName.hashCode());
		result = prime * result + ((employePhone == null) ? 0 : employePhone.hashCode());
		result = prime * result + ((employeSurname == null) ? 0 : employeSurname.hashCode());
		result = prime * result + expiredDays;
		result = prime * result + ((takeOutDate == null) ? 0 : takeOutDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		ExpiredBooks other = (ExpiredBooks) obj;
		if (employeName == null) {
			if (other.employeName != null)
				return false;
		} else if (!employeName.equals(other.employeName))
			return false;
		if (employePhone == null) {
			if (other.employePhone != null)
				return false;
		} else if (!employePhone.equals(other.employePhone))
			return false;
		if (employeSurname == null) {
			if (other.employeSurname != null)
				return false;
		} else if (!employeSurname.equals(other.employeSurname))
			return false;
		if (expiredDays != other.expiredDays)
			return false;
		if (takeOutDate == null) {
			if (other.takeOutDate != null)
				return false;
		} else if (!takeOutDate.equals(other.takeOutDate))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ExpiredBooks [Since: " + takeOutDate.getTime() + ", Title:" + title + ", Days over: " + expiredDays
				+ ", Employe: " + employeName + " " + employeSurname + ", Phone: " + employePhone + "]";
	}

}
