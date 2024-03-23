package com.restapi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.restapi.Entity.Book;

@EnableJpaRepositories
public interface BookRepo extends JpaRepository<Book, Integer>{

}
