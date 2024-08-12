package vn.edu.likelion.bookManagement.service;

import vn.edu.likelion.bookManagement.entity.Sale;
import vn.edu.likelion.bookManagement.model.ISaleCount;

public interface SaleService extends BaseService<Sale> {
	boolean sell(int quantity, int book_id);

	Iterable<ISaleCount> sortByTotalSale(Boolean order);
}
