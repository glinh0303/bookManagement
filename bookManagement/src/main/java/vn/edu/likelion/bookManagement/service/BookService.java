package vn.edu.likelion.bookManagement.service;

import java.util.Date;

import vn.edu.likelion.bookManagement.entity.Book;
import vn.edu.likelion.bookManagement.entity.Sale;

public interface BookService extends BaseService<Book> {
	Iterable<Book> sortByPrice(boolean ascending);

	boolean substractBook(int quantity, int id);

	Book filterByIdOrName(int id, String name);

	Iterable<Book> filterByDate(Date from, Date to);
	
	Iterable<Book> getBestSeller();
}
