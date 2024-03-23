package com.restapi.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.Entity.Book;
import com.restapi.Repositories.BookRepo;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepo bookrepo;

	//@Override
	//public List<Book> getAllBook(Book book) {
		// TODO Auto-generated method stub
	//	return this.bookrepo.findAll();
	//}

	@Override
	public Book addBook(Book book) {
		// TODO Auto-generated method stub
		return this.bookrepo.save(book);
	}

	@Override
	public Book updateBook(Book book) {
		// TODO Auto-generated method stub
		Book save = this.bookrepo.save(book);
		return save;
	}

	@Override
	public Book getBookById(int id) {
		// TODO Auto-generated method stub
		
		return this.bookrepo.findById(id).get() ;
	}

	@Override
	public Book deleteBookById(int id) {
		Optional<Book> byId = this.bookrepo.findById(id);
		Book book = byId.get();
		this.bookrepo.delete(book);
		return book ;
	}

	@Override
	public List<Book> getAllBook() {
		// TODO Auto-generated method stub
		return this.bookrepo.findAll();
	}
	


}
