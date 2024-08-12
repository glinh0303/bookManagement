package vn.edu.likelion.bookManagement.service;

import java.util.Optional;

public interface BaseService<T> {
	Iterable<T> findAll(); // read

	Optional<T> findById(int id);

	T save(T t); // create + update

	void removeById(int id); // delete
}
