package vn.edu.likelion.bookManagement.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import vn.edu.likelion.bookManagement.entity.Book;
import vn.edu.likelion.bookManagement.entity.Sale;
import vn.edu.likelion.bookManagement.model.BookDTO;
import vn.edu.likelion.bookManagement.model.ErrorResponse;
import vn.edu.likelion.bookManagement.model.SaleDTO;
import vn.edu.likelion.bookManagement.service.impl.BookServiceImpl;

@RestController
@RequestMapping("/api/books")
public class BookController {
	@Autowired
	private BookServiceImpl bookServiceImpl;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public Iterable<BookDTO> findAll() {
		Iterable<Book> books = bookServiceImpl.findAll();
		List<BookDTO> list = new ArrayList<>();
		for (Book book : books) {
			list.add(convertToDTO(book));
			
		}
		return list;
		
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public BookDTO addBook(@RequestBody BookDTO bookDTO) {
		Book book = convertToEntity(bookDTO);
		Book bookCreated = bookServiceImpl.save(book);
		return convertToDTO(bookCreated);
	}

	@GetMapping(value = "/{id}")
	@ResponseBody
	public BookDTO getBook(@PathVariable("id") int id) {
		Optional<Book> option = bookServiceImpl.findById(id);
		Book book = option.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
		return convertToDTO(option.get());
	}

	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public BookDTO updateBook(@PathVariable("id") int id, @RequestBody BookDTO updateBook) {
		Optional<Book> option = bookServiceImpl.findById(id);
		Book book = option.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

		updateBook.setId(id);
		Book updatedBook = convertToEntity(updateBook);
		bookServiceImpl.save(updatedBook);

		return convertToDTO(updatedBook);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") int id) {
		Optional<Book> option = bookServiceImpl.findById(id);
		Book book = option.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
		bookServiceImpl.removeById(id);
	}

	@GetMapping("/sortByPrice")
	public Iterable<BookDTO> sortBookByPrice(@RequestParam boolean ascending) {
		Iterable<Book> books = bookServiceImpl.sortByPrice(ascending);
		List<BookDTO> bookDTOList = new ArrayList<>();
		for (Book book : books) {
			bookDTOList.add(convertToDTO(book));
		}
		return bookDTOList;
	}

	public Book convertToEntity(BookDTO bookDTO) {
		Book book = modelMapper.map(bookDTO, Book.class);
		return book;
	}

	public BookDTO convertToDTO(Book book) {
		BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
		return bookDTO;
	}

	@GetMapping("/filter")
	public ResponseEntity<?> filterByIdOrName(@RequestParam(required = false) Integer id,
			@RequestParam(required = false) String name) {

		if (id == null) {
			id = 0;
		}

		Book book = bookServiceImpl.filterByIdOrName(id, name);

		if (book != null) {
			BookDTO bookDTO = convertToDTO(book);
			return ResponseEntity.ok(bookDTO);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Book not found"));
	}

	@GetMapping("/filterByDate")
	public ResponseEntity<?> filterByDate(@RequestParam Date from, @RequestParam Date to) {
		// Validate input dates
		if (from == null || to == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Both 'from' and 'to' dates must be provided"));
		}

		if (from.after(to)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "'From' date cannot be after 'To' date."));
		}
		Iterable<Book> books = bookServiceImpl.filterByDate(from, to);
		List<BookDTO> list = new ArrayList<>();
		for (Book book : books) {
			list.add(convertToDTO(book));
		}
		return ResponseEntity.ok(list);
	}

	@GetMapping("/bestSeller")
	public Iterable<BookDTO> bestSeller() {
		Iterable<Book> books = bookServiceImpl.getBestSeller();
		List<BookDTO> list = new ArrayList<>();

		for (Book book : books) {
			list.add(convertToDTO(book));
		}
		return list;
	}
}
