package vn.edu.likelion.bookManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.edu.likelion.bookManagement.entity.Sale;
import vn.edu.likelion.bookManagement.model.ISaleCount;

@Repository
public interface SaleRepo extends JpaRepository<Sale, Integer> {
	@Query(value = "SELECT b.book_name AS bookName, SUM(s.sale_quantity) AS totalSales "
			+ "FROM book_sale s JOIN book_detail b " + "ON s.book_detail = b.id " + "GROUP BY b.book_name "
			+ "ORDER BY totalSales ASC", nativeQuery = true)
	Iterable<ISaleCount> sortByTotalSaleAsc();

	@Query(value = "SELECT b.book_name AS bookName, SUM(s.sale_quantity) AS totalSales "
			+ "FROM book_sale s JOIN book_detail b " + "ON s.book_detail = b.id " + "GROUP BY b.book_name "
			+ "ORDER BY totalSales DESC", nativeQuery = true)
	Iterable<ISaleCount> sortByTotalSaleDESC();

}
