package com.book.bookpricing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.book.bookpricing.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

	@Query(value = "select * from book b where b.price>?", nativeQuery = true)
	public List<Book> findBookByPrice(double price);
}
