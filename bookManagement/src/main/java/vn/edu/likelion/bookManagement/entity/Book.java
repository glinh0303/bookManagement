package vn.edu.likelion.bookManagement.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "book_detail")
public class Book extends BaseEntity {
	@Column
	private String book_name;

	@Column
	private int quantity;

	@Column
	private float price;

	@Column
	private Date created_date;

	@OneToMany(mappedBy = "book_detail", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
	private List<Sale> sales;

	public Book() {
		// TODO Auto-generated constructor stub
	}

	public Book(String book_name, int quantity, float price, Date created_date, List<Sale> sales) {
		super();
		this.book_name = book_name;
		this.quantity = quantity;
		this.price = price;
		this.created_date = created_date;
		this.sales = sales;
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

	public List<Sale> getSales() {
		return sales;
	}

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

}
