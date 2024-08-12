package vn.edu.likelion.bookManagement.Controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.likelion.bookManagement.entity.Sale;
import vn.edu.likelion.bookManagement.model.BookDTO;
import vn.edu.likelion.bookManagement.model.ErrorResponse;
import vn.edu.likelion.bookManagement.model.ISaleCount;
import vn.edu.likelion.bookManagement.model.SaleDTO;
import vn.edu.likelion.bookManagement.service.impl.BookServiceImpl;
import vn.edu.likelion.bookManagement.service.impl.SaleServiceImpl;

@RestController
@RequestMapping("/sales")
public class SaleController {
	@Autowired
	SaleServiceImpl saleImpl;

	@Autowired
	ModelMapper modelMapper;

	public SaleDTO convertToDTO(Sale sale) {
		SaleDTO saleDTO = modelMapper.map(sale, SaleDTO.class);
		return saleDTO;
	}

	public Sale convertToEntity(SaleDTO saleDTO) {
		Sale sale = modelMapper.map(saleDTO, Sale.class);
		return sale;
	}

	@PostMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ErrorResponse> sell(@PathVariable("id") int id, @RequestParam int quantity) {
		try {
			if (!saleImpl.sell(id, quantity)) {
				return new ResponseEntity<>(
						new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Book not found or insufficient quantity"),
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();

			// Return a 500 Internal Server Error with a custom message
			return new ResponseEntity<>(
					new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.CREATED.value(), "successful"), HttpStatus.CREATED);
	}

	@GetMapping
	public Iterable<SaleDTO> getAll() {
		Iterable<Sale> sales = saleImpl.findAll();
		List<SaleDTO> list = new ArrayList<>();
		for (Sale sale : sales) {
			list.add(convertToDTO(sale));
		}
		return list;
	}

	@GetMapping("/totalSales")
	public Iterable<ISaleCount> sortTotalSales(@RequestParam("ascending") Boolean ascending) {
		Iterable<ISaleCount> list = saleImpl.sortByTotalSale(ascending);
		return list;
	}

}
