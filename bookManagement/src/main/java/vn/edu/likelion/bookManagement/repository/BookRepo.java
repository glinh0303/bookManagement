package vn.edu.likelion.bookManagement.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import vn.edu.likelion.bookManagement.entity.Book;
import vn.edu.likelion.bookManagement.entity.Sale;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
	@Query(value = "SELECT * FROM book_detail ORDER BY price ASC", nativeQuery = true)
	List<Book> sortByPriceAsc();

	@Query(value = "SELECT * FROM book_detail ORDER BY price DESC", nativeQuery = true)
	List<Book> sortByPriceDesc();

	@Modifying
	@Transactional
	@Query(value = "UPDATE book_detail b SET b.quantity = b.quantity - :quantity WHERE b.id = :id AND b.quantity >= :quantity", nativeQuery = true)
	int subtractQuantityById(@Param("quantity") int quantity, @Param("id") int id);

	@Query(value = "SELECT * FROM book_detail WHERE id = :id OR book_name = :book_name", nativeQuery = true)
	Book findByIdOrName(@Param("id") int id, @Param("book_name") String bookName);

	@Query(value = "Select * from book_detail where created_date between :from and :to", nativeQuery = true)
	Iterable<Book> filterByDate(@Param("from") Date from, @Param("to") Date to);

	@Query(value = "SELECT b.* FROM book_sale s "
			+ "JOIN book_detail b ON s.book_detail = b.id "
			+ "GROUP BY b.id, b.book_name, b.quantity, b.price, b.created_date "
			+ "ORDER BY SUM(s.sale_quantity) "
			+ "DESC FETCH FIRST 5 ROWS ONLY", nativeQuery = true)
	Iterable<Book> bestSeller();
}
