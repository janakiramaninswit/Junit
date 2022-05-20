package com.bookstore.controller.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jni.Status;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.error.ShouldHaveSameSizeAs;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bookstore.controller.BookStoreController;
import com.bookstore.entity.Book;
import com.bookstore.services.BookStoreService;
import com.bookstore.services.IBookStoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@SpringBootTest
@SpringJUnitWebConfig
public class BookControllerTest {
	
	private MockMvc movMVC;
	
	ObjectMapper mapper = new ObjectMapper();
	ObjectWriter writer = mapper.writer();
	
	@Mock
	BookStoreService bookStoreService;
	
	@InjectMocks
	BookStoreController bookStoreController;
		
	Book books1 = new Book(1,"java","jana","","",1,1);
	Book books2 = new Book(2,"react","siva","","",2,2);
	Book books3 = new Book(3,"html","vj","","",3,3);
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		this.movMVC = MockMvcBuilders.standaloneSetup(bookStoreController).build();
	}
	
	@Test
	public void getBooks_Success() throws Exception {
		
		List<Book> bookList = new ArrayList<>();
		bookList.add(books1);
		bookList.add(books2);
		bookList.add(books3);
		
		Mockito.when(bookStoreService.getBooks()).thenReturn(bookList);
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/bookservice/books")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON);
		
		movMVC.perform(reqBuilder)
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].author", is("jana")))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].name", is("react")))
			.andExpect(MockMvcResultMatchers.jsonPath("$[2].id", is(3)));
		
	}
	
	@Test
	public void getBook_Success() throws Exception {
		
		Book books1 = new Book(1,"java","jana","","",1,1);
		
		Mockito.when(bookStoreService.getBook(books1.getId())).thenReturn(books1);
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/bookservice/books/1")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON);
		
		movMVC.perform(reqBuilder)
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
			.andExpect(MockMvcResultMatchers.jsonPath("$.author", is("jana")));
		
	}
	
	@Test
	public void createBook_Success() throws Exception {
		
		Book book1 = new Book(4,"java","bharath","","",1,1);
		String content = writer.writeValueAsString(book1);
		
		Mockito.when(bookStoreService.createBook(book1)).thenReturn(book1);
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/bookservice/books")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON).content(content);
		
		movMVC.perform(reqBuilder)
			.andExpect(status().isOk());
			//.andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
			//.andExpect(MockMvcResultMatchers.jsonPath("$.author", is("bharath")));
		
	}
	
	@Test
	public void updateBook_Success() throws Exception {
		
		Book book1 = new Book(1,"java","janakiraman","","",1,1);
		
		String contentStr = writer.writeValueAsString(book1);
		Mockito.when(bookStoreService.updateBook(book1.getId(), book1)).thenReturn(book1);
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.put("/bookservice/books/1")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON).content(contentStr);
		
		movMVC.perform(reqBuilder)
			.andExpect(status().isOk());
			//.andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
			//.andExpect(MockMvcResultMatchers.jsonPath("$.author", is("janakiraman")))
		
	}
	
	@Test
	public void deleteBook_Success() throws Exception {
		
		Book book1 = new Book(1,"java","jana","","",1,1);
		
		Mockito.when(bookStoreService.deleteBook(book1.getId())).thenReturn(true);
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.delete("/bookservice/books/1")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON);
		
		movMVC.perform(reqBuilder)
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
			.andExpect(MockMvcResultMatchers.jsonPath("$", is("Book has been deleted successfully")));
		
	}
	

}
