package vn.edu.likelion.bookManagement.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "book_sale")
public class Sale extends BaseEntity {

	@Column(name = "date_sale", nullable = false)
	private Date sale_date;

	@ManyToOne
	@JoinColumn(name = "book_detail", nullable = false)
	@JsonBackReference
	private Book book_detail;

	@Column
	private int sale_quantity;

	public Sale() {
		// TODO Auto-generated constructor stub
	}

	public Sale(Date sale_date, Book book_detail, int sale_quantity) {
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

	public Book getBook_detail() {
		return book_detail;
	}

	public void setBook_detail(Book book_detail) {
		this.book_detail = book_detail;
	}

	public int getSale_quantity() {
		return sale_quantity;
	}

	public void setSale_quantity(int sale_quantity) {
		this.sale_quantity = sale_quantity;
	}

}
