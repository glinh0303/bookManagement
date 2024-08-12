package vn.edu.likelion.bookManagement.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.edu.likelion.bookManagement.entity.Book;
import vn.edu.likelion.bookManagement.entity.Sale;
import vn.edu.likelion.bookManagement.repository.BookRepo;
import vn.edu.likelion.bookManagement.service.BookService;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepo bookRepo;

	@Override
	public boolean substractBook(int quantity, int id) {
		int updateRow = bookRepo.subtractQuantityById(quantity, id);
		if (updateRow == 0) {
			return false;
		}
		return true;
	}

	@Override
	public Iterable<Book> findAll() {
		return bookRepo.findAll();
	}

	@Override
	public Book save(Book book) {
		return bookRepo.save(book);
	}

	@Override
	public void removeById(int id) {
		bookRepo.deleteById(id);
	}

	@Override
	public Optional<Book> findById(int id) {
		return bookRepo.findById(id);
	}

	@Override
	public Iterable<Book> sortByPrice(boolean ascending) {
		List<Book> books = new ArrayList<>();

		if (ascending) {
			books = bookRepo.sortByPriceAsc();
		} else {
			books = bookRepo.sortByPriceDesc();
		}
		return books;

	}

	@Override
	public Book filterByIdOrName(int id, String name) {
		Book book = bookRepo.findByIdOrName(id, name);
		return book;
	}

	@Override
	public Iterable<Book> filterByDate(Date from, Date to) {
		// Call the repository method to filter books by date
		Iterable<Book> filteredList = bookRepo.filterByDate(from, to);

		return filteredList;
	}

	@Override
	public Iterable<Book> getBestSeller() {
		Iterable<Book> books = bookRepo.bestSeller();
		return books;
	}
}
