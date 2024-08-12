package vn.edu.likelion.bookManagement.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.edu.likelion.bookManagement.entity.Book;
import vn.edu.likelion.bookManagement.entity.Sale;
import vn.edu.likelion.bookManagement.model.ISaleCount;
import vn.edu.likelion.bookManagement.repository.SaleRepo;
import vn.edu.likelion.bookManagement.service.SaleService;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

	@Autowired
	SaleRepo saleRepo;

	@Autowired
	BookServiceImpl bookService;

	@Override
	public Iterable<Sale> findAll() {
		return saleRepo.findAll();
	}

	@Override
	public Optional<Sale> findById(int id) {
		return saleRepo.findById(id);
	}

	@Override
	public Sale save(Sale sale) {
		return saleRepo.save(sale);
	}

	@Override
	public void removeById(int id) {
		saleRepo.deleteById(id);
	}

	@Override
	public boolean sell(int book_id, int quantity) {
		Optional<Book> book = bookService.findById(book_id);
		if (book.isPresent() && book.get().getQuantity() >= quantity) {
			Date sell_date = new Date();

			System.out.println("date: " + sell_date);
			System.out.println(book.get().getQuantity());
			// Subtract the quantity from the book's stock
			if (bookService.substractBook(quantity, book_id)) {

				// Create a new Sale object and set the details
				Sale sale = new Sale();
				sale.setSale_date(sell_date);
				sale.setBook_detail(book.get());
				sale.setSale_quantity(quantity);

				// Save the sale
				save(sale);
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterable<ISaleCount> sortByTotalSale(Boolean ascending) {
		Iterable<ISaleCount> list = null;
		if (ascending) {
			list = saleRepo.sortByTotalSaleAsc();
		} else {
			list = saleRepo.sortByTotalSaleDESC();
		}
		return list;
	}

}
