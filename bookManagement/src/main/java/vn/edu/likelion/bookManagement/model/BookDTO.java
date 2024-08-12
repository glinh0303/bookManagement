package vn.edu.likelion.bookManagement.model;

import java.util.Date;

public class BookDTO extends BaseDTO {
	private String book_name;

	private int quantity;

	private float price;

	private Date created_date;

	public BookDTO() {
		// TODO Auto-generated constructor stub
	}

	public BookDTO(String book_name, int quantity, float price, Date created_date) {
		super();
		this.book_name = book_name;
		this.quantity = quantity;
		this.price = price;
		this.created_date = created_date;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

}
