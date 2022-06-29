package com.book.bookpricing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.bookpricing.entity.Book;
import com.book.bookpricing.exception.BookNotFoundException;
import com.book.bookpricing.repository.BookRepository;
import com.book.bookpricing.service.BookServiceImpl;

@RestController
public class BookController {

	@Autowired
	BookServiceImpl bookservice;

	@Autowired
	BookRepository bookrepository;

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello Postman!"; // possible only coz of RestController(Comb of Controller + ResponseBody)
	}

	@PostMapping("/addbook")
	public ResponseEntity<Book> createbook(@RequestBody Book book) {
		return new ResponseEntity<Book>(bookservice.addBook(book),HttpStatus.OK);
	}

	// Using path variable, put url like this in postman: ../getbook/1
	@GetMapping("/getbook/{id}")
	public ResponseEntity<Book> findBookById(@PathVariable("id") Integer bookNumber) throws BookNotFoundException {
		return new ResponseEntity<Book>(bookservice.findById(bookNumber),HttpStatus.OK);
	}

	// Using request param, put url like this in postman: ..//deletebook?id=1 (just enter key & value)
	@DeleteMapping("/deletebook")
	public String deleteBook(@RequestParam("id") Integer bookId) throws BookNotFoundException {
		bookservice.deleteBook(bookId);
		return "Book with Book number: " + bookId + " removed";
	}

	@GetMapping("/viewbyprice")
	public List<Book> viewByPrice(@RequestParam("id") Double price) {
		List<Book> books= bookrepository.findBookByPrice(price);
		return books;
	}

	@PutMapping("/updatebook")
	public String updateBook(@RequestBody Book book) throws BookNotFoundException {
		bookservice.updateBook(book);
		return "Book with Book number: " + book.getId() + " updated";
	}
	
	//this is used without any exception or error msg class
//	@ExceptionHandler(BookNotFoundException.class)
//	public ResponseEntity<String> handleEx(BookNotFoundException e){
//		return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
//	}
	
	//this is used when ErrorMessage class is created, and we have to display extra info
	//(write this in another Class such that exception is handled in all app, outside this controller too)
//	@ExceptionHandler(BookNotFoundException.class)
//	public ResponseEntity<ErrorMessage> handleEx(BookNotFoundException e){
//		return new ResponseEntity<ErrorMessage>(
//				new ErrorMessage(
//						e.getMessage(), 
//						LocalDateTime.now(), 
//						e.getClass().toString())
//				, HttpStatus.OK);
//	}
	

}
