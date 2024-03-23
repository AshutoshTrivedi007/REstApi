package com.restapi.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.Entity.Book;
import com.restapi.Exceptions.ResponseHandler;
import com.restapi.Services.BookService;
import com.restapi.sendemails.SendEmail;

import jakarta.validation.Valid;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookservice;
	
	@GetMapping("/home")
	public String home() {
		return "this is home";
		
	}
	
	@GetMapping("/book")
	public ResponseEntity <List<Book>> getAllBook(){
		List<Book> list = this.bookservice.getAllBook();
		if(list.size()<=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(list));
		
	}
//	
//	@PostMapping("/book")
//	public ResponseEntity<Object> addBook(@Valid @RequestBody Book book, BindingResult b) {
//		if (b.hasErrors()) {
//			
//	        // Handle validation errors
//	      return ResponseHandler.generateResponse(HttpStatus.FORBIDDEN, false, b.getFieldError().toString(), book);
//	      //  return this.bookservice.addBook(book);
//	    }
//		else {
//			this.bookservice.addBook(book);
//		}
//		return null;
//		
//		
//	}
//	
	

	@PostMapping("/book")
	public ResponseEntity<Object> addBook(@Valid @RequestBody Book book) {
		
		SendEmail se=new SendEmail();
		String email="ashutosh.trivedi0077@gmail.com";
		String password="ashu";
		boolean b=se.sendEmail(email, password);		
		
		if(b) {
			System.out.println("email is sent successfully");
		}else {
			System.out.println("there is a problem");
		}
		
		
			this.bookservice.addBook(book);
		
			return ResponseHandler.generateResponse(HttpStatus.OK, true, "book save", book);
		
		
	}
	
	@PutMapping("/book")
	public ResponseEntity<Object> updateBook(@RequestBody Book book) {
//		Book updateBook = 
		this.bookservice.updateBook(book);
		return ResponseHandler.generateResponse(HttpStatus.OK, true, "update All book", book);
		
	}
	
	@DeleteMapping("/book/{id}")
	public ResponseEntity<Object> deleteBookById(@PathVariable("id") int id) {
//		Book deleteBook = 
		this.bookservice.deleteBookById(id);
		return ResponseHandler.generateResponse(HttpStatus.OK, true, "given book has been deleted", id);
		
		
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {
		Book bid = this.bookservice.getBookById(id);
		if(bid==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.of(Optional.of(bid));
		
	}
	

}
