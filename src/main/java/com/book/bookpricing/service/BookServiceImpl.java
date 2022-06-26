package com.book.bookpricing.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.bookpricing.entity.Book;
import com.book.bookpricing.exception.BookNotFoundException;
import com.book.bookpricing.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookRepository bookrepository;
	
	@Override
	public Book findById(Integer id) throws BookNotFoundException {
		Optional<Book> book =  bookrepository.findById(id);
		if(!book.isPresent()) {
			throw new BookNotFoundException("Book with id: "+id+" not available");
		} else {
			return book.get();
		}
	}

	@Override
	public Book addBook(Book book) {
		return bookrepository.save(book);
	}

	@Override
	public void deleteBook(Integer id) throws BookNotFoundException {
		
		if(id!=null && bookrepository.existsById(id)) {
			bookrepository.deleteById(id);
		}
		else {
			throw new BookNotFoundException("Book with id: "+id+" not available");
		}
		
	}

	@Override
	public void updateBook(Book book) throws BookNotFoundException {
		if(book.getId()!=null && bookrepository.existsById(book.getId())) {
			bookrepository.save(book);
		}
		else {
			throw new BookNotFoundException("Book with id: "+book.getId()+" not available");
		}
		
	}

	
	

}
