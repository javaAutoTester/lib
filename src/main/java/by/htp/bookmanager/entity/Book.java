package by.htp.bookmanager.entity;

public class Book implements AbstractItem {
	private int id;
	private String title;
	private String authorName;
	private String authorSurname;

	public Book(int id, String title, String author_name, String author_surname) {
		super();
		this.id = id;
		this.title = title;
		this.authorName = author_name;
		this.authorSurname = author_surname;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String author_name) {
		this.authorName = author_name;
	}

	public String getAuthorSurname() {
		return authorSurname;
	}

	public void setAuthorSurname(String author_surname) {
		this.authorSurname = author_surname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorName == null) ? 0 : authorName.hashCode());
		result = prime * result + ((authorSurname == null) ? 0 : authorSurname.hashCode());
		result = prime * result + id;
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
		Book other = (Book) obj;
		if (authorName == null) {
			if (other.authorName != null)
				return false;
		} else if (!authorName.equals(other.authorName))
			return false;
		if (authorSurname == null) {
			if (other.authorSurname != null)
				return false;
		} else if (!authorSurname.equals(other.authorSurname))
			return false;
		if (id != other.id)
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
		return "Book [ID: " + id + ", Title: " + title + ", Author: " + authorName + " " + authorSurname + "]";
	}

}
