package vn.edu.likelion.bookManagement.util;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RestPreconditions {
	public static <T> T checkFound(T resource) {
		if (resource == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
		} else {
			return resource;
		}
	}
}
