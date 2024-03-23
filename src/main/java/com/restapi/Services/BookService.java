package com.restapi.Services;

import java.util.List;

import com.restapi.Entity.Book;

public interface BookService {
	
	public List<Book> getAllBook();
	public Book addBook(Book book);
	public Book updateBook(Book book);
	public Book getBookById(int id);
	public Book deleteBookById(int id);

}
