package vn.edu.likelion.bookManagement.model;

import java.util.Date;

public class SaleDTO {
	private Date sale_date;

	private BookDTO book_detail;

	private int sale_quantity;

	public SaleDTO() {
		// TODO Auto-generated constructor stub
	}

	public SaleDTO(Date sale_date, BookDTO book_detail, int sale_quantity) {
		super();
		this.sale_date = sale_date;
		this.book_detail = book_detail;
		this.sale_quantity = sale_quantity;
	}

	public Date getSale_date() {
		return sale_date;
	}

	public void setSale_date(Date sale_date) {
		this.sale_date = sale_date;
	}

	public BookDTO getBook_detail() {
		return book_detail;
	}

	public void setBook_detail(BookDTO book_detail) {
		this.book_detail = book_detail;
	}

	public int getSale_quantity() {
		return sale_quantity;
	}

	public void setSale_quantity(int sale_quantity) {
		this.sale_quantity = sale_quantity;
	}

}
